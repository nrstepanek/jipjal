package com.nrstepanek.jipjal;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.nrstepanek.jipjal.Player;
import com.nrstepanek.jipjal.Cell;
import com.nrstepanek.jipjal.GameMap;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.JipjalInputProcessor;
import com.nrstepanek.jipjal.JipjalLogic;

public class JipjalGame extends ApplicationAdapter {
	SpriteBatch batch;

	TextureHolder textureHolder;

	GameMap gameMap;

	Player player;

	JipjalLogic logic;
	
	@Override
	public void create () {
		textureHolder = new TextureHolder();

		batch = new SpriteBatch();
		player = new Player(textureHolder.getTexture("player"), 1, 1);

		gameMap = new GameMap(32, 32, textureHolder);

		logic = new JipjalLogic(player, gameMap);

		InputProcessor inputProcessor = new JipjalInputProcessor(logic);
		Gdx.input.setInputProcessor(inputProcessor);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0.9f, 0.9f, 1, 1);
		batch.begin();
		gameMap.drawCells(batch);
		player.getSprite().draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}