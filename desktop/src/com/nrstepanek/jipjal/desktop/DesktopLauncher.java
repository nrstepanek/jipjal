package com.nrstepanek.jipjal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.JipjalGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jipjal";
		config.width = Configuration.SCREEN_WIDTH;
		config.height = Configuration.SCREEN_HEIGHT;
		new LwjglApplication(new JipjalGame(), config);
	}
}
