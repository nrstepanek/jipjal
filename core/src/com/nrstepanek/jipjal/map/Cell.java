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

	private ObjectTypeEnum objectType;

	private GroundTypeEnum groundType;

	private Sprite groundSprite;

	private Item item;

    public Cell(Texture groundTexture, int x, int y) {
        super(groundTexture, x, y);
        this.solid = false;
		this.objectType = ObjectTypeEnum.NONE;
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

	public ObjectTypeEnum getObjectType() {
		return this.objectType;
	}

	public boolean getIsPlayerStart() {
		return this.isPlayerStart;
	}

	public void setIsPlayerStart(boolean isPlayerStart) {
		this.isPlayerStart = isPlayerStart;
	}

	public GroundTypeEnum getGroundType() {
		return this.groundType;
	}

	public void setGroundType(GroundTypeEnum groundType) {
		this.groundType = groundType;
	}

	public void setObjectType(ObjectTypeEnum objectType, Texture texture) {
		this.objectType = objectType;
		this.groundSprite = this.getSprite();
		this.setSprite(new Sprite(texture));
	}

	public void destroyObject() {
		this.objectType = ObjectTypeEnum.NONE;
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
