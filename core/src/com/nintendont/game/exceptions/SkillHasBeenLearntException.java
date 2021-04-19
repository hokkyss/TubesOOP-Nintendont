package com.nintendont.game.exceptions;

import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Skill;

public class SkillHasBeenLearntException extends Exception{
    Skill s;
    Engimon e;
    public SkillHasBeenLearntException(Skill s, Engimon e)
    {
        this.s = s;
        this.e = e;
    }

    public String getMessage()
    {
        return this.s.skillName + " has been learnt by " + e.getName();
    }
}
