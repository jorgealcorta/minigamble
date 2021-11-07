package minigamble;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5339514091919298198L;
	
	public Game() {
		new VentanaPrincipal("Minigamble!", this);
	}
	
	//HILOS

	private Thread thread;
	private boolean running = false; // Variable para guardar si el hilo está runeando o no

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}

	public static void main(String[] args) {
		new Game();
	}
}
