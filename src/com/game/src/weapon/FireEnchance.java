package com.game.src.weapon;

import java.awt.image.BufferedImage;

import com.game.src.sprites.flyweight.ImageFactory;

public class FireEnchance extends EnchancedWeapon {
	
	int attackAmount = 3; // additional amount

	public FireEnchance(IWeapon weapon) {
		super(weapon);
	}

	@Override
	public int attack() {
		return attackAmount + weapon.attack();
//		System.out.println("Atak fire");
	}

	@Override
	public String getName() {
		return "Ognista "+weapon.getName().toLowerCase();
	}

	@Override
	public BufferedImage getImage() {
		return ImageFactory.joinBufferedImage(weapon.getImage(), "fireEnch");
	}

}
