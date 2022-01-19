package minigamble;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.FileReader;





public class Game extends Canvas implements Runnable{
	
	
	public static int game1Check =0;
	public static int game2Check =0;
	public static int game3Check =0;
	public static int game4Check =0;
	public static int game5Check =0;
	public static int game6Check =0;
	public static int game7Check =0;
	public static int piCheck =0;
	
	public static Partida partida;
	public static Game1 game1;
	public static Game2 game2;
	public static Game3 game3;
	public static Game4 game4;
	public static Game5 game5;
	public static Game6 game6;
	public static Game7 game7;
	public static PantallaIntermedia pi;
	
	public static media media;
	
	public static VentanaPrincipal ventana;
	
	private static final long serialVersionUID = -5339514091919298198L;
	Inicio inicio = new Inicio(); 				// Pantalla inicio
	StartScreen start = new StartScreen();
	SignIn signin = new SignIn();
	LogIn login = new LogIn();
	public static Game game;



	
	//Enumeracion con los distintos estados del juego.
	public enum ESTADO{
		Inicio,
		Start,
		LogIn,
		SignIn,
		Game1, // PAREJAS
		Game2, // SIMON SAYS
		Game3, // LABERINTO
		Game4, // DIANAS
		Game5, // TRAGAPERRAS
		Game6,  // FLECHAS CAYENDO
		Game7,
		PantallaIntermedia
	};
	
	static ESTADO estadoJuego = ESTADO.Inicio;
	
	
	public Game() {
		
		media = new media();
		ventana = new VentanaPrincipal("Minigamble!", this) ;
		this.addMouseListener(inicio); 				// anyado un mouseListener a la pantalla inicio
		this.addMouseMotionListener(inicio);
		this.addMouseListener(start); 				// anyado un mouseListener a la pantalla start
		this.addMouseMotionListener(start);
		this.addMouseListener(signin); 				// anyado un mouseListener a la pantalla signin
		this.addMouseMotionListener(signin);
		this.addKeyListener(signin);
		this.addMouseListener(login); 				
		this.addMouseMotionListener(login);
		this.addKeyListener(login);
		
		
//		this.addMouseListener(game1); 				// anyado un mouseListener a la pantalla game1
//		this.addMouseMotionListener(game1);
//		this.addKeyListener(game2);
		
		
		
	}
	
	public static void eventoRaton(){
		
		
		if (estadoJuego == ESTADO.Game1 && game1Check==0) {
			
			game.addMouseListener(game1);
			game.addMouseMotionListener(game1);
			game1Check=1;
			System.out.println("game1 listener add");
			
		}else if (estadoJuego == ESTADO.Game2 && game2Check==0){
			
			game.addKeyListener(game2);
			game2Check=1;
			System.out.println("game2 listener add");
			
		}else if(estadoJuego == ESTADO.Game3 && game3Check==0) {
			game.addMouseListener(game3);
			game.addMouseMotionListener(game3);
			game3Check=1;
			System.out.println("game3 listener add");
			
		}else if(estadoJuego == ESTADO.Game4 && game4Check==0) {
			game.addMouseListener(game4);
			game.addMouseMotionListener(game4);
			game4Check=1;
			System.out.println("game4 listener add");
			
		}else if(estadoJuego == ESTADO.Game5 && game5Check==0) {
			game.addKeyListener(game5);
			game5Check=1;
			System.out.println("game5 listener add");
			
		}else if(estadoJuego == ESTADO.Game6 && game6Check==0) {
			game.addKeyListener(game6);
			game6Check=1;
			System.out.println("game6 listener add");
			
		}else if(estadoJuego == ESTADO.Game7 && game7Check==0) {
			game.addMouseListener(game7);
			game7Check=1;
			System.out.println("game7 listener add");
			
		}else if(estadoJuego == ESTADO.PantallaIntermedia && piCheck==0) {
			game.addKeyListener(pi);
			piCheck=1;
			System.out.println("pi listener add");
			
		}
	}
	

	
	//HILOS

	private Thread thread;
	private boolean running = false; // Variable para guardar si el hilo esta runeando o no

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
				timer += 1000; // Si la diferencia entre el tiempo real y el timer es de mas de 1000ms, implica que ha pasado un segundo.
				System.out.println("FPS : " + frames + " " + estadoJuego); // Printea los frames que se han registrado en ese segundo.
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


		
		switch(estadoJuego) {
		
		case Inicio:
			inicio.render(g); 	
			break;
		case Start:
			start.render(g);
			break;	
		case SignIn:
			signin.render(g);
			break;
		case Game1:
			game1.render(g);
			break;
		case Game2:
			game2.render(g);
			break;
		case Game3:
			game3.render(g);
			break;
		case Game4:
			game4.render(g);
			break;
		case Game5:
			game5.render(g);
			break;
		case Game6:
			game6.render(g);
			break;
		case Game7:
			game7.render(g);
			break;
		case PantallaIntermedia:
			pi.render(g);
			break;
		case LogIn:
			login.render(g);
			break;
		default:
			break;
		}
		

		g.dispose();
		bs.show();
		
		
	}

	public static void main(String[] args) {
				
		game = new Game();
	}

}
