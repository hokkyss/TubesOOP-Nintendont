package com.nintendont.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.nintendont.game.GameConfig;

public class EngimonLiar extends Engimon implements Creature {
    private Position position;

    public EngimonLiar() {

    }

    public EngimonLiar(String name, Species species, int level, Position p) {
        super(name, species, level);
        this.position = p;
    }

    public EngimonLiar(Engimon e, Position p) {
        super(e.getName(), e.getSpecies(), e.getLevel());
        this.position = p;
    }

    @Override
    public Position getPosition() { return position; }

    @Override
    public void setPosition(Position p) {
        this.position = p;
    }

    public void increaseLevel() {
        this.addExp(Engimon.EXP_PER_LEVEL);
    }

    public void draw(Batch batch, float delta) {
        this.stateTime += delta;
        this.engimonTexture = this.getSpecies().getIconAnimation().getKeyFrame(stateTime, true);

        batch.draw(
                this.engimonTexture,
                this.position.getX() * GameConfig.SCALED_TILE_SIZE - 16,
                this.position.getY() * GameConfig.SCALED_TILE_SIZE,
                this.engimonTexture.getRegionWidth(),
                this.engimonTexture.getRegionHeight()
//                GameConfig.SCALED_TILE_SIZE * 1.5f,
//                GameConfig.SCALED_TILE_SIZE * 1.5f
        );
    }
}
