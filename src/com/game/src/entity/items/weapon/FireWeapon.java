package com.game.src.entity.items.weapon;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.items.AItem;
import com.game.src.sprites.flyweight.ImageFactory;

public class FireWeapon  extends AItem {
	
	public FireWeapon(int x, int y){
		super(x, y);
		sprite = ImageFactory.getImage("fireItem");
	}

	@Override
	public void action() {
		ButtonMediator.playerWeaponAdd("fire");
		
	}

}
