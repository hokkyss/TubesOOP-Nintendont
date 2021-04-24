package com.nintendont.game.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Optional;

public class OptionScreen extends Table{
    private ArrayList<Image> arrows = new ArrayList<>();
    private ArrayList<Label> options = new ArrayList<>();
    private int selectedIndex = 0;

    private Table uiContainer;

    public OptionScreen(Skin skin)
    {
        super(skin);
        // drawableName = "background"
        this.setBackground("");
        this.uiContainer = new Table();
        this.add(uiContainer).pad(5f);
    }

    public void addOption(String option)
    {
        Label optionLabel = new Label(option, this.getSkin());
        this.options.add(optionLabel);
        // drawableName = "right-arrow"
        Image arrow = new Image(this.getSkin(), "");
        arrow.setVisible(false);
        this.arrows.add(arrow);

        this.uiContainer.add(arrow).expand().align(Align.left);
        this.uiContainer.add(optionLabel).expand().align(Align.left).space(5f);
        this.uiContainer.row();

        changeArrowVisibility();
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

    public void moveDown()
    {
        if(this.selectedIndex == this.arrows.size() - 1)
        {
            this.selectedIndex = 0;
        }
        else
            this.selectedIndex++;
        changeArrowVisibility();
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
}
