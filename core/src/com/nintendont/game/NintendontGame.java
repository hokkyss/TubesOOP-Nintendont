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
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.PlayerSprite;
import com.nintendont.game.entities.Species;
import com.nintendont.game.screens.Play;
import com.nintendont.game.entities.Player;
//import com.nintendont.game.entities.Util;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);

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

		setScreen(new Play(camera, batch));
		try {
			player = new Player(new Engimon("ember", Species.get("Emberon"), 1));
			//PlayerSprite playerSprite = new PlayerSprite(new Sprite(new Texture("../core/assets/cat.jpg")));
			stage.addActor(player);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void render () {
		super.render();

		Texture tex = new Texture("E:/STEI/Sem4/PBO/Nintendont-Game/core/assets/Characters/boy_run.png");
		update();
		batch.begin();
		batch.draw(tex, 50F,50F);
		stage.draw();
		batch.end();
	}

	private void update() {
		stage.act();
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
