package com.game.src.sprites.flyweight;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.game.src.main.Game;
import com.game.src.sprites.SpriteSheet;

public class ImageFactory {

	private static Map<String, IFly> flyMap = new HashMap<String, IFly>();

	private static SpriteSheet sprite_sheet;

	public ImageFactory(Game game) {

		sprite_sheet = new SpriteSheet(game.getSpriteSheet());

	}

	public static BufferedImage getImage(String key) {

		if (flyMap.get(key) != null || setImage(key)) {

//			if (setImage(key))
				return flyMap.get(key).getImage();
		}

		return null;
	}

	public static void emptyMap(){
		flyMap.clear();
	}
	
	private static boolean setImage(String key) {

		if (key == "fire") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 7, 32, 32)));
			return true;
		}
		if (key == "player") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 8, 32, 32)));
			return true;
		}
		if (key == "weapon") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 8, 32, 32)));
			return true;
		}
		if (key == "fireEnch") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(3, 8, 32, 32)));
			return true;
		}
		if (key == "holyEnch") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(4, 8, 32, 32)));
			return true;
		}
		if (key == "missle") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 9, 32, 32)));
			return true;
		}
		if (key == "enemyFurry") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 10, 32, 32)));
			return true;
		}
		if (key == "enemyMage") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 10, 32, 32)));
			return true;
		}
		if (key == "enemyBoss") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 2, 32, 32)));
			return true;
		}
		if (key == "field") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 6, 32, 32)));
			return true;
		}
		if (key == "mountain") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 6, 32, 32)));
			return true;
		}
		if (key == "water") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(3, 6, 32, 32)));
			return true;
		}
		if (key == "forest") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(4, 6, 32, 32)));
			return true;
		}
		if (key == "chest") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(5, 6, 32, 32)));
			return true;
		}
		if (key == "chestOpen") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(6, 6, 32, 32)));
			return true;
		}
		if (key == "spellHeal") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 7, 32, 32)));
			return true;
		}
		if (key == "spellFireball") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 7, 32, 32)));
			return true;
		}
		if (key == "door") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(7, 6, 32, 32)));
			return true;
		}
		if (key == "doorOpen") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(8, 6, 32, 32)));
			return true;
		}
		if (key == "princess") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(1, 9, 32, 32)));
			return true;
		}
		if (key == "potion") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(2, 9, 32, 32)));
			return true;
		}
		if (key == "fireItem") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(3, 9, 32, 32)));
			return true;
		}
		if (key == "holyItem") {
			flyMap.put(key, new ImageFW(sprite_sheet.grabImage(4, 9, 32, 32)));
			return true;
		}

		return true;
	}
	
	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();

        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, 0);
        g2.dispose();
        return newImage;
    }
	
	public static BufferedImage joinBufferedImage(String img1S, String img2S) {
		
		BufferedImage img1 = getImage(img1S);
		BufferedImage img2 = getImage(img2S);

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();

        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, 0);
        g2.dispose();
        return newImage;
    }
	
public static BufferedImage joinBufferedImage(BufferedImage img1, String img2S) {
		
		BufferedImage img2 = getImage(img2S);

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();

        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, 0, 0);
        g2.dispose();
        return newImage;
    }

}
