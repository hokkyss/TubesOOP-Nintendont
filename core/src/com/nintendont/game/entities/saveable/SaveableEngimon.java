package com.nintendont.game.entities.saveable;

import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Skill;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveableEngimon {
    public int idEngimon;
    public String name;
    public SaveableSpecies species;
    public int level;
    public int exp;
    public int cumExp;
    public int life;
    public ArrayList<Skill> skills;
    public HashMap<String, String> parents;

    public SaveableEngimon() {

    }

    public SaveableEngimon(Engimon e) {
        this.idEngimon = e.getID();
        this.name = e.getName();
        this.species = new SaveableSpecies(e.getSpecies());
        this.level = e.getLevel();
        this.exp = e.getExp();
        this.cumExp = e.getCumExp();
        this.life = e.getLife();
        this.skills = e.getSkills();
        this.parents = e.getParents();
    }

    public Engimon toEngimon() {
        return new Engimon(
                this.name,
                this.species.toSpecies(),
                this.level,
                this.parents
        );
    }
}
