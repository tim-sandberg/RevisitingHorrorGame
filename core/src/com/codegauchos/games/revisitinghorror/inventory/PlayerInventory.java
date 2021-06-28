package com.codegauchos.games.revisitinghorror.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventBattle;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.game.GameEventPlayerInventory;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class PlayerInventory extends ImageBase {
	private float _timer = 0f;
	private int _totalGold;
	private GameEventManager _gameEventManager;
	private GameEventPlayerInventory _playerInventoryEvent;

	public String getGameEventType() {
		return "PLAYER_INVENTORY";
	}
	
	public int getTotalGold() {
		return _totalGold;
	}

	public void setTotalGold(int totalGold) {
		_totalGold = totalGold;
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
			Gdx.app.log("PlayerInventory", String.format(
					"In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));

			this._playerInventoryEvent = (GameEventPlayerInventory) playerInventoryEvent;

			this.setVisible(true);
		}

	}

	private void addEventHandlers() {
		Gdx.app.log("PlayerInventory", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}

	@Override
	public boolean handle(Event event) {
		GameEventBattle gameEventBattle = new GameEventBattle("START_BATTLE", this._playerInventoryEvent.getIsAttack());

		this._gameEventManager.broadcastEvent(gameEventBattle);

		return true;
	}

	/************ END: EVENT HANDLERS **************/

	// ************ METHODS ***********************

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;

	}

	@Override
	public void act(float delta) {
		this._timer += delta;

		Gdx.app.log("PlayerInventory", "In act(), visibility timer: " + this._timer);

		if (this._timer > 35) {
			this.setVisible(false);

			if (this._playerInventoryEvent != null) {
				this.handle(this._playerInventoryEvent);
			}
		}
	}
}
