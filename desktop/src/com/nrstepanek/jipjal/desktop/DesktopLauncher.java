package com.nrstepanek.jipjal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nrstepanek.jipjal.JipjalGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jipjal";
		config.width = 1280;
		config.height = 640;
		new LwjglApplication(new JipjalGame(), config);
	}
}
