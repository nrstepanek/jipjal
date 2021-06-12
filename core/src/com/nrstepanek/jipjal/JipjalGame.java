package com.nrstepanek.jipjal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nrstepanek.jipjal.game.GameScreen;

public class JipjalGame extends Game {
	public SpriteBatch batch;

	public TextureHolder textureHolder;
	
	@Override
	public void create () {
		textureHolder = new TextureHolder();

		batch = new SpriteBatch();

		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
