package com.nintendont.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nintendont.game.GameConfig;
import com.nintendont.game.NintendontGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.nintendont.game.Sounds;
import com.nintendont.game.entities.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nintendont.game.maps.MapLoader;
import com.nintendont.game.util.OnSelectHandler;
import org.apache.batik.swing.gvt.Overlay;

import java.util.Arrays;

public class MainScreen implements Screen {
    private MapLoader mapLoader;

    private DialogueScreen dialogueScreen;
    private OptionScreen optionBox;

    private OverlayScreen activeScreen;

    private Player player;
    private Engimon starter;

    private Stage uiStage;
    private Table root;

    private int uiScale = 1;
    private OrthographicCamera camera;

    public MainScreen(Engimon starter) {
        this.starter = starter;
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
//        Sounds.defaultMusic.setLooping(true);
//        Sounds.defaultMusic.setVolume(0.15f);
//        Sounds.defaultMusic.play();

        mapLoader = new MapLoader();
        camera = new OrthographicCamera();
        Skin skin = new Skin(Gdx.files.internal("Skin/glassy-ui.json"));

        //define all the screen
//        dialogueScreen = new DialogueScreen();

        try {
            player = new Player(
                    this,
                    starter,
                    mapLoader
            );
            starter.showDetails();
            Gdx.input.setInputProcessor(player);
        } catch (Exception e) {
            System.out.println("Failed to create player");
        }

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


    private void initOverlays(){
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);

        //onSelectHandler for optionBox
        OnSelectHandler onBattle = () -> {dummyFunction("Battle");};
        OnSelectHandler onInteract = () -> {
            hideDialog();
            handleInteract(player.getLookDir());
        };
        OnSelectHandler onEngimon = () -> {dummyFunction("Engimon");};
        OnSelectHandler onSwitchEngimon = () -> {dummyFunction("Switch Engimon");};
        OnSelectHandler onSkillItem = () -> {dummyFunction("Skill item...");};
        OnSelectHandler onCancel = () -> {hideDialog();};

        dialogueScreen = new DialogueScreen();
        optionBox = new OptionScreen(
                Arrays.asList("Battle","Interact","Engimon","Switch Engimon", "Skill Items", "Cancel"),
                Arrays.asList(onBattle,onInteract, onEngimon, onSwitchEngimon, onSkillItem, onCancel)
        );

        addBottomOverlay(dialogueScreen);
        addRightOverlay(optionBox);

        activeScreen = optionBox;
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
        activeScreen.close();
        activeScreen = optionBox;
        activeScreen.toggle();
    }

    public void handleInteract(Direction dir){
        System.out.println("SINI BANG");
        Position pos = player.getPosition();

        if(dir == null){
            System.out.println("BROKE!!!!!!!!");
            return;
        }
        activeScreen = dialogueScreen;
        activeScreen.open();
        if(mapLoader.isWalkable(pos.getX(), pos.getY(),dir.getDeltaX(), dir.getDeltaY())){
            dialogueScreen.animateText("You can walk there!");
        }else{
            dialogueScreen.animateText("You can't walk there!");
        }
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
