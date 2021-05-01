package com.codegauchos.games.revisitinghorror.models;

public class Player {
	/*
	 * this is a field!
	 */
	private String _name;
	private String _keyAbility;
	private String _hairColor;
	private float _health;
	private int _agility;
	private int _defense;
	private int _agro;
	private int _stamina;
	
	/**
	 * getter!
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


	public float getHealth() {
		return _health;
	}


	public void setHealth(float _health) {
		this._health = _health;
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
