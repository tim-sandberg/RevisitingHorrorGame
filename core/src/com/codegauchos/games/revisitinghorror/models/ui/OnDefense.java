package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.game.GameEventPlayerInventory;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class OnDefense extends ImageBase {
	private GameEventManager _gameEventManager;
	private float _visibilityCounter = 0.0f;

	public String getGameEventType() {

		return "ON_DEFENSE";
	}

	public OnDefense(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	@Override
	public void act(float delta) {
		if (this.isVisible() == true) {
			this._visibilityCounter += delta;

			Gdx.app.log("OnDefense", "In act(), visibility counter: " + this._visibilityCounter);

			if (this._visibilityCounter > 10) {
				this.setVisible(false);

				this.handle(null);
			}
		}

	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract onDefenseEvent) {
		Gdx.app.log("OnDefense", String.format("In onEvent(), event: %s occurred.", onDefenseEvent.getGameEventType()));

		if (this.getGameEventType() == onDefenseEvent.getGameEventType()) {
			Gdx.app.log("OnDefense", String.format("In onEvent(), event: %s occurred. will make image visible.",
					this.getGameEventType()));
			this.setVisible(true);
		}
	}

	@Override
	public boolean handle(Event event) {
		GameEventPlayerInventory gameEventPlayerInventory = new GameEventPlayerInventory("PLAYER_INVENTORY", false);
		gameEventPlayerInventory.Level = 1;
		
		this._gameEventManager.broadcastEvent(gameEventPlayerInventory);

		return true;
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;

		ImageBase.GAME_EVENT_TYPE = "ON_DEFENSE";
	}

	private void addEventHandlers() {
		Gdx.app.log("OnDefense", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
