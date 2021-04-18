package com.nintendont.game.entities;

public class Position {
    private int x;
    private int y;

    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position p) {
        return (this.x == p.x && this.y == p.y);
    }
}
