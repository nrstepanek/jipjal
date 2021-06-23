package com.nrstepanek.jipjal.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.JipjalGame;

public class MenuScreen implements Screen{

	JipjalGame game;

	BitmapFont font;

	MenuInputProcessor menuInputProcessor;

	public MenuScreen(JipjalGame game) {
		this.game = game;
		font = new BitmapFont();
		menuInputProcessor = new MenuInputProcessor(this.game);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(menuInputProcessor);	
	}

	@Override
	public void render(float delta) {
		updateCamera();

		Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.setProjectionMatrix(game.camera.combined);
		
        game.batch.begin();
        //font.draw(game.batch, "Jipjal!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
		font.draw(game.batch, "Jipjal!", Configuration.RESOLUTION_WIDTH * .25f, Configuration.RESOLUTION_HEIGHT * .75f);
        font.draw(game.batch, "Press space to play.", Configuration.RESOLUTION_WIDTH * .25f, Configuration.RESOLUTION_HEIGHT * .25f);
        game.batch.end();
	}

	public void updateCamera() {
		game.camera.position.set(Configuration.RESOLUTION_WIDTH / 2, Configuration.RESOLUTION_HEIGHT / 2, 0);
		game.camera.update();
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
