package com.game.src.stages.builder;

import com.game.src.entity.AEntity;
import com.game.src.entity.Chest;
import com.game.src.entity.Door;
import com.game.src.entity.Forest;
import com.game.src.entity.Grass;
import com.game.src.entity.Mountain;
import com.game.src.entity.Princess;
import com.game.src.entity.ScreenChange;
import com.game.src.entity.Water;
import com.game.src.entity.enemies.Boss;
import com.game.src.entity.enemies.Enemy;
import com.game.src.entity.enemies.Furry;
import com.game.src.entity.enemies.Mage;

public class BoardBuilderForest extends IBoardBuilder {

	@Override
	public void addSegment(int x, int y) {
		Mountain tempEntity = new Mountain(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentW(int x, int y) {
		Water tempEntity = new Water(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentL(int x, int y) {
		Forest tempEntity = new Forest(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentC(int x, int y) {
		Chest tempEntity = new Chest(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentD(int x, int y) {
		Door tempEntity = new Door(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentP(int x, int y) {
		Princess tempEntity = new Princess(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentF(int x, int y) {
		Furry tempEnemy = new Furry(x, y);
		field.setEnemy(x, y, tempEnemy);
		Grass tempEntity = new Grass(x, y);
		field.setObject(x, y, tempEntity);
	}
	
	@Override
	public void addSegmentB(int x, int y) {
		Boss tempEnemy = new Boss(x, y);
		field.setEnemy(x, y, tempEnemy);
		Grass tempEntity = new Grass(x, y);
		field.setObject(x, y, tempEntity);
	}

	@Override
	public void addSegmentM(int x, int y) {
		Mage tempEnemy = new Mage(x, y);
		field.setEnemy(x, y, tempEnemy);
		Grass tempEntity = new Grass(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addSegmentG(int x, int y) {
		Grass tempEntity = new Grass(x, y);
		field.setObject(x, y, tempEntity);

	}

	@Override
	public void addScreenChange(int x, int y, int changeTo) {
		ScreenChange tempEntity = new ScreenChange(x, y, changeTo);
		field.setObject(x, y, tempEntity);

	}

}
