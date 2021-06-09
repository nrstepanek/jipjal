package com.nrstepanek.jipjal;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class Player {

    private Sprite sprite;

    public Player(Texture t, float x, float y) {
        this.sprite = new Sprite(t);
        this.sprite.setX(x);
        this.sprite.setY(y);
    }

    public void setPosition(float x, float y) {
        this.sprite.setX(x);
        this.sprite.setY(y);
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public float getX() {
        return this.sprite.getX();
    }

    public void setX(float x) {
        this.sprite.setX(x);
    }

    public float getY() {
        return this.sprite.getY();
    }

    public void setY(float y) {
        this.sprite.setY(y);
    }
}