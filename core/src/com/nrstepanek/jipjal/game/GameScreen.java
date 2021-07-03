package com.nrstepanek.jipjal.game;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.MapLoader;
import com.nrstepanek.jipjal.menu.MenuScreen;

public class GameScreen implements Screen {

	JipjalGame game;

	JipjalMap gameMap;

	Player player;

	GameLogic logic;

	float timeSinceLastUpdate;

	public GameScreen(JipjalGame game) {
		this.game = game;

		MapLoader loader = new MapLoader(game.textureHolder);

		// gameMap = new GameMap(32, 32, textureHolder);
		// gameMap = new JipjalMap(game.textureHolder);
		try {
			gameMap = loader.loadFromFile("level_1.json");
		} catch(FileNotFoundException fnfe) {
			System.out.println("ERROR: Could not find map file.");
		}

		player = new Player(game.textureHolder.getTexture("player"), gameMap.getPlayerStartX(), gameMap.getPlayerStartY());

		logic = new GameLogic(player, gameMap, this);
		updateCamera();
		
		timeSinceLastUpdate = 0.0f;
	}

	@Override
	public void show() {
		InputProcessor inputProcessor = new GameInputProcessor(logic);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render(float delta) {
		logic.updateEntities(delta);

		ScreenUtils.clear(0.9f, 0.9f, 1, 1);

		game.batch.setProjectionMatrix(game.camera.combined);

		game.batch.begin();
		gameMap.drawCells(game.batch);
		player.getSprite().draw(game.batch);
		for (Monster monster : gameMap.getMonsters()) {
			monster.getSprite().draw(game.batch);
		}
		game.batch.end();
	}

	public void updateCamera() {
		game.camera.position.set(player.getSprite().getX(), player.getSprite().getY(), 0);
		game.camera.update();
	}

	public void gameOver() {
		game.setScreen(new MenuScreen(game));
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
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
