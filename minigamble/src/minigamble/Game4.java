package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import minigamble.Game.ESTADO;


public class Game4 implements MouseMotionListener, MouseListener, Renderizable { //Dianas
	
			
	// CopyOnWriteArrayList que almacena las dianas generadas aleatoriamente.
	private static CopyOnWriteArrayList<Diana> dianasCreadas = new CopyOnWriteArrayList<Diana>();
	
	// CopyOnWriteArrayList que almacena las dianas que se encuentran en pantalla.
	private static CopyOnWriteArrayList<Diana> dianasActivas = new CopyOnWriteArrayList<Diana>();
	
	// CopyOnWriteArrayList que almacena todas las dianas que ya se han disparado, y ha pasado el tiempo para que desaparezcan mediante el hilo.
	private static CopyOnWriteArrayList<Diana> dianasRotas = new CopyOnWriteArrayList<Diana>();
	
	private static int mox;				//Posicion en la que se presiona el raton
	private static int moy;
	
	// Hilo que se utilizar� para gestionar las dianas activas.
	private Thread hda;
	
	public static int puntos;
	private static int puntSumados;
	public static int puntLocal;
	public static int idPartida;
	public static String jugador;
	public static int fallos;
	public static long tiempoComienzo = System.currentTimeMillis();
	public static long tiempoTotal;
	private int vidasRestadas = 0;
	
	// Numero de dianas, que se ajustara conforme a la dificultad del juego.
	private int nDianas;
	
	// Booleano utilizado para indicar que todas las dianas han sido disparadas.
	public static boolean todasRotas = false;
	private boolean unaRota = false;
	
	public static String superado = "true";
	public static int dificultad = 0;

	/**
	 * Constructor de la clase Game4
	 * @param puntuacion acumulada
	 * @param nombreJugador
	 * @param idPart identificador de la partida
	 */
	
	public Game4(int puntuacion, String nombreJugador, int idPart){
		
//		dianasCreadas = new CopyOnWriteArrayList<Diana>();
		dianasCreadas.removeAll(dianasCreadas);
//		
//		dianasActivas = new CopyOnWriteArrayList<Diana>();
		dianasActivas.removeAll(dianasActivas);
//		
//		dianasRotas = new CopyOnWriteArrayList<Diana>();
		dianasRotas.removeAll(dianasRotas);

		
		puntos = puntuacion;
		idPartida = idPart;
		jugador = nombreJugador;
		
		// Reset
		puntLocal = 0;
		
		fallos = 0;
		vidasRestadas = 0;
		
				
		
		tiempoComienzo = System.currentTimeMillis();

		//Establecemos el numero de dianas que apareceneran, que aumentara conforme la puntuacion acumulada aumente.
		if(puntos >= 0 && puntos < 1500) {
			nDianas = 5;
			dificultad = 1;
		}else if(puntos >= 1500 && puntos < 3000) {
			nDianas = 10;
			dificultad = 2;
		}else if(puntos >= 3000 && puntos < 4500) {
			nDianas = 15;
			dificultad = 3;
		}else if(puntos >= 4500) {
			nDianas = 20;
			dificultad = 4;
		}
		
		todasRotas = false;
		
		/*
		 * Se crean dianas aleatoriamente, primero se genera el size de la diana, y segun ese tamanyo se estableceran los
		 * limites para las coordenadas x e y de la diana, con el fin de que no se salgan de la ventana en ningun momento.
		 */
		
		for(int i=0; i<nDianas; i++) {
			int rSize = (int)(Math.random() * (1000/nDianas)) + 50;
			Diana dRandom = new Diana((int)(Math.random() * (1200 - rSize + 1)), (int)(Math.random() * (660-rSize + 1)), rSize, false);
			dianasCreadas.add(dRandom);
		}
		
		//Una partida perfecta implica una suma de 500 puntos
		puntSumados = (int)(500 / (dianasCreadas.size()));

		runThreadDianasActivas();

	    
	}
	
	
	// Metodo para lanzar el hilo que se encarga de insertar, con un delay, las dianas creadas aleatoriamente,
	// al CopyOnWriteArrayList de las dianas activas que se dibujaran.

	public void runThreadDianasActivas(){
		ThreadDianasActivas da = new ThreadDianasActivas(dianasCreadas, dianasActivas);
		hda = new Thread(da);
		hda.start();
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
	 * Reproduce el sonido del disparo
	 */
	private void sonidoDisparo() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/Game4/disparo.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}
	
	/**
	 * Reproduce el sonido de acertar
	 */
	private void sonidoDiana() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/Game4/diana.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
	        sonido.open(ais);
	        sonido.start();
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}
	
	
	/**	Evalua si el raton esta sobre una region
	 * @param mx posicion X del raton
	 * @param my posicion Y del raton
	 * @param x	posicion X en la que comienza la region
	 * @param y	posicion Y en la que comienza la region
	 * @param width	anchura de la region
	 * @param heigth altura de la region
	 * @return true si el raton esta sobre esa region y dalse si no lo esta
	 * 
	 */
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int heigth) {
		
		if(mx > x && mx < x + width) {
			if(my > y && my < y + heigth) {
				return true;			
			}else {
				return false;
			}
		}else {
			return false;
		}}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		unaRota = false;
		
		if(Game.estadoJuego==Game.ESTADO.Game4) {
			// Guardamos la posicion en la que se pulsa el raton.
			mox = e.getX();
			moy = e.getY();
			
			//Reproducir sonido
			sonidoDisparo();
			
			
			/*
			 * Verifica si el click esta sobre alguna de las dianas.
			 * Si lo esta, pone el atributo rota de la diana a true, y lanza el hilo de borrar diana con la(s) diana(s) acertada(s).
			 */
			
			for(Diana d : dianasActivas) {
				
				if(e.getX() >= d.getX() && e.getX() <= d.getX() + d.getSize() && e.getY() >= d.getY() && e.getY() <= d.getY() + d.getSize() && !d.isRota()) {
					sonidoDiana();
					d.setRota(true);
					
					ThreadBorrarDiana bd = new ThreadBorrarDiana(d, dianasActivas, dianasRotas);
					Thread hbd = new Thread(bd);
					hbd.start();
					
					puntLocal += puntSumados;
					unaRota = true;
					
					// Comprueba si todas las dianas estan rotas
					
					for(Diana dd : dianasCreadas) {
						
						if(!dd.isRota()) {
							todasRotas = false;
							break;
						}
						todasRotas = true;
					}
					
					// Si todas las dianas estan rotas, el usuario ha ganado.
					
					if(todasRotas) {
						tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
						delaySeg(2);
						if(fallos>=3) {
							vidasRestadas = 1;
							superado = "false";
						}
						
						
						BaseDatos.insertarGame4(idPartida, puntLocal, fallos, tiempoTotal, superado, dificultad);
						
						
						Game.pi = new PantallaIntermedia(puntos, puntLocal, vidasRestadas, 3, jugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();
					}
					
					// Reproducir clip de sonido al disparar
					
					
					
				}
				
			
			}
			
			// Si no se ha roto ninguna de las dianas con el disparo efectuado, miss.
			
			if(!unaRota) {
				fallos++;
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mox = e.getX();	// guarda la posicion en la que esta el raton
		moy = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mox = e.getX();	// guarda la posicion en la que esta el raton
		moy = e.getY();
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteImg, 0, 0, 1200, 672, null);
		}
			
			// Cada frame se dibujan todas las dianas en dianasActivas, comprobando el booleano rota para decidir si dibujar la diana
			// entera o rota
		for(Diana d : dianasActivas) {
			if(!d.isRota()) {
				g.drawImage(media.diana_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
			}else if(d.isRota() && !dianasRotas.contains(d)){
				g.drawImage(media.dianaRota_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
				
				Font fuente = media.customFontBot;
				fuente = fuente.deriveFont(Font.PLAIN, (int)d.getSize()/2);
				g.setFont(fuente);
				g.drawString(String.valueOf(puntSumados), d.getX() + (int)(d.getSize()/2), d.getY()+(int)(d.getSize()/3));
			}
		}
		
		//En cada frame se dibuja el punto de mira encima del raton
		g.drawImage(media.mira_IMG, mox-16, moy-16, 32, 32, null);		
		g.drawImage(media.mira_IMG, mox-16, moy-16, 32, 32, null);	
	}

}