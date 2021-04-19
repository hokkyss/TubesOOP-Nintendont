package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nintendont.game.Logger;
import com.nintendont.game.Util;
import com.nintendont.game.comparators.SkillComparator;
import com.nintendont.game.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player extends Actor {
    private final int EXP_MULT = 15;

    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;

    public Position activeEngimonPos;

    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    TextureRegion[] walkFrames;
    private final static int STARTING_X = 1024;
    private final static int STARTING_Y = 1024;
    TextureRegion reg;
    float stateTime;

    public Player() throws InputTooLargeException {
        this(new Engimon("ember", Species.get("Emberon"), 1));
    }

    public Player(Engimon starter) throws InputTooLargeException {
        this.activeEngimonIdx = 0;
        this.setPosition(STARTING_X, STARTING_Y);

        try {
            engimonList = new Inventory<Engimon>();
            skillItemList = new Inventory<SkillItem>();
            activeEngimonPos = new Position(0, 0);
            this.engimonList.insert(starter);
        } catch (Exception err) {
            throw err;
        }

        createIdleAnimation();
    }

    public Position getPosition() {
        return new Position((int) this.getX(), (int) this.getY());
    }

    private void createIdleAnimation() {
        walkSheet = new Texture(
                Gdx.files.internal("E:/STEI/Sem4/PBO/Nintendont-Game/core/assets/Characters/boy_run.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        stateTime = 0f;
        reg = walkAnimation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        reg = walkAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color color = getColor();
        // batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(walkFrames[0], getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

    public Engimon getActiveEngimon() {
        return this.engimonList.get(this.activeEngimonIdx);
    }

    public void switchActiveEngimon(int idx) {
        this.activeEngimonIdx = idx;
    }

    public void showAllEngimon() {
        Engimon currActive = this.getActiveEngimon();
        Inventory.sortInventory(this.engimonList);
        this.activeEngimonIdx = engimonList.find(currActive);
    }

    public void showSkillItem() {
        Inventory.sortInventory(skillItemList, true);
    }

    public void useSkillItem(Engimon e1, SkillItem si) throws InputTooLargeException, SkillNotCompatibleException,
            ItemNotFoundException, SkillHasBeenLearntException {
        try {
            e1.learnSkill(si);
            skillItemList.remove(si);
        } catch (Exception err) {
            throw err;
        }
    }

    public void throwSkillItem(int amount, SkillItem si) throws InputTooLargeException, ItemNotFoundException {
        try {
            this.skillItemList.remove(si, amount);
        } catch (Exception err) {
            throw err;
        }
    }

    public void releaseEngimon(Engimon e) throws InputTooLargeException, ItemNotFoundException {
        try {
            if (engimonList.size() == 1) {
                Logger.print("You only have 1 Engimon! Release failed");
                return;
            }

            if (engimonList.find(e) == this.activeEngimonIdx) {
                this.activeEngimonIdx = 0;
            }

            engimonList.remove(e);
        } catch (Exception err) {
            throw err;
        }
    }

    public String toString() {
        String s = "";
        s += "activeEngimonIdx: " + this.activeEngimonIdx + "\n";
        s += "engimonList: " + this.engimonList.toString() + "\n";
        s += "skillItemList: " + this.skillItemList.toString() + "\n";
        s += "pos: (" + this.getX() + "," + this.getY() + ")";

        return s;
    }

    public ArrayList<Element> inheritElmt(Engimon A, Engimon B) {
        Element elmtA = A.getElements().get(0);
        Element elmtB = B.getElements().get(0);
        ArrayList<Element> inheritedElmt = new ArrayList<Element>();
        if (elmtA == elmtB) {
            inheritedElmt.add(elmtA);
        } else if (elmtA != elmtB) {
            if (Element.getAdvantage(elmtA, elmtB) > Element.getAdvantage(elmtB, elmtA)) {
                inheritedElmt.add(elmtA);
            } else if (Element.getAdvantage(elmtA, elmtB) < Element.getAdvantage(elmtB, elmtA)) {
                inheritedElmt.add(elmtB);
            } else {
                inheritedElmt.add(elmtA);
                inheritedElmt.add(elmtB);
            }
        }
        return inheritedElmt;
    }

    public ArrayList<Skill> inheritSkill(Engimon A, Engimon B, Skill uniqueSkill) {
        ArrayList<Skill> inheritedSkill = new ArrayList<Skill>();
        inheritedSkill.add(uniqueSkill);

        ArrayList<Skill> parentSkills = new ArrayList<Skill>(A.getSkills());
        parentSkills.addAll(B.getSkills());
        parentSkills.sort(new SkillComparator());
        for (int i = 0; i < parentSkills.size() && inheritedSkill.size() < 4; i++) {
            if (!inheritedSkill.contains(parentSkills.get(i))) {
                inheritedSkill.add(parentSkills.get(i));
            }
        }
        return inheritedSkill;
    }

    public void breed(Engimon A, Engimon B) throws InputTooLargeException, ParentLevelException {
        try {
            if (A.getLevel() >= 4 && B.getLevel() >= 4) {
                Scanner input = new Scanner(System.in);
                Logger.print("Enter your new Engimon's name: ");
                String childName = input.nextLine();

                ArrayList<Element> childElmt = inheritElmt(A, B);
                Species childSpecies = new Species(A.getSpecies());
                if (Util.isElementSame(childElmt, A.getElements())) {
                    childSpecies = Species.getSpeciesByName(A.getSpecies());
                } else if (Util.isElementSame(childElmt, B.getElements())) {
                    childSpecies = Species.getSpeciesByName(B.getSpecies());
                } else {
                    childSpecies = Species.getSpeciesByElement(childElmt);
                }

                Skill childUniqueSkill = childSpecies.getUniqueSkill();
                ArrayList<Skill> childSkill = inheritSkill(A, B, childUniqueSkill);

                HashMap<String, String> parents = new HashMap<String, String>();
                parents.put(A.getName(), A.getSpecies().getSpecies());
                parents.put(B.getName(), B.getSpecies().getSpecies());

                Engimon child = new Engimon(childName, childSpecies, 1, parents);

                child.setSkills(childSkill);
                engimonList.insert(child);

                A.setLevel(A.getLevel() - 3);
                B.setLevel(B.getLevel() - 3);

                Logger.print("Breeding successful!");
                Logger.print(childName + " is in inventory.");
            } else
                throw new ParentLevelException();
        } catch (Exception err) {
            throw err;
        }
    }

    public void battle(EngimonLiar enemy) throws ItemNotFoundException, InputTooLargeException, EngimonRanOutException {
        Engimon myEngimon = this.getActiveEngimon();
        int winner = Util.handleBattle(myEngimon, enemy);

        Logger.print(myEngimon.getName() + " VS " + enemy.getName());

        if (winner == 1) {
            Logger.print(myEngimon.getName() + " Won the battle!");
            int expWon = enemy.getLevel() * EXP_MULT;
            myEngimon.addExp(expWon);

            try {
                if (myEngimon.isDead()) {
                    Logger.EngimonDeadByLevel(myEngimon);
                    this.engimonList.remove(myEngimon);
                } else {
                    Logger.print("You get new Engimon: " + enemy.getName());
                    Engimon rewardEngimon = new Engimon(enemy.getName(), enemy.getSpecies(), enemy.getLevel());
                    this.engimonList.insert(rewardEngimon);

                    SkillItem rewardSkillItem = SkillItem.getRandomSkillItem(enemy.getElements());
                    this.skillItemList.insert(rewardSkillItem);
                    Logger.print("You get new SkillItem: \n" + rewardSkillItem);
                }
            } catch (Exception err) {
                throw err;
            }
        } else {
            Logger.print(myEngimon.getName() + " Lost the battle!");
            myEngimon.faint();

            if (myEngimon.getLife() == 0) {
                this.engimonList.remove(myEngimon);
            }
        }

        if (engimonList.size() == 0) {
            throw new EngimonRanOutException();
        }
    }

}
