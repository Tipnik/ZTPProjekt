package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Grass extends AEntity {
	
	public Grass(int x, int y){
		super(x, y);
		sprite = ImageFactory.getImage("field");
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Textures.field, (int) x * 32, (int) y * 32, null);		
//	}

}
