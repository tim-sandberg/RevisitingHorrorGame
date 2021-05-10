package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Opponent extends CharacterBase {
	private int _agro;

	public int getAgro() {
		return _agro;
	}

	public void setAgro(int agro) {
		this._agro = agro;
	}

	public Opponent(Texture texture, final String actorName) {
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
