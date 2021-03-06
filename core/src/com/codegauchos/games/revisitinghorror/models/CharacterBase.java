package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventListener;

/**
 * Reference: The Actor class is a node in the graph which has a position,
 * rectangular size, origin, scale, rotation, and color
 * 
 * @author tim
 *
 */
public class CharacterBase extends Actor implements GameEventListener {
	/**
	 * static member
	 * 
	 * @return
	 */
	public String getGameEventType() {
		return "";
	}

	public static String GAME_EVENT_TYPE = "";

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
