package com.nintendont.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerSprite extends Sprite {
        public PlayerSprite(Sprite sprite){
                super(sprite);
        }

        public void draw(SpriteBatch spriteBatch){
                update(Gdx.graphics.getDeltaTime());
                super.draw(spriteBatch);
        }

        public void update(float delta){
                setX(getX()-10);
                setY(getY()-10);
        }
}
