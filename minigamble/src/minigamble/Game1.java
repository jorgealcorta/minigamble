package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;


/**
 * Clase encargada del juego 1 (Memory de cartas)
 */

public class Game1  implements MouseMotionListener, MouseListener { 
	
	private int mox;				
	private int moy;
	private int mdx;
	private int mdy;	
	
	private int nCol;
	private int nFil;
	private int cartX;
	private int cartY;
	
	private Font customFontG1;
	
	private ArrayList<ArrayList<ArrayList<Integer>>> posCartas;
	
	private boolean bStart_state = false;
	private int nivel = 1;
	private int fallos = 0;
	
	private ArrayList<Carta> allCards = new ArrayList<Carta>();
	private List<Carta> selectCards = new ArrayList<Carta>();
	
	private Carta c1 = new Carta("card_hearts_A", false, false);
	private Carta c2 = new Carta("card_hearts_Q", false, false);
	private Carta c3 = new Carta("card_hearts_K", false, false);
	private Carta c4 = new Carta("card_hearts_A", false, false);
	private Carta c5 = new Carta("card_hearts_Q", false, false);
	private Carta c6 = new Carta("card_hearts_K", false, false);
	private Carta c7 = new Carta("card_clubs_A", false, false);
	private Carta c8 = new Carta("card_clubs_Q", false, false);
	private Carta c9 = new Carta("card_clubs_K", false, false);
	private Carta c10 = new Carta("card_clubs_A", false, false);
	private Carta c11 = new Carta("card_clubs_Q", false, false);
	private Carta c12 = new Carta("card_clubs_K", false, false);
	private Carta c13 = new Carta("card_hearts_J", false, false);
	private Carta c14 = new Carta("card_clubs_J", false, false);
	private Carta c15 = new Carta("card_hearts_J", false, false);
	private Carta c16 = new Carta("card_clubs_J", false, false);
	private Carta c17 = new Carta("card_diamonds_A", false, false);
	private Carta c18 = new Carta("card_diamonds_K", false, false);
	private Carta c19 = new Carta("card_diamonds_A", false, false);
	private Carta c20 = new Carta("card_diamonds_K", false, false);
	
	private int start = 1;
	private int click1 = -1;
	private int click2 = -1;
	private int puntTotal = 0;
	private int puntLocal =0;
	private int puntIni = 0;
	private int puntTemp = 1000;
	private String jugador;
	private int idPartida;
	private String primeraCarta = "";
	private long tiempoComienzo = System.currentTimeMillis();
	private long tiempoTotal;
	private long tiempoPrimeraCarta;
	

	
	
	/**
	 * Constructor del juego 1
	 * @param puntuacion Puntuacion del juego 1 según la cual aparecerán más o menos cartas
	 * @param nombreJugador	nombre del jugador (se usa para la base de datos)
	 * @param idPart identificador de la partida (se usa para la base de datos)
	 */
	
	public Game1(int puntuacion, String nombreJugador, int idPart) {
		
		puntTotal = puntuacion;
		puntIni = puntuacion;
		jugador = nombreJugador;
		idPartida = idPart;
		
		
		if (nivel == 1) {
			nCol = 3;
			nFil = 2;
			cartX = 140;//126
			cartY = 190;//171
		}else if(nivel == 2) {
			nCol = 3;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}else if(nivel == 3) {
			nCol = 4;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}else if(nivel == 4) {
			nCol = 5;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}
		
			
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
		allCards.add(c17);
		allCards.add(c18);
		allCards.add(c19);
		allCards.add(c20);
		
		
		selectCards  =  allCards.subList(0, nCol*nFil);
		
		
		Collections.shuffle(selectCards);
		
		for (Carta c : selectCards) {
			System.out.println(c.getId());
		}
				
	}
	
	/**
	 * Obtener imagen de una Carta segun su posición en el array de cartas seleccionadas
	 * @param index posición de la carta en el array de cartas
	 * @return Devuelve la imagen de la carta.
	 */
	
	public Image getImagenCarta(int index) {
		
		if(selectCards.get(index).getId() == "card_hearts_A") {
			return media.card_hearts_A_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_Q") {
			return media.card_hearts_Q_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_K") {
			return media.card_hearts_K_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_A") {
			return media.card_clubs_A_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_Q") {
			return media.card_clubs_Q_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_K") {
			return media.card_clubs_K_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_J") {
			return media.card_hearts_J_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_J") {
			return media.card_clubs_J_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_A") {
			return media.card_diamonds_A_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_K") {
			return media.card_diamonds_K_IMG;
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
	 * Realiza un delay en Milisegundo
	 * @param n numero de Milisegundo que se quiere hacer el delay
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
			
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false && start == 1){	// caso start == 1
				bStart_state = false;
			}
			
			if(start == 3) {
				int nCarta = 0;
				for (ArrayList<ArrayList<Integer>> filas : posCartas) {
					for(ArrayList<Integer> columnas : filas) {
						if(mouseOver(mdy, mdx, columnas.get(1), columnas.get(0), cartY, cartX) == false) {  //pos1
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
		if(Game.estadoJuego == Game.ESTADO.Game1) {				//si se esta en otro estado no hace nada
			
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			
			
			if( mouseOver(mox, moy, 500, 290, 190, 50) && start == 1 ){	             // Caso start == 1
				bStart_state = true;
				try {																				
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }}
			
				if (start == 3) {
					int nCarta = 0;
					for (ArrayList<ArrayList<Integer>> filas : posCartas) {
						for(ArrayList<Integer> columnas : filas) {
							if(mouseOver(moy, mox, columnas.get(1), columnas.get(0), cartY, cartX)) {  //pos1
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
			
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacio n de la ruta hasta el archivo de audio 2
			
			
			if (start == 1) {                                          // caso start == 1
				if(bStart_state == true){ 
					try {																				
				        Clip sonido = AudioSystem.getClip();
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
				        sonido.open(ais);
				        sonido.start();
			        }catch(Exception e2) {
			        	System.out.println("error");
			        }
					
					posCartas = generaMatriz(nCol, nFil, cartX, cartY);
					
					start = 3;                                            //cambia a start=2
					
					for (int i = 0; i < (nCol*nFil); i++) {
						selectCards.get(i).setArriba(true);
					}
					
					delaySeg(2);
					
					for (int i = 0; i < (nCol*nFil); i++) {
						selectCards.get(i).setArriba(false);
					}
					
				}
			bStart_state = false;
			}
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
								System.out.println("ckick1 en carta " + nCarta);
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
									System.out.println("ckick1 en carta " + nCarta);
								}
								
								nCarta++;
							}
						}
						
						
						if(click2 != -1) {
							
							
							if(allCards.get(click1).getId() == selectCards.get(click2).getId()){
								puntLocal += puntTemp;
								puntTemp=1000;
								
							}else {
								                            //delay de sec
								delaySeg(2);
								puntTemp = (int)Math.round(0.66*puntTemp);
								selectCards.get(click1).setArriba(false);
								selectCards.get(click2).setArriba(false);
								fallos = fallos + 1;
							}
							
							click1=-1;
							click2=-1;
							
							if (todasLevantadas()) {
								tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
								delaySeg(2);
								start = 4;
								delaySeg(1);
								BaseDatos.insertarGame1(idPartida, puntLocal, fallos, primeraCarta, tiempoPrimeraCarta, tiempoTotal);
								Game.partida  = new Partida( puntTotal + puntLocal ,0, 0, jugador, idPartida);
								
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
	
	/**	Evalua si el ratón está sobre una región
	 * @param mx posición X del ratón
	 * @param my posición Y del ratón
	 * @param x	posición X en la que comienza la región
	 * @param y	posición Y en la que comienza la región
	 * @param width	anchura de la región
	 * @param heigth altura de la región
	 * @return True si el ratón está sobre esa región y False si no lo está
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
	
		g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		
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
			String strPunt = String.valueOf(puntLocal);
			
			int nCarta = 0;
			for (ArrayList<ArrayList<Integer>> filas : posCartas) {
				for(ArrayList<Integer> columnas : filas) {
					if(selectCards.get(nCarta).isArriba()) {
						g.drawImage(getImagenCarta(nCarta), columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}else {
						g.drawImage(media.cardBackIMG, columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}
					nCarta++;
				}
			}
			
			
			
			g.drawString(strPunt, 1020, 60);
			
		}
		
		if(start == 4) {
			customFontG1 = media.customFontBot.deriveFont(Font.PLAIN,70);
			g.setFont(customFontG1);
			g.setColor(Color.BLACK);
			g.drawString("ENHORABUENA", 220, 300);
			g.setFont(media.customFontBot);
			String strPunt = String.valueOf(puntLocal);
			g.drawString(strPunt, 570, 400);
		}
	}	
}
