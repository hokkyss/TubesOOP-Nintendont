package com.nintendont.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class OverlayScreen extends  Table {
// extends InputAdapter
    private String text ="";
    private float animTimer = 0f;
    private float animationTotalTime = 0f;
    private float TIME_PER_CHARACTER =0.05f;

    private Window pause;
    private Table popup;
    private Skin mySkin = new Skin(Gdx.files.internal("Skin/glassy-ui.json"));

    public OverlayScreen(){
        int row_height = Gdx.graphics.getWidth() / 12;
        popup = new Table();
        Label text = new Label("HELLO WORLD:", mySkin);
        popup.add(text);
        popup.setSize(Gdx.graphics.getWidth(),row_height * 5);
        popup.setColor(200, 200, 200, 1);
        popup.setPosition(0, row_height * 2);

//        pause = new Window("Paused", skin);
//        pause.setMoveable(false); //So the user can't move the window
//        pause.add(new TextButton("Unpause", skin)); //Add a new text button that unpauses the game.
//        pause.pack(); //Important! Correctly scales the window after adding new elements.
//        float newWidth = 400, newHeight = 200;
//        pause.setBounds((Gdx.graphics.getWidth() - newWidth ) / 2, (Gdx.graphics.getHeight() - newHeight ) / 2, newWidth , newHeight ); //Center on screen.
    }
    public void open(){
        popup.setVisible(true);
    }

    public void close(){
        popup.setVisible(false);
    }
}
