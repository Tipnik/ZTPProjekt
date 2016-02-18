package com.game.src.buttons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Button implements IButton {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Rectangle rectangle;
	protected String text;
	protected boolean clicked = false;

	protected Color borderColor = Color.WHITE;
	protected Color backgroundColor = Color.GRAY;
	protected Color backgroundClickedColor = Color.DARK_GRAY;

	private boolean visible;

	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.rectangle = new Rectangle(x, y, width, height);
		this.visible = true;
	}

	public void render(Graphics2D g2d) {
		int stringLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d)
				.getWidth();
		int stringHei = (int) g2d.getFontMetrics().getStringBounds(text, g2d)
				.getHeight();
		if (clicked) {
			g2d.setColor(backgroundClickedColor);
		} else {
			g2d.setColor(backgroundColor);
		}
		g2d.fillRect(x, y, width, height);
		g2d.setColor(borderColor);
		g2d.drawString(text, x + (-stringLen + width) / 2, y
				+ (stringHei + height) / 2 - 5);
		g2d.draw(rectangle);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isInBounds(int mx, int my) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {

				return true;
			}
		}
		return false;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public abstract void action();

	public void clicked(boolean b) {
		clicked = b;

	}

}
