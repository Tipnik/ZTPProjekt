package com.game.src.entity.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.AEntity;
import com.game.src.entity.ALivingEntity;
import com.game.src.main.Game;
import com.game.src.sprites.Textures;
import com.game.src.stages.CollisionManager;
import com.game.src.stages.Field;
import com.game.src.stages.StageManager;

public abstract class Enemy extends ALivingEntity {

	// private int exp = 20;

	private boolean selected = false;
	
//	private IEnemyStrategy strategy = null;

	public Enemy(int x, int y) {
		super(x, y);
	}

	@Override
	public void tick() {

//		Field currentField = ButtonMediator.getCurrentField();
//
//		Random rnd = new Random();
//
//		int dx = (rnd.nextBoolean() ? 0 : rnd.nextBoolean() ? -1 : 1);
//		int dy;
//		if (dx == 0)
//			dy = (rnd.nextBoolean() ? 0 : rnd.nextBoolean() ? -1 : 1);
//		else
//			dy = 0;
//
//		if (collisionManager.checkCollision((AEntity) currentField.getObject(x + dx, y + dy), this)) {
//
//			currentField.setEnemy(x, y, null);
//			x += dx;
//			y += dy;
//			currentField.setEnemy(x, y, this);
//		}
		
		move();

	}
	
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
	public void render(Graphics g) {

		g.drawImage(sprite, x * 32, y * 32, null);
		
		g.setColor(Color.RED);
		
		if (selected)
			g.drawRect(x * 32 - 2, y * 32 - 2, 35, 35);

	}

	public void select(boolean b) {

		selected = b;
	}

	public String getType() {

		return getName().substring(0, 1);
	}

	@Override
	public void addDamage(int amount) {
		this.health -= amount;
		getHealth();

	}

	@Override
	public int getHealth() {
		if (health <= 0) {
			health = 0;
			return health;
		}
		if(health<(int)(0.3*maxHealth)){
			lastCall();
		}
		
		return health;
	}

	protected abstract void lastCall();

//	@Override
//	public void death(){
//		
//		ButtonMediator.addItem(this);
//		
//		Game.logData.addToElement(" Pokonujesz "+ getName()+". ");
//
//		Game.logData.addElement();
//		Game.logData.addToElement("Otrzymujesz ");
//		Game.logData.addToElement(getExp() + " ", Color.YELLOW);
//		Game.logData.addToElement("punktów doœwiadczenia!");
//		
//		ButtonMediator.addExp(getExp());
//		ButtonMediator.getCurrentField().deleteEnemy(getX(), getY());
//	}
	
}
