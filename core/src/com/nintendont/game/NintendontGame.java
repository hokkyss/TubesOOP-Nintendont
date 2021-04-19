package com.nintendont.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nintendont.game.entities.PlayerSprite;
import com.nintendont.game.screens.Play;
import com.nintendont.game.entities.Player;
//import com.nintendont.game.entities.Util;

public class NintendontGame extends Game {
	Stage stage;
	SpriteBatch batch;
	Sprite sprite;
	Texture img;

	Player player;
	PlayerSprite playersprite;

	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 360;
	public static final float SCALE = 1f;

	@Override
	public void create () {
		OrthographicCamera camera = new OrthographicCamera();
		stage = new Stage(new StretchViewport(1280, 720, camera));
//		batch = new SpriteBatch();
//		Gdx.input.setInputProcessor(stage);

//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

//		camera = new OrtohraphicCamera();
//		camera.setToOrtho(false, )

		// testing load species
		try {
			Util.loadSpecies();
		} catch (Exception e) {
			e.printStackTrace();
		}


		try {
			player = new Player();
			//PlayerSprite playerSprite = new PlayerSprite(new Sprite(new Texture("../core/assets/cat.jpg")));
			//stage.addActor(player);
			setScreen(new Play(camera));
		} catch (Exception e) {
			System.out.println("Gagal!")
		}

	}

	@Override
	public void render () {
		super.render();

//		Texture tex = new Texture("boy_run.png");
//		sprite = new Sprite(tex, 0, 0, 44, 44);
//		update();
//		batch.begin();
//		playersprite.draw(batch);
//		batch.end();
	}

	private void update() {
//		stage.act();
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void resume () {
		super.resume();
	}

	@Override
	public void pause () {
		super.pause();
	}
}
