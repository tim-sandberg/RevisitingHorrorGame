package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class MainMenuScreen implements Screen {
	final RevisitingHorror revisitingHorrorGame;
	private OrthographicCamera _camera;

	public MainMenuScreen(RevisitingHorror game) {
		this.revisitingHorrorGame = game;

		this._camera = new OrthographicCamera();
		this._camera.setToOrtho(false, RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		this._camera.update();
		this.revisitingHorrorGame.batch.setProjectionMatrix(this._camera.combined);

		this.revisitingHorrorGame.batch.begin();
		this.revisitingHorrorGame.horrorFont.draw(this.revisitingHorrorGame.batch, "Revisting Horror!!", 500, 650);
		this.revisitingHorrorGame.horrorFont.draw(this.revisitingHorrorGame.batch, "(tap anywhere to start)", 100, 100);
		this.revisitingHorrorGame.batch.end();
		
		if(Gdx.input.isTouched()==true) {
			this.revisitingHorrorGame.setScreen(new GameScreen(this.revisitingHorrorGame));
			this.dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
