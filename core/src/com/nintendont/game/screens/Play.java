package com.nintendont.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.nintendont.game.entities.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nintendont.game.entities.PlayerSprite;

public class Play implements Screen {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    public OrthographicCamera camera;
    private Player player;
    private PlayerSprite playerSprite;

    public Play(OrthographicCamera camera) {
        this.camera = camera;
//        this.player = player;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(camera);
        renderer.render();

//        renderer.getBatch().begin();
//        playerSprite.draw(renderer.getBatch());
//        renderer.getBatch().end();

//        renderer.getSpriteBatch().begin();

//        player.draw(new Batch(new Sprite(new Texture(Gdx.files.internal("../core/assets/Characters/boy_run.png")))), 1);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
//        camera.position.set(width/2f, height/2f, 0);
        camera.update();
    }

    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        map = new TmxMapLoader().load("E:/STEI/Sem4/PBO/Nintendont-Game/core/assets/Maps/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2, layer0.getHeight() * layer0.getTileHeight() / 2, 0);
        camera.position.set(center);
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, w,h);
//        camera.position.set(w/2,h/2,0);
//        camera.zoom = 1/5;
    }

    public void updateCamera() {
        camera.position.set(30, 30, 0);
        camera.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    @Override
    public void hide() {}
}
