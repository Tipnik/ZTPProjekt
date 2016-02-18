package com.game.src.stages;

public class FieldIterator<E> implements IIterator<E> {

	E[][] map;
	int x;
	int y;

	protected FieldIterator(E[][] map) {
		this.map = map;
		x = 0;
		y = -1;
	}

	public E next() {
		
		for (int i = x; i < map.length; i++) {
			for (int j = y+1; j < map[0].length; j++) {
				if (map[i][j] != null){
					x = i;
					y = j;
					return map[i][j];
				}
			}
			y=-1;
		}
		return null;
	}

	public Boolean hasNext() {
//		if (x==18){
//			x=0;
//			y++;
//		}
//		if (y>=15&&x>=18)
//			return false;
//		
		int xt = x;
		int yt = y;
		
		for (int i = xt; i < map.length; i++) {
			for (int j = yt+1; j < map[0].length; j++) {
				if (map[i][j] != null){
					return true;
				}
			}
			yt=-1;
		}
		return false;
	}

}
