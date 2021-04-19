package com.nintendont.game.entities;

import com.nintendont.game.Logger;
import com.nintendont.game.exceptions.SkillNotCompatibleException;
import java.util.*;
import java.util.stream.Collectors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nintendont.game.Logger;
import com.nintendont.game.Util;
import com.nintendont.game.comparators.SkillComparator;
import com.nintendont.game.exceptions.*;

public class Engimon {
    public static final int EXP_PER_LEVEL = 100;
    public static final int MAX_LEVEL = 50;
    public static int EngimonCount;

    private int idEngimon;
    private String name;
    private final Species species;
    private int level;
    private int exp;
    private int cumExp;
    private int life;
    private ArrayList<Skill> skills;
    private HashMap<String, String> parents;

    public Engimon(String name, Species species, int level, HashMap<String, String> parents) {
        this.idEngimon = EngimonCount + 1;
        this.name = name;
        this.species = species;
        this.level = level;
        this.exp = 0;
        this.cumExp = (level - 1) * EXP_PER_LEVEL;
        this.life = 3;

        this.skills = new ArrayList<Skill>();
        this.skills.add(new Skill(species.getUniqueSkill()));

        this.parents = parents;
        EngimonCount++;
    }

    public Engimon(String name, Species species, int level) {
        this(name, species, level, null);
    }

    public int getID() {
        return this.idEngimon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int targetLevel) {
        if (this.level != targetLevel) {
            this.level = targetLevel;
            this.exp = 0;
            this.cumExp = EXP_PER_LEVEL * (targetLevel - 1);
        }
    }

    public int getLife() {
        return this.life;
    }

    public Species getSpecies() {
        return this.species;
    }

    public ArrayList<Element> getElements() {
        return this.species.getElements();
    }

    public ArrayList<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public int getSkillPower() {
        int pow = 0;

        for (Skill s : this.skills) {
            pow += s.basePower * s.getMasteryLevel();
        }

        return pow;
    }

    public void addExp(int exp) {
        if (this.exp + exp >= EXP_PER_LEVEL && !(this instanceof EngimonLiar)) {
            Logger.EngimonLevelUp(this, this.exp + exp);
        }
        this.cumExp += exp;
        this.exp = (this.exp + exp) % EXP_PER_LEVEL;
        this.level = (this.cumExp / EXP_PER_LEVEL) + 1;
    }

    public boolean isDead() {
        boolean isDead = this.cumExp >= EXP_PER_LEVEL * (MAX_LEVEL - 1);
        if (isDead) {
            Logger.EngimonDeadByLevel(this);
        }
        return isDead;
    }

    public void interact(){
        Logger.print(this.species.interact());
    }

    public void showDetails() {
        Logger.print("=======ENGIMON'S DETAIL=======");
        Logger.print("ID Engimon : " + this.idEngimon);
        Logger.print("Nama : " + this.name);
        Logger.print("Species : " + this.species.getSpecies());
        Logger.print("Elements : "
                + this.species.getElements().stream().map(e -> e.name()).collect(Collectors.joining(", ")));
        Logger.print("Level : " + this.level);
        Logger.print("EXP : (" + this.exp + "/100)");
        Logger.print("Total EXP : " + this.cumExp);
        Logger.print("Unique Skill : " + this.species.getUniqueSkill().skillName);

        // TODO: Print skill level, mastery level, etc.
        Logger.print("Skills : " + this.getSkills().stream().map(s -> s.skillName).collect(Collectors.joining(", ")));

        if (this.parents != null && this.parents.size() == 2) {
            Logger.print("Parents : " + this.parents.entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(" <3 ")));
        } else {
            Logger.print("Parents : -");
        }

        Logger.print("==============================");
    }

    public void faint() {
        this.life--;
        if (this.life > 0) {
            Logger.EngimonLoseLife(this);
        } else {
            Logger.EngimonDead(this);
        }
    }

    private boolean isSkillCompatible(Skill s) {
        ArrayList<Element> target = s.learnableBy;
        for (Element e : this.getElements()) {
            if (target.contains(e)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasLearnt(Skill s) {
        return this.skills.contains(s);
    }

    public void learnSkill(Skill s) throws SkillNotCompatibleException {
        if (isSkillCompatible(s) && !hasLearnt(s)) {
            // jangan langsung add(s).
            // ntar kalau masterynya naik, itu naiknya dari enumerasinya.
            // cek MainSkill untuk lebih detail.
            this.skills.add(new Skill(s));
        } else {
            Logger.print("Skill not compatible or engimon has learnt the skill");
            throw new SkillNotCompatibleException(s, this);
        }
    }

    public void learnSkill(SkillItem si) throws SkillNotCompatibleException {
        learnSkill(si.containedSkill);
    }
}
