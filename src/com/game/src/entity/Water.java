package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Water extends AEntity {

	public Water(int x2, int y2) {
		super(x2, y2);
		sprite = ImageFactory.getImage("water");
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Textures.water, (int) x * 32, (int) y * 32, null);	
//		
//	}

}
