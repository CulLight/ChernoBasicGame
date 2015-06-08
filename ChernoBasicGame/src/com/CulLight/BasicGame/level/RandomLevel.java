package com.CulLight.BasicGame.level;

import java.util.Random;

public class RandomLevel extends Level {
//since level has no default constructor (Level())
//RandomLevel needs to have one of the super constructor

	private final Random random = new Random();
	
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	// since this method has same name as a function in Level
	// and both are protected, this method will overwrite the
	//Level.generateRandomLevel() function. This would not be the case
	//if the levels are private
	protected void generateRandomLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = random.nextInt(4); //assume 4 different tiles
			}
		}
	}

}
