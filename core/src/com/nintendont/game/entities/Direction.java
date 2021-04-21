package com.nintendont.game.entities;

import com.nintendont.game.Logger;

public enum Direction {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    public final int idx;

    private Direction(int i) {
        this.idx = i;
    }

    public static Direction randomize() {
        switch (Logger.randomize.nextInt(4)) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            default:
                return RIGHT;
        }
    }

    public static Direction getDirection(int dx, int dy) {
        if (dx == 0 && dy == 1) {
            return UP;
        } else if (dx == 0 && dy == -1) {
            return DOWN;
        } else if (dx == -1 && dy == 0) {
            return LEFT;
        } else if (dx == 1 && dy == 0) {
            return RIGHT;
        }
        return null;
    }
}
