package com.game.src.buttons;

import java.awt.Graphics2D;

public class RadioButton extends Button {
	
	private Runnable run = null;

	private boolean checked = false;

	public RadioButton(int x, int y, int width, int heigth, String text, Runnable run) {
		super(x, y, width, heigth, text);

		this.run = run;
	}

	@Override
	public void action() {
		toggle();
		
		run.run();
	}
	
	public void toggle(){
		checked = !checked;
	}

	@Override
	public void render(Graphics2D g2d) {
		// int stringLen = (int) g2d.getFontMetrics().getStringBounds(text,
		// g2d).getWidth();
		int stringHei = (int) g2d.getFontMetrics().getStringBounds(text, g2d)
				.getHeight();
		g2d.setColor(backgroundColor);
		g2d.fillRect(x, y, width, height);
		g2d.setColor(borderColor);
		g2d.drawString(text, (int) (x + (width * 1.5)), y
				+ (stringHei + height) / 2 - 5);
		if (checked) {
			g2d.drawLine(x + width, y , x, y + height);
			g2d.drawLine(x, y, x + width, y + height);
		}
		g2d.draw(rectangle);
	}

}
