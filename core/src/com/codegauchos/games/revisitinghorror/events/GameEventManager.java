package com.codegauchos.games.revisitinghorror.events;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class GameEventManager {

	/************* FIELDS *****************/
	private ArrayList<GameEventListener> _gameEventListeners = new ArrayList<GameEventListener>();
	public static String[] GameEventTypes;
	public boolean IsCountDownDone = false;

	/************* CONSTRUCTORS *****************/
	public GameEventManager() {
		GameEventTypes = new String[] { "COUNT_DOWN_1", "COUNT_DOWN_2", "COUNT_DOWN_3", "COUNT_DOWN_4", "COUNT_DOWN_5",
				"START_INTRO", "START_BATTLE" };
	}

	/************* METHODS *****************/
	/**
	 * Register for an event
	 * 
	 * @param gameEventListener
	 */
	public void addEventListener(GameEventListener gameEventListener) {

		// 1. is this listener registered ALREADY?
		boolean alreadyExists = false;

		for (int counter = 0; counter < this._gameEventListeners.size(); counter++) {
			if (gameEventListener.getGameEventType() == this._gameEventListeners.get(counter).getGameEventType()) {
				alreadyExists = true;

				break;
			}
		}

		if (alreadyExists == false) {
			Gdx.app.log("GameEventManager",
					"In addEventListener(), adding listener for event type: " + gameEventListener.getGameEventType());

			this._gameEventListeners.add(gameEventListener);
		}
	}

	/**
	 * Broadcast the event to anyone who has registered for the event (event
	 * listeners)
	 * 
	 * @param gameEvent
	 */
	public void broadcastEvent(GameEventAbstract gameEvent) {
		// https://www.javatpoint.com/java-string-format
		Gdx.app.log("GameEventManager",
				String.format("In broadcastEvent(), dispatching event notifications for game event type: %s.",
						gameEvent.getGameEventType()));

		for (int counter = 0; counter < this._gameEventListeners.size(); counter++) {
			if (gameEvent.getGameEventType() == this._gameEventListeners.get(counter).getGameEventType()) {
				Gdx.app.log("GameEventManager",
						String.format("In broadcastEvent(), dispatching event notification for: %s.",
								this._gameEventListeners.get(counter).getClass().toString()));

				this._gameEventListeners.get(counter).onEvent(gameEvent);
			}
		}

		if (gameEvent.isHandled() == true) {
			Gdx.app.log("GameEventManager", "In broadcastEvent(), event handled.  Now, stopping event");

			gameEvent.stop();
		}
	}
	/************* END: METHODS *****************/

}
