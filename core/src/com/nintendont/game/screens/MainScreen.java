package com.nintendont.game.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nintendont.game.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.nintendont.game.entities.*;
import com.nintendont.game.maps.MapLoader;
import com.nintendont.game.util.OnSelectHandler;
import com.nintendont.game.util.OnSubmitHandler;
import java.util.ArrayList;

import java.util.Arrays;

public class MainScreen implements Screen {
    private DialogueScreen dialogueScreen;
    private InputScreen nameInput;
    private OptionScreen optionBox;

    private OptionScreen engimonInventory;
    private OptionScreen engimonSelection;
    private OptionScreen engimonBreed;
    private OptionScreen skillItemInventory;
    private OptionScreen skillItemSelection;
    private OptionScreen skillItemToEngimon;

    private OverlayScreen activeScreen;

    public static MapLoader mapLoader;
    public static ArrayList<EngimonLiar> wildEngimons = new ArrayList<EngimonLiar>();
    public static Player player;
    public static int turn = 0;

    public static Engimon starter;

    private Stage uiStage;
    private Table root;

    private int uiScale = 1;
    private OrthographicCamera camera;

    public MainScreen() {
        this(null);
    }

    public MainScreen(Engimon starter) {
        MainScreen.starter = starter;

        mapLoader = new MapLoader();
        camera = new OrthographicCamera();

        try {
            // starter null = loaded game
            player = new Player(
                    this,
                    starter,
                    mapLoader
            );

            Gdx.input.setInputProcessor(player);

            if (starter != null) InGameHelper.spawnWildEngimons();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to create player");
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapLoader.getRenderer().setView(camera);
        mapLoader.render();

        // start rendering anything necessary
        mapLoader.getBatch().begin();

        // draw player
        player.update(delta);
        player.draw(mapLoader.getBatch());

        player.getActiveEngimon().draw(mapLoader.getBatch(),
                player.getActiveEngimonWorldX(),
                player.getActiveEngimonWorldY(),
                delta
        );

        for (EngimonLiar e : this.wildEngimons) {
            e.draw(mapLoader.getBatch(), delta);
        }

        // draw dialogbox
        uiStage.act(delta);

        // move camera to player's center
        Vector3 v = new Vector3(
                player.getWorldX() * GameConfig.SCALED_TILE_SIZE,
                player.getWorldY() * GameConfig.SCALED_TILE_SIZE,
                0
        );
        camera.position.set(v);
        camera.update();

        mapLoader.getBatch().end();

        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        uiStage.getViewport().update(width/uiScale, height/uiScale, true);
    }

    @Override
    public void show() {
        Sounds.defaultMusic.setLooping(true);
        Sounds.defaultMusic.setVolume(0.15f);
        Sounds.defaultMusic.play();

        mapLoader.initAnimationTiles();

        mapLoader.centerToScreen(camera);

        initOverlays();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        mapLoader.getMap().dispose();
        mapLoader.getRenderer().dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public DialogueScreen getDialogueScreen() {
        return dialogueScreen;
    }

    private void initOverlays(){
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);
        uiStage.setDebugAll(true);

        //onSelectHandler for optionBox
        OnSelectHandler onBattle = () -> {
            try {
                hideDialog();
                InGameHelper.battleNearbyEngimon(player, this);
            } catch (Exception err) {
                dialog(err.getMessage());
            }
        };
        OnSelectHandler onInteract = () -> {
            hideDialog();
            handleInteract(player.getLookDir());
        };

        OnSelectHandler onEngimon = () -> {
            updateEngimonInventoryOverlay();
            switchOverlayScreen(engimonInventory);
        };
        OnSelectHandler onSkillItem = () -> {
            updateSkillItemInventoryOverlay();
            switchOverlayScreen(skillItemInventory);
        };
        OnSelectHandler onCheck = () -> {
            dialog(player.getActiveEngimon().interact());
        };
        OnSelectHandler onSave = () -> {
            InGameHelper.saveGame(this, player);
            dialog("Game saved!");
            Sounds.saveGame.play(0.25f);
        };
        OnSelectHandler onCancel = () -> {
            Sounds.menuClose.play(0.25f);
            hideDialog();
        };

        dialogueScreen = new DialogueScreen();
        optionBox = new OptionScreen(
                Arrays.asList("Battle", "Engimon","Skill Items","Interact", "Check Engimon", "Save", "Cancel"),
                Arrays.asList(onBattle, onEngimon,onSkillItem,onInteract, onCheck, onSave,  onCancel)
        );

        root.clearChildren();
        addRightOverlay(optionBox);

        activeScreen = optionBox;
    }

    private void updateEngimonInventoryOverlay(){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<OnSelectHandler>();
        for(int i = 0; i<player.engimonList.size(); i++){
            int idx = i;
            selectHandlers.add(() -> {
                generateEngimonSelectionMenu(player.getEngimon(idx), idx);
                switchOverlayScreen(engimonSelection);
            });
        }

        selectHandlers.add(() -> {
            Sounds.menuClose.play(0.25f);
            hideDialog();
        });

        ArrayList<String> menu = player.getAllEngimonDisplayText();
        menu.add("Cancel");
        engimonInventory = new OptionScreen(menu, selectHandlers, 0.65f);
    }

    private void updateSkillItemInventoryOverlay(){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<>();
        for(int i = 0; i<player.skillItemList.size(); i++){
            int idx = i;
            selectHandlers.add(() -> {
                generateSkillItemSelectionMenu(player.skillItemList.get(idx));
                switchOverlayScreen(skillItemSelection);
            });
        }

        selectHandlers.add(()->{
            Sounds.menuClose.play(0.25f);
            hideDialog();
        });

        ArrayList<String> menu = player.getAllSkillItemDisplayText();
        menu.add("Close");

        skillItemInventory = new OptionScreen(menu, selectHandlers, 0.65f);
    }

    private void generateEngimonSelectionMenu(Engimon e, int idx){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<>();
        selectHandlers.add(()->{
            new EngimonScreen(player, this, e);
//            dialog(e.details(), 0.65f);
        });
        selectHandlers.add(()->{ dialog(player.switchActiveEngimon(idx)); });
        selectHandlers.add(()->{ generateSetNameFor(idx); }); //rename
        selectHandlers.add(()->{
            if(player.engimonList.size()>1){
                generateEngimonBreedMenu(e, idx);
                switchOverlayScreen(engimonBreed);
            }else{
                dialog("You don't have any other engimon to breed "+e.getName()+" with!");
            }
        }); //breed
        selectHandlers.add(()->{ dialog(player.releaseEngimon(e)); });
        selectHandlers.add(()->{
            Sounds.menuClose.play(0.25f);
            hideDialog();
        });

        engimonSelection = new OptionScreen(Arrays.asList("Detail Engimon", "Switch Engimon", "Rename", "Breed", "Release Engimon","Cancel"), selectHandlers, 0.9f);
    }

    private void generateSetNameFor(int idx){
        OnSubmitHandler onSetName = (name) -> {
            player.setEngimonName(idx, name.toString());
            Gdx.input.setInputProcessor(player);
            Sounds.menuClose.play(0.25f);
            hideDialog();
        };
        nameInput = new InputScreen("Enter name : ", onSetName);
        switchOverlayScreen(nameInput);
    }

    private void generateSetNameForBreed(Engimon a, Engimon b){
        OnSubmitHandler onSetName = (name) -> {
            dialog(player.breed(a, b, name.toString()));
            Gdx.input.setInputProcessor(player);
//            hideDialog();
        };
        nameInput = new InputScreen("Enter name for your new engimon : ", onSetName);
        switchOverlayScreen(nameInput);
    }

    private void generateEngimonBreedMenu(Engimon selected, int selectedIdx){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<OnSelectHandler>();

        for(int i = 0; i<player.engimonList.size(); i++){
            if(i!=selectedIdx){
                int idx = i;
                selectHandlers.add(() -> {
                    if(player.isAbleToBreed(selected, player.getEngimon(idx))){
                        generateSetNameForBreed(selected, player.getEngimon(idx));
                    }else{
                        dialog("Parent level is not enough for breeding!");
                    }
                });
            }
        }

        selectHandlers.add(()->{
            Sounds.menuClose.play(0.25f);
            hideDialog();
        });

        ArrayList<String> menu = player.getAllEngimonDisplayText();
        menu.remove(selected.display()); //deleting current engimon
        menu.add("Cancel");
        engimonBreed = new OptionScreen(menu, selectHandlers, 0.65f);
    }

    private void generateSkillItemSelectionMenu(SkillItem s){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<OnSelectHandler>();
        selectHandlers.add(()->{ dialog(s.display(), 0.65f);});
        selectHandlers.add(()->{ generateSkillItemUseMenu(s); switchOverlayScreen(skillItemToEngimon);});
        selectHandlers.add(()->{ dialog(player.throwSkillItem(1, s));});
        selectHandlers.add(()->{
            hideDialog();
            Sounds.menuClose.play(0.25f);
        });

        skillItemSelection = new OptionScreen(Arrays.asList("Detail Skill Item", "Use Skill Item", "Dispose Skill Item","Cancel"),selectHandlers, 0.9f);
    }

    private void generateSkillItemUseMenu(SkillItem s){
        ArrayList<OnSelectHandler> selectHandlers = new ArrayList<OnSelectHandler>();
        for(int i = 0; i<player.engimonList.size(); i++){
            int idx = i;
            selectHandlers.add(() -> { dialog(player.useSkillItem(player.getEngimon(idx), s)); });
        }
        skillItemToEngimon = new OptionScreen(player.getAllEngimonDisplayText(), selectHandlers, 0.65f);
    }

    private void addBottomOverlay(OverlayScreen overlay){
        root.add(overlay)
                .expand()
                .align(Align.bottom)
                .pad(8f)
                ;
    }

    private void addRightOverlay(OverlayScreen overlay){
        root.add(overlay)
                .expand()
                .align(Align.right)
                .pad(8f)
        ;
    }

    public void openController(){
        root.clearChildren();
        addRightOverlay(optionBox);
        activeScreen.close();
        activeScreen = optionBox;
        activeScreen.toggle();
    }

    public void handleInteract(Direction dir){
        Position pos = player.getPosition();

        if(dir == null){
            return;
        }

        if(mapLoader.isWalkable(pos.getX(), pos.getY(),dir.getDeltaX(), dir.getDeltaY())){
            dialog("You can walk there!");
        }else{
//            dialogueScreen.animateText("You can't walk there!");
            dialog("You can't walk there!");
        }
    }

    public void dialog(String str, float scale){
        root.clearChildren();
        addBottomOverlay(dialogueScreen);
        activeScreen.close();
        activeScreen = dialogueScreen;
        dialogueScreen.animateText(str, scale);
        activeScreen.open();
    }

    public void dialog(String str){
        dialog(str, 1);
    }

    public void switchOverlayScreen(OverlayScreen screen){
        root.clearChildren();
        if(screen instanceof OptionScreen){
            addRightOverlay(screen);
        }else{
            addBottomOverlay(screen);
        }

        activeScreen.close();
        activeScreen = screen;
        activeScreen.open();
    }

    public void dummyFunction(String str){
        System.out.println("MASUK BANG");
        System.out.println(str);
    }

    public void hideDialog(){
        activeScreen.close();
    }

    public OverlayScreen getActiveScreen(){
        return this.activeScreen;
    }
}
