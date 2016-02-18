package com.game.src.weapon;

public abstract class EnchancedWeapon implements IWeapon {
	
	protected IWeapon weapon;
	
	public EnchancedWeapon(IWeapon weapon){
		this.weapon = weapon;
	}

	@Override
	public abstract int attack();

}
