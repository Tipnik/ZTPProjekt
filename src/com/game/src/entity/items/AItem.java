package com.game.src.entity.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class AItem implements IItem {
	
	private int x, y;
	
	protected BufferedImage sprite;
	
	public AItem(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int) x * 32, (int) y * 32, null);	
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x*32 + 14, y*32 + 14, 32 - 30, 32 - 30);
	}

	public abstract void action();
	
}
