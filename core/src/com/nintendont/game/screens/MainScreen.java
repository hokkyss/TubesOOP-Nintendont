package com.nintendont.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nintendont.game.entities.PlayerSprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nintendont.game.entities.Species;

public class MainScreen implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Player player;
    private PlayerSprite playerSprite;

    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        renderer.setView(camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();

//        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load("Maps/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        try {
            player = new Player(new Engimon("ember", Species.get("Emberon"), 1));
            stage.addActor(player);
            stage.setKeyboardFocus(player);
        } catch (Exception e) {
            System.out.println("Failed to create player");
        }

        TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2, layer0.getHeight() * layer0.getTileHeight() / 2, 0);
        camera.position.set(center);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        map.dispose();
        stage.dispose();
        renderer.dispose();
    }

    @Override
    public void hide() {
        dispose();
    }
}
