package com.codegauchos.games.revisitinghorror.events.game;

/**
 * In this class, place any information related to the event, that you want to
 * pass on
 * 
 * @author tim
 *
 */
public class GameEventBattle extends GameEventAbstract {
	private boolean _isAttack;

	public boolean getIsAttack() {
		return this._isAttack;
	}

	public void setIsAttack(boolean isAttack) {
		this._isAttack = isAttack;
	}

	/**
	 * 
	 * @param gameEventType
	 * @param isAttack
	 */
	public GameEventBattle(String gameEventType, boolean isAttack) {
		super(gameEventType);

		this.setIsAttack(isAttack);
	}
}
