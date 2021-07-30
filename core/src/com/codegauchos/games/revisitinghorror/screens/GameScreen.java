package com.codegauchos.games.revisitinghorror.screens;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.codegauchos.games.revisitinghorror.RevisitingHorror;
import com.codegauchos.games.revisitinghorror.assetmanager.Asset;
import com.codegauchos.games.revisitinghorror.assetmanager.RevisitingHorrorAssetDescriptor;
import com.codegauchos.games.revisitinghorror.events.game.GameEventCountDown;
import com.codegauchos.games.revisitinghorror.events.game.GameEventManager;
import com.codegauchos.games.revisitinghorror.events.game.GameEventOnDefense;
import com.codegauchos.games.revisitinghorror.events.game.GameEventPrepareToAttack;
import com.codegauchos.games.revisitinghorror.events.battle.BattleEventListener;
import com.codegauchos.games.revisitinghorror.events.game.GameEventBattle;
import com.codegauchos.games.revisitinghorror.inventory.PlayerInventory;
import com.codegauchos.games.revisitinghorror.models.Opponent;
import com.codegauchos.games.revisitinghorror.models.Player;
import com.codegauchos.games.revisitinghorror.models.Protection;
import com.codegauchos.games.revisitinghorror.models.Weapon;
import com.codegauchos.games.revisitinghorror.models.ui.CountDownFive;
import com.codegauchos.games.revisitinghorror.models.ui.CountDownFour;
import com.codegauchos.games.revisitinghorror.models.ui.CountDownOne;
import com.codegauchos.games.revisitinghorror.models.ui.CountDownThree;
import com.codegauchos.games.revisitinghorror.models.ui.CountDownTwo;
import com.codegauchos.games.revisitinghorror.models.ui.OnDefense;
import com.codegauchos.games.revisitinghorror.models.ui.PrepareToAttack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Reference: https://github.com/libgdx/libgdx/wiki/Scene2d
 * 
 * @author tim
 *
 */
public class GameScreen implements Screen {
	// fields are here ( because they are private -- exclusively accessed
	// in this class!
	private final AssetManager _assetManager;
	private Music _battleMusic;
	private Opponent _cato;
	private BattleEventListener _battle;
	private CountDownFive _countDownFive;
	private CountDownFour _countDownFour;
	private CountDownOne _countDownOne;
	private CountDownThree _countDownThree;
	private CountDownTwo _countDownTwo;
	private GameEventCountDown _gameEventCountDown;
	private GameEventManager _gameEventManager;
	private GameEventPrepareToAttack _gameEventPrepareToAttack;
	private Player _katniss;
	private PrepareToAttack _prepareToAttack;
	private final RevisitingHorror _revisitingHorrorGame;
	private boolean _startBattleMusic;
	private boolean _startCountDown;
	private int _playerAttackFactor = 0;
	private Label _lblTotalGold;
	
	/**
	 * The Stage class has a camera, SpriteBatch, and a root group and handles
	 * drawing the actors and distributing input events.
	 */
	private Stage _gameScreenStage;
	private Viewport _viewport;
	private OnDefense _onDefense;
	private GameEventOnDefense _gameEventOnDefense;
	private Gson _gson;
	private PlayerInventory _playerInventory;

	// ***** CONSTRUCTORS ********************
	public GameScreen(final RevisitingHorror revisitingHorrorGame) {
		this._startBattleMusic = false;

		this._startCountDown = true;

		this._assetManager = new AssetManager();

		this._gameEventManager = new GameEventManager();

		this._revisitingHorrorGame = revisitingHorrorGame;
		
		this.initialize();
	}
	// ***** CONSTRUCTORS ********************

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "In show(), ");
		
		this._viewport = new FitViewport(RevisitingHorror.SCREEN_WIDTH, RevisitingHorror.SCREEN_HEIGHT);

		// let the stage use the existing spriteBatch. object is very heavy
		// instantiate it one time!!
		this._gameScreenStage = new Stage(this._viewport, this._revisitingHorrorGame.getSpriteBatch());

		// this coordinates event handling
		Gdx.input.setInputProcessor(this._gameScreenStage);
		
//		todo: complete battle code here
//		this._battle = new BattleEventListener();

		this.loadAssets();

		this.loadActors();
	}

	@Override
	public void render(float delta) {
//		Gdx.app.log("GameScreen", "In render(), updating screen");

		// clear (r, g, b, a) the screen with dark blue
//		ScreenUtils.clear(0, 0, 0.2f, 1);
		Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);

		// start intro of game
		if (this._startCountDown == true) {
			this._startCountDown = false;

			this.startCountdown(1);
		}

		if (this._startBattleMusic == false && this._gameEventManager.IsCountDownDone == true) {
//			Gdx.app.debug("GameScreen", "Countdown is done.");
			this._startBattleMusic = true;

			playBattleMusic();

			this.priortizeAttack();
//			this.doBattle();
		}

		// like update, any movement can be handled at this time
		// every actor's act() gets called
		this._gameScreenStage.act(Gdx.graphics.getDeltaTime());

//		this._gameScreenStage.getBatch().begin();

		// Reference: https://www.codinginsights.blog/libgdx-assetmanager/
//		Texture battleSceneBackground = (Texture) this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene);

//	When blending is disabled, anything already on the screen at that location 
//	is replaced by the texture. This is more efficient, so blending should always
//	be disabled unless it is needed
//		this._gameScreenStage.getBatch().disableBlending();
//
//		this._gameScreenStage.getBatch().enableBlending();
//
//		this._gameScreenStage.getBatch().end();

		this._gameScreenStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		this._viewport.update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		this._battleMusic.dispose();
		this._gameScreenStage.dispose();
	}

	/**
	 * Reference: https://github.com/libgdx/libgdx/wiki/Streaming-music
	 * 
	 * https://freesound.org/people/Sirkoto51/sounds/338817/
	 */
	private void playBattleMusic() {
		this._battleMusic = Gdx.audio.newMusic(Gdx.files.internal(Asset.BATTLE_MUSIC_1));

		this._battleMusic.setVolume(0.15f);
		this._battleMusic.play();
	}

	/**
	 * broadcast countdown event
	 * 
	 * @param level
	 */
	private void startCountdown(int level) {
		Gdx.app.log("GameScreen", "In startCountdown(), starting countdown to battle");

		// 1. instantiate the event
		this._gameEventCountDown = new GameEventCountDown(this._countDownFive.getGameEventType());
		this._gameEventCountDown.Level = level;
		this._gameEventCountDown.setStage(this._gameScreenStage);

		// 2. broadcast the event
		this._gameEventManager.broadcastEvent(this._gameEventCountDown);

	}


	/**
	 * player is either in attack mode or on defense
	 */
	private void priortizeAttack() {
		Gdx.app.log("GameScreen", "In priortizeAttack(), showing 'Prepare To Attack' message.");

		// 1. roll dice to calculate who goes first
		int playerDice = (int) (Math.random() * 100);

		if (playerDice + this._playerAttackFactor > 49) {
			// 1. instantiate the event
			this._gameEventPrepareToAttack = new GameEventPrepareToAttack(this._prepareToAttack.getGameEventType());

			// 2. do broadcast
			this._gameEventManager.broadcastEvent(_gameEventPrepareToAttack);
		} else {
			// 1. instantiate the event
			this._gameEventOnDefense = new GameEventOnDefense(this._onDefense.getGameEventType());

			// 2. do broadcast
			this._gameEventManager.broadcastEvent(_gameEventOnDefense);

		}
	}


	private void initialize() {
		Gdx.app.log("GameScreen", "In initialize(), ");

		this._gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("../data/user_data.json"));

			if (bufferedReader != null) {
				this._playerInventory = this._gson.fromJson(bufferedReader, PlayerInventory.class);

				Label.LabelStyle ancientYellowStyle = new Label.LabelStyle();
				ancientYellowStyle.font = this._revisitingHorrorGame.AncientYellowFont;
//				ancientYellowStyle.fontColor = Color.RED;

				this._lblTotalGold = new Label(String.valueOf(this._playerInventory.getTotalGold()),
						ancientYellowStyle);
				this._lblTotalGold.setPosition(1110, 980);
				this._lblTotalGold.setAlignment(Align.right);
				this._lblTotalGold.setSize(28, 28);

				Gdx.app.log("GameScreen",
						"In initialize(), userData totalGold: " + this._playerInventory.getTotalGold());
			} else {
				Gdx.app.error("GameScreen", "In initialize(), could not find  user Data.");
			}

			bufferedReader.close();
		} catch (Exception ex) {
			Gdx.app.log("GameScreen", "In initialize(), system ran into exception: " + ex.getMessage());
		}
		
	}

	/*
	 * !! the order by which you add the actors matters!!
	 */
	private void loadActors() {
		Gdx.app.log("GameScreen", "In loadActors(), ");

		// **************************** this order matters!!
		// **************************************
		Image battleScene = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.battleScene));
		Image loadout = new Image(this._assetManager.get(RevisitingHorrorAssetDescriptor.loadout));
		loadout.setPosition(750, 0);

		Image totalGoldBackground = new Image(
				this._assetManager.get(RevisitingHorrorAssetDescriptor.goldTotalBackground));
		totalGoldBackground.setPosition(1000, 970);
		
		PlayerInventory playerInventory = new PlayerInventory(
				this._assetManager.get(RevisitingHorrorAssetDescriptor.playerInventory), this._gameEventManager);
		playerInventory.setPosition(200, 200);
		playerInventory.setVisible(false);

		Image sword1 = new Weapon(this._assetManager.get(RevisitingHorrorAssetDescriptor.sword1),
				this._gameEventManager, "Sword 1");
		sword1.setPosition(200, 200);
		sword1.setVisible(false);

		Image shield1 = new Protection(this._assetManager.get(RevisitingHorrorAssetDescriptor.shield1),
				this._gameEventManager, "Shield 1");
		shield1.setPosition(200, 200);
		shield1.setVisible(false);

		this._onDefense = new OnDefense(this._assetManager.get(RevisitingHorrorAssetDescriptor.onDefense),
				this._gameEventManager);
		this._onDefense.setVisible(false);

		this._prepareToAttack = new PrepareToAttack(
				this._assetManager.get(RevisitingHorrorAssetDescriptor.prepareToAttack), this._gameEventManager);
		this._prepareToAttack.setVisible(false);

		this._countDownOne = new CountDownOne(this._assetManager.get(RevisitingHorrorAssetDescriptor.one),
				this._gameEventManager);
		this._countDownOne.setVisible(false);

		this._countDownTwo = new CountDownTwo(this._assetManager.get(RevisitingHorrorAssetDescriptor.two),
				this._gameEventManager);
		this._countDownTwo.setVisible(false);

		this._countDownThree = new CountDownThree(this._assetManager.get(RevisitingHorrorAssetDescriptor.three),
				this._gameEventManager);
		this._countDownThree.setVisible(false);

		this._countDownFive = new CountDownFive(this._assetManager.get(RevisitingHorrorAssetDescriptor.five),
				this._gameEventManager);
		this._countDownFive.setVisible(false);

		this._countDownFour = new CountDownFour(this._assetManager.get(RevisitingHorrorAssetDescriptor.four),
				this._gameEventManager);
		this._countDownFour.setVisible(false);

		// to get the countdown right, the base clase GAME_EVENT_TYPE needs to be
		// modified
		// **************************** END: this order matters!!
		// **************************************

		this._katniss = new Player(this._assetManager.get(RevisitingHorrorAssetDescriptor.player),
				RevisitingHorrorAssetDescriptor.player.fileName, this._gameEventManager);
		this._katniss.spritePosition(100, 200);
//		
		this._cato = new Opponent(this._assetManager.get(RevisitingHorrorAssetDescriptor.opponent),
				RevisitingHorrorAssetDescriptor.opponent.fileName, this._gameEventManager);
		this._cato.spritePosition(1200, 200);

		// add tags for easier referencing
		battleScene.setName("battleScene");
		this._katniss.setName("katniss");
		this._cato.setName("cato");

		// this order matters
		// z-order (who gets drawn first. based on who is added before the other
		this._gameScreenStage.addActor(battleScene);
		this._gameScreenStage.addActor(loadout);
		this._gameScreenStage.addActor(totalGoldBackground);
		this._gameScreenStage.addActor(this._lblTotalGold);
		this._gameScreenStage.addActor(this._katniss);
		this._gameScreenStage.addActor(this._cato);
		this._gameScreenStage.addActor(this._countDownOne);
		this._gameScreenStage.addActor(this._countDownTwo);
		this._gameScreenStage.addActor(this._countDownThree);
		this._gameScreenStage.addActor(this._countDownFour);
		this._gameScreenStage.addActor(this._countDownFive);
		this._gameScreenStage.addActor(this._prepareToAttack);
		this._gameScreenStage.addActor(this._onDefense);
		this._gameScreenStage.addActor(playerInventory);
		this._gameScreenStage.addActor(sword1);
		this._gameScreenStage.addActor(shield1);

		// !! this is crucial for scene2D to know player event handling
		// needs to be aware
		this._gameScreenStage.setKeyboardFocus(this._katniss);
	}

	private void loadAssets() {
		this._assetManager.load(RevisitingHorrorAssetDescriptor.player);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.opponent);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.battleScene);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.goldTotalBackground);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.five);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.four);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.three);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.two);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.one);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.prepareToAttack);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.onDefense);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.playerInventory);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.sword1);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.shield1);
		this._assetManager.load(RevisitingHorrorAssetDescriptor.loadout);

		this._assetManager.finishLoading();
	}
}
