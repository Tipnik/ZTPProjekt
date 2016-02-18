package com.game.src.entity.items.weapon;

import com.game.src.buttons.controlls.ButtonMediator;
import com.game.src.entity.items.AItem;
import com.game.src.sprites.flyweight.ImageFactory;

public class HolyWeapon  extends AItem  {

	public HolyWeapon(int x, int y) {
		super(x, y);
		sprite = ImageFactory.getImage("holyItem");
	}

	@Override
	public void action() {
		ButtonMediator.playerWeaponAdd("holy");
		
	}

}
