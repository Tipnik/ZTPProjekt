package com.game.src.stages;

import java.awt.Graphics;
import java.util.LinkedList;

public class Stage {
	
	private LinkedList<Field> fieldList;
	private int currentField;
	
	private int startX, startY;
	
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public Stage(){
		fieldList = new LinkedList<Field>();
		
		//TODO zmieniæ na coœ...
		currentField = 0;
	}
	
	public void addField(Field field){
		fieldList.add(field);
	}
	
	public void render(Graphics g){
		fieldList.get(currentField).render(g);
	}
	
	public void clear(){
	    fieldList = new LinkedList<Field>();
		currentField = 0;
	}
	
	public Field getCurrentField(){
		return fieldList.get(currentField);
	}
	
	public void changeField(int i){
		currentField = i;
	}

}
