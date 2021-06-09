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
import com.nrstepanek.jipjal.JipjalInputProcessor;

public class JipjalGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Texture playerTexture;

	Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		playerTexture = new Texture(Gdx.files.internal("player.png"));

		player = new Player(playerTexture, 32.0f, 32.0f);

		InputProcessor inputProcessor = new JipjalInputProcessor(player);
		Gdx.input.setInputProcessor(inputProcessor);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.9f, 0.9f, 1, 1);
		batch.begin();
		player.getSprite().draw(batch);
		batch.end();

		shapeRenderer.setColor(Color.RED);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.rect(player.getX(), player.getY(), 32, 32);
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		playerTexture.dispose();
		shapeRenderer.dispose();
	}
}
