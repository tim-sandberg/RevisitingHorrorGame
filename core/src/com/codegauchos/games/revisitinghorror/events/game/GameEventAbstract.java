package com.codegauchos.games.revisitinghorror.events.game;

import com.badlogic.gdx.scenes.scene2d.Event;

public abstract class GameEventAbstract extends Event {
	/*********** FIELDS ******************/
	private String _gameEventType;
	
	/*********** MEMBERS ******************/
	public int Level;
	
	public String getGameEventType() {
		return _gameEventType;
	}
	
	public void setGameEventType(String gameEventType) {
		this._gameEventType = gameEventType;
	}
	
	/*********** CONSTRUCTORS ******************/
	/**
	 * From GameEventManager.GameEventTypes;
	 * 
	 * @param gameEventType (String)
	 */
	public GameEventAbstract(String gameEventType) {
		this.setGameEventType(gameEventType);
	}
}
