package com.game.src.entity.items;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface IItem {

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);
	
	public void action();
	
	public Rectangle getBounds();

	public void render(Graphics g);

}
