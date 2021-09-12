package com.nrstepanek.jipjal.editor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nrstepanek.jipjal.Configuration;
import com.nrstepanek.jipjal.TextureHolder;
import com.nrstepanek.jipjal.game.MonsterTypeEnum;
import com.nrstepanek.jipjal.map.GroundTypeEnum;
import com.nrstepanek.jipjal.map.ItemTypeEnum;
import com.nrstepanek.jipjal.map.ObjectTypeEnum;

public class SelectorTable extends Table {

	private float scale = 1.0f;
	private List<String> tileList;
	private int cols = 2;
	private float width, height;

	public SelectorTable(TextureHolder th, Skin skin) {
		super(skin);
		tileList = new ArrayList<>();
		tileList.addAll(GroundTypeEnum.getAllStrings());
		tileList.addAll(ItemTypeEnum.getAllStrings());
		tileList.addAll(ObjectTypeEnum.getAllStrings());
		tileList.addAll(MonsterTypeEnum.getAllStrings());

		this.setPosition(Configuration.SCREEN_WIDTH - (Configuration.GRID_SIZE * cols), 0);
		this.width = Configuration.GRID_SIZE * cols;
		this.setWidth(width);
		this.height = Configuration.GRID_SIZE * (tileList.size() / cols);
		this.setHeight(height);

		int col = 0;
		for (String tileType : tileList) {
			Image image = new Image(th.getTexture(tileType));
			image.setScale(scale);
			this.add(image);
			col += 1;
			if (col >= cols) {
				this.row();
				col = 0;
			}
		}
	}

	private String getTileTypeFromClick(int clickX, int clickY) {
		int relX = clickX - (int) this.getX();
		int col = Math.floorDiv(relX, (int) Configuration.GRID_SIZE); 
		int row = Math.floorDiv(Math.abs(clickY - (int) this.height), (int) Configuration.GRID_SIZE);
		int index = (row * (int) (this.width / Configuration.GRID_SIZE)) + col;
		return tileList.get(index);
	}

	public boolean checkClick(int clickX, int clickY, EditorState state) {
		clickY = Configuration.SCREEN_HEIGHT - clickY;
		if (clickX >= this.getX() && clickX <= this.getX() + width &&
			clickY >= this.getY() && clickY <= this.getY() + height) {
			String tileType = getTileTypeFromClick(clickX, clickY);
			state.selectedCell.setFromString(tileType);
			return true;
		} else {
		   return false;
		}
	}

}
