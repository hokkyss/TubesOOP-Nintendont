package com.nintendont.game.exceptions;

public class InputTooLargeException extends Exception {
    public String getMessage() {
        return "Too bad! Your inventory is full!\nYou can't get anymore item/engimon!";
    }
}
