package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;

public class ShopScreen implements Screen {
	private final AssetManager _assetManager;
	private final RevisitingHorror _revisitingHorrorGame;
	private Stage _shopScreenStage;
	private FitViewport _viewport;

	public ShopScreen(final RevisitingHorror revisitingHorrorGame) {
		this._assetManager = new AssetManager();

		this._revisitingHorrorGame = revisitingHorrorGame;

		this.initialize();
	}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		this._shopScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());

		Gdx.input.setInputProcessor(this._shopScreenStage);

		this.loadAssets();

		this.loadActors();
	}

	@Override
	public void render(float delta) {
		// clear the screen and put a blue background on it
		ScreenUtils.clear(0.6f, 0.4f, 0.0f, 1);
		
		this._shopScreenStage.act(Gdx.graphics.getDeltaTime());
		
		this._shopScreenStage.draw();
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

	private void initialize() {
		
	}

	private void loadActors() {
		Gdx.app.log("ShopScreen", "In loadActors(), ");

		Image dailyAwardButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.dailyAwardButton));
		dailyAwardButton.setPosition(500, 500);
		dailyAwardButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				_revisitingHorrorGame.gotoShopScreen();

				return true;
			}
		});

		this._shopScreenStage.addActor(dailyAwardButton);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.dailyAwardButton);

		this._assetManager.finishLoading();
	}
}
