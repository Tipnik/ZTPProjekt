package com.game.src.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.buttons.controlls.ButtonController;
import com.game.src.main.Game;
import com.game.src.stages.StageManager;

public class MouseInput implements MouseListener {

	Game game;
	ButtonController buttonController;
	StageManager stageManager;

	int mx;
	int my;

	public MouseInput(Game game, StageManager stageManager) {
		this.game = game;
		buttonController = game.getButtonController();
		this.stageManager = stageManager;

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		mx = e.getX();
		my = e.getY();

		buttonController.checkClicked(mx, my);
		stageManager.checkClicked(mx, my);
	}

	public void mouseReleased(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

		buttonController.checkReleased(mx, my);
	}

}
