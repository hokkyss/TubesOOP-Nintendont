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
import com.nintendont.game.screens.InputScreen;
import com.nintendont.game.screens.MainScreen;
import com.nintendont.game.screens.OptionScreen;
import com.nintendont.game.screens.OverlayScreen;
import com.nintendont.game.InGameHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player implements Creature, InputProcessor {
    private MainScreen screen;

    private MapLoader mapLoader;

    public static final int EXP_MULT = 15;

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
    private float activeEngimonWorldX = STARTING_X, activeEngimonWorldY = STARTING_Y + 1;
    private int srcX, srcY;
    private int destX, destY;
    private int activeEngimonSrcX, activeEngimonSrcY;
    private int activeEngimonDestX, activeEngimonDestY;
    private int lastDx, lastDy;
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
        CHEAT();
        this.playerTexture = PlayerSprite.STANDING_SOUTH;
        this.state = PlayerState.STANDING;
        this.walkDir = null;
        this.lookDir = Direction.DOWN;

        this.lastDx = 0;
        this.lastDy = -1;
    }

    private void CHEAT()
    {
        this.engimonList.insert(new Engimon("test", Species.get("Emberon"), 31));
        this.engimonList.insert(new Engimon("fred", Species.get("Frederon"), 31));
        try {
            this.engimonList.get(0).learnSkill(SkillItem.TM01);
            this.skillItemList.insert(SkillItem.TM04);
            this.skillItemList.insert(SkillItem.TM11, 3);
            this.skillItemList.insert(SkillItem.TM20);
        }
        catch(Exception e) {
        }
    }


    @Override
    public Position getPosition() { return pos; }

    @Override
    public void setPosition(Position p) { pos = p; }

    public Direction getLookDir()
    {
        return this.lookDir;
    }

    public Engimon getEngimon(int idx)
    {
        return this.engimonList.get(idx);
    }
    public Engimon getActiveEngimon() {
        return this.engimonList.get(this.activeEngimonIdx);
    }

    public String switchActiveEngimon(int idx) {
        String s = "Active engimon has been changed from " + this.engimonList.get(this.activeEngimonIdx).getName();
        s = s + "\nto " + this.engimonList.get(idx).getName();
        this.activeEngimonIdx = idx;
        return s;
    }

    public ArrayList<String> getAllEngimonDisplayText()
    {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i<engimonList.size(); i++){
            Engimon curr = engimonList.get(i);
            res.add(curr.display());
        }
        return res;
    }

    public ArrayList<String> getAllSkillItemDisplayText()
    {
        ArrayList<String> res = new ArrayList<>();
        for(SkillItem curr : skillItemList.invenList){
            res.add(this.skillItemList.getCount(curr) + "x " + curr.itemName + ": " + curr.containedSkill.skillName);
        }
        return res;
    }

    public ArrayList<String> getAllEngimonDetail()
    {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i<engimonList.size(); i++){
            Engimon curr = engimonList.get(i);
            res.add(curr.details());
        }
        return res;
    }

    public void showAllEngimon() {
        Engimon currActive = this.getActiveEngimon();
        Inventory.sortInventory(this.engimonList);
        this.activeEngimonIdx = engimonList.find(currActive);
    }

    public void showSkillItem() {
        Inventory.sortInventory(skillItemList, true);
    }

    public String useSkillItem(Engimon e1, SkillItem si){
        try {
            e1.learnSkill(si);
            skillItemList.remove(si);
            return "Berhasil menggunakan skillItem";
        } catch (SkillNotCompatibleException err) {
            return e1.getName() + " tidak dapat menggunakan " + si.itemName;
        } catch (SkillHasBeenLearntException err) {
            return e1.getName() + " sudah mempelajari " + si.containedSkill.skillName;
        } catch (Exception err) {
            return "";
        }
    }

    public String throwSkillItem(int amount, SkillItem si)  {
        try {
            this.skillItemList.remove(si, amount);
            return "Sukses membuang";
        } catch (Exception err) {
            return "Ggagal Membuang";
        }
    }

    public String releaseEngimon(Engimon e){
        try {
            if (engimonList.size() == 1) {
                return ("You only have 1 Engimon! Release failed");
            }

            if (engimonList.find(e) == this.activeEngimonIdx) {
                this.activeEngimonIdx = 0;
            }

            engimonList.remove(e);
            return "You have released " + e.getName();
        } catch (Exception err) {
        }
        return "";
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

    public void setEngimonName(int idx, String name){
        this.engimonList.get(idx).setName(name);
    }

    public String breed(Engimon A, Engimon B) {
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

                return "Breeding successful!\n"+childName + " is now in inventory.";
            } else {
                return "Parent level is not enough for breeding!";
            }
//                return throw new ParentLevelException();
        } catch (Exception err) {
            return err.toString();
        }
    }

    public float getWorldX() {
        return this.worldX;
    }

    public float getWorldY() {
        return this.worldY;
    }

    public float getActiveEngimonWorldX() { return this.activeEngimonWorldX; }

    public float getActiveEngimonWorldY(){ return this.activeEngimonWorldY; }

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

            this.activeEngimonWorldX = Interpolation.pow2.apply(this.activeEngimonSrcX, this.activeEngimonDestX, this.animTimer/this.ANIM_TIME);
            this.activeEngimonWorldY = Interpolation.pow2.apply(this.activeEngimonSrcY, this.activeEngimonDestY, this.animTimer/this.ANIM_TIME);

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
        this.activeEngimonSrcX = oldX - this.lastDx;
        this.activeEngimonSrcY = oldY - this.lastDy;
        this.activeEngimonDestX = oldX;
        this.activeEngimonDestY = oldY;
        this.activeEngimonWorldX = oldX - this.lastDx;
        this.activeEngimonWorldY = oldY - this.lastDy;

        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = oldX + dx;
        this.destY = oldY + dy;
        this.worldX = oldX;
        this.worldY = oldY;

        this.animTimer = 0f;
        this.state = PlayerState.WALKING;
        this.walkDir = Direction.getDirection(dx, dy);

        this.lastDx = dx;
        this.lastDy = dy;
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

        OverlayScreen activeScreen = screen.getActiveScreen();

        //kalau ada popup yang sedang aktif dan bukan dialog biasa
        if (activeScreen.isVisible()) {
            if(activeScreen instanceof OptionScreen){
                if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                    ((OptionScreen) activeScreen).moveUp();
                }
                if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                    ((OptionScreen) activeScreen).moveDown();
                }
                if (keycode == Input.Keys.SPACE){
                    ((OptionScreen) activeScreen).handleSelect();
                }
            }else if(activeScreen instanceof InputScreen){
                ((InputScreen) activeScreen).onChange(keycode);
            }else{
                activeScreen.close();
            }
        } else {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                MainScreen.turn++;
                dx = -1;
                this.lookDir = Direction.LEFT;
            }
            if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                MainScreen.turn++;
                dx = 1;
                this.lookDir = Direction.RIGHT;
            }
            if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                MainScreen.turn++;
                dy = 1;
                this.lookDir = Direction.UP;
            }
            if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                MainScreen.turn++;
                dy = -1;
                this.lookDir = Direction.DOWN;
            }
            if (keycode == Input.Keys.SPACE) {
                screen.openController();
                return true;
            } else {
                screen.hideDialog();
            }

            if (MainScreen.turn % InGameHelper.RESPAWN_TURN == 0) InGameHelper.spawnWildEngimons();
            else if (MainScreen.turn % InGameHelper.WILD_ENGIMON_MOVE_TURN == 0) InGameHelper.moveWildEngimon();

            if (mapLoader.isWalkable(this.pos.getX(),this.pos.getY(),dx,dy, true)) {
                initializeMove(this.pos.getX(), this.pos.getY(), dx, dy);
                this.activeEngimonPos.move(dx, dy);
                this.pos.move(dx,dy);
            } else {
                if (dx != 0 || dy != 0) {
                    Direction dir = Direction.getDirection(dx, dy);

                    setPlayerTexture(dir, false);

                    Sounds.playerBump.play();
                }
            }
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
