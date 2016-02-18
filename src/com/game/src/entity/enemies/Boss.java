package com.game.src.entity.enemies;

import java.awt.Color;

import com.game.src.main.Game;
import com.game.src.sprites.flyweight.ImageFactory;

public class Boss extends Enemy {

	public Boss(int x, int y) {
		super(x, y);
		init();
	}

	@Override
	public void init() {
		exp = 300;
		name = "Boss";
		level = 5;
		power = 5;
		defence = 2;
		maxHealth = 15;
		health = maxHealth;
		maxMana = 0;
		mana = maxMana;
		ulecz = 1;
		sprite = ImageFactory.getImage("enemyBoss");
		
	}
	
	protected void lastCall(){

		if (ulecz>0){
			ulecz--;
			Game.logData.addElement();
			Game.logData.addToElement(getName()+" leczy siê za");
			Game.logData.addToElement((maxHealth-health)+"", Color.GREEN);
			health=maxHealth;
		}
	}
	
	@Override
	public void move() {
		
	}

}
