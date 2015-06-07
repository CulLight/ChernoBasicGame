package com.CulLight.BasicGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	//one boolean for every key on keyboard
	private boolean[] keys = new boolean[120]; //65536 is max array length
	public boolean up, down, left, right;
	
	public void update() {
		//check if key is left
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		
//		for (int i = 0; i < keys.length; i++) {
//			if (keys[i]) {
//				System.out.println("Key: " + i);
//			}
//		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {	
	}



}
