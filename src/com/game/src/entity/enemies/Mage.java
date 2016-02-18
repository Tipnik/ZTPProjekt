package com.game.src.entity.enemies;

import java.awt.Color;
import java.util.Random;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.AEntity;
import com.game.src.main.Game;
import com.game.src.sprites.flyweight.ImageFactory;
import com.game.src.stages.Field;

public class Mage extends Enemy {

	public Mage(int x, int y) {
		super(x, y);
		init();
	}

	@Override
	public void init() {
		exp = 30;
		name = "Mage";
		level = 2;
		power = 3;
		defence = 2;
		maxHealth = 10;
		health = maxHealth;
		maxMana = 0;
		mana = maxMana;
		ulecz = 1;
		sprite = ImageFactory.getImage("enemyMage");
	}

	@Override
	public void move() {

		 Field currentField = ButtonMediator.getCurrentField();
		
		 Random rnd = new Random();
		
		 int dx = (rnd.nextBoolean() ? 0 : rnd.nextBoolean() ? -1 : 1);
		 int dy;
		 if (dx == 0)
		 dy = (rnd.nextBoolean() ? 0 : rnd.nextBoolean() ? -1 : 1);
		 else
		 dy = 0;
		
		 if (collisionManager.checkCollision((AEntity)
		 currentField.getObject(x + dx, y + dy), this)) {
		
		 currentField.setEnemy(x, y, null);
		 x += dx;
		 y += dy;
		 currentField.setEnemy(x, y, this);
		 }

	}

	@Override
	protected void lastCall() {
		if (ulecz > 0) {
			health++;
			ulecz--;
			Game.logData.addElement();
			Game.logData.addToElement(getName() + " leczy siê za ");
			Game.logData.addToElement(1 + "", Color.GREEN);
		}
		
	}

}
