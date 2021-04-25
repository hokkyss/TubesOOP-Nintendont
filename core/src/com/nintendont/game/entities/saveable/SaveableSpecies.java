package com.nintendont.game.entities.saveable;

import com.nintendont.game.entities.Element;
import com.nintendont.game.entities.Skill;
import com.nintendont.game.entities.Species;

import java.util.ArrayList;

public class SaveableSpecies {
    public String species;
    public ArrayList<Element> elements;
    public Skill uniqueSkill;
    public ArrayList<String> response;

    public String pathBattleSprite;
    public String pathIconSprite;

    public SaveableSpecies() {

    }

    public SaveableSpecies(Species s) {
        this.species = s.getSpecies();
        this.elements = s.getElements();
        this.uniqueSkill = s.getUniqueSkill();
        this.response = s.getResponses();
        this.pathBattleSprite = s.getPathBattleSprite();
        this.pathIconSprite = s.getPathIconSprite();
    }

    public Species toSpecies() {
        return new Species(
                this.species,
                this.elements,
                this.uniqueSkill,
                this.response,
                this.pathBattleSprite,
                this.pathIconSprite
        );
    }
}
