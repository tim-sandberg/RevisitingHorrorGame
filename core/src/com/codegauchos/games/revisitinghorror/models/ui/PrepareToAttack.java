package com.codegauchos.games.revisitinghorror.models.ui;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codegauchos.games.revisitinghorror.events.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.GameEventManager;

public class PrepareToAttack extends Image implements GameEventListener {
	private GameEventManager _gameEventManager;
	private String _gameEventType;
	private GameEventAbstract _prepareToAttackEvent;

	@Override
	public String getGameEventType() {

		return this._gameEventType;
	}

	@Override
	public void setGameEventType(int gameEventTypeIndex) {
		this._gameEventType = GameEventManager.GameEventTypes[gameEventTypeIndex];
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

		this._prepareToAttackEvent = prepareToAttackEvent;

		if (this._prepareToAttackEvent.getGameEventType() == "PREPARE_TO_ATTACK") {
			Gdx.app.log("PrepareToAttack",
					String.format("In onEvent(), event: %s occurred. doCountDown() will execute now.",
							this._prepareToAttackEvent.getGameEventType()));
			this.setVisible(true);
//			this.doCountDown(countDownEvent);
		}
	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("PREPARE_TO_ATTACK");
		this.setGameEventType(gameEventTypeIndex);

		this._gameEventManager = gameEventManager;
	}

	private void addEventHandlers() {
		Gdx.app.log("PrepareToAttack", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addEventListener(this);
	}
}
