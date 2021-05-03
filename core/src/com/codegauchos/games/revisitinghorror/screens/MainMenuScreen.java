package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class MainMenuScreen implements Screen {
	private final RevisitingHorror _revisitingHorrorGame;
	private OrthographicCamera _camera;
	
	public MainMenuScreen(RevisitingHorror game) {
		this._revisitingHorrorGame = game;

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
		this._revisitingHorrorGame.batch.setProjectionMatrix(this._camera.combined);

		this._revisitingHorrorGame.batch.begin();
		this._revisitingHorrorGame.horrorFont.draw(this._revisitingHorrorGame.batch, "Revisting Horror!!", 500, 650);
		this._revisitingHorrorGame.horrorFont.draw(this._revisitingHorrorGame.batch, "(tap anywhere to start)", 100, 100);
		this._revisitingHorrorGame.batch.end();
		
		if(Gdx.input.isTouched()==true) {
			this._revisitingHorrorGame.setScreen(new GameScreen(this._revisitingHorrorGame));
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
