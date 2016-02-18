package com.game.src.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PromptButton extends Button {

	private Runnable yes = null;
	private Runnable no = null;

	private Boolean yesClicked = false;
	private Boolean noClicked = false;

	private int offsetX = 40;

	public PromptButton(int x, int y, int width, int height, String text, Runnable yes, Runnable no) {
		super(x, y, width, height, text);

		this.yes = yes;
		this.no = no;
	}

	@Override
	public void action() {

	}

	@Override
	public void render(Graphics2D g2d) {

		int stringLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int stringHei = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();

		g2d.setColor(backgroundColor);

		g2d.fillRect(x, y, width, height);
		g2d.setColor(borderColor);

		Font current = g2d.getFont();
		Font fnt1 = new Font("arial", Font.BOLD | Font.ITALIC, 15);
		g2d.setFont(fnt1);

		drawString(g2d, text, x + 10, y + 10);

		g2d.setFont(current);

		if (yesClicked) {
			g2d.setColor(backgroundClickedColor);
		} else {
			g2d.setColor(backgroundColor);
		}

		offsetX = 40;

		Rectangle rectangle = new Rectangle(x + width / 2 - 40 - offsetX, (int) (y + (height) / 1.5 - 10), 80, 30);

		// g2d.draw(rectangle);

		g2d.fillRect(x + width / 2 - 40 - offsetX, (int) (y + (height) / 1.5 - 10), 80, 30);

		g2d.setColor(Color.WHITE);

		g2d.drawString("Yes",
				x - offsetX + (-(int) g2d.getFontMetrics().getStringBounds("Yes", g2d).getWidth() + width) / 2,
				(int) (y + (stringHei + height) / 1.5 - 5));

		g2d.draw(rectangle);

		if (noClicked) {
			g2d.setColor(backgroundClickedColor);
		} else {
			g2d.setColor(backgroundColor);
		}

		offsetX *= -1;

		rectangle = new Rectangle(x + width / 2 - 40 - offsetX, (int) (y + (height) / 1.5 - 10), 80, 30);

		// g2d.draw(rectangle);

		g2d.fillRect(x + width / 2 - 40 - offsetX, (int) (y + (height) / 1.5 - 10), 80, 30);

		g2d.setColor(Color.WHITE);

		g2d.drawString("No",
				x - offsetX + (-(int) g2d.getFontMetrics().getStringBounds("Yes", g2d).getWidth() + width) / 2,
				(int) (y + (stringHei + height) / 1.5 - 5));

		g2d.draw(rectangle);

	}

	@Override
	public boolean isInBounds(int mx, int my) {

		return false;
	}

	void drawString(Graphics2D g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public boolean isInBoundsYes(int mx, int my) {
		int xt = x + width / 2 - offsetX;
		int yt = (int) (y + (height) / 1.5 - 10);

		if (mx > xt && mx < xt + 40) {
			if (my > yt && my < yt + 30) {

				return true;
			}
		}
		return false;
	}

	public boolean isInBoundsNo(int mx, int my) {
		int xt = x + width / 2 + offsetX;
		int yt = (int) (y + (height) / 1.5 - 10);

		if (mx > xt && mx < xt + 40) {
			if (my > yt && my < yt + 30) {

				return true;
			}
		}
		return false;
	}

	public void clickedYes(boolean b) {
		if (b) {
			this.yesClicked = true;
			System.out.println("Yes");
		} else {
			System.out.println("No");
			this.noClicked = true;

		}
	}

	@Override
	public void clicked(boolean b) {
		yesClicked = b;
		noClicked = b;

	}

}
