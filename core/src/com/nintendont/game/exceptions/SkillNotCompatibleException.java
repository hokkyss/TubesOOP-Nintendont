package com.nintendont.game.exceptions;

public class SkillNotCompatibleException extends Exception {
    public String getMessage() {
        return "Skill not compatible!";
    }
}
