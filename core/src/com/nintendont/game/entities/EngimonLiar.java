package com.nintendont.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.nintendont.game.GameConfig;
import com.badlogic.gdx.graphics.Texture;

public class EngimonLiar extends Engimon implements Creature {
    private Position position;

    public EngimonLiar(String name, Species species, int level, Position p) {
        super(name, species, level);
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

    public void draw(Batch batch) {
        batch.draw(
                new Texture(this.getSpecies().getPathIconSprite()),
                this.position.getX() * GameConfig.SCALED_TILE_SIZE,
                this.position.getY() * GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE,
                GameConfig.SCALED_TILE_SIZE * 1.5f
        );
    }
}
