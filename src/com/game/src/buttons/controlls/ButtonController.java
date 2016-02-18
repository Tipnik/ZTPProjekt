package com.game.src.buttons.controlls;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.concurrent.Callable;

import com.game.src.buttons.AttackButton;
import com.game.src.buttons.BackButton;
import com.game.src.buttons.Button;
import com.game.src.buttons.HelpButton;
import com.game.src.buttons.OptionsButton;
import com.game.src.buttons.PromptButton;
import com.game.src.buttons.QuitButton;
import com.game.src.buttons.RadioButton;
import com.game.src.buttons.SpellButton;
import com.game.src.buttons.SpellsButton;
import com.game.src.buttons.StartButton;
import com.game.src.main.Game;
import com.game.src.main.Game.STATE;
import com.game.src.memento.Caretaker;
import com.game.src.sound.MusicManager;
import com.game.src.sprites.Textures;
import com.game.src.stages.builder.BoardBuilderForest;
import com.game.src.stages.builder.BoardBuilderHell;
import com.game.src.stages.builder.BoardBuilderOcean;

public class ButtonController implements Cloneable {

	private static LinkedList<Button> buttonList;

	private static LinkedList<Button> spellsButtonList;

	private LinkedList<Button> menuButtonList;

	private LinkedList<Button> gameButtonList;

	private LinkedList<Button> helpButtonList;

	private LinkedList<Button> optionsButtonList;

	private LinkedList<LinkedList> ButtonLists;

	private static boolean spellsShown = false;

	private Button clicked;

	private int standardWidth = 180;

	private int standardHeight = 50;

	private int offsetX = 120;

	private STATE previousState;

	public ButtonController() {

		init();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void render(Graphics g, STATE state) {

		Graphics2D g2d = (Graphics2D) g;

		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);

		if (previousState != null)
			previousState = state;
		else {
			if (previousState != state)
				showAllBut(state);
		}

		for (LinkedList<Button> list : ButtonLists) {

			for (Button b : list) {
				if (b.isVisible()) {
					b.render(g2d);
				}
			}
		}

		for (Button b : buttonList) {

			if (b.isVisible()) {
				b.render(g2d);
			}
		}

		// if (spellsShown)
		for (Button b : spellsButtonList) {

			if (b.isVisible()) {
				b.render(g2d);
			}
		}
	}

	public void checkClicked(int mx, int my) {

		for (Button b : buttonList) {

			if (b.isVisible()) {

				if (b instanceof PromptButton) {

					if (((PromptButton) b).isInBoundsYes(mx, my)) {
						click(b, true);
						return;
					}

					if (((PromptButton) b).isInBoundsNo(mx, my)) {
						click(b, false);
						return;
					}

				} else

				if (b.isInBounds(mx, my)) {
					click(b);
					return;
				}
			}
		}

		for (Button b : spellsButtonList) {
			if (b.isVisible()) {
				if (b.isInBounds(mx, my)) {
					click(b);
					return;
				}
			}
		}

		for (LinkedList<Button> list : ButtonLists) {

			for (Button b : list) {

				if (b.isVisible()) {
					if (b.isInBounds(mx, my)) {
						click(b);
						return;
					}
				}
			}
		}
	}

	public void checkReleased(int mx, int my) {

		if (clicked != null && clicked.isInBounds(mx, my)) {
			clicked.action();

		}
		unclick();

	}

	private void click(Button b) {

		if (b.isVisible()) {
			b.clicked(true);
			clicked = b;
		}
	}

	private void click(Button b, Boolean left) {

		if (b.isVisible()) {
			((PromptButton) b).clickedYes(true);
			clicked = b;
		}
	}

	private void unclick() {
		if (clicked != null)
			clicked.clicked(false);
		clicked = null;

	}

	private void showAllBut(STATE state) {

		for (LinkedList<Button> list : ButtonLists) {

			hideList(list);
		}
		showList(buttonList);
		// showList(spellsButtonList);

		switch (state) {
		case GAME:

			if (spellsShown)
				showList(spellsButtonList);

			showList(gameButtonList);
			break;

		case MENU:

			showList(menuButtonList);
			break;

		case HELP:

			showList(helpButtonList);
			break;

		case OPTIONS:

			showList(optionsButtonList);
			hideList(spellsButtonList);
			break;

		case PAUSE:

			showList(buttonList);
			hideList(spellsButtonList);
			break;

		default:

			break;
		}

	}

	public static void showList(LinkedList<Button> list) {
		for (Button b : list) {

			b.setVisible(true);
		}

	}

	public static void hideList(LinkedList<Button> list) {
		for (Button b : list) {

			b.setVisible(false);
		}
	}

	public static void addButton(Button alertButton) {
		buttonList.add(alertButton);

	}

	public static void deleteButton(Button alertButton) {
		buttonList.remove(alertButton);

	}

	public void init() {
		buttonList = new LinkedList<Button>();

		menuButtonList = new LinkedList<Button>();

		gameButtonList = new LinkedList<Button>();

		helpButtonList = new LinkedList<Button>();

		optionsButtonList = new LinkedList<Button>();

		spellsButtonList = new LinkedList<Button>();

		ButtonLists = new LinkedList<LinkedList>();

		Button playMenuButton = new StartButton(Game.WIDTH / 2 - standardWidth / 2, Game.HEIGHT / 2, standardWidth,
				standardHeight, "Play");

		Button optionsMenuButton = new OptionsButton(Game.WIDTH / 2 - standardWidth / 2, Game.HEIGHT / 2 + 50 + 10,
				standardWidth, standardHeight, "Options");

		Button helpMenuButton = new HelpButton(Game.WIDTH / 2 - standardWidth / 2, Game.HEIGHT / 2 + 100 + 10 + 10,
				standardWidth, standardHeight, "Help");

		Button quitMenuButton = new QuitButton(Game.WIDTH / 2 - standardWidth / 2, Game.HEIGHT / 2 + 150 + 10 + 10 + 10,
				standardWidth, standardHeight, "Quit");

		Button optionsGameButton = new OptionsButton(Game.WIDTH / 2 + 220, Game.HEIGHT / 2 + 200, standardWidth,
				standardHeight, "Options");

		Button quitGameButton = new QuitButton(Game.WIDTH / 2 + 220, Game.HEIGHT / 2 + 250, standardWidth,
				standardHeight, "Quit");

		Runnable save = new Runnable() {
			@Override
			public void run() {

				try {
					Game.saveToMemento();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		Button saveButton = new RadioButton(Game.WIDTH / 2 - 200, 480, 15, 15, "Save state", save);

		Runnable restore = new Runnable() {
			@Override
			public void run() {

				Game.restoreFromMemento();
			}
		};

		Button restoreButton = new RadioButton(Game.WIDTH / 2 - 200, 500, 15, 15, "Restore state", restore);

		Runnable setOcean = new Runnable() {
			@Override
			public void run() {

				ButtonMediator.SetWorld(new BoardBuilderOcean());
			}
		};

		Runnable setForest = new Runnable() {
			@Override
			public void run() {

				ButtonMediator.SetWorld(new BoardBuilderForest());
			}
		};

		Runnable setHell = new Runnable() {
			@Override
			public void run() {
				ButtonMediator.SetWorld(new BoardBuilderHell());
			}
		};

		Runnable r2 = new Runnable() {
			@Override
			public void run() {

				MusicManager.toggle("theme");
			}
		};

		Runnable r3 = new Runnable() {
			@Override
			public void run() {

				MusicManager.toggleStopSounds();
			}
		};

		Button radioButtonTheme = new RadioButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 15, 15, "Theme Music", r2);

		Button radioButtonMusic = new RadioButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 30, 15, 15, "Mute Sounds",
				r3);

		Button radioButtonWordThemeOcean = new RadioButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 3, 15, 15, "Ocean",
				setOcean);
		Button radioButtonWordThemeForest = new RadioButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 4, 15, 15, "Forest",
				setForest);
		Button radioButtonWordThemeHell = new RadioButton(Game.WIDTH / 2 - 100, Game.HEIGHT / 5, 15, 15, "Hell",
				setHell);

		//// Button radioButtonWordThemeHell = new RadioButton(Game.WIDTH / 2 -
		//// 100, Game.HEIGHT / 2, 15, 15, "Hell", r2);

		Button backButton = new BackButton(Game.WIDTH / 2 - standardWidth / 2, Game.HEIGHT / 2 + 150 + 10 + 10 + 10,
				standardWidth, standardHeight, "Back");

		Button attackGameButton = new AttackButton(Game.WIDTH / 2 + 218, Game.HEIGHT / 2 - 50, standardWidth,
				standardHeight, "Attack");

		Button spellsGameButton = new SpellsButton(Game.WIDTH / 2 + 218, Game.HEIGHT / 2, standardWidth, standardHeight,
				"Spells");

		Button spell1GameButton = new SpellButton(Game.WIDTH / 2 + 218, Game.HEIGHT / 2 + 50, 32, 32, "Heal",
				Textures.spellHeal);

		Button spell2GameButton = new SpellButton(Game.WIDTH / 2 + 252, Game.HEIGHT / 2 + 50, 32, 32, "Fireball",
				Textures.spellFireball);

		// Runnable r1 = new Runnable() {
		// @Override
		// public void run() {
		// Game.startOver();
		// }
		// };

		// Button alertButton = new AlertButton(Game.WIDTH / 2 - standardWidth -
		// 100,
		// Game.HEIGHT / 2 - 200, standardWidth*2, standardHeight*3, "OK",
		// "Dosta³eœ po dupie\n Leszczu", r1);
		//

		// buttonList.add(alertButton);

		menuButtonList.add(playMenuButton);
		menuButtonList.add(optionsMenuButton);
		menuButtonList.add(helpMenuButton);
		menuButtonList.add(quitMenuButton);

		// gameButtonList.add(but1);
		gameButtonList.add(quitGameButton);
		gameButtonList.add(optionsGameButton);

		gameButtonList.add(attackGameButton);
		gameButtonList.add(spellsGameButton);
		gameButtonList.add(restoreButton);
		gameButtonList.add(saveButton);

		// buttonList.add(but2);

		helpButtonList.add(backButton);

		// buttonList.add(but3);

		optionsButtonList.add(backButton);
		optionsButtonList.add(radioButtonTheme);
		optionsButtonList.add(radioButtonMusic);
		optionsButtonList.add(radioButtonWordThemeOcean);
		optionsButtonList.add(radioButtonWordThemeForest);
		optionsButtonList.add(radioButtonWordThemeHell);
		radioButtonTheme.action();

		if (MusicManager.isMuteSounds())
			((RadioButton) radioButtonMusic).toggle();

		// spells
		spellsButtonList.add(spell1GameButton);
		spellsButtonList.add(spell2GameButton);
		hideList(spellsButtonList);
		// showList(spellsButtonList);

		// special
		// buttonList.add(new PromptButton(150, 150, 150, 150, "Jazda?", null,
		// null));

		// ButtonLists.add(buttonList);
		ButtonLists.add(menuButtonList);
		ButtonLists.add(gameButtonList);
		ButtonLists.add(helpButtonList);
		ButtonLists.add(optionsButtonList);
		// ButtonLists.add(spellsButtonList);

	}

	// public static void spellsList() {
	// // if (spellsShown) {
	// // hideList(spellsButtonList);
	// // } else {
	// // showList(spellsButtonList);
	// // }
	// spellsShown = !spellsShown;
	// }

	public static void spellsList(boolean b) {
		spellsShown = b;
	}

	public static void toggleSpells() {
		if (spellsShown) {
			hideList(spellsButtonList);
		} else {
			showList(spellsButtonList);
		}
		spellsShown = !spellsShown;
	}

}
