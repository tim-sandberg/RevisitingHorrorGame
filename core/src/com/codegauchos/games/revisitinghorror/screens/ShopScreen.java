package com.codegauchos.games.revisitinghorror.screens;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class ShopScreen implements Screen {
	private final AssetManager _assetManager;
	private Image _dailyAwardButton;
	private JsonParser _jsonParser;
	private final RevisitingHorror _revisitingHorrorGame;
	private Stage _shopScreenStage;
	private FitViewport _viewport;

	/**
	 * CONSTRUCTORS
	 **/
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
		Gson gson = new Gson();

		try {
			Reader fileReader = Files.newBufferedReader(Paths.get("data/user_data.json"));

			gson.fromJson(fileReader, Map.class);

			fileReader.close();
		} catch (Exception ex) {
			Gdx.app.log("ShopScreen", "In initialize(), system ran into exception: " + ex.getMessage());
		}
	}

	private void loadActors() {
		Gdx.app.log("ShopScreen", "In loadActors(), ");

		Image shopScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.shopScreen));
		this._dailyAwardButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.dailyAwardButton));
		this._dailyAwardButton.setPosition(500, 500);
		this._dailyAwardButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				collectDailyAward();

				return true;
			}
		});

		Image goldTotal = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.goldTotal));
		goldTotal.setPosition(1000, 970);

		// **************************** this order matters!!
		// **************************************
		this._shopScreenStage.addActor(shopScene);
		this._shopScreenStage.addActor(_dailyAwardButton);
		this._shopScreenStage.addActor(goldTotal);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shopScreen);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.dailyAwardButton);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.goldTotal);

		this._assetManager.finishLoading();
	}

	private void collectDailyAward() {
		Gdx.app.log("ShopScreen", "In collectDailyAward()");

		// todo: add daily award amount to gold total
//		this.totalGold = this.totalGold + this._dailyAward;

		this._dailyAwardButton.setVisible(false);
	}

}
