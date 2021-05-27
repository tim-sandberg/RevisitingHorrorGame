package com.codegauchos.games.revisitinghorror.models.ui;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.events.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.GameEventManager;

public class CountDownOne extends Image implements GameEventListener {
	private GameEventManager _gameEventManager;
	private String _gameEventType;
	private GameEventAbstract _countDownEvent;
	private float _scaleCounter = 0.02f;

	@Override
	public String getGameEventType() {
		return this._gameEventType;
	}

	@Override
	public void setGameEventType(int gameEventTypeIndex) {
		this._gameEventType = GameEventManager.GameEventTypes[gameEventTypeIndex];

	}

	public CountDownOne(Texture texture, GameEventManager gameEventManager) {
		super(texture);

		this.initialize(texture, gameEventManager);

		this.addEventHandlers();
	}

	/************ EVENT HANDLERS **************/

	@Override
	public void onEvent(GameEventAbstract countDownEvent) {
		Gdx.app.log("CountDownOne",
				String.format("In onEvent(), event: %s occurred.", countDownEvent.getGameEventType()));

		this._countDownEvent = countDownEvent;

		if (this._countDownEvent.getGameEventType() == "COUNT_DOWN_1") {
			Gdx.app.log("CountDownOne",
					String.format("In onEvent(), event: %s occurred. doCountDown() will execute now.",
							this._countDownEvent.getGameEventType()));

			this.doCountDown(countDownEvent);
		}

	}

	@Override
	public boolean handle(Event event) {
		Gdx.app.log("CountDownOne", String.format("handled event: %s", event.getTarget()));

//		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("COUNT_DOWN_1");
//
//		// 1. instantiate the event
//		GameEventCountDown gameEventCountDown = new GameEventCountDown(gameEventTypeIndex);
//		gameEventCountDown.Level = 1;
//		gameEventCountDown.setStage(this.getStage());
//
//		// 2. broadcast the event
//		this._gameEventManager.broadcastEvent(gameEventCountDown);
		this._gameEventManager.IsCountDownDone = true;

		return true;

	}

	private void addEventHandlers() {
		Gdx.app.log("CountDownOne", "addEventHandlers(), registering event handlers.");

		this._gameEventManager.addEventListener(this);
	}

	/************ END: EVENT HANDLERS **************/

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		if (this.getScaleX() < 5 && this.isVisible() == true) {
			Gdx.app.log("CountDownOne", "Scaling up count down number");

			this.scaleBy(this._scaleCounter);

			Gdx.app.debug("CountDownOne", String.format("Scaling up count down number. scale: %f", this.getScaleX()));

		} else if (this.isVisible() == true) {
			this.setVisible(false);

			this.handle(this._countDownEvent);
		}
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("COUNT_DOWN_1");
		this.setGameEventType(gameEventTypeIndex);

		this._gameEventManager = gameEventManager;
	}

	private void doCountDown(GameEventAbstract gameEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
	}
	// ************ END: METHODS ********************
}
