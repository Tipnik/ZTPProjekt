package com.game.src.buttons;

public class QuitButton extends Button {

	public QuitButton(int x, int y, int width, int heigth, String text) {
		super(x, y, width, heigth, text);
	}

	@Override
	public void action() {
		
//		setVisible(false);
		
		System.exit(1);
	}

}
