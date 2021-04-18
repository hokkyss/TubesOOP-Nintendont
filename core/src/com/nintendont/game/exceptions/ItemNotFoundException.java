package com.nintendont.game.exceptions;

public class ItemNotFoundException extends Exception {
    public String getMessage() {
        return "Skill Item not found!";
    }
}