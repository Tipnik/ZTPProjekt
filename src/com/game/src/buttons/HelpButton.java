package com.game.src.buttons;

import com.game.src.main.Game;
import com.game.src.main.Game.STATE;

public class HelpButton extends Button {

	public HelpButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
	}

	public void action() {
		if (Game.state == STATE.MENU)
			Game.state = STATE.HELP;
		else
			Game.state = STATE.MENU;
	}

}
