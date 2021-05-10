package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;
import com.codegauchos.games.revisitinghorror.models.Opponent;
import com.codegauchos.games.revisitinghorror.models.Player;

/**
 * Reference: https://github.com/libgdx/libgdx/wiki/Scene2d
 * 
 * @author tim
 *
 */
public class GameScreen implements Screen {
	private final AssetManager _assetManager;
	private boolean _startCountDown;
	private final RevisitingHorror _revisitingHorrorGame;
//	private Sprite _backgroundSprite;
	private Texture _backgroundTexture;
	private OrthographicCamera _camera;
	private BitmapFont _countDownFont;
//	private Player _player;
//	private Opponent _cato;
	/**
	 * The Stage class has a camera, SpriteBatch, and a root group and handles
	 * drawing the actors and distributing input events.
	 */
	private Stage _stage;
	private Viewport _viewport;

	public GameScreen(final RevisitingHorror revisitingHorrorGame) {
		this._startCountDown = false;

		this._assetManager = new AssetManager();

		this._revisitingHorrorGame = revisitingHorrorGame;

		_camera = new OrthographicCamera();
		// in pixels
		_camera.setToOrtho(false, RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		setImageObjects();

	}

	private void setImageObjects() {
		this._backgroundTexture = new Texture(Gdx.files.internal("images/Battle Scene.png"));
//		this._backgroundSprite = new Sprite(this._backgroundTexture, 0, 0, RevisitingHorror.SCREEN_WIDTH,
//				RevisitingHorror.SCREEN_HEIGHT);

		this._countDownFont = new BitmapFont(Gdx.files.internal("fonts/parchment.fnt"));
//		this._cato = new Opponent("images/Cato sprite.png", 64, 64, 2000, 200);
//		this._player = new Player("images/Katniss sprite.png", 64, 64, 100, 200);
	}

	@Override
	public void show() {
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		this._stage = new Stage(this._viewport);

		Gdx.input.setInputProcessor(this._stage);

		this.loadAssets();

		loadActors();
	}

	private void loadActors() {
		Player katniss = new Player(this._assetManager.get(RevisitingHorrorAssetDescriptor.player),
				RevisitingHorrorAssetDescriptor.player.fileName);
		katniss.spritePosition(100, 200);

		Opponent cato = new Opponent(this._assetManager.get(RevisitingHorrorAssetDescriptor.opponent),
				RevisitingHorrorAssetDescriptor.opponent.fileName);
		cato.spritePosition(2000, 200);

		this._stage.addActor(katniss);
		this._stage.addActor(cato);
	}

	@Override
	public void render(float delta) {

		// clear (r, g, b, a) the screen with dark blue
//		ScreenUtils.clear(0, 0, 0.2f, 1);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);

		this._stage.act(Gdx.graphics.getDeltaTime());

		this._stage.getBatch().begin();

		this._stage.getBatch().draw(this._backgroundTexture, 0, 0, RevisitingHorror.SCREEN_WIDTH,
				RevisitingHorror.SCREEN_HEIGHT);

		this._stage.getBatch().end();

		this._stage.draw();
		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
//		this._revisitingHorrorGame.batch.setProjectionMatrix(_camera.combined);
//
//		// bad guy walk up
//		if (this._cato.getHitBox().x > 1600) {
//			this._cato.getHitBox().x -= 1;
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
//		this._cato.getCharacterImage().dispose();
		this._stage.dispose();
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.player);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.opponent);

		this._assetManager.finishLoading();
	}
}
