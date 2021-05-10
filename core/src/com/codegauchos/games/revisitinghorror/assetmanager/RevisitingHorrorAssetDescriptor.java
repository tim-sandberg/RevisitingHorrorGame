package com.codegauchos.games.revisitinghorror.assetmanager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class RevisitingHorrorAssetDescriptor {
	public static final AssetDescriptor<Texture> player = new AssetDescriptor<Texture>(Asset.PLAYER, Texture.class);
	public static final AssetDescriptor<Texture> opponent = new AssetDescriptor<Texture>(Asset.OPPONENT, Texture.class);
	
	
	private RevisitingHorrorAssetDescriptor() {
	}
}
