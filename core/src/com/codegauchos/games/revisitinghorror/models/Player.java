package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Player extends Actor {
	// ****** fields *************
	private String _hairColor;
	private int _agility;
	private int _defense;
	private Sprite _sprite;
	private boolean _movingRight = false;
	private int _stamina;
	private float _deltaX;

	// ****** members *************
	public String getHairColor() {
		return this._hairColor;
	}

	public void setHairColor(String value) {
		this._hairColor = value;
	}

	public int getDefense() {
		return _defense;
	}

	public void setDefense(int defense) {
		this._defense = defense;
	}

	public int getAgility() {
		return _agility;
	}

	public void setAgility(int agility) {
		this._agility = agility;
	}

	public int getStamina() {
		return _stamina;
	}

	public void setStamina(int stamina) {
		this._stamina = stamina;
	}

	public Sprite getSprite() {
		return _sprite;
	}

	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}

	public float getDeltaX() {
		return _deltaX;
	}

	public void setDeltaX(int speed) {
		this._deltaX = speed;
	}

	// ****** END: members *************

	// ******* constructor ***********
	public Player(Texture texture, final String actorName) {
		// set player horizontal movement speed
		this.setDeltaX(1);

		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());

		this.setTouchable(Touchable.enabled);

		this.addEventHandlers(actorName);
	}

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		super.act(Gdx.graphics.getDeltaTime());
	}

	/**
	 * Reference:
	 * https://github.com/elefher/LibGDXExamples/blob/6b982317ea9eb9687016cd7e49e69a81510352b7/core/src/com/codinginsights/libgdxexamples/Scene2D/MyActor.java#L35
	 * 
	 * @param batch
	 * @param parentAlpha
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
//		Gdx.app.log("Player", "In draw(), updating player image");

		if (this._movingRight == true) {
			this.getSprite().translateX(this.getDeltaX());
		}

		this.getSprite().draw(batch);
	}

	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(), this.getSprite().getHeight());
	}

	private void addEventHandlers(String actorName) {
		Gdx.app.log("Player", "addEventHandlers(), registering event handlers.");

		this.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keyCode) {
				Gdx.app.log("Player", "keyDown(), key down event occurred for " + actorName);

				handleMovement(keyCode);

				return true;
			}
		});
	}

	/**
	 * Reference:
	 * https://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_Z
	 * 
	 * @param keyCode
	 */
	public void handleMovement(int keyCode) {
		// 'd'
		if (keyCode == Input.Keys.D || keyCode == Input.Keys.LEFT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the right");

			this.getSprite().translateX(this.getDeltaX());
		} else if (keyCode == Input.Keys.A || keyCode == Input.Keys.RIGHT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the left");

			this.getSprite().translateX(-this.getDeltaX());
		}
		
//		Gdx.app.log("Player", "Player position: " + this.getSprite().getX());
	}
}