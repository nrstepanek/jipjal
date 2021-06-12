package com.nrstepanek.jipjal.game;

import java.util.ArrayList;
import java.util.List;

import com.nrstepanek.jipjal.map.Item;
import com.nrstepanek.jipjal.map.ItemType;

public class Inventory {

	List<Item> items;

	public Inventory() {
		this.items = new ArrayList<>();
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public boolean hasItem(ItemType itemType) {
		for (Item item : items) {
			if (item.getItemType() == itemType)
				return true;
		}

		return false;
	}

	public int getItemIndex(ItemType itemType) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getItemType() == itemType)
				return i;
		}

		return -1;
	}

	public Item removeItemAtIndex(int index) {
		return this.items.remove(index);
	}
}