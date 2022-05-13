package com.nrstepanek.jipjal.menu.mainmenu;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.nrstepanek.jipjal.Configuration;

public class MainMenuStage extends Stage {

    private TextButton campaignButton;

    public MainMenuStage(Skin skin) {
        super();
        setup(skin);
    }

    public void setup(Skin skin) {
        campaignButton = new TextButton("Campaign", skin);
        campaignButton.setPosition(50.0f, 50.0f);
        campaignButton.setSize(100.0f, 50.0f);
        this.addActor(campaignButton);
    }

    public boolean hitCampaignButton(int screenX, int screenY) {
        int stageY = Configuration.SCREEN_HEIGHT - screenY;
        float minY = campaignButton.getY();
        float minX = campaignButton.getX();
        float maxY = campaignButton.getY() + campaignButton.getHeight();
        float maxX = campaignButton.getX() + campaignButton.getWidth();
        return screenX > minX && screenX < maxX && stageY > minY && stageY < maxY;
    }
}