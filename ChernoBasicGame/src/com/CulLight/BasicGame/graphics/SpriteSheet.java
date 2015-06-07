package com.CulLight.BasicGame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private final int SIZE; //size of spritesheet, not sprites
	private int[] pixels;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
	}
	
	public void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			//save image into pixels
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
