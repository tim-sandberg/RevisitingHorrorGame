package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;

public class Weapon extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _weaponEvent;
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
	
	public Weapon(Texture texture, GameEventManager gameEventManager, String weaponName) {
		super(texture);

		this.initialize(texture, gameEventManager, weaponName);

	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract weaponEvent) {
		Gdx.app.log(this.getName(),
				String.format("In onEvent(), event: %s occurred.", weaponEvent.getGameEventType()));

		if (this.getGameEventType() == weaponEvent.getGameEventType()) {
			Gdx.app.log(this.getName(), String
					.format("In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));

			this._weaponEvent = weaponEvent;
			
			this.setVisible(true);
//			this.doCountDown(this._weaponEvent);
		}

	}
	
	// ************ METHODS ***********************
	private void initialize(Texture texture, GameEventManager gameEventManager, String weaponName) {
		this._gameEventManager = gameEventManager;
		
		this.setName(weaponName);
		
		this.addEventHandlers();
	}

	private void addEventHandlers() {
		Gdx.app.log(this.getName(), "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
