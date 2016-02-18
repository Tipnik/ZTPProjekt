package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Door extends AEntity {
	
	public boolean open = false;

	public Door(int x2, int y2) {
		super(x2, y2);
		sprite = ImageFactory.getImage("door");
	}

	@Override
	public void tick() {
		open = !open;
		sprite = ImageFactory.getImage("doorOpen");
		System.out.println("DoorLol");

	}

//	@Override
//	public void render(Graphics g) {
//
//		if (!open)
//			g.drawImage(Textures.door, (int) x * 32, (int) y * 32, null);
//		else
//			g.drawImage(Textures.doorOpen, (int) x * 32, (int) y * 32, null);
//	}

}
