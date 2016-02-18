package com.game.src.main;

import com.game.src.entity.Player;

public class GameMediator {
	
	
	private static Player player;
	
	public GameMediator(Game game, Player player){
		
		this.player = player;
		
	}

	public GameMediator(Player player2) {
		this.player = player;
	}

	public static void setPlayer(int x, int y) {
		
		player.setX(x);
		player.setY(y);
		
	}

	public static Player getPlayer() {
		return player;
	}

}
