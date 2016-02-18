package com.game.src.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.main.Game;

public class Menu {
	
	Font fnt0;
	
	int length = 200;
	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		String title = "JeJ's Quest!";
		int stringLen = (int)
	            g2d.getFontMetrics().getStringBounds(title, g2d).getWidth();
		
		g.drawString(title, (Game.WIDTH-stringLen) / 2, 100);
		
	}

}
