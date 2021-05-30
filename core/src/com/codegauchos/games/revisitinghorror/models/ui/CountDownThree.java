package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventCountDown;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class CountDownThree extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _countDownThreeEvent;
	private float _scaleCounter = 0.02f;

	public CountDownThree(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	@Override
	public String getGameEventType() {
		return "COUNT_DOWN_3";
	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract countDownEvent) {
		Gdx.app.log("CountDownThree",
				String.format("In onEvent(), event: %s occurred.", countDownEvent.getGameEventType()));

		if (this._countDownThreeEvent.getGameEventType() == "COUNT_DOWN_3") {
			Gdx.app.log("CountDownThree",
					String.format("In onEvent(), event: %s occurred. doCountDown() will execute now.",
							this._countDownThreeEvent.getGameEventType()));

			this._countDownThreeEvent = countDownEvent;

			this.doCountDown(countDownEvent);
		}

	}

	@Override
	public boolean handle(Event event) {
		Gdx.app.log("CountDownThree", String.format("handled event: %s", event.getTarget()));

		// 1. instantiate the event
		GameEventCountDown gameEventCountDown = new GameEventCountDown(CountDownTwo.GAME_EVENT_TYPE);
		gameEventCountDown.Level = 1;
		gameEventCountDown.setStage(this.getStage());

		// 2. broadcast the event
		this._gameEventManager.broadcastEvent(gameEventCountDown);
		
		return true;

	}

	private void addEventHandlers() {
		Gdx.app.log("CountDownThree", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addImageEventListener(this);
	}

	/************ END: EVENT HANDLERS **************/

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		if (this.getScaleX() < 5 && this.isVisible() == true) {
			Gdx.app.log("CountDownThree", "Scaling up count down number");

			this.scaleBy(this._scaleCounter);

			Gdx.app.debug("CountDownThree", String.format("Scaling up count down number. scale: %f", this.getScaleX()));

		} else if (this.isVisible() == true) {
			this.setVisible(false);

			this.handle(this._countDownThreeEvent);
		}
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {		
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "COUNT_DOWN_3";
	}

	private void doCountDown(GameEventAbstract gameEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
	}
	// ************ END: METHODS ********************
}
