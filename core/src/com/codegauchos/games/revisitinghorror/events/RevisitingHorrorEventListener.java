package com.codegauchos.games.revisitinghorror.events;

import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface RevisitingHorrorEventListener extends EventListener {
	
	public String getGameEventType();

	public void setGameEventType(int gameEventTypeIndex);
	
	public void onEvent(GameEventAbstract gameEvent);

}
