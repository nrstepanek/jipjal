package com.nrstepanek.jipjal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GridDrawable {

    private Sprite sprite;

    private int x;
    private int y;

    public GridDrawable(Texture t, int x, int y) {
        this.sprite = new Sprite(t);
        this.setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.sprite.setX(x * Configuration.GRID_WIDTH);
        this.sprite.setY(y * Configuration.GRID_HEIGHT);
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.sprite.setX(x * Configuration.GRID_WIDTH);
        this.sprite.setY(y * Configuration.GRID_HEIGHT);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
        this.sprite.setX(x * Configuration.GRID_WIDTH);
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
        this.sprite.setY(y * Configuration.GRID_HEIGHT);
    }
}