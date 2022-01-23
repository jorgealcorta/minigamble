package minigamble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import minigamble.Game.ESTADO;


/**
 * Clase encargada del juego 1 (Memory de cartas)
 */

public class Game1 extends minigame implements MouseMotionListener, MouseListener, Runnable, Renderizable { 
	
	private int mox;				
	private int moy;
	private int mdx;
	private int mdy;	
	
	private int nCol;
	private int nFil;
	private int cartX;
	private int cartY;
		
	private ArrayList<ArrayList<ArrayList<Integer>>> posCartas;
	
	private boolean bStart_state = false;
	private int fallos = 0;
	
	private ArrayList<Carta> allCards = new ArrayList<Carta>();
	private List<Carta> selectCards = new ArrayList<Carta>();
	
	private Carta c1 = new Carta("aNegra", false, false);
	private Carta c2 = new Carta("aNegra", false, false);
	private Carta c3 = new Carta("aRoja", false, false);
	private Carta c4 = new Carta("aRoja", false, false);
	private Carta c5 = new Carta("comodinNegro", false, false);
	private Carta c6 = new Carta("comodinNegro", false, false);
	private Carta c7 = new Carta("comodinRojo", false, false);
	private Carta c8 = new Carta("comodinRojo", false, false);
	private Carta c9 = new Carta("kNegra", false, false);
	private Carta c10 = new Carta("kNegra", false, false);
	private Carta c11 = new Carta("kRoja", false, false);
	private Carta c12 = new Carta("kRoja", false, false);
	private Carta c13 = new Carta("tresNegro", false, false);
	private Carta c14 = new Carta("tresNegro", false, false);
	private Carta c15 = new Carta("tresRojo", false, false);
	private Carta c16 = new Carta("tresRojo", false, false);
	
	
	private int start;
	private int click1 = -1;
	private int click2 = -1;
	private int puntTotal = 0;
	private float puntLocal =0;
	private double puntTemp;
	private String jugador;
	private int idPartida;
	private String primeraCarta = "";
	private long tiempoComienzo = System.currentTimeMillis();
	private long tiempoTotal;
	private long tiempoPrimeraCarta;
	
	private double puntuacionPorCarta;
	
	private int maxFallos;
	private int vidasRestadas = 0;
	private String superado;
	private Integer dificultad;
	

	
	
	/**
	 * Constructor del juego 1
	 * @param puntuacion Puntuacion del juego 1 segun la cual aparecerán más o menos cartas
	 * @param nombreJugador	nombre del jugador (se usa para la base de datos)
	 * @param idPart identificador de la partida (se usa para la base de datos)
	 */
	
	public Game1(int puntuacion, String nombreJugador, int idPart) {
		
		puntTotal = puntuacion;
		System.out.println("La puntuacion inicial con la que empieza es: " + puntTotal);
		jugador = nombreJugador;
		idPartida = idPart;
		
		
		if (puntTotal < 1500) {
			nCol = 3;
			nFil = 2;
			cartX = 165;
			cartY = 225;
			puntuacionPorCarta = (double) 500/3;
			maxFallos = 1;
			dificultad = 1;
		}else if(puntTotal < 3000) {
			nCol = 4;
			nFil = 2;
			cartX = 165;
			cartY = 225;
			puntuacionPorCarta = 500/4;
			maxFallos = 2;
			dificultad = 2;
		}else if(puntTotal < 4500) {
			nCol = 4;
			nFil = 3;
			cartX = 120;
			cartY = 164;
			puntuacionPorCarta = 500/6;
			maxFallos = 4;
			dificultad = 3;
		}else if(puntTotal >= 4500) {
			nCol = 4;
			nFil = 4;
			cartX = 98;
			cartY = 133;
			puntuacionPorCarta = 500/8;
			maxFallos = 5;
			dificultad = 4;
		}
		
		
		
		puntTemp = puntuacionPorCarta;
			
		allCards.add(c1);
		allCards.add(c2);
		allCards.add(c3);
		allCards.add(c4);
		allCards.add(c5);
		allCards.add(c6);
		allCards.add(c7);
		allCards.add(c8);
		allCards.add(c9);
		allCards.add(c10);
		allCards.add(c11);
		allCards.add(c12);
		allCards.add(c13);
		allCards.add(c14);
		allCards.add(c15);
		allCards.add(c16);
		
		
		selectCards  =  allCards.subList(0, nCol*nFil);
		
		
		Collections.shuffle(selectCards);
		
		for (Carta c : selectCards) {
			System.out.println(c.getId());
		}
			
		posCartas = generaMatriz(nCol, nFil, cartX, cartY);
		start = 3;
		
		Thread t = new Thread(this);
		t.start();
		
		
	}
	
	
	public void run() {
		
		for (int i = 0; i < (nCol*nFil); i++) {
			selectCards.get(i).setArriba(true);
		}
		
		delaySeg(2);
		
		for (int i = 0; i < (nCol*nFil); i++) {
			selectCards.get(i).setArriba(false);
		}
		
	}
	
	
	
	
	/**
	 * Obtener imagen de una Carta segun su posición en el array de cartas seleccionadas
	 * @param index posición de la carta en el array de cartas
	 * @return Devuelve la imagen de la carta.
	 */
	
	public Image getImagenCarta(int index) {
		
		if(selectCards.get(index).getId() == "aNegra") {
			return media.aNegra_IMG;
		}else if(selectCards.get(index).getId() == "aRoja") {
			return media.aRoja_IMG;
		}else if(selectCards.get(index).getId() == "comodinNegro") {
			return media.comodinNegro_IMG;
		}else if(selectCards.get(index).getId() == "comodinRojo") {
			return media.comodinRojo_IMG;
		}else if(selectCards.get(index).getId() == "tresNegro") {
			return media.tresNegro_IMG;
		}else if(selectCards.get(index).getId() == "tresRojo") {
			return media.tresRojo_IMG;
		}else if(selectCards.get(index).getId() == "kNegra") {
			return media.kNegra_IMG;
		}else if(selectCards.get(index).getId() == "kRoja") {
			return media.kRoja_IMG;
		}
		return null;
		
	}
	
	
	/**
	 * Comprueba si todas las cartas están levantadas
	 * @return Devuelve True si lo están y False si no
	 */
	public boolean todasLevantadas() {
		for(Carta c : selectCards) {
			if(!c.isArriba())
				return false;
		}
		return true;
	}
	
	/**
	 * Reproduce el sonido de dar la vuelta a la carta
	 */
	private void sonidoCarta() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/levantar_carta.wav");	//Continuación de la ruta hasta el archivo de audio 1
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
	 * Realiza un delay en el juego
	 * @param n numero de milisegundo que queremos  que dure el delay
	 */
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
	/**
	 * Genera una matriz encargada de la distribución de las cartas
	 * @param ncol Número de columnas de la matriz
	 * @param nfil Número de filas de la matriz
	 * @param xCarta Anchura de la carta
	 * @param yCarta Altura de la carta
	 * @return Devuelve la matriz con las posiciones en las que deben colocarse las cartas
	 */
	private ArrayList<ArrayList<ArrayList<Integer>>> generaMatriz(int ncol, int nfil, int xCarta, int yCarta) {
		ArrayList<ArrayList<ArrayList<Integer>>> matriz = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
		for(int i = 1; i <= nfil; i++) {
			ArrayList<ArrayList<Integer>> filas = new ArrayList<ArrayList<Integer>>();
			for(int j = 1; j <= ncol; j++) {
				int posX = (j*(1200/(ncol + 1)) - (xCarta/2));
				int posY = (i*(700/(nfil + 1)) - (yCarta/2));
				ArrayList<Integer> posicion = new ArrayList<Integer>();
				posicion.add(posX);
				posicion.add(posY);
				filas.add(posicion);
			}
			
			matriz.add(filas);
		}
		
		return matriz;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game1 ) {				
			mdx = e.getX();
			mdy = e.getY();
			
			if(start == 3) {
				int nCarta = 0;
				for (ArrayList<ArrayList<Integer>> filas : posCartas) {
					for(ArrayList<Integer> columnas : filas) {
						if(mouseOver(mdy, mdx, columnas.get(1), columnas.get(0), cartY, cartX) == false) {  
								selectCards.get(nCarta).setPresionada(false);
						}
						System.out.println(nCarta);
						nCarta++;
					}
				}
			}
		}
	}
	
		
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game1) {		
			
			mox = e.getX();	
			moy = e.getY();
		
			
			if (start == 3) {
				int nCarta = 0;
				for (ArrayList<ArrayList<Integer>> filas : posCartas) {
					for(ArrayList<Integer> columnas : filas) {
						if(mouseOver(moy, mox, columnas.get(1), columnas.get(0), cartY, cartX)) {  
							if(!selectCards.get(nCarta).isArriba()) {
								selectCards.get(nCarta).setPresionada(true);
							}
						}
						nCarta++;
					}	
				}	
			}
		}
	}
			
	
	public void mouseReleased(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game1) {
			
			
			if(start == 3) {
				if(click1==-1) {
					int nCarta = 0;
					for (ArrayList<ArrayList<Integer>> filas : posCartas) {
						for(ArrayList<Integer> columnas : filas) {
							if(selectCards.get(nCarta).isPresionada()) {
								if(primeraCarta.equals("")) {
									primeraCarta = selectCards.get(nCarta).getId();
									tiempoPrimeraCarta = System.currentTimeMillis() - tiempoComienzo;
								}
								selectCards.get(nCarta).setPresionada(false);
								sonidoCarta();
								delayMS(100);
								selectCards.get(nCarta).setArriba(true);
								click1=nCarta;
							}
							
							nCarta++;
						}
					}
					}else {
						int nCarta = 0;
						for (ArrayList<ArrayList<Integer>> filas : posCartas) {
							for(ArrayList<Integer> columnas : filas) {
								if(selectCards.get(nCarta).isPresionada()) { 
									selectCards.get(nCarta).setPresionada(false);
									sonidoCarta();
									delayMS(100);
									selectCards.get(nCarta).setArriba(true);
									click2=nCarta;
								}
								
								nCarta++;
							}
						}
						
						
						if(click2 != -1) {
							
							
							if(allCards.get(click1).getId() == selectCards.get(click2).getId()){
								puntLocal += puntTemp;
								puntTemp = puntuacionPorCarta;
								
							}else {
								                           
								delaySeg(2);
								puntTemp = (int)Math.round(0.5*puntTemp);
								selectCards.get(click1).setArriba(false);
								selectCards.get(click2).setArriba(false);
								fallos = fallos + 1;
							}
							
							click1=-1;
							click2=-1;
							
							if (todasLevantadas()) {
								tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
								delaySeg(2);
								
								superado = "true";
								if(fallos > maxFallos) {
									vidasRestadas = 1;
									superado = "false";
								}
								
								BaseDatos.insertarGame1(idPartida,(int) Math.round(puntLocal), fallos, primeraCarta, tiempoPrimeraCarta, tiempoTotal, superado, dificultad);
																
								Game.pi = new PantallaIntermedia(puntTotal,(int) Math.round(puntLocal), vidasRestadas, 0, jugador, idPartida);
								Game.estadoJuego = ESTADO.PantallaIntermedia;
								Game.eventoRaton();								
							}
							
						}
					}
				}
			}
		}	
	

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {		
	}

	public void mouseExited(MouseEvent e) {		
	}
	
   	public void mouseMoved(MouseEvent e) {			
	}
	
	/**	Evalua si el raton esta sobre una region
	 * @param mx posicion X del raton
	 * @param my posicion Y del raton
	 * @param x	posicion X en la que comienza la region
	 * @param y	posicion Y en la que comienza la region
	 * @param width	anchura de la region
	 * @param heigth altura de la region
	 * @return True si el raton esta sobre esa regio y False si no lo esta
	 */
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int heigth) {   // devuelve true si el raton ha sido presionado dentro de un cuadrado 
		
		if(mx > x && mx < x + width) {
			if(my > y && my < y + heigth) {
				return true;			
			}else {
				return false;
			}
		}else {
			return false;
		}}
	
	
	/** Hace el render de los elementos
	 * @param g recibe Grephics de Game
	 */
	public void render(Graphics g) {
	
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteImg, 0, 0, 1200, 672, null);
		}
		
		if(start==1) {
			g.setFont(media.customFontBot);
			g.setColor(Color.BLACK);
			if(bStart_state == true) {					                  
				g.drawImage(media.bStartIMG_True, 500, 294, null);
				g.drawString("Start", 540, 326);
			}else {									
				g.drawImage(media.bStartIMG_False, 500, 290, null);
				g.drawString("Start", 547, 322);
			}
		}
		
		
		if (start == 3) {
			
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);			
			g.setFont(media.customFontBot);
			g.setColor(Color.BLACK);
			
			
			int nCarta = 0;
			for (ArrayList<ArrayList<Integer>> filas : posCartas) {
				for(ArrayList<Integer> columnas : filas) {
					if(selectCards.get(nCarta).isArriba()) {
						g.drawImage(getImagenCarta(nCarta), columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}else {
						g.drawImage(media.reverso_IMG, columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}
					nCarta++;
				}
			}
			
			
			String strPunt = String.valueOf(Math.round(puntLocal));
			g.drawString(strPunt, 1020, 60);
			
		}

	}	
}
