package com.codegauchos.games.revisitinghorror.models;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;

public class Opponent extends CharacterBase {
	// ****** fields *************
	private int _aggro;
	private GameEventManager _gameEventManager;
	private Sprite _sprite;
	private float _deltaX;
	private boolean _canWalkOut;

	// ****** members *************
	@Override
	public String getGameEventType() {
		return "START_INTRO";
	}

	public int getAggro() {
		return _aggro;
	}

	public void setAggro(int aggro) {
		this._aggro = aggro;
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

				// do first blood calculation
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
		CharacterBase.GAME_EVENT_TYPE = "START_INTRO";

		// set player horizontal movement speed
		this.setDeltaX(1.0f);

		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());

		this.setTouchable(Touchable.enabled);

		this._gameEventManager = gameEventManager;

		this._canWalkOut = true;

//		this.walkOut();
	}

	private void addEventHandlers(String actorName) {

		this._gameEventManager.addCharacterEventListener(this);

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
