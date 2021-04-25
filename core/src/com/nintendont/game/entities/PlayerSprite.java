package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerSprite {
        public PlayerSprite() {

        }

        private static Texture WALKING_SPRITE_MAP = new Texture(Gdx.files.internal("Characters/boy_run_animation.png"));
        private static TextureRegion[][] WALKING_ANIMATION = TextureRegion.split(WALKING_SPRITE_MAP, WALKING_SPRITE_MAP.getWidth() / 4, WALKING_SPRITE_MAP.getHeight() / 4);

        private static Texture SURFING_SPRITE_MAP = new Texture(Gdx.files.internal("Characters/boy_surf_animation.png"));
        private static TextureRegion[][] SURFING_ANIMATION = TextureRegion.split(SURFING_SPRITE_MAP, SURFING_SPRITE_MAP.getWidth() / 4, SURFING_SPRITE_MAP.getHeight() / 4);

        public static Animation<TextureRegion> WALKING_SOUTH = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[0]);
        public static Animation<TextureRegion> WALKING_WEST = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[1]);
        public static Animation<TextureRegion> WALKING_EAST = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[2]);
        public static Animation<TextureRegion> WALKING_NORTH = new Animation<TextureRegion>(0.125f, WALKING_ANIMATION[3]);

        public static Animation<TextureRegion> SURFING_SOUTH = new Animation<TextureRegion>(0.125f, SURFING_ANIMATION[0]);
        public static Animation<TextureRegion> SURFING_WEST = new Animation<TextureRegion>(0.125f, SURFING_ANIMATION[1]);
        public static Animation<TextureRegion> SURFING_EAST = new Animation<TextureRegion>(0.125f, SURFING_ANIMATION[2]);
        public static Animation<TextureRegion> SURFING_NORTH = new Animation<TextureRegion>(0.125f, SURFING_ANIMATION[3]);

        public static TextureRegion STANDING_SOUTH = WALKING_SOUTH.getKeyFrame(0);
        public static TextureRegion STANDING_WEST = WALKING_WEST.getKeyFrame(0);
        public static TextureRegion STANDING_EAST = WALKING_EAST.getKeyFrame(0);
        public static TextureRegion STANDING_NORTH = WALKING_NORTH.getKeyFrame(0);
}
