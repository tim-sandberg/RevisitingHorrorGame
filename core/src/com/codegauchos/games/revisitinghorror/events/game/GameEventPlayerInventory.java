package com.codegauchos.games.revisitinghorror.events.game;

public class GameEventPlayerInventory extends GameEventAbstract {
	private boolean _isAttack;

	public String getGameEventType() {
		return "PLAYER_INVENTORY";
	}
	
	public boolean getIsAttack() {
		return this._isAttack;
	}

	public void setIsAttack(boolean isAttack) {
		this._isAttack = isAttack;
	}

	public GameEventPlayerInventory(String gameEventType, boolean isAttack) {
		super(gameEventType);
		
		this.setIsAttack(isAttack);
	}

}
