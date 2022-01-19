package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

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
		
		flechasActivas.removeAll(flechasActivas);
		flechasCreadas.removeAll(flechasCreadas);
		
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
		
		System.out.println(flechasCreadas);
		
		runThreadFlechasActivas();
		runThreadFlechasMain();
	}
	
	public void runThreadFlechasActivas() {
		ThreadFlechasActivas fa = new ThreadFlechasActivas(flechasCreadas, flechasActivas);
		Thread hfa = new Thread(fa);
		hfa.start();
	}
	
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
		
		aciertoReciente = false;
		
		for(Flecha f : flechasActivas) {
			//Damos margen de acierto con 64 pixeles de fallo arriba y abajo
			if(((key == 37 && f.getDir() == "izq") ||
				(key == 38 && f.getDir() == "arr") ||
				(key == 40 && f.getDir() == "abj") ||
				(key == 39 && f.getDir() == "dch")) && 

				f.getY()>386 && f.getY()<514) {
					
					System.out.println("acierto");
					algunAcierto = true;
					aciertoReciente = true;
				
					puntLocal += puntSumados;
					flechasActivas.remove(f);
					
					nPulsadas++;
					if(nPulsadas == nFlechas) todasPulsadas = true;
					
					if(todasPulsadas) {
						System.out.println("victoria");
						tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
						delaySeg(2);
						//BaseDatos.insertarGame1(idPartida, puntLocal, fallos, primeraCarta, tiempoPrimeraCarta, tiempoTotal);
						//new PantallaIntermedia(puntos + puntLocal, vidasRestadas, 5, jugador, idPartida);
						if(fallos > 2) {
						vidasRestadas = 1;
						superado = "false";
						}						
						BaseDatos.insertarGame6(idPartida, puntLocal, fallos, tiempoTotal, superado, dificultad);
						Game.pi = new PantallaIntermedia(puntos, puntLocal, vidasRestadas, 5, idJugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();
					}
			}
		}
		
		if(!aciertoReciente) System.out.println("miss");
		if(!aciertoReciente) fallos++;
		
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

		