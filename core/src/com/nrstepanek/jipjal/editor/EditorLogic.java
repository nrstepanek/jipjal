package com.nrstepanek.jipjal.editor;

import java.util.ArrayList;
import java.util.List;

import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.map.JipjalMap;

public class EditorLogic {

	JipjalMap map;
	EditorScreen editorScreen;

	// Keeps track of whether arrow keys are pressed or not.
	boolean leftDown;
	boolean rightDown;
	boolean upDown;
	boolean downDown;
	
	public EditorLogic(JipjalMap map, EditorScreen editorScreen) {
		this.map = map;
		this.editorScreen = editorScreen;
	}

	public void convertScreenCoords(int screenX, int screenY) {
		// System.out.println("Click coords: " + screenX + " " + screenY);
		float cameraX = editorScreen.getCameraX();
		float cameraY = editorScreen.getCameraY();
		// System.out.println("Camera coords: " + cameraX + " " + cameraY);

		int resolutionWidth = editorScreen.getResolutionWidth();
		int resolutionHeight = editorScreen.getResolutionHeight();

		float screenXPercent = (float) screenX / resolutionWidth;
		float screenYPercent = 1 - ((float) screenY / resolutionHeight);
		// System.out.println("X Y %: " + screenXPercent + " " + screenYPercent);

		float gameCoordX = cameraX - (Configuration.VIEWPORT_WIDTH / 2) + 
					(screenXPercent * Configuration.VIEWPORT_WIDTH);
		float gameCoordY = cameraY - (Configuration.VIEWPORT_HEIGHT / 2) +
					(screenYPercent * Configuration.VIEWPORT_HEIGHT);
		// System.out.println("Game coords: " + gameX + " " + gameY);

		int gameCellX = (int) Math.floor(gameCoordX / Configuration.GRID_WIDTH);
		int gameCellY = (int) Math.floor(gameCoordY / Configuration.GRID_HEIGHT);
		System.out.println("Game cells: " + gameCellX + " " + gameCellY);
	}

	public List<Float> getCameraChange(float delta) {
		float pixelsToMove = delta * Configuration.CAMERA_PPS;
		float xd = 0f;
		float yd = 0f;

		if (leftDown)
			xd -= pixelsToMove;
		if (rightDown)
			xd += pixelsToMove;
		if (downDown)
			yd -= pixelsToMove;
		if (upDown)
			yd += pixelsToMove;

		List<Float> xyd = new ArrayList<>();
		xyd.add(xd);
		xyd.add(yd);

		return xyd;
	}
}
