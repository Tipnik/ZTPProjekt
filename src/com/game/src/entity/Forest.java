package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Forest extends AEntity {

	public Forest(int x2, int y2) {
		super(x2, y2);
		sprite = ImageFactory.getImage("forest");
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Textures.forest, (int) x * 32, (int) y * 32, null);	
//		
//	}

}
