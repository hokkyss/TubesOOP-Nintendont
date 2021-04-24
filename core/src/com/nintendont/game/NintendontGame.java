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
import com.nintendont.game.screens.StarterScreen;
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

		StarterScreen starterScreen = new StarterScreen(this);
		setScreen(starterScreen);
	}

	public void GoToMainScreen() {
		setScreen(new MainScreen());
	}

	@Override
	public void render () {
		super.render();
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
