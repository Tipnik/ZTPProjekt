package com.game.src.stages;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.AEntity;
import com.game.src.entity.ALivingEntity;
import com.game.src.entity.Entity;
import com.game.src.entity.Player;
import com.game.src.entity.enemies.Enemy;
import com.game.src.entity.items.IItem;
import com.game.src.main.Game;

public class Field {

	private AEntity[][] map;
	private int fieldNumber;
	private ALivingEntity[][] enemyMap;
	private IItem[][] itemMap;
	private Enemy selectedEnemy;
	private Random rnd = new Random();
	private FieldIterator<ALivingEntity> enemyIterator;
	private FieldIterator<AEntity> fieldIterator;
	private FieldIterator<IItem> itemIterator;

	public Field() {
		this.map = new AEntity[19][15];
		this.enemyMap = new ALivingEntity[19][15];
		this.itemMap = new IItem[19][15];

	}

	public Entity[][] getObjectMap() {
		return map;
	}

	public Entity getObject(int x, int y) {
		if (y >= map[0].length || x >= map.length) {
			return null;
		}

		if (y < 0 || x < 0) {
			return null;
		}

		if (y >= enemyMap[0].length || x >= enemyMap.length) {
			return null;
		} else if (enemyMap[x][y] != null) {

			return enemyMap[x][y];
		}

		return map[x][y];
	}

	public void setObject(int x, int y, AEntity e) {
		this.map[x][y] = e;
	}

	public void deleteObject(int x, int y) {
		this.map[x][y] = null;
	}

	public void setNumber(int i) {
		this.setFieldNumber(i);

	}

	public void render(Graphics g) {

		fieldIterator = new FieldIterator<AEntity>(map);

		while (fieldIterator.hasNext())
			fieldIterator.next().render(g);

		// for (int i = 0; i < map.length; i++) {
		// for (int j = 0; j < map[0].length; j++) {
		// if (map[i][j] != null)
		// map[i][j].render(g);
		// }
		// }
		
		itemIterator = new FieldIterator<IItem>(itemMap);
		
		while (itemIterator.hasNext())
			itemIterator.next().render(g);

		enemyIterator = new FieldIterator<ALivingEntity>(enemyMap);

		while (enemyIterator.hasNext())
			enemyIterator.next().render(g);

		// for (int i = 0; i < enemyMap.length; i++) {
		// for (int j = 0; j < enemyMap[0].length; j++) {
		// if (enemyMap[i][j] != null) {
		// enemyMap[i][j].render(g);
		// }
		// }
		// }

	}

	public void setEnemy(int x, int y, ALivingEntity e) {
		this.enemyMap[x][y] = e;
	}

	public Entity getEnemy(int x, int y) {
		return enemyMap[x][y];
	}

	public void tick() {

		checkItems();
		
		enemyIterator = new FieldIterator<ALivingEntity>(enemyMap);

		ALivingEntity enemyTemp;
		Player player = ButtonMediator.getPlayer();

		while (enemyIterator.hasNext()) {
			enemyTemp = enemyIterator.next();

			if (player.getBounds().intersects(enemyTemp.getBounds())) {
				
				ButtonMediator.enemyAttackPlayer(enemyTemp);

			} else {
				enemyTemp.tick();
			}
		}
	}

	public void deleteEnemy(int x, int y) {
		selectedEnemy = null;
		this.enemyMap[x][y] = null;

	}
	
	public void checkItems(){
		
		itemIterator = new FieldIterator<IItem>(itemMap);
		
		IItem temp;
		
		while (itemIterator.hasNext()){
			temp = itemIterator.next();
			if (temp.getBounds().intersects(ButtonMediator.getPlayer().getBounds())){
				temp.action();
				itemMap[temp.getX()][temp.getY()] = null;
			}
		}
	}
	
	public void addItem(int x, int y, IItem item){
		itemMap[x][y] = item;
	}

	public void checkClicked(int mx, int my) {

		int xf = (int) Math.floor(mx / 32.0);
		int yf = (int) Math.floor(my / 32.0);

		if (xf < enemyMap.length && yf < enemyMap[0].length)

			if (enemyMap[xf][yf] != null) {

				if (selectedEnemy != null) {

					if (enemyMap[xf][yf] == selectedEnemy) {
						selectedEnemy.select(false);
						selectedEnemy = null;
					} else {
						selectedEnemy.select(false);
						selectedEnemy = (Enemy) enemyMap[xf][yf];
						selectedEnemy.select(true);
					}
				} else {
					selectedEnemy = (Enemy) enemyMap[xf][yf];
					selectedEnemy.select(true);

				}
			} else {
				if (selectedEnemy != null) {
					selectedEnemy.select(false);
					selectedEnemy = null;
				}
			}

	}

	public Enemy getSelectedEnemy() {
		return selectedEnemy;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

}
