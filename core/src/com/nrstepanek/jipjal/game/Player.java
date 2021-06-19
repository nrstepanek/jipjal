package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.GridDrawable;
import com.nrstepanek.jipjal.map.Item;
import com.nrstepanek.jipjal.map.ItemTypeEnum;

public class Player extends GridDrawable {

	Inventory inventory;

	private boolean slipping;
	private DirectionEnum slipDirection;

    public Player(Texture t, int x, int y) {
        super(t, x, y);
		this.inventory = new Inventory();
    }

	public boolean hasItem(ItemTypeEnum itemType) {
		return inventory.hasItem(itemType);
	}

	public int getItemIndex(ItemTypeEnum itemType) {
		return inventory.getItemIndex(itemType);
	}

	public Item removeItemAtIndex(int index) {
		return inventory.removeItemAtIndex(index);
	}

	public boolean getSlipping() {
		return this.slipping;
	}

	public void setSlipping(boolean slipping) {
		this.slipping = slipping;
	}

	public DirectionEnum getSlipDirection() {
		return this.slipDirection;
	}

	public void setSlipDirection(DirectionEnum slipDirection) {
		this.slipDirection = slipDirection;
	}
}