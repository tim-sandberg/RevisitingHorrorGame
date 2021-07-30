package com.codegauchos.games.revisitinghorror.screens;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;
import com.codegauchos.games.revisitinghorror.inventory.PlayerInventory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ShopScreen implements Screen {
	private final AssetManager _assetManager;
	private Gson _gson;
	private Image _dailyAwardButton;
	private Label _lblTotalGold;
	private PlayerInventory _playerInventory;
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
		Gdx.app.log("ShopScreen", "In show(), ");

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

		// updates the label to show the current total gold, after the daily award has been collected
		if (this._lblTotalGold.textEquals(String.valueOf(this._playerInventory.getTotalGold())) == false) {
			this._lblTotalGold.setText(this._playerInventory.getTotalGold());
		}

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
		Gdx.app.log("ShopScreen", "In initialize(), ");

		this._gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("../data/user_data.json"));

			if (bufferedReader != null) {
				this._playerInventory = this._gson.fromJson(bufferedReader, PlayerInventory.class);

				Label.LabelStyle ancientYellowStyle = new Label.LabelStyle();
				ancientYellowStyle.font = this._revisitingHorrorGame.AncientYellowFont;
//				ancientYellowStyle.fontColor = Color.RED;

				this._lblTotalGold = new Label(String.valueOf(this._playerInventory.getTotalGold()),
						ancientYellowStyle);
				this._lblTotalGold.setPosition(1110, 980);
				this._lblTotalGold.setAlignment(Align.right);
				this._lblTotalGold.setSize(28, 28);

				Gdx.app.log("ShopScreen",
						"In initialize(), userData totalGold: " + this._playerInventory.getTotalGold());
			} else {
				Gdx.app.error("ShopScreen", "In initialize(), system ran into a problem.");
			}

			bufferedReader.close();
		} catch (Exception ex) {
			Gdx.app.error("ShopScreen", "In initialize(), system ran into exception: " + ex.getMessage());
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

		Image backButton = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.backButton));
		backButton.setPosition(0, 970);
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
				_revisitingHorrorGame.gotoMainMenuScreen();

				return true;
			}
		});
		
		Image totalGoldBackground = new Image(
				this._assetManager.get(RevisitingHorrorAssetDescriptor.goldTotalBackground));
		totalGoldBackground.setPosition(1000, 970);

		// **************** this order matters (z-index) !! ***********************
		this._shopScreenStage.addActor(shopScene);
		this._shopScreenStage.addActor(_dailyAwardButton);
		this._shopScreenStage.addActor(totalGoldBackground);
		this._shopScreenStage.addActor(this._lblTotalGold);
		this._shopScreenStage.addActor(backButton);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shopScreen);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.dailyAwardButton);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.goldTotalBackground);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.backButton);
		
		this._assetManager.finishLoading();
	}

	private void collectDailyAward() {
		Gdx.app.log("ShopScreen", "In collectDailyAward()");

		this._dailyAwardButton.setVisible(false);

		// DONE: add daily award amount to gold total
		int dailyAward = 10;

		int totalGold = this._playerInventory.getTotalGold() + dailyAward;

		this._playerInventory.setTotalGold(totalGold);

		Gdx.app.log("ShopScreen",
				"In collectDailyAward(), playerInventory - totalGold: " + this._playerInventory.getTotalGold());

//		LET's save the total gold to the player's file
		try {
			Writer userDataWriter = Files.newBufferedWriter(Paths.get("../data/user_data.json"));

			if (userDataWriter != null) {
				this._gson.toJson(this._playerInventory, userDataWriter);

				Gdx.app.log("ShopScreen",
						"In collectDailyAward(), finished updating JSON file for userData totalGold: " + this._playerInventory.getTotalGold());
			} else {
				Gdx.app.error("ShopScreen", "In collectDailyAward(), could not find  user Data.");
			}

			userDataWriter.close();
		} catch (Exception ex) {
			Gdx.app.log("ShopScreen", "In initialize(), system ran into exception: " + ex.getMessage());
		}
	}

}
