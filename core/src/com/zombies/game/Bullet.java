package com.zombies.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    Sprite sprite;
    float rotation;
    public Bullet(Sprite sprite, float x, float y, float rotation){
        this.sprite = sprite;
        sprite.setX(x);
        sprite.setY(y);
        this.rotation = rotation;
    }

    public void draw(SpriteBatch batch){
        sprite.setY(sprite.getY() + (float)(Math.sin(Math.toRadians(rotation)) * 400 * Gdx.graphics.getDeltaTime()));
        sprite.setX(sprite.getX() + (float)(Math.cos(Math.toRadians(rotation)) * 400 * Gdx.graphics.getDeltaTime()));

        sprite.draw((batch));
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }
}
