package com.codegauchos.games.revisitinghorror.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.codegauchos.games.revisitinghorror.events.game.GameEventAbstract;
import com.codegauchos.games.revisitinghorror.events.game.GameEventListener;

/**
 * Reference:
 * 
 * @author tim
 *
 */
public class ImageBase extends Image implements GameEventListener {
	private String _name;
	
	/**
	 * static member
	 * 
	 * @return
	 */
	public String getGameEventType() {
		return "";
	}

	public String getName() {
		return this._name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public static String GAME_EVENT_TYPE = "";

	@Override
	public void onEvent(GameEventAbstract gameEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handle(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * constructor 
	 * 
	 * 
	 * @param texture
	 */
	public ImageBase(Texture texture) {
		super(texture);
	}

}
