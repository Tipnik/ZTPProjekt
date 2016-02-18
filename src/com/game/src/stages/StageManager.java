package com.game.src.stages;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import com.game.src.entity.Player;
import com.game.src.main.GameMediator;
import com.game.src.stages.builder.IBoardBuilder;

public class StageManager implements Cloneable {

	private static LinkedList<Stage> stageList;
	private static int currentStage;
	private static StageLoader stageLoader = new StageLoader();
//	private static Player player;
	private static LinkedList<String> stagesList = new LinkedList<String>();

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public StageManager() {
		stageList = new LinkedList<Stage>();

//		this.player = GameMediator.getPlayer();
		currentStage = -1;

		stagesList.add("./res/field1.txt");
		stagesList.add("./res/field2.txt");

	}

	public static void addStage(Stage stage) {
		stageList.add(stage);
	}

	public static void loadStages() {
		try {

			for (String name : stagesList) {
				addStage(stageLoader.loadStage(name));
//				addStage(stageLoader.loadStage("./res/field2.txt"));
			}

			nextStage();
			// "./res/field1.txt"

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void SetWorld(IBoardBuilder builder) {
		stageLoader.setBoardBuilder(builder);

//		stageList = new LinkedList<Stage>();
//		currentStage--;

		reloadCurrentStage();

		/// Game.getStage().clear();
//		loadStages();
	}

	private static void reloadCurrentStage() {
		// TODO Auto-generated method stub
		int tempCurrentField = getCurrentStage().getCurrentField().getFieldNumber();
		try {
			stageList.set(currentStage, stageLoader.loadStage(stagesList.get(currentStage)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stageList.get(currentStage).changeField(tempCurrentField);
		
	}

	private static void reloadAfterBuilderChange() throws FileNotFoundException, IOException {
		clear();
		addStage(stageLoader.loadStage("./res/field1.txt"));
		addStage(stageLoader.loadStage("./res/field2.txt"));

	}

	public void render(Graphics g) {
		stageList.get(currentStage).render(g);
	}

	public static void clear() {
		stageList = new LinkedList<Stage>();
		currentStage = -1;
	}

	public static void nextStage() {
		if (currentStage < stageList.size() - 1) {
			currentStage++;

			GameMediator.getPlayer().setX(getCurrentStage().getStartX());
			GameMediator.getPlayer().setY(getCurrentStage().getStartY());
		}
	}

	public static Stage getCurrentStage() {
		return stageList.get(currentStage);
	}

	public void checkClicked(int mx, int my) {
		stageList.get(currentStage).getCurrentField().checkClicked(mx, my);
	}

}
