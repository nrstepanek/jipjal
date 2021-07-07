package com.nrstepanek.jipjal.editor;

import java.util.ArrayList;
import java.util.List;

import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.map.Cell;
import com.nrstepanek.jipjal.map.JipjalMap;
import com.nrstepanek.jipjal.map.PrefabLoader;

public class EditorLogic {

	JipjalMap map;
	EditorScreen editorScreen;
	EditorState editorState;
	PrefabLoader pl;

	public EditorLogic(JipjalMap map, EditorScreen editorScreen, EditorState editorState) {
		this.map = map;
		this.editorScreen = editorScreen;
		this.editorState = editorState;
		this.pl = new PrefabLoader(new TextureHolder());
	}
	
	public void modifyCell(int screenX, int screenY) {
		List<Integer> cellCoords = convertScreenCoords(screenX, screenY);
		map.removeCellAt(cellCoords.get(0), cellCoords.get(1));
		// Cell newCell = pl.getWallPrefab(cellCoords.get(0), cellCoords.get(1));
		Cell newCell = editorState.selectedCell.getSelectedCellPrefab(cellCoords.get(0), cellCoords.get(1));
		map.addToCellMap(newCell);
	}

	public List<Integer> convertScreenCoords(int screenX, int screenY) {
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

		List<Integer> gameCoords = new ArrayList<>();
		gameCoords.add(gameCellX);
		gameCoords.add(gameCellY);

		return gameCoords;
	}

	public List<Float> getCameraChange(float delta) {
		float pixelsToMove = delta * Configuration.CAMERA_PPS;
		float xd = 0f;
		float yd = 0f;

		if (editorState.leftDown)
			xd -= pixelsToMove;
		if (editorState.rightDown)
			xd += pixelsToMove;
		if (editorState.downDown)
			yd -= pixelsToMove;
		if (editorState.upDown)
			yd += pixelsToMove;

		List<Float> xyd = new ArrayList<>();
		xyd.add(xd);
		xyd.add(yd);

		return xyd;
	}
}
