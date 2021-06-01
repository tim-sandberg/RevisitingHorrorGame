package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class OnDefense extends ImageBase {
	private GameEventManager _gameEventManager;

	public String getGameEventType() {

		return "ON_DEFENSE";
	}

	public OnDefense(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	@Override
	public void act(float delta) {

	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract onDefenseEvent) {
		Gdx.app.log("OnDefense",
				String.format("In onEvent(), event: %s occurred.", onDefenseEvent.getGameEventType()));

		if (this.getGameEventType() == onDefenseEvent.getGameEventType()) {
			Gdx.app.log("OnDefense", String.format(
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
		
		ImageBase.GAME_EVENT_TYPE = "ON_DEFENSE";
	}

	private void addEventHandlers() {
		Gdx.app.log("OnDefense", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}
}
