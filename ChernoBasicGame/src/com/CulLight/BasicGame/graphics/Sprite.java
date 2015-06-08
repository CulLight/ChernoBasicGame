package com.CulLight.BasicGame.graphics;

public class Sprite {

	//sprite size
	public final int SIZE;
	//coordinates of sprite on spritesheet
	private int x, y;
	public int[] pixels;
	// which spritesheed this sprite is in
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, SpriteSheet.tiles, 0, 0);
	
	public Sprite(int size, SpriteSheet sheet, int x, int y) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.sheet = sheet;
		//coordinate on spritesheet is x * size
		this.x = x * size;
		this.y = y;
		load();
	}
	
	//load sprite from spritesheet into this.pixels
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y*SIZE] = sheet.pixels[x + this.x + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
