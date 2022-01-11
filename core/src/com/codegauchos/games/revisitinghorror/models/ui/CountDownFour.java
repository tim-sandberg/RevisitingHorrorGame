package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventCountDown;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class CountDownFour extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _countDownFourEvent;
	private float _scaleCounter = 0.02f;

	public String getGameEventType() {
		return "COUNT_DOWN_4";
	}

	public CountDownFour(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract countDownEvent) {
		Gdx.app.log("CountDownFour",
				String.format("In onEvent(), event: %s occurred.", countDownEvent.getGameEventType()));

		if (this.getGameEventType() == countDownEvent.getGameEventType()) {
			Gdx.app.log("CountDownFour", String
					.format("In onEvent(), event: %s occurred. doCountDown() will execute now.", this.getGameEventType()));

			this._countDownFourEvent = countDownEvent;
			
			this.doCountDown(this._countDownFourEvent);
		}

	}

	@Override
	public boolean handle(Event event) {
		Gdx.app.log("CountDownFour", String.format("handled event: %s", event.getTarget()));

		// 1. instantiate the event
		CountDownThree.GAME_EVENT_TYPE = "COUNT_DOWN_3";
		
		GameEventCountDown gameEventCountDown = new GameEventCountDown(CountDownThree.GAME_EVENT_TYPE);
		gameEventCountDown.Level = 1;
		gameEventCountDown.setStage(this.getStage());

		// 2. broadcast the event
		this._gameEventManager.broadcastEvent(gameEventCountDown);

		// we are done handling this event
		return true;

	}

	/************ END: EVENT HANDLERS **************/

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		if (this.getScaleX() < 5 && this.isVisible() == true) {
			Gdx.app.log("CountDownFour", "Scaling up count down number");

			this.scaleBy(this._scaleCounter);

			Gdx.app.debug("CountDownFour", String.format("Scaling up count down number. scale: %f", this.getScaleX()));

		} else if (this.isVisible() == true) {
			this.setVisible(false);

			this.handle(this._countDownFourEvent);
		}
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "COUNT_DOWN_4";
	}

	private void addEventHandlers() {
		Gdx.app.log("CountDownFour", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}

	private void doCountDown(GameEventAbstract gameEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
	}
	// ************ END: METHODS ********************

}
