package com.codegauchos.games.revisitinghorror.models;

public class Opponent extends CharacterBase {
	public Opponent(String characterImagePath, int width, int height, int startingX, int startingY) {
		this.setCharacter(characterImagePath, width, height, startingX, startingY);
	}
}
