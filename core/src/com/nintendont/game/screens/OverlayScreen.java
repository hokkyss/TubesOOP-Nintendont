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

public class OverlayScreen extends  Table {
// extends InputAdapter
    private String targetText ="";
    private float animTimer = 0f;
    private float animationTotalTime = 0f;
    private float TIME_PER_CHARACTER =0.05f;
    private STATE state = STATE.IDLE;

    private Label textLabel;

    private enum STATE {
        ANIMATING,
        IDLE,
        ;
    }

    private Window pause;
    private Table popup;
    private Skin mySkin = new Skin(Gdx.files.internal("Skin/glassy-ui.json"));

    public OverlayScreen(Skin skin){
        super(skin);
        Texture bg = new Texture(Gdx.files.internal("Util/bg.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));
        this.setBackground(temp);
        textLabel = new Label("\n", mySkin);
        this.add(textLabel).expand().align(Align.left).pad(5f);
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

    public float getPrefWidth(){
        return 200f;
    }
}
