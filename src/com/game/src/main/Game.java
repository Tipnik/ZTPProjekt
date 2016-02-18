package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import com.game.src.buttons.controlls.ButtonController;
import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.Player;
import com.game.src.input.*;
import com.game.src.libs.Controller;
import com.game.src.memento.Memento;
import com.game.src.memento.WrapperMemento;
import com.game.src.screens.HelpScreen;
import com.game.src.screens.Menu;
import com.game.src.screens.OptionsScreen;
import com.game.src.screens.gui.GuiBackground;
import com.game.src.screens.gui.LogData;
import com.game.src.screens.gui.PlayerStats;
import com.game.src.sound.MusicManager;
import com.game.src.sprites.*;
import com.game.src.sprites.flyweight.ImageFactory;
import com.game.src.stages.*;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int scale = 1;
	public final String TITLE = "JeJ's Quest";

	public static enum STATE {
		MENU, GAME, HELP, OPTIONS, PAUSE
	};

	public static STATE state = STATE.MENU;

	private static boolean running = false;
	public static boolean started = false;
	private Thread thread;

	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private static Player player;
	private static PlayerStats playerStats;
	private static Textures tex;
	private static Menu menu;
	private static OptionsScreen optionsScreen;
	private static HelpScreen helpScreen;
	private static GuiBackground guiBackground;
	private static ButtonController buttonController;
	private static MusicManager musicManager = new MusicManager();
	private static StageLoader stageLoader = new StageLoader();
	private static ButtonMediator buttonMediator;
	private static GameMediator gameMediator;

	private static ImageFactory imageFactory;

	private static Stage stage;
	private static StageManager stageManager;

	private static Memento memento;

	boolean pressed = true;

	// LOG STUFF
	public static LogData logData;
	public static int mouseX;
	public static int mouseY;

	public static void startOver() {

		player.init();
		
//		gameMediator = new GameMediator(player);

//		buttonMediator = new ButtonMediator(player, stageManager);

//		playerStats = new PlayerStats();

		logData.init();

//		playerStats = new PlayerStats();
//		logData = new LogData();

		imageFactory.emptyMap();
		
		stageManager.clear();
		stageManager.loadStages();
		
//		stage = new Stage();
//		stageManager.addStage(stage);
//		LoadStage();
		state = STATE.MENU;
		buttonController.init();

//		musicManager.reset();
		
		started = false;

//		MusicManager.play("theme");
		
//		ButtonMediator.startGame();

	}

	private void init() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		requestFocus();

		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			spriteSheet = loader.loadImage("/sprite3.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageFactory = new ImageFactory(this);

		player = new Player();
		
		gameMediator = new GameMediator(this, player);

		stageManager = new StageManager();
		stageManager.loadStages();
		
		buttonMediator = new ButtonMediator(this, player, stageManager);

		playerStats = new PlayerStats();

		logData = new LogData();
//		stage = new Stage();
//		stageManager.addStage();
//		LoadStage();
		tex = new Textures(this);

		// c = new Controller(this);
		menu = new Menu();
		helpScreen = new HelpScreen();
		optionsScreen = new OptionsScreen();
		guiBackground = new GuiBackground();
		buttonController = new ButtonController();

		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this, stageManager));
		addMouseMotionListener(new MouseMotionInput());

		musicManager.init();

		MusicManager.play("theme");
	}

	public static void saveToMemento() throws CloneNotSupportedException {
		WrapperMemento wrapper = new WrapperMemento();
		wrapper.setPlayer((Player) player.clone());
		wrapper.setPlayerStats((PlayerStats) playerStats.clone());
		/// StageManager s = stageManager.clone(player);
		/// wrapper.setStageManager(stageManager.clone(player));
		wrapper.setGuiBackground((GuiBackground) guiBackground.clone());
		// wrapper.setList(stageManager.clone(player));

		Game.logData.addToElement("Zapamiêtano stan ");
		memento = new Memento(wrapper);
	}

	public static void restoreFromMemento() {
		WrapperMemento wrap = memento.getState();
		player = wrap.getPlayer();
		playerStats = wrap.getPlayerStats();

		// playerStats.setPlayer(player);
		stageManager.clear();
		//// stageManager= wrap.getStageManager();
		// StageManager.setStages(wrap.getList());
		// stageManager.setPlayer(player);
		guiBackground = wrap.getGuiBackground();

		Game.logData.addToElement("Odnowiono stan ");
	}

	private synchronized void start() {

		if (running)
			return;

		running = true;
		thread = new Thread();
		thread.start();

	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);

	}

//	public static void SetWorld(IBoardBuilder builder) {
//		stageLoader.setBoardBuilder(builder);
//		resetStage();
//	}

//	public static void resetStage() {
//		stage.clear();
//		LoadStage();
//	}

//	public static void LoadStage() {
//		try {
//			stageLoader.loadStage(stage);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//	}

	public void run() {
		// init();

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				// tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				// LOGData test
				// logData.addElement();
				// logData.addToElement(frames + " Co za Dupa, ");
				// logData.addToElement("Straszna Dupa ", Color.RED);
				// logData.addToElement("A¿ nie mogê uwierzyæ", Color.BLUE);

				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
			}

		}
		stop();
	}

	// private void tick() {
	//
	// if (state == STATE.GAME) {
	// // p.tick();
	//// c.tick();
	// }
	//
	// }

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// guiBackground.render(g);

		g.drawImage(background, 0, 0, this);

		if (state == STATE.GAME) {

			guiBackground.render(g);

			stageManager.render(g);

			player.render(g);

			playerStats.render(g);

			// c.render(g);

			logData.render(g);

		} else if (state == STATE.MENU) {
			// g.drawImage(background, 0, 0, this);
			menu.render(g);
		} else if (state == STATE.HELP) {
			// g.drawImage(background, 0, 0, this);
			helpScreen.render(g);
		} else if (state == STATE.OPTIONS) {
			// g.drawImage(background, 0, 0, this);
			optionsScreen.render(g);

			// startOver();
			// state = STATE.MENU;

		} else if (state == STATE.PAUSE) {

			guiBackground.render(g);
			logData.render(g);
			playerStats.render(g);
			MusicManager.stop("theme");
		}
		buttonController.render(g, state);

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * scale, HEIGHT * scale));
		game.setMaximumSize(new Dimension(WIDTH * scale, HEIGHT * scale));
		game.setMinimumSize(new Dimension(WIDTH * scale, HEIGHT * scale));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
		game.init();
		game.run();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void keyPressed(KeyEvent e) {

		if (state == STATE.GAME) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_RIGHT) {
				player.setVelX(1);
			} else if (key == KeyEvent.VK_LEFT) {
				player.setVelX(-1);
			} else if (key == KeyEvent.VK_UP) {
				player.setVelY(-1);
			} else if (key == KeyEvent.VK_DOWN) {
				player.setVelY(1);
			}
		}
		player.tick();
		pressed = true;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(0);
		}

	}

	public Menu getMenu() {
		return menu;
	}

	public ButtonController getButtonController() {
		return buttonController;
	}

	public static boolean isRunning() {
		return started;
	}

}
