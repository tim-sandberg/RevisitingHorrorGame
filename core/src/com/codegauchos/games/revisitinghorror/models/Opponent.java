package com.codegauchos.games.revisitinghorror.models;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.codegauchos.games.revisitinghorror.events.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.GameEventListener;
import com.codegauchos.games.revisitinghorror.events.GameEventManager;

public class Opponent extends Actor implements GameEventListener {
	// ****** fields *************
	private int _agro;
	private GameEventManager _gameEventManager;
	private String _gameEventType;
	private Sprite _sprite;

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
		this.setSprite(new Sprite(texture));

		this.spritePosition(this.getSprite().getX(), this.getSprite().getY());

		this.setTouchable(Touchable.enabled);

		int gameEventTypeIndex = Arrays.asList(GameEventManager.GameEventTypes).indexOf("START_INTRO");
		this.setGameEventType(gameEventTypeIndex);

		this._gameEventManager = gameEventManager;


	}

	private void addEventHandlers(String actorName) {

		this._gameEventManager.addEventListener(this);

	}

	public void walkOut() {
		MoveByAction pacing = new MoveByAction();
		pacing.setAmount(-300f, 0f);
		pacing.setDuration(60000000f);
		Opponent.this.addAction(pacing);

	}

	public void pace() {
		// TODO Auto-generated method stub

	}

	public void startIntro(int level) {
		Gdx.app.log("Opponent", "In startIntro(), for level: " + level);
		this.walkOut();
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
