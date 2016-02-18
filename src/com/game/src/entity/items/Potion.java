package com.game.src.entity.items;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.sprites.flyweight.ImageFactory;

public class Potion extends AItem {
	
	public Potion(int x, int y) {
		super(x, y);
		sprite = ImageFactory.getImage("potion");
	}

	@Override
	public void action() {
		
		ButtonMediator.healPlayer(3);
		
	}

}
