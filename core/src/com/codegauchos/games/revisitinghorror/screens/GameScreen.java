package com.codegauchos.games.revisitinghorror.screens;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;
import com.codegauchos.games.revisitinghorror.events.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.GameEventStartIntro;
import com.codegauchos.games.revisitinghorror.models.Opponent;
import com.codegauchos.games.revisitinghorror.models.Player;

/**
 * Reference: https://github.com/libgdx/libgdx/wiki/Scene2d
 * 
 * @author tim
 *
 */
public class GameScreen implements Screen {
	// fields are here ( because they are private -- exclusively accessed
	// in this class!
	private final AssetManager _assetManager;
	private final GameEventManager _gameEventManager;
	private final RevisitingHorror _revisitingHorrorGame;
	private boolean _startCountDown;
	private BitmapFont _countDownFont;
	private Player _katniss;
	private Opponent _cato;
	/**
	 * The Stage class has a camera, SpriteBatch, and a root group and handles
	 * drawing the actors and distributing input events.
	 */
	private Stage _gameScreenStage;
	private Viewport _viewport;

	// ***** CONSTRUCTORS ********************
	public GameScreen(final RevisitingHorror revisitingHorrorGame) {
		this._startCountDown = true;

		this._assetManager = new AssetManager();

		this._gameEventManager = new GameEventManager();

		this._revisitingHorrorGame = revisitingHorrorGame;
	}
	// ***** CONSTRUCTORS ********************

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		// let the stage use the existing spriteBatch. object is very heavy
		// instantiate it one time!!
		this._gameScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());

		// this coordinates event handling
		Gdx.input.setInputProcessor(this._gameScreenStage);

		this.loadAssets();

		this.loadActors();
	}

	@Override
	public void render(float delta) {
//		Gdx.app.log("GameScreen", "In render(), updating screen");

		// clear (r, g, b, a) the screen with dark blue
//		ScreenUtils.clear(0, 0, 0.2f, 1);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);

		// start intro of game
		if (this._startCountDown == true) {
			this._startCountDown = false;

			this.startIntro(1);
		}

		// like update, any movement can be handled at this time
		// every actor's act() gets called
		this._gameScreenStage.act(Gdx.graphics.getDeltaTime());

		this._gameScreenStage.getBatch().begin();

		// Reference: https://www.codinginsights.blog/libgdx-assetmanager/
		Texture battleSceneBackground = (Texture) this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene);

//	When blending is disabled, anything already on the screen at that location 
//	is replaced by the texture. This is more efficient, so blending should always
//	be disabled unless it is needed
		this._gameScreenStage.getBatch().disableBlending();

//		this._gameScreenStage.getBatch().draw(battleSceneBackground, 0, 0, RevisitingHorror.SCREEN_WIDTH,
//				RevisitingHorror.SCREEN_HEIGHT);

		this._gameScreenStage.getBatch().enableBlending();

		this._gameScreenStage.getBatch().end();

		this._gameScreenStage.draw();
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
//		this._revisitingHorrorGame.batch.setProjectionMatrix(_camera.combined);
//
//		// bad guy walk up
//		if (this._cato.getSprite().getX() > 1600) {
//			float newPosition = this._cato.getSprite().getX() - 1;
//
//			this._cato.getSprite().setX(newPosition);
//		}
//
//		// begin a new batch and start drawing
//		this._revisitingHorrorGame.batch.begin();
//
////		When blending is disabled, anything already on the screen at that location 
////		is replaced by the texture. This is more efficient, so blending should always
////		be disabled unless it is needed
//		this._revisitingHorrorGame.batch.disableBlending();
//		this._backgroundSprite.draw(this._revisitingHorrorGame.batch);
//		this._revisitingHorrorGame.batch.enableBlending();
//
//		this._revisitingHorrorGame.batch.draw(this._cato.getCharacterImage(), this._cato.getHitBox().x,
//				this._cato.getHitBox().y);
//		this._revisitingHorrorGame.batch.draw(this._player.getCharacterImage(), this._player.getHitBox().x,
//				this._player.getHitBox().y);
//
//		// start count down
//		if (this._cato.getHitBox().x <= 1600) {
//			this._countDownFont.draw(this._revisitingHorrorGame.batch, "3", 900, 650);
//		}
//
//		this._revisitingHorrorGame.batch.end();

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
		this.dispose();
	}

	@Override
	public void dispose() {
		this._gameScreenStage.dispose();
	}

	private void startIntro(int level) {
		Gdx.app.log("GameScreen", "In startIntro(), starting intro to battle");

		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("START_INTRO");

		GameEventStartIntro gameEventStartIntro = new GameEventStartIntro(gameEventTypeIndex);

		gameEventStartIntro.Level = 1;
		gameEventStartIntro.setStage(this._gameScreenStage);
		gameEventStartIntro.setListenerActor(_katniss);

		this._gameEventManager.broadcastEvent(gameEventStartIntro);

	}

	public void startBattle() {
		// TODO: broadcast from here

	}

	/* 
	* 
	*/
	private void loadActors() {
		Gdx.app.log("GameScreen", "In loadActors(), ");

		Image battleScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene));

		_katniss = new Player(this._assetManager.get(RevisitingHorrorAssetDescriptor.player),
				RevisitingHorrorAssetDescriptor.player.fileName, this._gameEventManager);
		_katniss.spritePosition(100, 200);
//		
		this._cato = new Opponent(this._assetManager.get(RevisitingHorrorAssetDescriptor.opponent),
				RevisitingHorrorAssetDescriptor.opponent.fileName, this._gameEventManager);
		this._cato.spritePosition(500, 200);

		// add tags for easier referencing
		battleScene.setName("battleScene");
		_katniss.setName("katniss");
		this._cato.setName("cato");

		// this order matters
		// z-order (who gets drawn first. based on who is added before the other
		this._gameScreenStage.addActor(battleScene);
		this._gameScreenStage.addActor(this._katniss);
		this._gameScreenStage.addActor(this._cato);

		// !! this is crucial for scene2D to know player event handling
		// needs to be aware
		this._gameScreenStage.setKeyboardFocus(this._katniss);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.player);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.opponent);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.battleScene);

		this._assetManager.finishLoading();
	}
}
