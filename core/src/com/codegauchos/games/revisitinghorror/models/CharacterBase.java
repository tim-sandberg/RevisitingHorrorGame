package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

/**
 * Reference:
 * The Actor class is a node in the graph which has a position, rectangular size, 
 * origin, scale, rotation, and color
 * 
 * @author tim
 *
 */
abstract class CharacterBase extends Actor {
	/*
	 * this is a field!
	 */
	private String _name;
	private Texture _characterImage;
	private float _health;
//	private Rectangle _hitBox;
	private Sprite _sprite;

	public float getHealth() {
		return _health;
	}

	public void setHealth(float _health) {
		this._health = _health;
	}

	/**
	 * getter!
	 * 
	 * @return
	 */
	public String getName() {
		return _name;
	}

	/*
	 * this is a setter!
	 * 
	 */
	public void setName(String value) {
		this._name = value;
	}

	public Texture getCharacterImage() {
		return _characterImage;
	}

	public void setCharacterImage(Texture characterImage) {
		this._characterImage = characterImage;
	}

//	public Rectangle getHitBox() {
//		return _hitBox;
//	}
//
//	public void setHitBox(Rectangle hitBox) {
//		this._hitBox = hitBox;
//	}

	public Sprite getSprite() {
		return _sprite;
	}

	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}

	// ********** CONSTRUCTORs *****************************
	/**
	 * need a default empty constructor
	 */
	public CharacterBase() {

	}

	public CharacterBase(Texture texture, final String actorName) {
		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), _health);

		this.setTouchable(Touchable.enabled);
		
		this.addEventHandlers(actorName);
	}
	// ********** END: CONSTRUCTORs *****************************

	// ********** METHODS *****************************
	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());
	}

	/**
	 * updates the actor by time (each frame)
	 */
	@Override
	public void act(float delta) {
		super.act(delta);
	}

	/**
	 * draw this actor
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.getSprite().draw(batch);
	}

	public void setCharacter(String characterImagePath, int width, int height, int startingX, int startingY) {
		this.setCharacterImage(new Texture(Gdx.files.internal(characterImagePath)));

		Rectangle character = new Rectangle();
		character.x = startingX;// RevisitingHorror.SCREEN_WIDTH / 2 - width / 2;
		character.y = startingY;
		character.width = width;
		character.height = height;

//		this.setHitBox(character);

	}

	private void addEventHandlers(String actorName) {
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("Touch down asset with name: ", actorName);

				return true;
			}
		});
	}

}
