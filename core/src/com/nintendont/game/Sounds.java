package com.nintendont.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Music defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Bicycle.mp3"));
    public static Sound playerBump = Gdx.audio.newSound(Gdx.files.internal("Sounds/Player bump.ogg"));
}
