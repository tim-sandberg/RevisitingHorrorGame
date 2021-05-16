package com.codegauchos.games.revisitinghorror.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public abstract class GameEventAbstract extends Event {
	/*********** FIELDS ******************/
	private String _gameEventType;
	public int Level;
	
	/*********** MEMBERS ******************/
	public String getGameEventType() {
		return _gameEventType;
	}
	public void setGameEventType(int gameEventTypeIndex) {
		this._gameEventType = GameEventManager.GameEventTypes[gameEventTypeIndex];
	}
	
	/*********** CONSTRUCTORS ******************/
	/**
	 * From GameEventManager.GameEventTypes;
	 * 
	 * @param gameEventTypeIndex
	 */
	public GameEventAbstract(int gameEventTypeIndex) {
		this.setGameEventType(gameEventTypeIndex);
	}
}
