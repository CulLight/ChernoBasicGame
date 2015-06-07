package com.CulLight.BasicGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.CulLight.BasicGame.graphics.Screen;
import com.CulLight.BasicGame.input.Keyboard;


public class Game extends Canvas implements Runnable{
	//canvas to draw on
	private static final long serialVersionUID = 1L;
	
	
	
	
	//FIELDS
	
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	
	public static String title = "ChernoGame";
	private Thread thread;
	private JFrame frame;
	private boolean bRunning = false;
	
	//final rendered view
	//alpha is transparency but dont need that here
	//Graphics g will draw this image
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	//get color of each pixel
	//if we set pixels to sth new, the pixels within image get automaticelly updated
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private Keyboard keyboard;
			
	
	
	
	//CONSTRUCTOR
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size); //inherited canvas method
		
		screen = new Screen(width, height);	
		frame = new JFrame();
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);
	}
	
	
	
	
	//METHODS
		
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		//add content to our frame (possible because we are subclass of canvas
		game.frame.add(game);
		// set the size of the window
		game.frame.pack();
		//without next command, program keeps running, but window is closed  (threads still running)
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//center window
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
//		game.frame.setFocusable(true);
//		game.frame.toFront();
//		game.frame.requestFocus();
		
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
		long lastTime = System.nanoTime();
		// need timer to measure one second
		long timer = System.currentTimeMillis();
		// 1/60th of a second
		final double ns = 1000000000.0 / 60.;
		double delta = 0;
		int frames = 0;
		// updates should be 60 times every secod
		int updates = 0;
		
		while (bRunning) {
			long nowTime = System.nanoTime();
			delta += (nowTime - lastTime) / ns;
			lastTime = nowTime;
			//update only every 1/60s = 16 ms
			while (delta >= 1) {
				update();
				delta--;
//				System.out.println(delta);
				updates++;
			}
			render();
			frames++;
			
			// do every 1 second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	int x = 0, y = 0;
	public void update() {
		keyboard.update();
		if (keyboard.up) y--;
		if (keyboard.down) y++;
		if (keyboard.right) x++;
		if (keyboard.left) x--;
	}
	
	public void render() {
		//we dont want to render image live (as fast possible)
		//therefore we need to store the new frame somewhere -> buffer
		BufferStrategy bs = getBufferStrategy(); //retrieves BufferStrategy from canvas
		if (bs == null) { 
			// why 3
			// 2: would have only two buffers, which would be frame storage place and the screen it will be displayed
			// 3: have two frame storage places -> speed improvement
			// because we can work on new frame, before frame in buffer storage 1 is displayed on screen
			createBufferStrategy(3);
			return;
		}
		//black out screen
		screen.clear();
		screen.render(x, y);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//image gets scaled (images is 300 x 300/16*9)
		// but Frame size is 3*300 x 3*300/16*9
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		
		bs.show();
	}
	
	
}
