package com.nrstepanek.jipjal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

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

	public Texture getTextureFromItemType(ItemType itemType) {
		switch (itemType) {
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

	public Texture getTextureFromObjectType(ObjectType objectType) {
		switch (objectType) {
			case BLUE_LOCK:
				return getTexture("blue_lock");
			case YELLOW_LOCK:
				return getTexture("yellow_lock");
			case RED_LOCK:
				return getTexture("red_lock");
			case GREEN_LOCK:
				return getTexture("green_lock");
			case NONE:
				return null;
		}

		return null;
	}
}