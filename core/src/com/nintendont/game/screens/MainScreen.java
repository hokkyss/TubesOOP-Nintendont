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
import org.apache.batik.swing.gvt.Overlay;
import java.util.ArrayList;
import com.nintendont.game.Util;

public class MainScreen implements Screen {
    private MapLoader mapLoader;
    private OverlayScreen overlayScreen;

    private Player player;
    private Engimon starter;

    private Stage uiStage;
    private Table root;
    private OverlayScreen overlay;
    private boolean isOverlayOpen = false;

    private int uiScale = 1;
    private OptionScreen optionBox;

    private OrthographicCamera camera;

    public MainScreen(Engimon starter) {
        this.starter = starter;
        ArrayList<EngimonLiar> engLiar = Util.spawnWildEngimons();

        for(EngimonLiar e : engLiar){
            e.showDetails();
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
        /*
         * root = new Table();
         * root.setFillParent(true);
         * uiStage.addActor(root);
         * Table dialogTable = new Table();
         * optionBox = new OptionScreen(new Skin(Gdx.files.internal("...."));
         * optionBox.setVisible(false);
         * optionBox.add("Battle");
         * optionBox.add("Interact");
         * optionBox.add("Engimon");
         * optionBox.add("Switch Engimon");
         * optionBox.add("Skill Item");
         */
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
        overlayScreen = new OverlayScreen();

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

        initUI();
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


    private void initUI(){
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);
        overlay = new OverlayScreen();
//        overlay.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/5);
        overlay.animateText("This is a test string\nAlso this one");
        overlay.setVisible(isOverlayOpen);

        root.add(overlay)
                .expand()
                .align(Align.bottom)
                .pad(8f)
                ;
    }

    public void toggleDialog(Direction dir){
        Position pos = player.getPosition();
        if(dir == null){
            System.out.println("BROKE!!!!!!!!");
            return;
        }
        if(mapLoader.isWalkable(pos.getX(), pos.getY(),dir.getDeltaX(), dir.getDeltaY())){
            overlay.animateText("You can walk there!");
        }else{
            overlay.animateText("You can't walk there!");
        }
        isOverlayOpen = !isOverlayOpen;
        overlay.setVisible(isOverlayOpen);
    }

    public void hideDialog(){
        overlay.setVisible(false);
    }
}
