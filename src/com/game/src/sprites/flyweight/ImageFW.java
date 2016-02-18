package com.game.src.sprites.flyweight;

import java.awt.image.BufferedImage;

public class ImageFW implements IFly {
	
	private BufferedImage image;
	
	public BufferedImage getImage(){
		return image;
	}
	
	protected ImageFW(BufferedImage image){
		this.image = image;
	}

}
