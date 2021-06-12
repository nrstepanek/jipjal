package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nrstepanek.jipjal.JipjalGame;

public class GameScreen implements Screen {

	JipjalGame game;

	public GameScreen(JipjalGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		System.out.println("show");
		// TODO Auto-generated method stub
		InputProcessor inputProcessor = new GameInputProcessor(game.logic);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.9f, 0.9f, 1, 1);
		game.batch.begin();
		game.gameMap.drawCells(game.batch);
		game.player.getSprite().draw(game.batch);
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
