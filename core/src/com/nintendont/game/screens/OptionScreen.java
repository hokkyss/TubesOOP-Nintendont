package com.nintendont.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.nintendont.game.util.OnSelectHandler;

import java.util.ArrayList;
import java.util.List;

public class OptionScreen extends OverlayScreen{
    private ArrayList<Image> arrows = new ArrayList<>();
    private List<OnSelectHandler> interfaces;
    private ArrayList<Label> options = new ArrayList<>();
    private int selectedIndex = 0;

    private Table uiContainer;
    private float fontScale;

    public OptionScreen(List<String> option, List<OnSelectHandler> onSelect, float fontScale){
        super(350f, 50f * option.size());
        Texture bg = new Texture(Gdx.files.internal("Util/ui-dialog.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));
        this.fontScale = fontScale;
        this.setBackground(temp);
        this.uiContainer = new Table();
        this.add(uiContainer).pad(5f);

        for (String s : option)
        {
            this.addOption(s);
        }
        this.interfaces = onSelect;
        changeArrowVisibility();
    }

    public OptionScreen(List<String> option, List<OnSelectHandler> onSelect)
    {
        this(option,onSelect,1);
    }

    private void addOption(String option)
    {
        Label optionLabel = new Label(option, this.getSkin());
        optionLabel.setColor(0,0,20,1);
        optionLabel.setFontScale(fontScale);
        this.options.add(optionLabel);
        Texture bg = new Texture(Gdx.files.internal("Util/arrow-right.png"));
        TextureRegionDrawable temp = new TextureRegionDrawable(new TextureRegion(bg));

        TextureRegion tr = temp.getRegion();
        Image arrow = new Image(temp);
        arrow.setSize(25f, 25f);
        arrow.setVisible(false);
        this.arrows.add(arrow);

        this.uiContainer.add(arrow).expand().size(25f, 25f).align(Align.left).pad(10f, 20f, 10f, 20f);
        this.uiContainer.add(optionLabel).expand().size(250f, 50f).align(Align.left);
        this.uiContainer.row();
    }

    private void changeArrowVisibility()
    {
        for(int i = 0; i < this.arrows.size(); i++)
        {
            this.arrows.get(i).setVisible(i == this.selectedIndex);
        }
    }

    public void moveUp()
    {
        if(this.selectedIndex == 0)
        {
            this.selectedIndex = this.arrows.size() - 1;
        }
        else
            this.selectedIndex--;
        changeArrowVisibility();
    }

    public void moveDown(){
        if(this.selectedIndex == this.arrows.size() - 1)
        {
            this.selectedIndex = 0;
        }
        else
            this.selectedIndex++;
        changeArrowVisibility();
    }

    public ArrayList<Label> getOptions()
    {
        return this.options;
    }

    public void handleSelect(){
        this.interfaces.get(this.selectedIndex).onSelect();
    }

    public int getSelected()
    {
        return this.selectedIndex;
    }

    public void clearChoices()
    {
        this.uiContainer.clearChildren();
        this.arrows.clear();
        this.options.clear();
        this.selectedIndex = 0;
    }

    public void cancel()
    {
        this.setVisible(false);
    }
}
