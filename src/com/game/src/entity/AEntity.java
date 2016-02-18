package com.game.src.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.sprites.Textures;

public abstract class AEntity implements Entity {
	
	protected int x;
	protected int y;
	protected BufferedImage sprite;
	
	public AEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x*32 -2, y*32 -2, 32 +2, 32 +2);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int) x * 32, (int) y * 32, null);	
		
	}

}
