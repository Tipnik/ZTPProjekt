package com.game.src.stages.builder;

import com.game.src.stages.Field;

public abstract class IBoardBuilder {

	protected Field field;
	// protected Enemy tempEnemy = null;
	// protected AEntity tempEntity = null;

	public void init() {
		field = new Field();
	}

	public abstract void addSegment(int x, int y);

	public abstract void addSegmentW(int x, int y);

	public abstract void addSegmentL(int x, int y);

	public abstract void addSegmentC(int x, int y);

	public abstract void addSegmentD(int x, int y);

	public abstract void addSegmentP(int x, int y);

	public abstract void addSegmentF(int x, int y);

	public abstract void addSegmentM(int x, int y);

	public abstract void addSegmentG(int x, int y);
	
	public abstract void addSegmentB(int x, int y);

	public abstract void addScreenChange(int x, int y, int changeTo);

	public void SetNumber(int number) {
		field.setNumber(number);
	}

	public Field getField() {
		return field;
	}


}
