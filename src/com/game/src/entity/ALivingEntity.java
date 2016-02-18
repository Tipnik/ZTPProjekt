package com.game.src.entity;

import java.awt.Color;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.main.Game;
import com.game.src.stages.CollisionManager;
import com.game.src.stages.StageManager;

public abstract class ALivingEntity extends AEntity {

	protected String name;
	protected int level;
	protected int power;
	protected int defence;
	protected int maxHealth;
	protected int health = maxHealth;
	protected int maxMana;
	protected int mana = maxMana;
	protected int exp;
	protected int ulecz;
//	protected CollisionManager collisionManager = CollisionManager.getInstance();
	protected CollisionManager collisionManager = ButtonMediator.getCollisionManager();
	
	public ALivingEntity(int x, int y) {
		super(x, y);
	}
	
	public abstract void init();
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getPower() {
		return power;
	}

	public int getDefence() {
		return defence;
	}
	
	public int getMana() {
		return mana;
	}

	public int getMaxMana() {
		return maxMana;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public int getHealth() {
		return health;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public int getExp() {
		return exp;
	}
	
	public void addDamage(int amount) {
		this.health -= amount;
	}
	
	public void death() {
		
		ButtonMediator.addItem(this);

		Game.logData.addToElement(" Pokonujesz "+ getName()+". ");

		Game.logData.addElement();
		Game.logData.addToElement("Otrzymujesz ");
		Game.logData.addToElement(exp + " ", Color.YELLOW);
		Game.logData.addToElement("punktów doœwiadczenia!");
		
		ButtonMediator.addExp(exp);
		ButtonMediator.getCurrentField().deleteEnemy(getX(), getY());
	
	}

}
