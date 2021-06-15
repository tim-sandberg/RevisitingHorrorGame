package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.Asset;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;

public class MainMenuScreen implements Screen {
	// fields
	private final AssetManager _assetManager;
	private Button _gameButton;
	private Stage _mainMenuStage;
	private final RevisitingHorror _revisitingHorrorGame;
	private Button _shopButton;
	private Viewport _viewport;

	// constructor for MainMenuScreen
	public MainMenuScreen(RevisitingHorror game) {
		this._revisitingHorrorGame = game;

		this._assetManager = new AssetManager();

		this.initialize();
	}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		// let the stage use the existing spriteBatch. object is very heavy
		// instantiate it one time!!
		this._mainMenuStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());

		Gdx.input.setInputProcessor(this._mainMenuStage);

		this.loadAssets();

		this.loadActors();

		this._mainMenuStage.addActor(this._gameButton);
	}

	@Override
	public void render(float delta) {
		// clear the screen and put a blue background on it
		ScreenUtils.clear(0.25f, 0.19f, 1.0f, 1);

		this._mainMenuStage.act(Gdx.graphics.getDeltaTime());

		this._mainMenuStage.getBatch().begin();

		this._revisitingHorrorGame.horrorTitleFont.draw(this._mainMenuStage.getBatch(), "Revisiting Horror!!", 500,
				650);
		this._revisitingHorrorGame.horrorTitleFont.draw(this._mainMenuStage.getBatch(), "(tap anywhere to start)", 100,
				100);
		this._mainMenuStage.getBatch().end();

		this._mainMenuStage.draw();

		// if you want to touch the screen
//		if (Gdx.input.isTouched() == true) {
//			this._revisitingHorrorGame.gotoGameScreen();
//
//			this.dispose();
//		}
	}

	@Override
	public void resize(int width, int height) {
		this._viewport.update(width, height, true);

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
		this._mainMenuStage.dispose();

	}

	/**
	 * Reference: input listener/screen transition
	 * https://www.youtube.com/watch?v=Z89v6l-SMVM&t=273s skins:
	 * https://github.com/czyzby/gdx-skins
	 */
	private void initialize() {
		Skin startButtonSkin = new Skin(Gdx.files.internal(Asset.COMIC_UI_SKIN));

		this._gameButton = new TextButton("Start Game", startButtonSkin, "default");

		_gameButton.setSize(230, 48);
		_gameButton.setPosition(400, 200);
		_gameButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_revisitingHorrorGame.gotoGameScreen();

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
			}

		});

//		this._shopButton = new TextButton("Shop", new TextButton.TextButtonStyle());
//
//		this._shopButton.setSize(230, 48);
//		this._shopButton.setPosition(700, 200);
	}

	private void loadActors() {
		Gdx.app.log("MainMenuScreen", "In loadActors(), ");

		Image shopButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.shopButton));
		shopButton.setPosition(700, 200);
		shopButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_revisitingHorrorGame.gotoShopScreen();

				return true;
			}
		});

		this._mainMenuStage.addActor(shopButton);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shopButton);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shopScreen);

		this._assetManager.finishLoading();
	}
}
