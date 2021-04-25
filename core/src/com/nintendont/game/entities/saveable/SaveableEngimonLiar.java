package com.nintendont.game.entities.saveable;

import com.nintendont.game.entities.Creature;
import com.nintendont.game.entities.EngimonLiar;
import com.nintendont.game.entities.Position;

public class SaveableEngimonLiar extends SaveableEngimon implements Creature {
    private Position position;

    public SaveableEngimonLiar() {

    }

    public SaveableEngimonLiar(EngimonLiar e) {
        super(e);
        this.position = e.getPosition();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
    }

    public EngimonLiar toEngimonLiar() {
        return new EngimonLiar(
                this.name,
                this.species.toSpecies(),
                this.level,
                this.position
        );
    }
}
