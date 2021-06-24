package com.nrstepanek.jipjal.map;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.io.Serializable;
import java.lang.Math;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.game.Monster;

public class JipjalMap implements Serializable {

    private int width;
    private int height;

	private int socketThreshold;

    private Map<Integer, Cell> cellMap;

	private List<Monster> monsters;

    private TextureHolder th;

    public JipjalMap(int width, int height, TextureHolder textureHolder) {
        this.width = width;
        this.height = height;
        this.th = textureHolder;
        this.cellMap = new HashMap<>();
		this.socketThreshold = 1;
		this.monsters = new ArrayList<>();
        randomGenerate();
    }

	public JipjalMap(TextureHolder textureHolder) {
		this.width = 10;
		this.height = 10;
		this.th = textureHolder;
		this.cellMap = new HashMap<>();
		this.socketThreshold = 2;
		this.monsters = new ArrayList<>();
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
		Item blueKeyItem = new Item(ItemTypeEnum.BLUE_KEY);
		cell4.addItem(blueKeyItem, th.getTextureFromItemType(ItemTypeEnum.BLUE_KEY));
		addToCellMap(cell4);

		Cell cell5 = new Cell(th.getTexture("grass"), 1, 5);
		cell5.setSolid(true);
		cell5.setObjectType(ObjectTypeEnum.BLUE_LOCK, th.getTextureFromObjectType(ObjectTypeEnum.BLUE_LOCK));
		addToCellMap(cell5);

		Cell fireCell = new Cell(th.getTexture("grass"), 1, 6);
		fireCell.setDangerous(true);
		fireCell.setObjectType(ObjectTypeEnum.FIRE, th.getTextureFromObjectType(ObjectTypeEnum.FIRE));
		addToCellMap(fireCell);

		Cell waterCell = new Cell(th.getTexture("grass"), 0, 6);
		waterCell.setDangerous(true);
		waterCell.setObjectType(ObjectTypeEnum.WATER, th.getTextureFromObjectType(ObjectTypeEnum.WATER));
		addToCellMap(waterCell);

		Cell goalCell = new Cell (th.getTexture("goal"), 5, 8);
		goalCell.setIsGoal(true);
		addToCellMap(goalCell);

		Cell iceCell = new Cell(th.getTexture("ice"), 3, 1);
		iceCell.setGroundType(GroundTypeEnum.ICE);
		addToCellMap(iceCell);
		Cell iceCell2 = new Cell(th.getTexture("ice"), 4, 1);
		iceCell2.setGroundType(GroundTypeEnum.ICE);
		addToCellMap(iceCell2);

		Cell forceUpCell = new Cell(th.getTexture("force_up"), 5, 0);
		forceUpCell.setGroundType(GroundTypeEnum.FORCE_UP);
		addToCellMap(forceUpCell);
		Cell forceLeftCell = new Cell(th.getTexture("force_left"), 9, 0);
		forceLeftCell.setGroundType(GroundTypeEnum.FORCE_LEFT);
		addToCellMap(forceLeftCell);
		Cell forceDownCell = new Cell(th.getTexture("force_down"), 9, 4);
		forceDownCell.setGroundType(GroundTypeEnum.FORCE_DOWN);
		addToCellMap(forceDownCell);
		Cell forceRightCell = new Cell(th.getTexture("force_right"), 6, 4);
		forceRightCell.setGroundType(GroundTypeEnum.FORCE_RIGHT);
		addToCellMap(forceRightCell);

		Cell forceLeftCell2 = new Cell(th.getTexture("force_left"), 8, 5);
		forceLeftCell2.setGroundType(GroundTypeEnum.FORCE_LEFT);
		addToCellMap(forceLeftCell2);

		for (int i = 0; i < width * height; i++) {
			if (!cellMap.containsKey(i)) {
				Cell newCell = new Cell(th.getTexture("grass"), i % width, (int) Math.floor(i / height));
				addToCellMap(newCell);
			}
		}

		Cell chipCell = new Cell(th.getTexture("grass"), 1, 8);
		Item chip = new Item(ItemTypeEnum.CHIP);
		chipCell.addItem(chip, th.getTextureFromItemType(ItemTypeEnum.CHIP));
		addToCellMap(chipCell);

		Cell chipCell2 = new Cell(th.getTexture("grass"), 1, 9);
		Item chip2 = new Item(ItemTypeEnum.CHIP);
		chipCell2.addItem(chip2, th.getTextureFromItemType(ItemTypeEnum.CHIP));
		addToCellMap(chipCell2);

		Cell socketCell = new Cell(th.getTexture("grass"), 4, 8);
		socketCell.setSolid(true);
		socketCell.setObjectType(ObjectTypeEnum.SOCKET, th.getTextureFromObjectType(ObjectTypeEnum.SOCKET));
		addToCellMap(socketCell);
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

	public boolean isWithinBounds(int x, int y) {
		if (x < this.width && x >= 0 && y < this.height && y >= 0)
			return true;
		return false;
	}

	public int getSocketThreshold() {
		return this.socketThreshold;
	}
	
	public void setSocketThreshold(int socketThreshold) {
		this.socketThreshold = socketThreshold;
	}

	public List<Monster> getMonsters() {
		return this.monsters;
	}

	public void addMonster(Monster monster) {
		this.monsters.add(monster);
	}
}