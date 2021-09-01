package com.nrstepanek.jipjal.menu.level_select;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.menu.MenuScreen;

public class LevelSelectListener extends InputListener {

	private JipjalGame game;
	
	public LevelSelectListener(JipjalGame game) {
		super();
		this.game = game;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			game.setScreen(new MenuScreen(game));
		}
		return true;
	}
}
