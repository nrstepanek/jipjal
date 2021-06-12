package com.nrstepanek.jipjal.game;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.GridDrawable;
import com.nrstepanek.jipjal.map.Item;
import com.nrstepanek.jipjal.map.ItemType;

public class Player extends GridDrawable {

	Inventory inventory;

	private boolean slipping;

    public Player(Texture t, int x, int y) {
        super(t, x, y);
		this.inventory = new Inventory();
    }

	public boolean hasItem(ItemType itemType) {
		return inventory.hasItem(itemType);
	}

	public int getItemIndex(ItemType itemType) {
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
}