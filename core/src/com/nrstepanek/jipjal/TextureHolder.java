package com.nrstepanek.jipjal;

import com.badlogic.gdx.Gdx;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class TextureHolder {

    private Map<String, Texture> textureMap;
    
    public TextureHolder() {
        textureMap = new HashMap<>();

        Texture playerTexture = new Texture(Gdx.files.internal("player.png"));
        textureMap.put("player", playerTexture);

		Texture grassTexture = new Texture(Gdx.files.internal("grass.png"));
        textureMap.put("grass", grassTexture);

		Texture wallTexture = new Texture(Gdx.files.internal("wall.png"));
        textureMap.put("wall", wallTexture);
    }

    public Texture getTexture(String textureName) {
        return textureMap.get(textureName);
    }

}