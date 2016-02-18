package com.game.src.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.game.src.main.Game;

public class MouseMotionInput implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouseX = e.getX();
		Game.mouseY = e.getY();

	}

}
