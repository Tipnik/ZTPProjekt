package com.game.src.entity;

import java.awt.Graphics;

public interface Entity {

	public void tick();
	public void render(Graphics g);
	
	public int getX();
	public int getY();


}
