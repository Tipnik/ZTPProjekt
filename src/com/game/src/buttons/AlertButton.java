package com.game.src.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.buttons.controlls.ButtonController;
import com.game.src.main.Game;

public class AlertButton extends Button {

	private String alertText;
	private Runnable run = null;

	public AlertButton(int x, int y, int width, int height, String text,
			String alertText) {
		super(x, y, width, height, text);

		// x = Game.WIDTH / 2 - width / 2;
		// y = Game.HEIGHT / 2 - height / 2;

		// rectangle = new Rectangle(x, y, width, height);
		this.alertText = alertText;
	}

	public AlertButton(int x, int y, int width, int height, String text,
			String alertText, Runnable run) {
		super(x, y, width, height, text);

		// x = Game.WIDTH / 2 - width / 2;
		// y = Game.HEIGHT / 2 - height / 2;

		// rectangle = new Rectangle(x, y, width, height);
		this.alertText = alertText;
		this.run = run;
	}
	
	public AlertButton(int x, int y, int width, int height, String text,
			String alertText, Runnable run, Runnable run2) {
		super(x, y, width, height, text);

		// x = Game.WIDTH / 2 - width / 2;
		// y = Game.HEIGHT / 2 - height / 2;

		// rectangle = new Rectangle(x, y, width, height);
		this.alertText = alertText;
		this.run = run;
		run2.run();
	}

	@Override
	public void action() {

		if (run != null) {
			run.run();
		}

		ButtonController.deleteButton(this);

	}

	@Override
	public void render(Graphics2D g2d) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d)
				.getWidth();
		int stringHei = (int) g2d.getFontMetrics().getStringBounds(text, g2d)
				.getHeight();

		g2d.setColor(backgroundColor);

		g2d.fillRect(x, y, width, height);
		g2d.setColor(borderColor);

		Font current = g2d.getFont();
		Font fnt1 = new Font("arial", Font.BOLD | Font.ITALIC, 15);
		g2d.setFont(fnt1);

		drawString(g2d, alertText, x + 10, y + 10);

		g2d.setFont(current);

		if (clicked) {
			g2d.setColor(backgroundClickedColor);
		} else {
			g2d.setColor(backgroundColor);
		}

		Rectangle rectangle = new Rectangle(x + width / 2 - 40, (int) (y
				+ (height) / 1.5 - 10), 80, 30);

//		g2d.draw(rectangle);

		g2d.fillRect(x + width / 2 - 40, (int) (y + (height) / 1.5 - 10), 80,
				30);

		g2d.setColor(Color.WHITE);

		g2d.drawString(text, x + (-stringLen + width) / 2, (int) (y
				+ (stringHei + height) / 1.5 - 5));

		g2d.draw(rectangle);

	}

	@Override
	public boolean isInBounds(int mx, int my) {

		int xt = x + width / 2 - 40;
		int yt = (int) (y + (height) / 1.5 - 10);

		if (mx > xt && mx < xt + 80) {
			if (my > yt && my < yt + 30) {

				return true;
			}
		}
		return false;
	}

	void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

}
