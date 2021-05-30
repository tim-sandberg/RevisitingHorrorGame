package com.codegauchos.games.revisitinghorror.events.opponent;

public interface OpponentEventListener {
	/**
	 * walk from off screen to battle position
	 */
	public void walkOut();
	
	/**
	 * paces within a range
	 */
	public void pace();
}
