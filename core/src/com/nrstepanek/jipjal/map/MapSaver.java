package com.nrstepanek.jipjal.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public final class MapSaver {

	private MapSaver() {}
	
	public static void saveMap(JipjalMap map) {
		Json json = map.toJson();
		String mapString = json.getWriter().getWriter().toString();
		FileHandle mapFileHandle = Gdx.files.local("./maps/" + map.getName() + ".json");
		mapFileHandle.writeString(mapString, false);
	}
}
