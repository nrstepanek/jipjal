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
        campaignButton.setSize(width, height);
        this.addActor(campaignButton);
    }

    public boolean hitCampaignButton(int screenX, int screenY) {
        int stageY = Configuration.SCREEN_HEIGHT - screenY;
        System.out.println(screenX);
        System.out.println(stageY);
        return campaignButton == this.hit(screenX, stageY, true);
    }
}