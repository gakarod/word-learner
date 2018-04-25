package com.curieo.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class third implements Screen {
	SpriteBatch batch;
	private Animation<TextureRegion> animation;
	Texture img;
	Texture texture1;
	private float showTime = 0;
	int index ;
	String a ;
	float xpos ,ypos;
	private TextureAtlas atlas;
	Array<TextureRegion> frames = new Array<TextureRegion>();
	private ExerciseData exercise;
come game;
	public third(come game , int index , ExerciseData exerciseData , String a)
	{
		this.game = game;
		this.index = index ;
		this.exercise = exerciseData;
		this.a = a ;
		batch = new SpriteBatch();

		img = new Texture("shape game.png");
		atlas = new TextureAtlas(Gdx.files.internal(a + ".atlas"));
		frames.add(new TextureRegion(atlas.findRegion(a ,0)));
		frames.add(new TextureRegion(atlas.findRegion(a,1)));
		frames.add(new TextureRegion(atlas.findRegion(a,2)));
		frames.add(new TextureRegion(atlas.findRegion(a,3)));
		frames.add(new TextureRegion(atlas.findRegion(a,4)));
		frames.add(new TextureRegion(atlas.findRegion(a,5)));
		animation = new Animation<TextureRegion>(1/4f,frames);
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
		batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if(!animation.isAnimationFinished(showTime)  ) {
			batch.draw(animation.getKeyFrame(showTime), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		else
		{
			game.setScreen(new ShapeGameScreen(game , index , exercise ));
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
