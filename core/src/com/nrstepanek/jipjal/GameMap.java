package com.nrstepanek.jipjal;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.nrstepanek.jipjal.Cell;
import com.nrstepanek.jipjal.TextureHolder;

public class GameMap {

    private int width;
    private int height;

    private Map<Integer, Cell> cellMap;

    private TextureHolder textureHolder;

    public GameMap(int width, int height, TextureHolder textureHolder) {
        this.width = width;
        this.height = height;
        this.textureHolder = textureHolder;
        this.cellMap = new HashMap<>();
        randomGenerate();
    }

    public void randomGenerate() {
        Random rand = new Random();

        for (int i = 0; i < width * height; i++) {
            Texture texture;
            boolean solid = false;

            if (rand.nextFloat() > 0.1f) {
                texture = textureHolder.getTexture("grass");
            } else {
                texture = textureHolder.getTexture("wall");
                solid = true;
            }

            Cell newCell = new Cell(texture, i % width, (int) Math.floor(i / height));
			newCell.setSolid(solid);

            cellMap.put(i, newCell);
        }
    }

    public void drawCells(SpriteBatch batch) {
        for (Cell cell : cellMap.values()) {
            cell.getSprite().draw(batch);
        }
    }

	public Cell getCell(int x, int y) {
		return cellMap.get(y * width + x);
	}

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}