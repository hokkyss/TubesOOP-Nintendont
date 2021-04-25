package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nintendont.game.Logger;
import java.util.ArrayList;

public class Species {
    private String species;
    private ArrayList<Element> elements;
    private Skill uniqueSkill;
    private ArrayList<String> response;

    private String pathBattleSprite;
    private String pathIconSprite;

    private Texture ICON_SPRITE_MAP;
    private Animation<TextureRegion> ICON_ANIMATION;

    public static ArrayList<Species> listOfSpecies;

    public Species() {

    }

    public Species(String species, ArrayList<Element> elements, Skill uniqueSkill, ArrayList<String> response, String pathBattleSprite, String pathIconSprite) {
        this.species = species;
        this.elements = elements;
        elements.sort(Element.comparator);
        this.uniqueSkill = uniqueSkill;
        this.response = response;

        this.pathBattleSprite = pathBattleSprite;
        this.pathIconSprite = pathIconSprite;

        this.ICON_SPRITE_MAP = new Texture(Gdx.files.internal(this.pathIconSprite));
        TextureRegion[][] temp = TextureRegion.split(ICON_SPRITE_MAP, ICON_SPRITE_MAP.getWidth() / 2, ICON_SPRITE_MAP.getHeight());
        this.ICON_ANIMATION = new Animation<TextureRegion>(0.5f, temp[0]);
    }

    public Species(Species target) {
        this.species = target.species;
        this.elements = target.elements;
        this.uniqueSkill = target.uniqueSkill;
        this.response = target.response;

        this.pathBattleSprite = target.pathBattleSprite;
        this.pathIconSprite = target.pathIconSprite;

        this.ICON_SPRITE_MAP = target.ICON_SPRITE_MAP;
        this.ICON_ANIMATION = target.ICON_ANIMATION;
    }

    public String getSpecies() {
        return this.species;
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    public Skill getUniqueSkill() {
        return this.uniqueSkill;
    }

    public ArrayList<String> getResponses() {
        return this.response;
    }

    public Animation<TextureRegion> getIconAnimation() {
        return this.ICON_ANIMATION;
    }

    public String interact() {
        int target = Logger.randomize.nextInt(response.size());
        return (response.get(target));
    }

    public String getPathBattleSprite() {
        return this.pathBattleSprite;
    }

    public String getPathIconSprite() {
        return this.pathIconSprite;
    }

    public static Species get(String name) {
        for (Species s : Species.listOfSpecies) {
            if (name.equalsIgnoreCase(s.getSpecies())) {
                return s;
            }
        }
        return null;
    }

    public static Species getSpeciesByName(Species s) {
        return Species.get(s.getSpecies());
    }

    public static Species getSpeciesByElement(ArrayList<Element> elements) {
        for (Species s : Species.listOfSpecies) {
            boolean haveAllElements = true;
            for (Element el : elements) {
                if (!s.elements.contains(el)) {
                    haveAllElements = false;
                }
            }

            if (haveAllElements) {
                return s;
            }
        }
        return null;
    }
}
