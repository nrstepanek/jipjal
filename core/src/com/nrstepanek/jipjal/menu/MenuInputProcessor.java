package com.nrstepanek.jipjal.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.editor.EditorScreen;
import com.nrstepanek.jipjal.game.GameScreen;

public class MenuInputProcessor extends InputAdapter {

	JipjalGame game;

	public MenuInputProcessor(JipjalGame game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Input.Keys.SPACE) {
			game.setScreen(new GameScreen(game, "level_1.json"));
		}
		if (keyCode == Input.Keys.E) {
			game.setScreen(new EditorScreen(game));
		}
		if (keyCode == Input.Keys.T) {
			game.setScreen(new GameScreen(game, "test_map.json"));
		}
		return true;
	}
}
