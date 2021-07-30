package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventCountDown;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class CountDownFive extends ImageBase {
	// fields
	private GameEventManager _gameEventManager;
	private GameEventAbstract _countDownFiveEvent;
	private float _scaleCounter = 0.05f;

	// member - getters/setter
	@Override
	public String getGameEventType() {
		return "COUNT_DOWN_5";
	}
	
	/**
	 * Constructor: 
	 * @param texture
	 * @param gameEventManager
	 */
	public CountDownFive(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "COUNT_DOWN_5";
	}

	
	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract countDownEvent) {
		Gdx.app.log("CountDownFive",
				String.format("In onEvent(), event: %s occurred.", countDownEvent.getGameEventType()));

		if (countDownEvent.getGameEventType() == getGameEventType()) {
			Gdx.app.log("CountDownFive", String.format(
					"In onEvent(), event: %s occurred. doCountDown() will execute now.", getGameEventType()));

			this._countDownFiveEvent = countDownEvent;
			
			this.doCountDown(countDownEvent);
		}

	}

	/**
	 * 1. broadcasting for the next event (CountDownFour)
	 * 2. Signaling this event is over
	 */
	@Override
	public boolean handle(Event event) {
		Gdx.app.log("CountDownFive", String.format("handled event: %s", event.getTarget()));

		CountDownFour.GAME_EVENT_TYPE = "COUNT_DOWN_4";
		
		// 1. instantiate the event
		GameEventCountDown gameEventCountDown = new GameEventCountDown(CountDownFour.GAME_EVENT_TYPE);
		gameEventCountDown.Level = 1;
		gameEventCountDown.setStage(this.getStage());

		// 2. broadcast the event
		this._gameEventManager.broadcastEvent(gameEventCountDown);

		return true;
	}

	private void addEventHandlers() {
		Gdx.app.log("CountDownFive", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}

	/************ END: EVENT HANDLERS **************/

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		if (this.getScaleX() < 5 && this.isVisible() == true) {
			Gdx.app.log("CountDownFive", "Scaling up count down number");

			this.scaleBy(this._scaleCounter);

			Gdx.app.debug("CountDownFive", String.format("Scaling up count down number. scale: %f", this.getScaleX()));

		} else if (this.isVisible() == true) {
			this.setVisible(false);

			this.handle(this._countDownFiveEvent);
		}
	}

	private void doCountDown(GameEventAbstract gameEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
	}
	// ************ END: METHODS ********************

}
