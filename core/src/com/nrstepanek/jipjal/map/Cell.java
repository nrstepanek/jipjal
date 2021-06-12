package com.nrstepanek.jipjal.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nrstepanek.jipjal.GridDrawable;

public class Cell extends GridDrawable {

    private boolean solid;
	private boolean dangerous;
	private boolean isGoal;
	private boolean isPlayerStart;
	private boolean isIce;

	private ObjectType objectType;

	private Sprite groundSprite;

	private Item item;

    public Cell(Texture groundTexture, int x, int y) {
        super(groundTexture, x, y);
        this.solid = false;
		this.objectType = ObjectType.NONE;
		this.dangerous = false;
		this.isGoal = false;
		this.isPlayerStart = false;
    }

    public boolean getSolid() {
        return this.solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

	public boolean getDangerous() {
		return this.dangerous;
	}

	public void setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
	}

	public boolean getIsGoal() {
		return this.isGoal;
	}

	public void setIsGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}

	public ObjectType getObjectType() {
		return this.objectType;
	}

	public boolean getIsPlayerStart() {
		return this.isPlayerStart;
	}

	public void setIsPlayerStart(boolean isPlayerStart) {
		this.isPlayerStart = isPlayerStart;
	}

	public boolean getIsIce() {
		return this.isIce;
	}

	public void setIsIce(boolean isIce) {
		this.isIce = isIce;
	}

	public void setObjectType(ObjectType objectType, Texture texture) {
		this.objectType = objectType;
		this.groundSprite = this.getSprite();
		this.setSprite(new Sprite(texture));
	}

	public void destroyObject() {
		this.objectType = ObjectType.NONE;
		this.setSprite(this.groundSprite);
		this.solid = false;
	}

	public void addItem(Item item, Texture texture) {
		this.item = item;
		this.groundSprite = this.getSprite();
		this.setSprite(new Sprite(texture));
	}

	public Item removeItem() {
		Item temp = this.item;
		this.setSprite(this.groundSprite);
		this.item = null;
		return temp;
	}

	public Item getItem() {
		return this.item;
	}

	public boolean hasItem() {
		return this.item != null;
	}
}