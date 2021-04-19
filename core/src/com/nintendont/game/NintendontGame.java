package com.nintendont.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.PlayerSprite;
import com.nintendont.game.entities.Species;
import com.nintendont.game.screens.MainScreen;
import com.nintendont.game.entities.Player;
//import com.nintendont.game.entities.Util;


public class NintendontGame extends Game {

	@Override
	public void create () {
		// testing load species
		try {
			Util.loadSpecies();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setScreen(new MainScreen());
	}

	@Override
	public void render () {
		super.render();

//		camera.update();
//		batch.setProjectionMatrix(camera.combined);
//
//		Texture tex = new Texture("E:/STEI/Sem4/PBO/Nintendont-Game/core/assets/Characters/boy_run.png");
//		update();
//
//		// starting to record drawing commands
//		batch.begin();
//		batch.draw(tex, 1024,1024);
//		stage.draw();
//		batch.end();
	}

//	private void update() {
//		stage.act();
//	}

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
