package com.nintendont.game.exceptions;

import com.nintendont.game.entities.Engimon;
import com.nintendont.game.entities.Skill;

public class SkillNotCompatibleException extends Exception {
    Skill s;
    Engimon e;
    public SkillNotCompatibleException(Skill s, Engimon e)
    {
        this.s = s;
        this.e = e;
    }

    public String getMessage() {
        return s.skillName + " not compatible for " + e.getName() + "!";
    }
}
