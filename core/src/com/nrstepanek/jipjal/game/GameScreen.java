package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.map.JipjalMap;

public class GameScreen implements Screen {

	JipjalGame game;

	JipjalMap gameMap;

	Player player;

	GameLogic logic;

	public GameScreen(JipjalGame game) {
		this.game = game;

		player = new Player(game.textureHolder.getTexture("player"), 1, 1);

		// gameMap = new GameMap(32, 32, textureHolder);
		gameMap = new JipjalMap(game.textureHolder);

		logic = new GameLogic(player, gameMap);
	}

	@Override
	public void show() {
		InputProcessor inputProcessor = new GameInputProcessor(logic);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.9f, 0.9f, 1, 1);
		game.batch.begin();
		gameMap.drawCells(game.batch);
		player.getSprite().draw(game.batch);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
