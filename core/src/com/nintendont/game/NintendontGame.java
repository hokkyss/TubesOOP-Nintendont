package com.nintendont.game;

import com.badlogic.gdx.Game;
import com.nintendont.game.screens.StarterScreen;

public class NintendontGame extends Game {

	@Override
	public void create () {
		try {
			Util.loadSpecies();
		} catch (Exception e) {
			e.printStackTrace();
		}

		StarterScreen screen = new StarterScreen(this);
		setScreen(screen);
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
