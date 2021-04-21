package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nintendont.game.GameConfig;
import com.nintendont.game.Logger;
import com.nintendont.game.Util;
import com.nintendont.game.comparators.SkillComparator;
import com.nintendont.game.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player implements Creature, InputProcessor {
    private final int EXP_MULT = 15;

    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;

    private Position pos;
    public Position activeEngimonPos;

    private Texture playerTexture;

    public Player() throws InputTooLargeException {
        this(new Engimon("ember", Species.get("Emberon"), 1));
    }

    public Player(Engimon starter) throws InputTooLargeException {
        this.activeEngimonIdx = 0;

        try {
            engimonList = new Inventory<Engimon>();
            skillItemList = new Inventory<SkillItem>();
            pos = new Position(32, 32);
            activeEngimonPos = new Position(0, 0);
            this.engimonList.insert(starter);
        } catch (Exception err) {
            throw err;
        }

        this.playerTexture = new Texture(Gdx.files.internal("Characters/boy_stand_south.png"));
    }

    @Override
    public Position getPosition() { return pos; }

    @Override
    public void setPosition(Position p) { pos = p; }

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
        s += "pos: (" + this.pos.getX() + "," + this.pos.getY() + ")";

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

    public void draw(Batch batch) {
        batch.draw(
                playerTexture,
                this.getPosition().getX() * GameConfig.SCALED_TILE_SIZE,
                this.getPosition().getY() * GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE * 1.5f
        );
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            this.pos.move(-1,0);
        }
        if (keycode == Input.Keys.RIGHT) {
            this.pos.move(1,0);
        }
        if (keycode == Input.Keys.UP) {
            this.pos.move(0,1);
        }
        if (keycode == Input.Keys.DOWN) {
            this.pos.move(0,-1);
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
