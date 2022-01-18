package minigamble;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import minigamble.Game.ESTADO;

/**
 * Clase encargada del juego 3 (laberinto)
 */

public class Game3 implements MouseListener , MouseMotionListener {

	private int dificultad;
	private String jugador;
	private int idPartida;
		
	private boolean bStart_state = false;
	
	private Font customFontG3;
	
	private int Mox;
	private int Moy;
	private int mox;				
	private int moy;
	private int mdx;
	private int mdy;
	
	
	private int startX;
	private int startY;
	
	private Color pathColor;
	private Color pathColor2;
	
	private ArrayList <Laberinto> allLabs = new ArrayList<Laberinto>();
	private Laberinto thisLab;
	
	private long tiempoComienzo = System.currentTimeMillis();
	private long tiempoTotal;
	private int start;
	private int numFallos;
	private int puntTotal;
	private int puntLocal;
	
	Robot robot;
	
		
	/**
	 * Constructor del juego 3
	 * @param dificultad Dificultad del juego 3 segun la cual se elegira un laberinto mas o menos dificil.
	 * @param nombreJugador	nombre del jugador (se usa para la base de datos)
	 * @param idPart identificador de la partida (se usa para la base de datos)
	 */
	
	public Game3(int dificultad, String nombreJugador, int idPart) {
		
	
		puntTotal = dificultad;				
		jugador = nombreJugador;
		idPartida = idPart;
		
		puntLocal = 1000;
		numFallos = 0;
		start = 1;
			
				
		Laberinto lab1 = new Laberinto(media.lab1Img, 001 );
		Laberinto lab2 = new Laberinto(media.lab2Img, 002 );
		Laberinto lab3 = new Laberinto(media.lab3Img, 003 );
		Laberinto lab4 = new Laberinto(media.lab4Img, 004 );
				
		allLabs.add(lab1);
		allLabs.add(lab2);
		allLabs.add(lab3);
		allLabs.add(lab4	);
		
		thisLab = getRandom(allLabs, puntTotal);		
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		
	}
		
	/**
	 * Elige un laberinto en funcion de la dificultad que reciba
	 * @param array Arraylist con todos los laberintos disponibles
	 * @return el laberinto elegido
	 */
	
	public Laberinto getRandom( ArrayList<Laberinto> array, int puntTotal) {
	    if (puntTotal<2000) {
	    	return array.get(0);
	    } else if (puntTotal < 4000) {
	    	return array.get(1);
	    } else if (puntTotal < 6000) {
	    	return array.get(2);
	    } else  {
	    	return array.get(3);
	    }
	 
	}
	
	/**	Evalua si el raton esta sobre una region
	 * @param mx posicion X del raton
	 * @param my posicion Y del raton
	 * @param x	posicion X en la que comienza la region
	 * @param y	posicion Y en la que comienza la region
	 * @param width	anchura de la region
	 * @param heigth altura de la region
	 * @return True si el raton esta sobre esa region y False si no lo esta. 
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
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(Game.estadoJuego==Game.ESTADO.Game3) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			
			String filePath = new File("").getAbsolutePath();				
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	
			
			
			
			if( mouseOver(mox, moy, 500, 290, 190, 50) && start == 1 ){	             
				bStart_state = true;
				try {																				
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }}	
			
			
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game3) {
			
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
					
					
				if( mouseOver(mox, moy, 500, 290, 190, 50)){	
					

					
					startX= Game.ventana.frame.getLocationOnScreen().x+1100;
					startY= Game.ventana.frame.getLocationOnScreen().y+600;
					
					robot.mouseMove(startX, startY);
					
					start=2;
															
				}
				
				bStart_state = false;
				}
							
			}
								
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(Game.estadoJuego == ESTADO.Game3) {
			
			if(start==2) {
			
				startX= Game.ventana.frame.getLocationOnScreen().x+1100;
				startY= Game.ventana.frame.getLocationOnScreen().y+600;
				
				robot.mouseMove(startX, startY);
			}
		}
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game3 ) {
			if(start==2) {
					
				Mox = e.getXOnScreen();	
				Moy = e.getYOnScreen();
				mox = e.getX();
				moy = e.getY();
				
				
				if(pathColor == null ) {
					
					pathColor = Color.decode("#c8bfe7");
					pathColor2 = Color.decode("#CABEEA");
				}
							
							
				if(robot.getPixelColor(Mox, Moy).getRGB() != pathColor.getRGB() && robot.getPixelColor(Mox, Moy).getRGB() != pathColor2.getRGB()) {
					System.out.println("path "+ pathColor.getRGB());
					System.out.println("gotten "+ robot.getPixelColor(Mox, Moy).getRGB());
					robot.mouseMove(startX, startY);	
					numFallos = numFallos +1;
					puntLocal = (int)Math.round( puntLocal * 0.66);
					delayMS(20);
					
					
				} else if ( mouseOver(mox, moy, 41, 43, 178, 88)) {
					start = 3;
					tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
					delayMS(2000);
					
					
					if(numFallos>3) {
						BaseDatos.insertarGame3(idPartida, puntLocal, numFallos, tiempoTotal, "false", thisLab.getId());
						start = 5;
						Game.pi = new PantallaIntermedia(puntTotal, puntLocal, 1, 2, jugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();
						
						//Game.partida  = new Partida( puntLocal ,1 , 2, jugador, idPartida);
					} else {
						BaseDatos.insertarGame3(idPartida, puntLocal, numFallos, tiempoTotal, "true", thisLab.getId());
						start = 5;
						Game.pi = new PantallaIntermedia(puntTotal, puntLocal, 0, 2, jugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();
						
						//Game.partida  = new Partida( puntLocal ,0 , 2, jugador, idPartida);
					}
					
				} 
			}				
			}
		}
			
	@Override
	public void mouseDragged(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game3 ) {
			if(start==1) {			
			
			mdx = e.getX();
			mdy = e.getY();
			
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false && start == 1){	// caso start == 1
				bStart_state = false;
			}
			
			}
		}
	}
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			b.printStackTrace();
		}
		
	}
	
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
		
		
		if (start==2) {			
			g.drawImage(thisLab.getImage() ,0, 0,1190,665,  null);
			customFontG3=media.customFontBot.deriveFont(Font.PLAIN,15);
			g.setFont(customFontG3);
			g.setColor(Color.BLACK);
			g.drawString("Points to obtain ( " + String.valueOf(puntLocal) +" )", 870, 40);						
			g.drawString("Number of mistakes ( "+String.valueOf(numFallos)+" )", 845, 20);			

		}
		
		if(start ==3) {
			g.drawImage(thisLab.getImage() ,0, 0,1190,665,  null);
			customFontG3=media.customFontBot.deriveFont(Font.PLAIN,50);
			g.setFont(customFontG3);
			g.setColor(Color.BLACK);
			if(numFallos<3) {
				g.drawString("Congrats you passed", 160, 326);
				g.drawImage(media.endingImg1, 100, 45, 80, 80, null);
			} else {
				g.drawString("Sorry.. too many mistakes", 160, 326);
				g.drawImage(media.endingImg2, 100, 45, 80, 80, null);
			}
			
			
		}
	
	}

}
