package com.CulLight.BasicGame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{
	//canvas to draw on
	private static final long serialVersionUID = 1L;
	
	//FIELDS
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	private Thread thread;
	private JFrame frame;
	private boolean bRunning = false;
	
	//CONSTRUCTOR
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size); //inherited canvas method
		
		frame = new JFrame();
	}
	
	//METHODS
		
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("ChernoGame");
		//add content to our frame (possible because we are subclass of canvas
		game.frame.add(game);
		// set the size of the window
		game.frame.pack();
		//without next command, program keeps running, but window is closed  (threads still running)
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//center window
		game.frame.setLocationRelativeTo(null);
		game.setVisible(true);
		
		game.start();
	}
	
	
	//synchronized
	//When one thread is executing a synchronized method for an object, all other threads that invoke 
	//synchronized methods for the same object block (suspend execution) until the first thread is done with the object.
	//Synchronized methods enable a simple strategy for preventing thread interference and memory consistency errors
	public synchronized void start() {
		bRunning = true;
		thread = new Thread(this, "Game"); // this = new Game()
		thread.start();
	}
	
	public synchronized void stop() {
		bRunning = false;
		try {
			//wait for the thread to die
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		while (bRunning) {
		}
	}
	
	
	
}
