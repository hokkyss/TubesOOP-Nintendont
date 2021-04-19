package com.nintendont.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nintendont.game.NintendontGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Nintendont";
		config.width = 1280;
		config.height = 720;

		new LwjglApplication(new NintendontGame(), config);
	}
}
