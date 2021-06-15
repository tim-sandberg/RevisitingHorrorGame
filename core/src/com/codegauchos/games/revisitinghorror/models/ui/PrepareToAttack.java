package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.game.GameEventPlayerInventory;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class PrepareToAttack extends ImageBase {
	private GameEventManager _gameEventManager;
	private float _visibilityCounter = 0.0f;

	public String getGameEventType() {

		return "PREPARE_TO_ATTACK";
	}

	/**
	 * CONSTRUCTOR
	 * 
	 * @param texture
	 * @param gameEventManager
	 */
	public PrepareToAttack(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	@Override
	public void act(float delta) {
		if (this.isVisible() == true) {
			this._visibilityCounter += delta;

			Gdx.app.log("PrepareToAttack", "In act(), visibility counter: " + this._visibilityCounter);

			if (this._visibilityCounter > 10) {
				this.setVisible(false);

				this.handle(null);
			}
		}
	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract prepareToAttackEvent) {
		Gdx.app.log("PrepareToAttack",
				String.format("In onEvent(), event: %s occurred.", prepareToAttackEvent.getGameEventType()));

		if (this.getGameEventType() == prepareToAttackEvent.getGameEventType()) {
			Gdx.app.log("PrepareToAttack", String.format(
					"In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));
			this.setVisible(true);
		}
	}

	/**
	 * show the player inventory to setup the initial attack
	 */
	@Override
	public boolean handle(Event event) {
		GameEventPlayerInventory gameEventPlayerInventory = new GameEventPlayerInventory("PLAYER_INVENTORY", true);
		gameEventPlayerInventory.Level = 1;

		this._gameEventManager.broadcastEvent(gameEventPlayerInventory);

		return true;
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
	}

	private void addEventHandlers() {
		Gdx.app.log("PrepareToAttack", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
