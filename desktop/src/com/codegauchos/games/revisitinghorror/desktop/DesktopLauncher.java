package com.codegauchos.games.revisitinghorror.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 900;
		config.width = 1600;
		config.title = "Revisiting Horror";
		// You should set it to true if you're not targeting Android
//		https://stackoverflow.com/questions/46753218/libgdx-should-i-use-gl30
		config.useGL30 = true;
		
		new LwjglApplication(new RevisitingHorror(), config);
	}
}
