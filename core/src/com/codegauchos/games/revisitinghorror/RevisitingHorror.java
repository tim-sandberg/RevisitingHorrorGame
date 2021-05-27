package com.codegauchos.games.revisitinghorror;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.codegauchos.games.revisitinghorror.screens.GameScreen;
import com.codegauchos.games.revisitinghorror.screens.MainMenuScreen;

/**
 * responsible for handling multiple screens and provides some helper methods
 * for this purpose, alongside an implementation of ApplicationListener for you
 * to use
 * 
 * @author tim
 *
 */
public class RevisitingHorror extends Game {
	// fields
	private SpriteBatch _spriteBatch;
	public BitmapFont horrorTitleFont;

	// COMMENT: constants
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 1024;

	// Members (getters and setters) here
	public SpriteBatch getSpriteBatch() {
		return this._spriteBatch;
	}

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		horrorTitleFont = new BitmapFont(Gdx.files.internal("fonts/parchment.fnt"));

		// SpriteBatch: special class that is used to draw 2D images
		_spriteBatch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		// important
		super.render();
	}

	@Override
	public void dispose() {
		_spriteBatch.dispose();
		horrorTitleFont.dispose();
	}

	public void gotoMainMenuScreen() {
		MainMenuScreen mainMenuScreen = new MainMenuScreen(this);

		this.setScreen(mainMenuScreen);
	}

	public void gotoGameScreen() {
		GameScreen gameScreen = new GameScreen(this);

		this.setScreen(gameScreen);
	}
}
