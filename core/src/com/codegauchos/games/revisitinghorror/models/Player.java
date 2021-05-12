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
	private boolean _leftMove;
	private boolean _rightMove;
	private Sprite _sprite;
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

	public void setDeltaX(float speed) {
		this._deltaX = speed;
	}

	public void setLeftMove(boolean isLeftMove) {
		if (this._rightMove && isLeftMove) {
			this._rightMove = false;
		}

		this._leftMove = isLeftMove;
	}

	public void setRightMove(boolean isRightMove) {
		if (this._leftMove && isRightMove) {
			this._leftMove = false;
		}

		this._rightMove = isRightMove;
	}

	// ****** END: members *************

	// ******* constructor ***********
	public Player(Texture texture, final String actorName) {
		// set player horizontal movement speed
		this.setDeltaX(100f);

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

		if (this._leftMove == true) {
			this.getSprite().translateX(-this.getDeltaX() * Gdx.graphics.getDeltaTime());		
		}

		if (this._rightMove == true) {
			this.getSprite().translateX(this.getDeltaX() * Gdx.graphics.getDeltaTime());
		}

		this.getSprite().draw(batch);
	}

	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());
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

		this.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keyCode) {
				Gdx.app.log("Player", "keyUp(), key up event occurred for " + actorName);

				if (_leftMove == true) {
					setLeftMove(false);
				}

				if (_rightMove == true) {
					setRightMove(false);
				}

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
	private void handleMovement(int keyCode) {
		// 'd'
		if (keyCode == Input.Keys.D || keyCode == Input.Keys.LEFT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the right");

			this.setRightMove(true);
		} else if (keyCode == Input.Keys.A || keyCode == Input.Keys.RIGHT) {
			Gdx.app.log("Player", "In HandleMovement(), move player to the left");

			this.setLeftMove(true);
		}

//		Gdx.app.log("Player", "Player position: " + this.getSprite().getX());
	}
}