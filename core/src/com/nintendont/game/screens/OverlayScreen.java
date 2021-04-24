package com.nintendont.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class OverlayScreen extends Table {
    private float width;
    private float height;

    private Skin mySkin = new Skin(Gdx.files.internal("Skin/craftacular-ui.json"));

    public OverlayScreen(){
        this(720f, 150f);
    }

    public OverlayScreen(float w, float h){
        super(new Skin(Gdx.files.internal("Skin/craftacular-ui.json")));
        Texture bg = new Texture(Gdx.files.internal("Util/ui-dialog.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));
        this.setBackground(temp);
        this.width = w;
        this.height = h;
        this.close();
    }

    public float getPrefWidth(){
        return this.width;
    }

    public float getPrefHeight(){
        return this.height;
    }

    public void open(){
        this.setVisible(true);
    }

    public void close()
    {
        this.setVisible(false);
    }

    public void toggle(){
        this.setVisible(!this.isVisible());
    }

}
