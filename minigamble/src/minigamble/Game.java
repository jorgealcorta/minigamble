package minigamble;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5339514091919298198L;
	Inicio inicio = new Inicio(); 				// Pantalla inicio
	
	public Game() {
		new VentanaPrincipal("Minigamble!", this);
		this.addMouseListener(inicio); 				// añado un mouseListener a la pantalla inicio
		
		
		
	}
	
	//HILOS

	private Thread thread;
	private boolean running = false; // Variable para guardar si el hilo est� runeando o no

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
		
		//GAME LOOP//
		
		long lastTime = System.nanoTime(); // In miliseconds.
		double amountOfTicks = 60.0; // Ticks per second.
		double ns = 1000000000 / amountOfTicks; // Nanoseconds per tick.
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime(); // In miliseconds.
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) { // delta = 1 significa que ha pasado un tick.
				 tick();
				 delta--; // Reseteamos delta a 0.
			}
			if(running)
				render(); // Refresca la ventana, ejecutando render.
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) { // Escribe los FPS una vez cada segundo.
				timer += 1000; // Si la diferencia entre el tiempo real y el timer es de m�s de 1000ms, implica que ha pasado un segundo.
				System.out.println("FPS : " + frames); // Printea los frames que se han registrado en ese segundo.
				frames = 0; // Resetea frames.
			}
		}
		stop();
	}
	
	private void tick() {
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy(); // El Buffer Strategy representa el mecanismo para organizar memoria compleja en una ventana.
		if(bs == null) { // El valor default es null.
			this.createBufferStrategy(3); // El numero de buffers que crea
			return;
		}
		
		Graphics g = bs.getDrawGraphics();		
		
		inicio.render(g); 	// pinto la pantalla inicio
		
		g.dispose();
		bs.show();
		
	}

	public static void main(String[] args) {
		
		new Game();
		
	}
}
