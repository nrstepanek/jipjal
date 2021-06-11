package com.nrstepanek.jipjal;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMap {

    private int width;
    private int height;

    private Map<Integer, Cell> cellMap;

    private TextureHolder th;

    public GameMap(int width, int height, TextureHolder textureHolder) {
        this.width = width;
        this.height = height;
        this.th = textureHolder;
        this.cellMap = new HashMap<>();
        randomGenerate();
    }

	public GameMap(TextureHolder textureHolder) {
		this.width = 10;
		this.height = 10;
		this.th = textureHolder;
		this.cellMap = new HashMap<>();
		generateTestMap();
	}

    public void randomGenerate() {
        Random rand = new Random();

        for (int i = 0; i < width * height; i++) {
            Texture texture;
            boolean solid = false;

            if (rand.nextFloat() > 0.1f) {
                texture = th.getTexture("grass");
            } else {
                texture = th.getTexture("wall");
                solid = true;
            }

            Cell newCell = new Cell(texture, i % width, (int) Math.floor(i / height));
			newCell.setSolid(solid);

            cellMap.put(i, newCell);
        }
    }

	public void generateTestMap() {
		Cell cell1 = new Cell(th.getTexture("wall"), 0, 0);
		cell1.setSolid(true);
		addToCellMap(cell1);
		Cell cell2 = new Cell(th.getTexture("wall"), 0, 1);
		cell2.setSolid(true);
		addToCellMap(cell2);
		Cell cell3 = new Cell(th.getTexture("wall"), 1, 0);
		cell3.setSolid(true);
		addToCellMap(cell3);

		Cell cell4 = new Cell(th.getTexture("grass"), 5, 5);
		Item blueKeyItem = new Item(ItemType.BLUE_KEY);
		cell4.addItem(blueKeyItem, th.getTextureFromItemType(ItemType.BLUE_KEY));
		addToCellMap(cell4);

		Cell cell5 = new Cell(th.getTexture("grass"), 1, 5);
		cell5.setSolid(true);
		cell5.setObjectType(ObjectType.BLUE_LOCK, th.getTextureFromObjectType(ObjectType.BLUE_LOCK));
		addToCellMap(cell5);
		// Cell keyDoorCell = new Cell(th.getTexture("keydoor"), 3, 3);
		// keyDoorCell.setSolid(true);
		// keyDoorCell.setObjectType(ObjectType.KEY_DOOR);
		// addToCellMap(keyDoorCell);

		for (int i = 0; i < width * height; i++) {
			if (!cellMap.containsKey(i)) {
				Cell newCell = new Cell(th.getTexture("grass"), i % width, (int) Math.floor(i / height));
				addToCellMap(newCell);
			}
		}
	}

	public void addToCellMap(Cell cell) {
		cellMap.put(getCellLoc(cell), cell);
	}

    public void drawCells(SpriteBatch batch) {
        for (Cell cell : cellMap.values()) {
            cell.getSprite().draw(batch);
        }
    }

	public int getCellLoc(Cell cell) {
		return cell.getX() + cell.getY() * this.width;
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