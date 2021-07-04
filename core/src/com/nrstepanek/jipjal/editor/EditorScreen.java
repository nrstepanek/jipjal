package com.nrstepanek.jipjal.editor;

import java.io.FileNotFoundException;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.MapLoader;

public class EditorScreen implements Screen {

	private JipjalGame game;

	private JipjalMap gameMap;

	private EditorLogic logic;
	private EditorInputProcessor inputProcessor;

	private float cameraX;
	private float cameraY;
	private int resolutionWidth;
	private int resolutionHeight;
	
	public EditorScreen(JipjalGame game) {
		this.game = game;

		MapLoader loader = new MapLoader(game.textureHolder);

		try {
			gameMap = loader.loadFromFile("level_1.json");
		} catch (FileNotFoundException fnfe) {
			System.out.println("ERROR: Could not find map file.");
		}

		EditorState editorState = new EditorState();
		logic = new EditorLogic(this.gameMap, this, editorState);
		this.inputProcessor = new EditorInputProcessor(this.logic, editorState);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this.inputProcessor);
		cameraX = Configuration.VIEWPORT_WIDTH / 2;
		cameraY = Configuration.VIEWPORT_HEIGHT / 2;
		resolutionWidth = Configuration.VIEWPORT_WIDTH;
		resolutionHeight = Configuration.VIEWPORT_HEIGHT;
	}

	@Override
	public void render(float delta) {
		List<Float> cameraChanges = logic.getCameraChange(delta);
		cameraX += cameraChanges.get(0);
		cameraY += cameraChanges.get(1);
		game.camera.translate(cameraChanges.get(0), cameraChanges.get(1));
		game.camera.update();

		ScreenUtils.clear(0.9f, 0.9f, 1, 1);

		game.batch.setProjectionMatrix(game.camera.combined);

		game.batch.begin();
		gameMap.drawCells(game.batch);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.resolutionWidth = width;
		this.resolutionHeight = height;
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
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public float getCameraX() {
		return this.cameraX;
	}

	public float getCameraY() {
		return this.cameraY;
	}

	public int getResolutionWidth() {
		return this.resolutionWidth;
	}

	public int getResolutionHeight() {
		return this.resolutionHeight;
	}
}
