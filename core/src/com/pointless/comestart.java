package com.pointless;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class comestart implements Screen {
	SpriteBatch batch;
	private Animation<TextureRegion> animation;
	Texture img;
	private float showTime = 0;
	private TextureAtlas atlas;
	Array<TextureRegion> frames = new Array<TextureRegion>();
come game;
	public comestart(come game)
	{
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		atlas = new TextureAtlas(Gdx.files.internal("first.atlas"));

		frames.add(new TextureRegion(atlas.findRegion("catend",0)));
		frames.add(new TextureRegion(atlas.findRegion("catend",1)));
		frames.add(new TextureRegion(atlas.findRegion("catend",2)));
		frames.add(new TextureRegion(atlas.findRegion("catend",3)));
		frames.add(new TextureRegion(atlas.findRegion("catend",4)));
		frames.add(new TextureRegion(atlas.findRegion("catend",5)));
		frames.add(new TextureRegion(atlas.findRegion("catend",6)));
		frames.add(new TextureRegion(atlas.findRegion("catend",7)));


		animation = new Animation<TextureRegion>(1/5f,frames);
	}

	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		showTime += delta;
		batch.begin();
	//	batch.draw(img, 0, 0);

		if(animation.getKeyFrame(showTime ) != null) {
			batch.draw(animation.getKeyFrame(showTime, true), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
