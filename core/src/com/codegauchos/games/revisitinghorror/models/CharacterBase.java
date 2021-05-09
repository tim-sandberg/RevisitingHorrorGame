package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;

public class CharacterBase extends Actor{/*
	 * this is a field!
	 */
	private String _name;
	private Texture _characterImage;
	private float _health;
	private Rectangle _hitBox;


	public float getHealth() {
		return _health;
	}


	public void setHealth(float _health) {
		this._health = _health;
	}

	
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


	public Texture getCharacterImage() {
		return _characterImage;
	}


	public void setCharacterImage(Texture characterImage) {
		this._characterImage = characterImage;
	}


	public Rectangle getHitBox() {
		return _hitBox;
	}


	public void setHitBox(Rectangle hitBox) {
		this._hitBox = hitBox;
	}

	// **********          METHODS *****************************
	public void setCharacter(String characterImagePath, int width, int height, int startingX, int startingY) {
		this.setCharacterImage(new Texture(Gdx.files.internal(characterImagePath)));

		Rectangle character = new Rectangle();
		character.x = startingX;//RevisitingHorror.SCREEN_WIDTH / 2 - width / 2;
		character.y = startingY;
		character.width = width;
		character.height = height;
		
		this.setHitBox(character);
		
	}

}
