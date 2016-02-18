package com.game.src.sprites;

import java.awt.image.BufferedImage;

import com.game.src.main.Game;

public class Textures {
	
	public static BufferedImage player, missle, mountain, field, water, forest, chest, chestOpen, fire;
	
	public static BufferedImage enemyFurry, enemyMage;
	
	public static BufferedImage spellHeal, spellFireball;
	
	public static BufferedImage door, doorOpen;
	
	public static BufferedImage princess;
	
	private SpriteSheet sprite_sheet = null;
	
	public Textures(Game game){
		sprite_sheet = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private void getTextures() {
		fire = sprite_sheet.grabImage(2, 7, 32, 32);
		player = sprite_sheet.grabImage(1, 8, 32, 32);
		missle = sprite_sheet.grabImage(1, 9, 32, 32);
		enemyFurry = sprite_sheet.grabImage(1, 10, 32, 32);
		enemyMage = sprite_sheet.grabImage(2, 10, 32, 32);
		field = sprite_sheet.grabImage(1, 6, 32, 32);
		mountain = sprite_sheet.grabImage(2, 6, 32, 32);
		water = sprite_sheet.grabImage(3, 6, 32, 32);
		forest = sprite_sheet.grabImage(4, 6, 32, 32);
		chest = sprite_sheet.grabImage(5, 6, 32, 32);
		chestOpen = sprite_sheet.grabImage(6, 6, 32, 32);
		
		spellHeal = sprite_sheet.grabImage(1, 7, 32, 32);
		spellFireball = sprite_sheet.grabImage(2, 7, 32, 32);
		
		door = sprite_sheet.grabImage(7, 6, 32, 32);
		doorOpen = sprite_sheet.grabImage(8, 6, 32, 32);
		
		princess = sprite_sheet.grabImage(1, 9, 32, 32);
	}

}
