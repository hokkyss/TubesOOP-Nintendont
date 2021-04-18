package com.nintendont.game.exceptions;

public class EngimonRanOutException extends Exception{
    public String getMessage() {
        return "You have ran out of Engimon!";
    }
}
