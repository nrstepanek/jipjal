package com.nrstepanek.jipjal.editor;

import java.io.FileNotFoundException;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.JipjalGame;
import com.nrstepanek.jipjal.game.monsters.Monster;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.MapLoader;
import com.nrstepanek.jipjal.menu.MenuScreen;

public class EditorScreen implements Screen {

    private JipjalGame game;

    private JipjalMap gameMap;

    private EditorLogic logic;
    private EditorInputProcessor inputProcessor;

    private float cameraX;
    private float cameraY;
    private int resolutionWidth;
    private int resolutionHeight;

    public Stage menuStage;

    public Stage selectorTableStage;

    private SelectorTable selectorTable;
    
    public EditorScreen(JipjalGame game) {
        this.game = game;
        this.menuStage = new Stage();
        this.selectorTableStage = new Stage();

        MapLoader loader = new MapLoader(game.textureHolder);

        try {
            gameMap = loader.loadFromFile("test_map.json");
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR: Could not find map file. Starting new map.");
            gameMap = new JipjalMap(32, 32, game.textureHolder, false);
        }

        gameMap.setName("test_map");
        EditorState editorState = new EditorState(game.textureHolder);
        logic = new EditorLogic(this.gameMap, this, editorState);

        selectorTable = new SelectorTable(game.textureHolder, new Skin(Gdx.files.internal(Configuration.SKIN_FILE_LOCATION)));
        selectorTableStage.addActor(selectorTable);

        this.inputProcessor = new EditorInputProcessor(this.logic, editorState, this.selectorTable);
    }

    @Override
    public void show() {
        setInputProcessor();
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
        for (Monster monster : gameMap.getMonsters()) {
            monster.getSprite().draw(game.batch);
        }
        game.batch.end();

        selectorTableStage.act();
        selectorTableStage.draw();

        menuStage.act();
        menuStage.draw();
    }

    // Sets the input processor back to the editor processor.
    public void setInputProcessor() {
        Gdx.input.setInputProcessor(this.inputProcessor);
    }

    public void mainMenu() {
        game.setScreen(new MenuScreen(game));
    }

    protected void saveMap(String mapName) {
        logic.saveMap(mapName);
        setInputProcessor();
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
