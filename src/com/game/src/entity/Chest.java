package com.game.src.entity;

import java.awt.Graphics;

import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Chest extends AEntity {
	
	public boolean open = false;

	public Chest(int x2, int y2) {
		super(x2, y2);
		sprite = ImageFactory.getImage("chest");
	}

	@Override
	public void tick() {
		open = !open;
		sprite = ImageFactory.getImage("chestOpen");
		System.out.println("CLol");
	}

	@Override
	public void render(Graphics g) {

		if (!open)
			g.drawImage(Textures.chest, (int) x * 32, (int) y * 32, null);
		else
			g.drawImage(Textures.chestOpen, (int) x * 32, (int) y * 32, null);
	}

}
