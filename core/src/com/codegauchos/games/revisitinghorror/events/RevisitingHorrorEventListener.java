package com.codegauchos.games.revisitinghorror.events;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;

public interface RevisitingHorrorEventListener extends EventListener {
	
	/**
	 * do actions when event occurs *
	 * 
	 * @param gameEvent
	 */
	public void onEvent(GameEventAbstract gameEvent);

}
