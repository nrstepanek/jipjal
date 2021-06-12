package com.nrstepanek.jipjal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nrstepanek.jipjal.game.GameScreen;
import com.nrstepanek.jipjal.game.GameLogic;
import com.nrstepanek.jipjal.game.Player;
import com.nrstepanek.jipjal.map.JipjalMap;

public class JipjalGame extends Game {
	public SpriteBatch batch;

	public TextureHolder textureHolder;

	public JipjalMap gameMap;

	public Player player;

	public GameLogic logic;
	
	@Override
	public void create () {
		textureHolder = new TextureHolder();

		batch = new SpriteBatch();
		player = new Player(textureHolder.getTexture("player"), 1, 1);

		// gameMap = new GameMap(32, 32, textureHolder);
		gameMap = new JipjalMap(textureHolder);

		logic = new GameLogic(player, gameMap);

		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
