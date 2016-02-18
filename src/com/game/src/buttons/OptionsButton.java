package com.game.src.buttons;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.main.Game;
import com.game.src.main.Game.STATE;

public class OptionsButton extends Button {

	public OptionsButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		ButtonMediator.toggleOptions();
	}

}
