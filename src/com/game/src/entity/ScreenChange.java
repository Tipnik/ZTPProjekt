package com.game.src.entity;

public class ScreenChange extends Grass{
	
	private int changeTo;
	
	public ScreenChange(int x, int y, int changeTo){
		super(x, y);
		this.changeTo = changeTo;
	}

	public int getTo(){
		return changeTo;
	}

}
