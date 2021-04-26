package com.nintendont.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Music defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Bicycle.mp3"));
    public static Sound playerBump = Gdx.audio.newSound(Gdx.files.internal("Sounds/Player bump.ogg"));
    public static Sound menuOpen = Gdx.audio.newSound(Gdx.files.internal("Sounds/GUI menu open.ogg"));
    public static Sound menuClose = Gdx.audio.newSound(Gdx.files.internal("Sounds/GUI menu close.ogg"));
    public static Sound menuCursor = Gdx.audio.newSound(Gdx.files.internal("Sounds/GUI sel cursor.ogg"));
    public static Sound switchEngimon = Gdx.audio.newSound(Gdx.files.internal("Sounds/GUI party switch.ogg"));
    public static Sound saveGame = Gdx.audio.newSound(Gdx.files.internal("Sounds/GUI save game.ogg"));
    public static Sound engimonFaint = Gdx.audio.newSound(Gdx.files.internal("Sounds/Pkmn faint.ogg"));
    public static Sound engimonLearnSkill = Gdx.audio.newSound(Gdx.files.internal("Sounds/Pkmn move learnt.ogg"));
    public static Music battleWin = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Battle victory wild.ogg"));
}
