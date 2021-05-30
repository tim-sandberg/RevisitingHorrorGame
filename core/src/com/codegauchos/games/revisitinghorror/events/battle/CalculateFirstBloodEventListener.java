package com.codegauchos.games.revisitinghorror.events.battle;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventListener;

public class CalculateFirstBloodEventListener implements GameEventListener {
	
	public static String getGameEventType() {
		return "CALCULATE_FIRST_BLOOD";
	}

	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

}
