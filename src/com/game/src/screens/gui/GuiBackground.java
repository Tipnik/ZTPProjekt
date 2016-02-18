package com.game.src.screens.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Na teraz rysowane s¹ kwadraty, ale mo¿e bêdzie trzeba zast¹piæ je rysunkami
 *
 */
public class GuiBackground implements Cloneable { 
	
	
	
	private Rectangle field, log, stats, menu;

	public GuiBackground(){
		
		this.field = new Rectangle(0, 0, 608, 480);
		this.log = new Rectangle(0, 480, 608, 130);
		this.stats = new Rectangle(608, 0, 200, 480);
		this.menu = new Rectangle(608, 480, 200, 130);
		
	}
	
	 @Override
	    public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }
	
	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.decode("#9BEA5A"));
		g2d.fillRect(field.x, field.y, field.width, field.height);
		g2d.draw(field);
		
		g2d.setColor(Color.decode("#323C2A"));
		g2d.fillRect(log.x, log.y, log.width, log.height);
		g2d.draw(log);
		
		g2d.setColor(Color.decode("#323C2A"));
		g2d.fillRect(stats.x, stats.y, stats.width, stats.height);
		g2d.draw(stats);
		
		g2d.setColor(Color.decode("#9BEA5A"));
		g2d.fillRect(menu.x, menu.y, menu.width, menu.height);
		g2d.draw(menu);
		
		
	}

}
