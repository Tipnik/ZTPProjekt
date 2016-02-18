package com.game.src.buttons;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.main.Game;

public class StartButton extends Button {

	public StartButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
	}

	public void action() {
		
		ButtonMediator.startGame();
	}


}
