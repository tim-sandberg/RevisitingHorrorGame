package com.codegauchos.games.revisitinghorror.models;

import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import com.codegauchos.games.revisitinghorror.events.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.GameEventManager;

public class Opponent extends Actor implements GameEventListener {
	// ****** fields *************
	private int _agro;
	private GameEventManager _gameEventManager;
	private String _gameEventType;
	private Sprite _sprite;
	private float _deltaX;
	private boolean _canWalkOut;

	// ****** members *************
	@Override
	public String getGameEventType() {
		return this._gameEventType;
	}

	@Override
	public void setGameEventType(int gameEventTypeIndex) {
		this._gameEventType = GameEventManager.GameEventTypes[gameEventTypeIndex];

	}

	public int getAgro() {
		return _agro;
	}

	public void setAgro(int agro) {
		this._agro = agro;
	}

	public Sprite getSprite() {
		return _sprite;
	}

	public void setSprite(Sprite sprite) {
		this._sprite = sprite;
	}

	public float getDeltaX() {
		return _deltaX;
	}

	public void setDeltaX(float speed) {
		this._deltaX = speed;
	}

	// ****** END: members *************

	// ******************** CONSTRUCTORS ***********
	public Opponent(Texture texture, final String actorName, GameEventManager gameEventManager) {
		this.initialize(texture, gameEventManager);

		this.addEventHandlers(actorName);
	}

	// ************ METHODS ***********************
	@Override
	public void act(float delta) {
		super.act(Gdx.graphics.getDeltaTime());

//		for (Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();) {
//			Gdx.app.log("Opponent", "In act(), doing the action, now");
//			iter.next().act(delta);
//		}

		if (this._canWalkOut == true && this._gameEventManager.IsCountDownDone == true) {
			this.spritePosition(this.getX() - this.getDeltaX(), this.getY());

			if (this.getX() <= 900) {
				this._canWalkOut = false;

				Gdx.app.debug("Opponent", String.format("Done with the walk out. X position: %f", this.getX()));
			}
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.getSprite().draw(batch);
	}

	public void spritePosition(float x, float y) {
		this.getSprite().setPosition(x, y);

		this.setBounds(this.getSprite().getX(), this.getSprite().getY(), this.getSprite().getWidth(),
				this.getSprite().getHeight());
	}

	private void initialize(Texture texture, GameEventManager gameEventManager) {
		// set player horizontal movement speed
		this.setDeltaX(1.0f);

		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());

		this.setTouchable(Touchable.enabled);

		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("START_INTRO");
		this.setGameEventType(gameEventTypeIndex);

		this._gameEventManager = gameEventManager;

		this._canWalkOut = true;

//		this.walkOut();
	}

	private void addEventHandlers(String actorName) {

		this._gameEventManager.addEventListener(this);

	}

	public void walkOut() {
		Gdx.app.log("Opponent", "In walkout(), ");

//		MoveByAction pacing = new MoveByAction();
//		pacing.setAmount(-500f, 0f);
//		pacing.setDuration(0.4f);
//		Opponent.this.addAction(pacing);

	}

	public void pace() {
		// TODO Auto-generated method stub

	}

	public void startIntro(int level) {
		Gdx.app.log("Opponent", "In startIntro(), for level: " + level);
	}

	/************ EVENT HANDLERS ************************/
	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		if (gameEvent.getGameEventType() == Arrays.asList(GameEventManager.GameEventTypes).get(0)) {
			this.startIntro(1);
		}

	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	/************ END: EVENT HANDLERS **************/

}
