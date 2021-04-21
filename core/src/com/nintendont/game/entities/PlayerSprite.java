package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerSprite {
        private static Texture SPRITE_MAP = new Texture(Gdx.files.internal("Characters/boy_run_animation.png"));
        private static TextureRegion[][] WALKING_ANIMATION = TextureRegion.split(SPRITE_MAP, SPRITE_MAP.getWidth() / 4, SPRITE_MAP.getHeight() / 4);

        public static Animation<TextureRegion> WALKING_SOUTH = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[0]);
        public static Animation<TextureRegion> WALKING_WEST = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[1]);
        public static Animation<TextureRegion> WALKING_EAST = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[2]);
        public static Animation<TextureRegion> WALKING_NORTH = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[3]);

        public static TextureRegion STANDING_SOUTH = WALKING_SOUTH.getKeyFrame(0);
        public static TextureRegion STANDING_WEST = WALKING_WEST.getKeyFrame(0);
        public static TextureRegion STANDING_EAST = WALKING_EAST.getKeyFrame(0);
        public static TextureRegion STANDING_NORTH = WALKING_NORTH.getKeyFrame(0);
}
