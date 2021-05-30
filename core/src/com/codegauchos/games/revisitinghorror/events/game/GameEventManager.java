package com.codegauchos.games.revisitinghorror.events.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.codegauchos.games.revisitinghorror.models.CharacterBase;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class GameEventManager {

	/************* FIELDS *****************/
	private ArrayList<CharacterBase> _characterEventListeners = new ArrayList<CharacterBase>();
	private ArrayList<ImageBase> _imageEventListeners = new ArrayList<ImageBase>();
	public static String[] GameEventTypes;
	public boolean IsCountDownDone = false;

	/************* CONSTRUCTORS *****************/
	public GameEventManager() {
		GameEventTypes = new String[] { "START_INTRO", "START_BATTLE" };
	}

	/************* METHODS *****************/

	public void addImageEventListener(ImageBase image) {
		// 1. is this listener registered ALREADY?
		boolean alreadyExists = false;

		for (int counter = 0; counter < this._imageEventListeners.size(); counter++) {

			if (ImageBase.GAME_EVENT_TYPE == this._imageEventListeners.get(counter).getGameEventType()) {
				alreadyExists = true;

				break;
			}
		}

		if (alreadyExists == false) {
			Gdx.app.log("GameEventManager",
					"In addImageEventListener(), adding listener for event type: " + ImageBase.GAME_EVENT_TYPE);

			this._imageEventListeners.add(image);
		}
	}

	/**
	 * Register for an event
	 * 
	 * @param character
	 */
	public void addCharacterEventListener(CharacterBase character) {

		// 1. is this listener registered ALREADY?
		boolean alreadyExists = false;

		for (int counter = 0; counter < this._characterEventListeners.size(); counter++) {

			if (CharacterBase.GAME_EVENT_TYPE == this._characterEventListeners.get(counter).getGameEventType()) {
				alreadyExists = true;

				break;
			}
		}

		if (alreadyExists == false) {
			Gdx.app.log("GameEventManager",
					"In addCharacterEventListener(), adding listener for event type: " + CharacterBase.GAME_EVENT_TYPE);

			this._characterEventListeners.add(character);
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

		for (int counter = 0; counter < this._characterEventListeners.size(); counter++) {
			if (gameEvent.getGameEventType() == this._characterEventListeners.get(counter).getGameEventType()) {
				Gdx.app.log("GameEventManager",
						String.format("In broadcastEvent(), dispatching character event notification for: %s.",
								this._characterEventListeners.get(counter).getClass().toString()));

				this._characterEventListeners.get(counter).onEvent(gameEvent);
			}
		}

		for (int counter = 0; counter < this._imageEventListeners.size(); counter++) {
			if (gameEvent.getGameEventType() == this._imageEventListeners.get(counter).getGameEventType()) {
				Gdx.app.log("GameEventManager",
						String.format("In broadcastEvent(), dispatching image event notification for: %s.",
								this._imageEventListeners.get(counter).getClass().toString()));

				this._imageEventListeners.get(counter).onEvent(gameEvent);
			}
		}

		if (gameEvent.isHandled() == true) {
			Gdx.app.log("GameEventManager", "In broadcastEvent(), event handled.  Now, stopping event");

			gameEvent.stop();
		}
	}
	/************* END: METHODS *****************/

}
