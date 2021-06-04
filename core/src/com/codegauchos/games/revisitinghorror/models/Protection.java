package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;

/**
 * for shields, armor
 * 
 * @author tim
 *
 */
public class Protection extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _protectiveEvent;
	private float _strength;
	private int _level;

	public float getStrength() {
		return this._strength;
	}

	public void setStrength(float value) {
		this._strength = value;
	}

	public int getLevel() {
		return this._level;
	}

	public void setLevel(int value) {
		this._level = value;
	}

	public String getGameEventType() {
		return "PLAYER_INVENTORY";
	}
	
	public Protection(Texture texture, GameEventManager gameEventManager, String protectionName) {
		super(texture);

		this.initialize(texture, gameEventManager, protectionName);

	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract protectiveEvent) {
		Gdx.app.log(this.getName(),
				String.format("In onEvent(), event: %s occurred.", protectiveEvent.getGameEventType()));

		if (this.getGameEventType() == protectiveEvent.getGameEventType()) {
			Gdx.app.log(this.getName(), String
					.format("In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));

			this._protectiveEvent = protectiveEvent;
			
			this.setVisible(true);
//			this.doCountDown(this._weaponEvent);
		}

	}
	
	// ************ METHODS ***********************
	private void initialize(Texture texture, GameEventManager gameEventManager, String protectionName) {
		this._gameEventManager = gameEventManager;
		
		this.setName(protectionName);
		
		this.addEventHandlers();
	}

	private void addEventHandlers() {
		Gdx.app.log(this.getName(), "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
