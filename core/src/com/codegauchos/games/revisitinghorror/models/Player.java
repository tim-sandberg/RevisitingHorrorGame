package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class Player extends CharacterBase {
	// ****** fields *************
	private String _hairColor;
	private int _agility;
	private int _defense;
	private int _stamina;

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
	// ****** END: members *************

	// ******* constructor ***********
	public Player(Texture texture, final String actorName) {
//		this.setCharacter(characterImagePath, width, height, startingX, startingY);
		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getHealth());

		this.setTouchable(Touchable.enabled);

		this.addEventHandlers(actorName);
	}

	// ************ METHODS ***********************
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
