package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class PrepareToAttack extends ImageBase {
	private GameEventManager _gameEventManager;

	public String getGameEventType() {

		return "PREPARE_TO_ATTACK";
	}

	public PrepareToAttack(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	@Override
	public void act(float delta) {

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

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "PREPARE_TO_ATTACK";
	}

	private void addEventHandlers() {
		Gdx.app.log("PrepareToAttack", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
