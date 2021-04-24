package com.nintendont.game.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class DialogueScreen extends OverlayScreen{
    private String targetText ="";
    private float animTimer = 0f;
    private float animationTotalTime = 0f;
    private float TIME_PER_CHARACTER =0.025f;
    private STATE state = STATE.IDLE;

    private Label textLabel;

    private enum STATE {
        ANIMATING,
        IDLE,
        ;
    }

    public DialogueScreen(){
        this(720f, 150f);
    }

    public DialogueScreen(float w, float h){
        super(w,h);
        textLabel = new Label("\n", this.getSkin());
        this.textLabel.setColor(0,0,0,1);
        this.add(textLabel).expand().align(Align.left).pad(0f, 30f, 0f, 30f);
    }

    public void animateText(String text){
        targetText = text;
        animationTotalTime = text.length()*TIME_PER_CHARACTER;
        state = STATE.ANIMATING;
        animTimer = 0f;
    }

    public boolean isFinished(){
        return state == STATE.IDLE;
    }

    private void setText(String text){
        if(!text.contains("\n")){
            text += "\n";
        }
        this.textLabel.setText(text);
    }

    public void act(float delta){
        super.act(delta);
        if(state == STATE.ANIMATING){
            animTimer+= delta;
            if(animTimer > animationTotalTime){
                state = STATE.IDLE;
                animTimer = animationTotalTime;
            }
            String displayedText = "";
            int characterToDisplay = (int)((animTimer/animationTotalTime) * targetText.length());
            for(int i = 0; i<characterToDisplay; i++){
                displayedText+= targetText.charAt(i);
            }
            if(!displayedText.equals(textLabel.getText().toString())){
                setText(displayedText);
            }
        }
    }
}
