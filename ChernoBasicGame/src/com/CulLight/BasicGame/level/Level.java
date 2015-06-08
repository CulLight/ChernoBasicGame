package com.CulLight.BasicGame.level;

import com.CulLight.BasicGame.graphics.Screen;

public class Level {
	//every new level will make new instance of level class
	
	protected int width, height;
	//contains integers. each integer stands for one type of tile, either stone, wood, grass...
	protected int[] tiles;
	
	//random level
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateRandomLevel();
	}
	
	//load level from file
	public Level(String path) {
		loadLevel(path);
	}
	
	protected void generateRandomLevel() {
	}
	
	private void loadLevel(String path) {
	}
	
	//update level, AI, position of entities
	public void update () {	
	}
	
	private void time() {
	}

	public void render(int cScroll, int yScroll, Screen screen) {
	}
}
