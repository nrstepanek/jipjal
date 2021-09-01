package com.nrstepanek.jipjal.menu;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.game.GameScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class MapLabelListener extends ClickListener {

	private String mapName;

	private JipjalGame game;

	public MapLabelListener(String mapName, JipjalGame game) {
		super();
		this.mapName = mapName;
		this.game = game;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new GameScreen(game, mapName));
	}
}
