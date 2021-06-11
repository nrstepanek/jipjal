package com.nrstepanek.jipjal;

import java.util.ArrayList;
import java.util.List;

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
}