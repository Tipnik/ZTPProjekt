package com.game.src.buttons;

import com.game.src.main.Game;
import com.game.src.main.Game.STATE;

public class BackButton extends Button {

	public BackButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		if (Game.state == STATE.HELP)
			Game.state = STATE.MENU;
		else
			if (Game.state == STATE.OPTIONS)
				if (Game.isRunning()){
					Game.state = STATE.GAME;
				} else {
					Game.state = STATE.MENU;
				}

	}

}
