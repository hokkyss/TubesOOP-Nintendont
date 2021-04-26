package com.nintendont.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.nintendont.game.GameConfig;
import com.nintendont.game.Logger;
import com.nintendont.game.entities.saveable.SaveableEngimon;
import com.nintendont.game.exceptions.SkillNotCompatibleException;
import com.nintendont.game.exceptions.SkillHasBeenLearntException;
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

    public TextureRegion engimonTexture;
    public float stateTime;

    public Engimon() {
        this.species = null;
    }

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

        this.engimonTexture = species.getIconAnimation().getKeyFrame(0);
    }

    public Engimon(SaveableEngimon se) {
        this.idEngimon = EngimonCount + 1;
        this.name = se.name;
        this.species = se.species.toSpecies();
        this.level = se.level;
        this.exp = se.exp;
        this.cumExp = se.cumExp;
        this.life = se.life;

        this.skills = new ArrayList<Skill>();
        this.skills.addAll(se.skills);

        this.parents = null;
        if (se.parents != null) {
            this.parents = new HashMap<String, String>();
            se.parents.forEach((key, value) -> this.parents.put(key, value));
        }

        EngimonCount++;

        this.engimonTexture = species.getIconAnimation().getKeyFrame(0);
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

    public int getExp() {
        return this.exp;
    }

    public int getCumExp() {
        return this.cumExp;
    }

    public void addExp(int exp) {
        if (this.exp + exp >= EXP_PER_LEVEL && !(this instanceof EngimonLiar)) {
            Logger.EngimonLevelUp(this, this.exp + exp);
        }
        this.cumExp += exp;
        this.exp = (this.exp + exp) % EXP_PER_LEVEL;
        this.level = (this.cumExp / EXP_PER_LEVEL) + 1;
    }

    public HashMap<String, String> getParents() {
        return this.parents;
    }

    public boolean isDead() {
        boolean isDead = this.cumExp >= EXP_PER_LEVEL * (MAX_LEVEL - 1);
        if (isDead) {
            Logger.EngimonDeadByLevel(this);
        }
        return isDead;
    }

    public String interact() {
        return this.species.interact();
    }

    public String details() {
        String s = "";
        s = s + ("=======ENGIMON'S DETAIL=======\n");
        s = s + ("ID Engimon : " + this.idEngimon + "\n");
        s = s + ("Nama : " + this.name + "\n");
        s = s + ("Species : " + this.species.getSpecies() + "\n");
        s = s + ("Elements : "
                + this.species.getElements().stream().map(e -> e.name()).collect(Collectors.joining(", ")) + "\n");
        s = s + ("Level : " + this.level + "\n");
        s = s + ("EXP : (" + this.exp + "/100)\n");
        s = s + ("Total EXP : " + this.cumExp + "\n");
        s = s + ("Unique Skill : " + this.species.getUniqueSkill().skillName + "\n");

        // TODO: Print skill level, mastery level, etc.
        s = s + ("Skills : " + this.getSkills().stream().map(S -> S.skillName).collect(Collectors.joining(", ")) + "\n");

        if (this.parents != null && this.parents.size() == 2) {
            s = s + ("Parents : " + this.parents.entrySet().stream()
                    .map(entry -> entry.getKey() + " - " + entry.getValue()).collect(Collectors.joining(" <3 ")) + "\n");
        } else {
            s = s + ("Parents : -\n");
        }

        s = s + ("==============================");
        return s;
    }

    public String faint() {
        this.life--;
        if (this.life > 0) {
            return Logger.EngimonLoseLife(this);
        } else {
            return Logger.EngimonDead(this);
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
    
    public String display()
    {
        return this.name+ " - " + this.species.getSpecies() + " / Lv. " + this.level;
    }
    private boolean hasLearnt(Skill s) {
        return this.skills.contains(s);
    }

    public void learnSkill(Skill s) throws SkillNotCompatibleException, SkillHasBeenLearntException {
        if (this.hasLearnt(s)) {
            Logger.print("Skill not compatible or engimon has learnt the skill");
            throw new SkillHasBeenLearntException(s, this);
        }

        if (!isSkillCompatible(s)) {
            Logger.print("Skill not compatible or engimon has learnt the skill");
            throw new SkillNotCompatibleException(s, this);
        }
        this.skills.add(new Skill(s));
    }

    public void learnSkill(SkillItem si) throws SkillNotCompatibleException, SkillHasBeenLearntException {
        learnSkill(si.containedSkill);
    }

    // draw for active engimon
    public void draw(Batch batch, float x, float y, float delta) {
        this.stateTime += delta;
        this.engimonTexture = this.getSpecies().getIconAnimation().getKeyFrame(stateTime, true);

        batch.draw(
                this.engimonTexture,
                x * GameConfig.SCALED_TILE_SIZE - 16,
                y * GameConfig.SCALED_TILE_SIZE,
                this.engimonTexture.getRegionWidth(),
                this.engimonTexture.getRegionHeight()
//                GameConfig.SCALED_TILE_SIZE * 1.5f,
//                GameConfig.SCALED_TILE_SIZE * 1.5f
        );
    }

    public void initializeMove(int srcX, int srcY, int destX, int destY) {

    }

    public void finishMove() {

    }
}
