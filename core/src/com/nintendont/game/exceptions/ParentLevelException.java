package com.nintendont.game.exceptions;

public class ParentLevelException extends Exception{
    public String getMessage(){
        return "Parent Level Not Enough";
    }
}