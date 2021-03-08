package com.zombies.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture manImg;
	Texture bulletImg;
	Sprite sprite;
	double rotation;
	Man man;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manImg = new Texture("man.png");
		bulletImg = new Texture("bullet.png");
		sprite = new Sprite(manImg);
		man = new Man(sprite, bulletImg, batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		man.draw(batch);
		batch.end();

		man.rotateTo(Gdx.input.getX(), Gdx.input.getY());
		man.control();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manImg.dispose();
		bulletImg.dispose();
		man.dispose();
	}
}
