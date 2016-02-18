package com.game.src.memento;

import java.util.LinkedList;

import com.game.src.buttons.controlls.ButtonController;
import com.game.src.entity.AEntity;
import com.game.src.entity.Player;
import com.game.src.libs.Controller;
import com.game.src.screens.gui.GuiBackground;
import com.game.src.screens.gui.LogData;
import com.game.src.screens.gui.PlayerStats;
import com.game.src.stages.Stage;
import com.game.src.stages.StageManager;

public class WrapperMemento {
	private StageManager stageManager;
	private Player player;
	private PlayerStats playerStats;
	private static GuiBackground guiBackground;
	private ButtonController buttonController;
	private Controller controller;
	private LogData logData;
	private LinkedList<Stage> list;
	private AEntity[][] enemyMap;

	public void setList(LinkedList<Stage> list) {

		this.list = list;
	}

	public LinkedList<Stage> getList() {
		return list;
	}

	public StageManager getStageManager() {
		return stageManager;
	}

	public void setStageManager(StageManager stageManager) {
		this.stageManager = stageManager;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerStats getPlayerStats() {
		return playerStats;
	}

	public void setPlayerStats(PlayerStats playerStats) {
		this.playerStats = playerStats;
	}

	public static GuiBackground getGuiBackground() {
		return guiBackground;
	}

	public static void setGuiBackground(GuiBackground guiBackground) {
		WrapperMemento.guiBackground = guiBackground;
	}

	public ButtonController getButtonController() {
		return buttonController;
	}

	public void setButtonController(ButtonController buttonController) {
		this.buttonController = buttonController;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public LogData getLogData() {
		return logData;
	}

	public void setLogData(LogData logData) {
		this.logData = logData;
	}

	public AEntity[][] getEnemyMap() {
		return enemyMap;
	}

	public void setEnemyMap(AEntity[][] enemyMap) {
		this.enemyMap = enemyMap;
	}
}
