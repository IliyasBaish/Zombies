package com.zombies.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Man {
    Sprite sprite;
    double rotation;
    Array<Bullet> bullets;
    Texture bulletTexture;
    SpriteBatch batch;
    long lastPress = 0;

    public Man(Sprite manSprite, Texture bullet, SpriteBatch batch){
        this.sprite = manSprite;
        this.sprite.setX(400 - 32);
        this.sprite.setY(240 - 32);

        bullets = new Array<Bullet>();
        this.bulletTexture = bullet;

        this.batch = batch;
        rotation = 0;
    }

    public void rotateTo(float x, float y){
        x -= sprite.getX() + 32;
        y = -(y - 480 +(sprite.getY() + 32));

        if ((x == 0) && (y > 0)){
            rotation = 0;
        }else if ((x == 0) && (y < 0)){
            rotation = 180;
        }else if((y == 0) && (x > 0)){
            rotation = -90;
        }else if((y == 0) && (x < 0)){
            rotation = 90;
        }else if(x > 0){
            rotation = -(90 - Math.toDegrees(Math.atan(y/x)));
        }else{
            rotation = 90 - Math.toDegrees(Math.atan(y/-x));
        }

        sprite.setRotation((float)rotation);
    }

    public void control(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            sprite.setX(sprite.getX() - 200 * Gdx.graphics.getDeltaTime());
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            sprite.setX(sprite.getX() + 200 * Gdx.graphics.getDeltaTime());
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            sprite.setY(sprite.getY() + 200 * Gdx.graphics.getDeltaTime());
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            sprite.setY(sprite.getY() - 200 * Gdx.graphics.getDeltaTime());

        if(Gdx.input.isTouched() && TimeUtils.nanoTime() - lastPress > 100000000){
            float x = (float)(sprite.getX() + 32 + Math.cos(Math.toRadians(rotation + 90)) * 32);
            float y = (float)(sprite.getY() + 32 + Math.sin(Math.toRadians(rotation + 90)) * 32);
            System.out.print(x + " ");
            System.out.println(y);
            System.out.println(rotation + 90);
            bullets.add(new Bullet(new Sprite(bulletTexture), x, y, (float)rotation + 90));
            lastPress = TimeUtils.nanoTime();
        }

        for(Iterator<Bullet> iter = bullets.iterator(); iter.hasNext(); ){
            Bullet bullet = iter.next();
            if (bullet.getX() < 0 || bullet.getX() > 800 || bullet.getY() < 0 || bullet.getY() > 480) iter.remove();
        }
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        for(Bullet bullet: bullets){
            bullet.draw(batch);
        }
    }

    public void dispose(){
        bulletTexture.dispose();
        batch.dispose();
    }
}
