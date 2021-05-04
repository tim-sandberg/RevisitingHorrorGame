package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class Player extends CharacterBase {
	private String _keyAbility;
	private String _hairColor;
	private int _agility;
	private int _defense;
	private int _agro;
	private int _stamina;

	public Player(String characterImagePath, int width, int height, int startingX, int startingY) {
		this.setCharacter(characterImagePath, width, height, startingX, startingY);

	}

	public String getKeyAbility() {
		return this._keyAbility;
	}

	public void setKeyAbility(String value) {
		this._keyAbility = value;
	}

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

	public int getAgro() {
		return _agro;
	}

	public void setAgro(int agro) {
		this._agro = agro;
	}

	public int getStamina() {
		return _stamina;
	}

	public void setStamina(int stamina) {
		this._stamina = stamina;
	}
}
