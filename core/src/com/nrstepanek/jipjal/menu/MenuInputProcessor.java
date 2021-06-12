package com.nrstepanek.jipjal.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.game.GameScreen;

public class MenuInputProcessor extends InputAdapter {

	JipjalGame game;

	public MenuInputProcessor(JipjalGame game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.SPACE) {
			game.setScreen(new GameScreen(game));
		}
		return true;
	}
}
