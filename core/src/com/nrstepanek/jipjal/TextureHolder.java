package com.nrstepanek.jipjal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.nrstepanek.jipjal.map.ItemTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;
import com.nrstepanek.jipjal.map.GroundTypeEnum;

public class TextureHolder {

    private Map<String, Texture> textureMap;
    
    public TextureHolder() {
        textureMap = new HashMap<>();

		FileHandle dirHandle = Gdx.files.internal("./");

		for (FileHandle entry : dirHandle.list()) {
			Texture t = new Texture(Gdx.files.internal(entry.name()));
			textureMap.put(entry.name().substring(0, entry.name().length() - 4), t);
		}
    }

    public Texture getTexture(String textureName) {
        return textureMap.get(textureName);
    }

	public Texture getTextureFromGroundType(GroundTypeEnum groundType) {
		switch (groundType) {
			case GRASS:
				return getTexture("grass");
			case ICE:
				return getTexture("ice");
			case FORCE_LEFT:
				return getTexture("force_left");
			case FORCE_UP:
				return getTexture("force_up");
			case FORCE_RIGHT:
				return getTexture("force_right");
			case FORCE_DOWN:
				return getTexture("force_down");
			case NONE:
				return null;
		}

		return null;
	}

	public Texture getTextureFromItemType(ItemTypeEnum itemType) {
		switch (itemType) {
			case CHIP:
				return getTexture("chip");
			case BLUE_KEY:
				return getTexture("blue_key");
			case YELLOW_KEY:
				return getTexture("yellow_key");
			case RED_KEY:
				return getTexture("red_key");
			case GREEN_KEY:
				return getTexture("green_key");
		}

		return null;
	}

	public Texture getTextureFromObjectType(ObjectTypeEnum objectType) {
		switch (objectType) {
			case BLUE_LOCK:
				return getTexture("blue_lock");
			case YELLOW_LOCK:
				return getTexture("yellow_lock");
			case RED_LOCK:
				return getTexture("red_lock");
			case GREEN_LOCK:
				return getTexture("green_lock");
			case FIRE:
				return getTexture("fire");
			case WATER:
				return getTexture("water");
			case SOCKET:
				return getTexture("socket");
			case NONE:
				return null;
		}

		return null;
	}
}