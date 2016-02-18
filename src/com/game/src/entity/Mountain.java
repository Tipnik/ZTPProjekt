package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Mountain extends AEntity {

	public Mountain(int x2, int y2) {
		super(x2, y2);
		sprite = ImageFactory.getImage("mountain");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void render(Graphics g) {
//		g.drawImage(Textures.mountain, (int) x * 32, (int) y * 32, null);	
//		
//	}

}
