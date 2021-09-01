package com.nrstepanek.jipjal.menu.level_select;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import com.nrstepanek.jipjal.JipjalGame;

public class LevelSelectStage extends Stage {
	
	public LevelSelectStage(Skin skin, JipjalGame game) {
		super();
		setup(skin, game);
	}

	public void setup(Skin skin, JipjalGame game) {
		Table container = new Table();
		this.addActor(container);
		container.setFillParent(true);

		Table table = new Table();
		final ScrollPane scroll = new ScrollPane(table, skin);
		scroll.setScrollingDisabled(true, false);

		table.pad(10).defaults().expandX().space(4);

		FileHandle mapFileHandle = Gdx.files.local("./maps/");
		FileHandle[] mapFiles = mapFileHandle.list();
		for (FileHandle mapFile : mapFiles) {
			table.row();
			Label label = new Label(mapFile.name(), skin);
			label.setAlignment(Align.center);
			label.setWrap(true);
			label.addListener(new MapLabelListener(mapFile.name(), game));
			table.add(label).width(Gdx.graphics.getWidth());
		}

		container.add(scroll).expand().fill();

		this.addListener(new LevelSelectListener(game));
	}
}
