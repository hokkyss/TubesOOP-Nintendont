package com.nintendont.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.nintendont.game.util.OnSubmitHandler;

public class InputScreen extends OverlayScreen implements InputProcessor {
    private Label label, input;
    private OnSubmitHandler onSubmitHandler;

    public InputScreen(String label, OnSubmitHandler onSubmitHandler) { this(720f, 150f, label, onSubmitHandler); }

    public InputScreen(float w, float h, String textLabel, OnSubmitHandler onSubmitHandler) {
        super(w, h);
        this.label = new Label(textLabel, this.getSkin());
        this.label.setSize(600f, 50f);
        this.label.setColor(0, 0, 20, 1);

        this.input = new Label("", this.getSkin());
        this.input.setSize(600f, 50f);
        this.input.setFontScale(1.25f);
        this.input.setColor(0,0,20,1);

        this.onSubmitHandler = onSubmitHandler;

        this.add(label).expand().size(720f, 50f).align(Align.center).pad(20f);
        this.row();
        this.add(input).expand().size(720f, 50f).align(Align.center).pad(20f);
    }

    public void onChange(int keycode){
        String strKey = Input.Keys.toString(keycode);
        int n = this.input.getText().length();

        if (keycode == Input.Keys.ENTER) {
            this.onSubmitHandler.onSubmit(this.input.getText());
        } else if(keycode == Input.Keys.BACKSPACE) {
            if(n > 0){
                this.input.setText(input.getText().substring(0,n-1));
            }
        } else {
            if(keycode == Input.Keys.SPACE){
                strKey = " ";
            }
            this.input.setText(input.getText() + strKey);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("INI DARI ON CHANGE");
        String strKey = Input.Keys.toString(keycode);
        int n = this.input.getText().length();

        if (keycode == Input.Keys.ENTER) {
            this.onSubmitHandler.onSubmit(this.input.getText());
        } else if(keycode == Input.Keys.BACKSPACE && n > 0) {
            this.input.setText(input.getText().substring(0,n-1));
        } else {
            if(keycode == Input.Keys.SPACE){
                strKey = " ";
            }
            System.out.println(Input.Keys.toString(keycode));
            this.input.setText(input.getText() + strKey);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void addOverlayTo(Table root){
        root.add(this).expand().align(Align.bottom).pad(8f);
    }
}

