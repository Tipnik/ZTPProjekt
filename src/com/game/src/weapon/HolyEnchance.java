package com.game.src.weapon;

import java.awt.image.BufferedImage;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.sprites.flyweight.ImageFactory;

public class HolyEnchance extends EnchancedWeapon {
	
	int healAmount = 1;

	public HolyEnchance(IWeapon weapon) {
		super(weapon);
	}

	@Override
	public int attack() {
		
		ButtonMediator.healPlayer(healAmount);
		
		return weapon.attack();
//		System.out.println("Atak holy");
	}

	@Override
	public String getName() {
		return "Œwiêta "+weapon.getName().toLowerCase();
	}

	@Override
	public BufferedImage getImage() {
		return ImageFactory.joinBufferedImage(weapon.getImage(), "holyEnch");
	}

}
