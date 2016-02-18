package com.game.src.weapon;

import java.awt.image.BufferedImage;

import com.game.src.sprites.flyweight.ImageFactory;

public class Weapon implements IWeapon {
	
	int attack = 1;
	
	String name = "Bro�";

	@Override
	public int attack() {
//		System.out.println("Atak normal");
		return attack;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BufferedImage getImage() {
		return ImageFactory.getImage("weapon");
	}


}
