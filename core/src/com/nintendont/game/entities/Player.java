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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nintendont.game.Sounds;
import com.nintendont.game.entities.PlayerSprite;
import com.nintendont.game.GameConfig;
import com.nintendont.game.Logger;
import com.nintendont.game.Util;
import com.nintendont.game.comparators.SkillComparator;
import com.nintendont.game.exceptions.*;
import com.nintendont.game.maps.MapLoader;
import com.nintendont.game.maps.Terrain;
import com.nintendont.game.screens.MainScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player implements Creature, InputProcessor {
    private MainScreen screen;

    private MapLoader mapLoader;

    private final int EXP_MULT = 15;

    private int activeEngimonIdx;
    public Inventory<Engimon> engimonList;
    public Inventory<SkillItem> skillItemList;

    private Position pos;
    public Position activeEngimonPos;

    private TextureRegion playerTexture;
    private PlayerState state;

    private static final int STARTING_X = 30;
    private static final int STARTING_Y = 40;

    // Tweening player movement attribute
    private float worldX = STARTING_X, worldY = STARTING_Y;
    private int srcX, srcY;
    private int destX, destY;
    private Direction lookDir;
    private Direction walkDir;
    private float animTimer;
    private float ANIM_TIME = 0.5f;

    public Player(MainScreen screen, Engimon starter, MapLoader mapLoader) throws InputTooLargeException {
        this.screen = screen;
        this.activeEngimonIdx = 0;
        // to access map properties
        this.mapLoader = mapLoader;

        try {
            this.engimonList = new Inventory<Engimon>();
            this.skillItemList = new Inventory<SkillItem>();
            this.pos = new Position(STARTING_X, STARTING_Y);
            this.activeEngimonPos = new Position(STARTING_X, STARTING_Y);
            this.engimonList.insert(starter);
        } catch (Exception err) {
            throw err;
        }

        this.playerTexture = PlayerSprite.STANDING_SOUTH;
        this.state = PlayerState.STANDING;
        this.walkDir = null;
        this.lookDir = Direction.DOWN;
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

    public float getWorldX() {
        return this.worldX;
    }

    public float getWorldY() {
        return this.worldY;
    }

    public void draw(Batch batch) {
        batch.draw(
                playerTexture,
                this.getWorldX() * GameConfig.SCALED_TILE_SIZE,
                this.getWorldY() * GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE * 1.5f
        );
    }

    public void update(float delta) {
        if (this.state == PlayerState.WALKING) {
            animTimer += delta;
            this.worldX = Interpolation.pow2.apply(this.srcX, this.destX, this.animTimer/this.ANIM_TIME);
            this.worldY = Interpolation.pow2.apply(this.srcY, this.destY, this.animTimer/this.ANIM_TIME);

            setPlayerTexture(this.walkDir, true);

            if (this.animTimer > this.ANIM_TIME) {
                this.finishMove();
            }
        }
    }

    private void setPlayerTexture(Direction dir, boolean animate) {
        float stateTime = animate ? animTimer : 0;

        Terrain terrain = mapLoader.getTerrain(this.pos.getX(), this.pos.getY());

        if (terrain == Terrain.SEA) {
            if (dir == Direction.UP) {
                this.playerTexture = PlayerSprite.SURFING_NORTH.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.DOWN) {
                this.playerTexture = PlayerSprite.SURFING_SOUTH.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.LEFT) {
                this.playerTexture = PlayerSprite.SURFING_WEST.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.RIGHT) {
                this.playerTexture = PlayerSprite.SURFING_EAST.getKeyFrame(stateTime, true);
            }
        } else {
            if (dir == Direction.UP) {
                this.playerTexture = PlayerSprite.WALKING_NORTH.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.DOWN) {
                this.playerTexture = PlayerSprite.WALKING_SOUTH.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.LEFT) {
                this.playerTexture = PlayerSprite.WALKING_WEST.getKeyFrame(stateTime, true);
            }
            if (dir == Direction.RIGHT) {
                this.playerTexture = PlayerSprite.WALKING_EAST.getKeyFrame(stateTime, true);
            }
        }
    }

    private void initializeMove(int oldX, int oldY, int dx, int dy) {
        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = oldX + dx;
        this.destY = oldY + dy;
        this.worldX = oldX;
        this.worldY = oldY;
        this.animTimer = 0f;
        this.state = PlayerState.WALKING;
        this.walkDir = Direction.getDirection(dx, dy);
    }

    private void finishMove() {
        state = PlayerState.STANDING;
        this.walkDir = null;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (this.state != PlayerState.STANDING) {
            return false;
        }

        int dx = 0;
        int dy = 0;
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
            dx = -1;
            this.lookDir = Direction.LEFT;
        }
        if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
            dx = 1;
            this.lookDir = Direction.RIGHT;
        }
        if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
            dy = 1;
            this.lookDir = Direction.UP;
        }
        if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
            dy = -1;
            this.lookDir = Direction.DOWN;
        }
        screen.hideDialog();
        if (keycode == Input.Keys.SPACE){
            screen.toggleDialog(lookDir);
        }

        if (mapLoader.isWalkable(this.pos.getX(),this.pos.getY(),dx,dy)) {
            initializeMove(this.pos.getX(), this.pos.getY(), dx, dy);
            this.pos.move(dx,dy);
        } else {
            Direction dir = Direction.getDirection(dx, dy);

            setPlayerTexture(dir, false);

            Sounds.playerBump.play();
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
