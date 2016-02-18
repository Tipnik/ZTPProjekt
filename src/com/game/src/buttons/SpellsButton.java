package com.game.src.buttons;

import com.game.src.buttons.controlls.ButtonController;

public class SpellsButton extends Button {

	boolean shown = false;

	public SpellsButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		ButtonController.toggleSpells();
	}

}
