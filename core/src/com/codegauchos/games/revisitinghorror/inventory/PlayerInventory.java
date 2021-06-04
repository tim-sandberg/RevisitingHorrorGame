package com.codegauchos.games.revisitinghorror.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.game.GameEventPlayerInventory;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class PlayerInventory extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _playerInventoryEvent;

	public String getGameEventType() {
		return "PLAYER_INVENTORY";
	}

	public PlayerInventory(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();

	}

	/************ EVENT HANDLERS **************/
	@Override
	public void onEvent(GameEventAbstract playerInventoryEvent) {
		Gdx.app.log("PlayerInventory",
				String.format("In onEvent(), event: %s occurred.", playerInventoryEvent.getGameEventType()));

		if (this.getGameEventType() == playerInventoryEvent.getGameEventType()) {
			Gdx.app.log("PlayerInventory", String
					.format("In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));

			this._playerInventoryEvent = playerInventoryEvent;
			
			this.setVisible(true);
		}

	}

	/************ EVENT HANDLERS **************/

	private void addEventHandlers() {
		Gdx.app.log("PlayerInventory", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;

	}

}
