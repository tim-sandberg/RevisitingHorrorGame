package com.codegauchos.games.revisitinghorror.events.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventBattle;
import com.codegauchos.games.revisitinghorror.events.game.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;

public class BattleEventListener implements GameEventListener {
	private GameEventManager _gameEventManager;
	
	public static String GAME_EVENT_TYPE = "START_BATTLE";

	public String getGameEventType() {

		return "START_BATTLE";
	}

	/************ Constructors **************/
	public BattleEventListener(GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
		this.addEventHandlers();
	}
	/************ EVENT HANDLERS **************/
	
	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		GameEventBattle battleEvent = (GameEventBattle)gameEvent;
		
		Gdx.app.log("BattleEventListener",
				String.format("In onEvent(), event: %s occurred.", battleEvent.getGameEventType()));

		if (this.getGameEventType() == battleEvent.getGameEventType()) {
//			if(battleEvent.isof)
			Gdx.app.log("BattleEventListener", String
					.format("In onEvent(), event: %s occurred. will start battle.", this.getGameEventType()));
			
		}
	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}
	/************ EVENT HANDLERS **************/
	

	private void addEventHandlers() {
		Gdx.app.log("PlayerInventory", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addEventListener(this);
	}
}
