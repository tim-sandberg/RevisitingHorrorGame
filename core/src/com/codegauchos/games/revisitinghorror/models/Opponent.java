package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Opponent extends Actor {
	// ****** fields *************
	private int _agro;
	private Sprite _sprite;

	// ****** members *************
	public int getAgro() {
		return _agro;
	}

	public void setAgro(int agro) {
		this._agro = agro;
	}

	public Sprite getSprite() {
		return _sprite;
	}

	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}
	// ****** END: members *************

	// ******************** CONSTRUCTORS ***********
	public Opponent(Texture texture, final String actorName) {
//		this.setCharacter(characterImagePath, width, height, startingX, startingY);
		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());

		this.setTouchable(Touchable.enabled);

		this.addEventHandlers(actorName);

		MoveByAction pacing = new MoveByAction();
		pacing.setAmount(-300f, 0f);
		pacing.setDuration(60000000f);
		Opponent.this.addAction(pacing);
	}

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		super.act(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.getSprite().draw(batch);
	}
	
	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());
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
