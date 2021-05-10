package com.codegauchos.games.revisitinghorror;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public SpriteBatch batch;
	public BitmapFont horrorTitleFont;

	// COMMENT: constants
	public static final int SCREEN_WIDTH = 1920;
	public static final int SCREEN_HEIGHT = 1080;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		horrorTitleFont = new BitmapFont(Gdx.files.internal("fonts/parchment.fnt"));

		// SpriteBatch: special class that is used to draw 2D images
		batch = new SpriteBatch();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		// important
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		horrorTitleFont.dispose();
	}
}
