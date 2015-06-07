package com.CulLight.BasicGame.graphics;

import java.util.Random;

//This is our screen. This will write the pixels
public class Screen {
	
	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
		
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
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
	
	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
//			if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset; 
//				if (xx < 0 || xx >= width) break;
				//speed up by not dividing by 16 but use bitshift
				int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
	

}
