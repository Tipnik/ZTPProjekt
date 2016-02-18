package com.game.src.entity.enemies;

import java.awt.Color;
import java.awt.Image;

import com.game.src.main.Game;
import com.game.src.sprites.Textures;
import com.game.src.sprites.flyweight.ImageFactory;

public class Furry extends Enemy {

	public Furry(int x, int y) {
		super(x, y);
		init();
	}

	@Override
	public void init() {
		exp = 20;
		name = "Furry";
		level = 1;
		power = 2;
		defence = 2;
		maxHealth = 10;
		health = maxHealth;
		maxMana = 0;
		mana = maxMana;
		ulecz = 1;
		sprite = ImageFactory.getImage("enemyFurry");
	}

	protected void lastCall() {
		if (ulecz > 0) {
			ulecz--;
			Game.logData.addElement();
			Game.logData.addToElement(getName() + " jest bardzo wœciek³y!");
			power++;
		}
	}

}
