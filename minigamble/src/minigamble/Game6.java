package minigamble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import minigamble.Game.ESTADO;

public class Game6 implements KeyListener{
	
	private boolean aciertoReciente = false;
	
	public static int puntos;
	private int puntSumados;
	public static int puntLocal = 0;
	public static int idPartida;
	public static String idJugador;
	public static int fallos;
	public static long tiempoComienzo = System.currentTimeMillis();
	public static long tiempoTotal;
	private int vidasRestadas = 0;
	public static boolean algunAcierto = false;
	
	public static boolean todasPulsadas = false;
	public static int nPulsadas = 0;

	
	public static int nFlechas = 5;
	
	public static int dificultad = 1;
	public String superado = "true";
	
	private String dirPosibles[] = {"izq", "arr", "abj", "dch"};
	
	private CopyOnWriteArrayList<Flecha> flechasCreadas = new CopyOnWriteArrayList<Flecha>();
	public static CopyOnWriteArrayList<Flecha> flechasActivas = new CopyOnWriteArrayList<Flecha>();

	public int check ;
	/**
	 * Constructor de la clase Game6
	 * @param puntuacion acumulada
	 * @param Jugador
	 * @param idPart identificador de la partida
	 */
	
	public Game6(int puntuacion, String Jugador, int Partida) {
		
		
		puntos = puntuacion;
		idJugador = Jugador;
		idPartida = Partida;
		puntLocal =0;
		check =1;
		vidasRestadas=0;
		
		flechasActivas.removeAll(flechasActivas);
		flechasCreadas.removeAll(flechasCreadas);
		
		// Establecemos el numero de flechas de acuerdo con la puntuacion actual de la partida
		if(puntos >= 0 && puntos < 1500) {
			nFlechas = 5;
		}else if(puntos >= 1500 && puntos < 3000) {
			nFlechas = 10;
			dificultad = 2;
		}else if(puntos >= 3000 && puntos < 4500) {
			nFlechas = 15;
			dificultad = 3;
		}else if(puntos >= 4500) {
			nFlechas = 20;
			dificultad = 4;
		}
		
		todasPulsadas = false;
		nPulsadas = 0;
		
		// Crear ArrayList de flechas aleatorias
		for(int i = 0; i<nFlechas; i++) {
			String dirRandom = dirPosibles[new Random().nextInt(dirPosibles.length)];
			Flecha f = new Flecha(dirRandom);
			flechasCreadas.add(f);
		}
		
		// Una partida perfecta implica sumar 500 puntos.
		puntSumados = (int)(500/(flechasCreadas.size()));
		runThreadFlechasActivas();
		runThreadFlechasMain();
	}
	
	/**
	 * Ejecuta el hilo que gestiona las flechas activas
	 */
	public void runThreadFlechasActivas() {
		ThreadFlechasActivas fa = new ThreadFlechasActivas(flechasCreadas, flechasActivas);
		Thread hfa = new Thread(fa);
		hfa.start();
	}
	
	/**
	 * Ejecuta el hilo que gestiona todas las flechas
	 */
	public void runThreadFlechasMain() {
		ThreadFlechasMain fm = new ThreadFlechasMain(flechasActivas);
		Thread hfm = new Thread(fm);
		hfm.start();
	}
	

	/**
	 * Realiza un delay en Segundos
	 * @param n numero de segundos que se quiere hacer el delay
	 */
	
	private void delaySeg(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
	/**
	 * Reproduce el sonido Izquierda
	 */
	private void sonidoIzq() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/simonSays/simonSound1.wav");	//Continuaci贸n de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error sonido izq");
        }
		
	}
	
	/**
	 * Reproduce el sonido Arriba
	 */
	private void sonidoArriba() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/simonSays/simonSound2.wav");	//Continuaci贸n de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error sonido up");
        }
		
	}
	
	/**
	 * Reproduce el sonido Derecha
	 */
	private void sonidoDcha() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/simonSays/simonSound3.wav");	//Continuaci贸n de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error sonido derecha");
        }
		
	}
	
	/**
	 * Reproduce el sonido Abajo
	 */
	private void sonidoAbajo() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/simonSays/simonSound4.wav");	//Continuaci贸n de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error sonido abajo");
        }
		
	}

	


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
//		izq 37
//		arr 38
//		abj 40
//		dch 39
			
		if(check==1) {
			
			
			switch(key) {
				case 37: sonidoIzq(); break;
				case 38: sonidoArriba(); break;
				case 39: sonidoDcha(); break;
				case 40: sonidoAbajo(); break;
			}
			
			aciertoReciente = false;
			
			for(Flecha f : flechasActivas) {
				//Damos margen de acierto con 64 pixeles de fallo arriba y abajo
				if(((key == 37 && f.getDir() == "izq") ||
					(key == 38 && f.getDir() == "arr") ||
					(key == 40 && f.getDir() == "abj") ||
					(key == 39 && f.getDir() == "dch")) && 
	
					f.getY()>386 && f.getY()<514) {
						
						algunAcierto = true;
						aciertoReciente = true;
						System.out.println("acierto");
						
					
						nPulsadas++;
						if(nPulsadas == nFlechas) todasPulsadas = true;
						
						puntLocal += puntSumados;
						flechasActivas.remove(f);
						
						
						if(todasPulsadas) {
							tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
							delaySeg(2);
							//BaseDatos.insertarGame1(idPartida, puntLocal, fallos, primeraCarta, tiempoPrimeraCarta, tiempoTotal);
							//new PantallaIntermedia(puntos + puntLocal, vidasRestadas, 5, jugador, idPartida);
							if(fallos > 2) {
								vidasRestadas = 1;
								superado = "false";
								System.out.println("vidas restadas menos uno");
							}			
							
							check=0;
							System.out.println("guardando desde game");
							BaseDatos.insertarGame6(idPartida, puntLocal, fallos, tiempoTotal, superado, dificultad);
							System.out.println("creando nueva PI de vidasRes " + vidasRestadas);
							Game.pi = new PantallaIntermedia(puntos, puntLocal, vidasRestadas, 5, idJugador, idPartida);
							
							
							System.out.println("pint creada");
							Game.estadoJuego = ESTADO.PantallaIntermedia;
							Game.eventoRaton();
							
							
							
						}
				}
			}
			
			System.out.println("acierto reciente es " + aciertoReciente + "check es " + check);
			if(!aciertoReciente) {
				fallos++;
				System.out.println("fallo a馻dido");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Graphics g) {
		
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteChainz_IMG, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteChainz_IMG, 0, 0, 1200, 672, null);
		}
		
		g.drawImage(media.flechaizqtrans_IMG, 240, 495, 128, 128, null);
		g.drawImage(media.flechaarrtrans_IMG, 435, 495, 128, 128, null);
		g.drawImage(media.flechaabjtrans_IMG, 623, 495, 128, 128, null);
		g.drawImage(media.flechadchtrans_IMG, 820, 495, 128, 128, null);
		
		g.setColor(Color.WHITE);
		g.setFont(media.customFontBot);
		g.drawString(String.valueOf(puntLocal), 1050, 100);
		
		for(Flecha f : flechasActivas) {
			if(f.getDir() == "izq" && f.getY()<700) {
				g.drawImage(media.flechaizq_IMG, 240, f.getY(), 128, 128, null);
			}else if (f.getDir() == "arr" && f.getY()<700) {
				g.drawImage(media.flechaarr_IMG, 435, f.getY(), 128, 128, null);
			}else if (f.getDir() == "abj" && f.getY()<700) {
				g.drawImage(media.flechaabj_IMG, 623, f.getY(), 128, 128, null);
			}else if (f.getDir() == "dch" && f.getY()<700) {
				g.drawImage(media.flechadch_IMG, 820, f.getY(), 128, 128, null);

			}
		}

	}
}

		