package com.CulLight.BasicGame.graphics;

import java.util.Random;

//This is our screen. This will write the pixels
public class Screen {
	
	private int width, height;
	public int[] pixels;
	
	public int[] tiles = new int[64 * 64];
	
	private Random random = new Random();
		
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < 64*64; i++) {
			// choose random color between black and white
			tiles[i] = random.nextInt(0xffffff);
		}
		
	}
	
	public void clear() {
		// blank screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void render() {
		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height) break;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x >= width) break;
				//speed up by not dividing by 16 but use bitshift
				int tileIndex = (x >> 4) + (y >> 4) * 64;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
	

}
