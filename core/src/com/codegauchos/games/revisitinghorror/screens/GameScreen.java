package com.codegauchos.games.revisitinghorror.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class GameScreen implements Screen {
	private final RevisitingHorror _revisitingHorrorGame;
	private Sprite _backgroundSprite;
	private Texture _backgroundTexture;
	private OrthographicCamera _camera;
	private Rectangle _cato;
	private Texture _catoImage;

	public GameScreen(final RevisitingHorror game) {
		this._revisitingHorrorGame = game;

		_camera = new OrthographicCamera();
		// in pixels
		_camera.setToOrtho(false, RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		setImageObjects();

	}

	private void setImageObjects() {
		this._backgroundTexture = new Texture(Gdx.files.internal("images/Battle Scene.png"));
		this._backgroundSprite = new Sprite(this._backgroundTexture, 0, 0, RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		this._catoImage = new Texture(Gdx.files.internal("images/Cato sprite.png"));

		this._cato = new Rectangle();
		this._cato.x = RevisitingHorror.SCREEN_WIDTH / 2 - 64 / 2;
		this._cato.y = 20;
		this._cato.width = 64;
		this._cato.height = 64;

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		// clear (r, g, b, a) the screen with dark blue
		ScreenUtils.clear(0, 0, 0.2f, 1);
//
//		// update matrix
		this._camera.update();
//
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		this._revisitingHorrorGame.batch.setProjectionMatrix(_camera.combined);

		// begin a new batch and start drawing
		this._revisitingHorrorGame.batch.begin();

//		When blending is disabled, anything already on the screen at that location 
//		is replaced by the texture. This is more efficient, so blending should always
//		be disabled unless it is needed
		this._revisitingHorrorGame.batch.disableBlending();
		this._backgroundSprite.draw(this._revisitingHorrorGame.batch);
		this._revisitingHorrorGame.batch.enableBlending();

		this._revisitingHorrorGame.batch.draw(this._catoImage, this._cato.x, this._cato.y);
		this._revisitingHorrorGame.batch.end();

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
		this._catoImage.dispose();

	}

}
