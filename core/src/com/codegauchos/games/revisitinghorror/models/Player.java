package com.codegauchos.games.revisitinghorror.models;

public class Player {
	/*
	 * this is a field!
	 */
	private String _name;
	private String _keyAbility;
	private String _hairColor;
	
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
}
