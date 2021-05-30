package com.codegauchos.games.revisitinghorror.models.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.models.ImageBase;

public class CountDownOne extends ImageBase {
	private GameEventManager _gameEventManager;
	private GameEventAbstract _countDownEvent;
	private float _scaleCounter = 0.02f;

	@Override
	public String getGameEventType() {
		return "COUNT_DOWN_1";
	}

	public String GameEventType = getGameEventType();
	
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

		this._gameEventManager.addImageEventListener(this);
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
		this._gameEventManager = gameEventManager;
		
		ImageBase.GAME_EVENT_TYPE = "COUNT_DOWN_1";
	}

	private void doCountDown(GameEventAbstract gameEvent) {
		this.setVisible(true);
		this.setPosition(RevisitingHorror.SCREEN_WIDTH / 2, RevisitingHorror.SCREEN_HEIGHT / 2);
	}
	// ************ END: METHODS ********************
}
