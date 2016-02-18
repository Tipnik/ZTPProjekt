package com.game.src.buttons;

import java.awt.Graphics2D;

public interface IButton {

	public abstract void render(Graphics2D g2d);
	
	public abstract void setVisible(boolean set);

	public abstract boolean isVisible();

	public abstract boolean isInBounds(int mx, int my);

	public abstract String getText();
	
	public abstract void setText(String text);

	public abstract void action();

}
