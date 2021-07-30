package com.codegauchos.games.revisitinghorror;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.codegauchos.games.revisitinghorror.assetmanager.Asset;
import com.codegauchos.games.revisitinghorror.screens.GameScreen;
import com.codegauchos.games.revisitinghorror.screens.MainMenuScreen;
import com.codegauchos.games.revisitinghorror.screens.ShopScreen;

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
	public AssetManager assetManager;
	private SpriteBatch _spriteBatch;
	public BitmapFont horrorTitleFont;
	public BitmapFont AncientYellowFont;
	public TextureAtlas SpriteSheetAtlas;
	public Skin SpriteSheetSkin;

	// COMMENT: constants
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 1024;

	// Members (getters and setters) here
	public SpriteBatch getSpriteBatch() {
		return this._spriteBatch;
	}

	public RevisitingHorror() {
		this.assetManager = new AssetManager();
	}

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		this.SpriteSheetAtlas = new TextureAtlas(Gdx.files.internal(Asset.SPRITE_SHEET_ATLAS));
//		TODO: implement skins
//		this.SpriteSheetSkin = new Skin(Gdx.files.internal(Asset.SPRITE_SHEET_SKIN));
		
		// 1. Create a BitmapFontParameter, pointing to your atlas
		BitmapFontLoader.BitmapFontParameter fontParameter = new BitmapFontLoader.BitmapFontParameter();
		fontParameter.atlasName = "skins/revisiting_horror_skin/sprite_sheet";

		// 2. Pass that parameter as the third argument to "load"
		this.assetManager.load("fonts/parchment.fnt", BitmapFont.class, fontParameter);
		this.assetManager.load("fonts/ancient_yellow.fnt", BitmapFont.class, fontParameter);

		horrorTitleFont = new BitmapFont(Gdx.files.internal("fonts/parchment.fnt"));
		AncientYellowFont = new BitmapFont(Gdx.files.internal("fonts/ancient_yellow.fnt"));

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
		this.AncientYellowFont.dispose();
	}

	public void gotoMainMenuScreen() {
		MainMenuScreen mainMenuScreen = new MainMenuScreen(this);

		this.setScreen(mainMenuScreen);
	}

	public void gotoGameScreen() {
		GameScreen gameScreen = new GameScreen(this);

		this.setScreen(gameScreen);
	}

	public void gotoShopScreen() {
		ShopScreen shopScreen = new ShopScreen(this);

		this.setScreen(shopScreen);
	}
}
