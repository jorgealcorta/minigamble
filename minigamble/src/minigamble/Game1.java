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




public class Game1  implements MouseMotionListener, MouseListener { // Memorizar cartas
	
	

	private ImageIcon card_hearts_A;
	private ImageIcon card_hearts_Q;
	private ImageIcon card_hearts_K;
	private ImageIcon card_clubs_A;
	private ImageIcon card_clubs_Q;
	private ImageIcon card_clubs_K;
	private ImageIcon card_clubs_J;
	private ImageIcon card_hearts_J;
	private ImageIcon card_diamonds_A;
	private ImageIcon card_diamonds_K;
	private ImageIcon card_diamonds_J;
	private ImageIcon card_diamonds_Q;
	
	private ImageIcon cardBack;
	
	private Image card_hearts_A_IMG;
	private Image card_hearts_Q_IMG;
	private Image card_hearts_K_IMG;
	private Image card_clubs_A_IMG;
	private Image card_clubs_Q_IMG;
	private Image card_clubs_K_IMG;
	private Image card_clubs_J_IMG;
	private Image card_hearts_J_IMG;
	private Image card_diamonds_A_IMG;
	private Image card_diamonds_K_IMG;
	private Image card_diamonds_J_IMG;
	private Image card_diamonds_Q_IMG;
	
	private Image cardBackIMG;
	private Image bStartIMG_True;
	private Image bStartIMG_False;

	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	private int nCol;
	private int nFil;
	private int cartX;
	private int cartY;
	private int nivel = 1;
	
	
	private Font customFontBot;
	private Font customFontFin;
	
	private ArrayList<ArrayList<ArrayList<Integer>>> posCartas;
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	private ArrayList<Carta> allCards = new ArrayList<Carta>();
	private List<Carta> selectCards = new ArrayList<Carta>();
	
	private Carta c1 = new Carta("card_hearts_A", false, false);
	private Carta c2 = new Carta("card_hearts_Q", false, false);
	private Carta c3 = new Carta("card_hearts_K", false, false);
	private Carta c4 = new Carta("card_clubs_A", false, false);
	private Carta c5 = new Carta("card_clubs_Q", false, false);
	private Carta c6 = new Carta("card_clubs_K", false, false);
	private Carta c7 = new Carta("card_hearts_A", false, false);
	private Carta c8 = new Carta("card_hearts_Q", false, false);
	private Carta c9 = new Carta("card_hearts_K", false, false);
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
	private Carta c21 = new Carta("card_diamonds_J", false, false);
	private Carta c22 = new Carta("card_diamonds_Q", false, false);
	private Carta c23 = new Carta("card_diamonds_J", false, false);
	private Carta c24 = new Carta("card_diamonds_Q", false, false);
	
	
	private int start = 1;
	private int click1 = -1;
	private int click2 = -1;
	private int puntTotal = 0;
	private int puntTemp = 1000;
	

	
	
	public Game1(int dificultad) {
		
		puntTotal = dificultad;
		
		if (nivel == 1) {
			nCol = 4;
			nFil = 3;
			cartX = 126;
			cartY = 171;
		}else if(nivel == 2) {
			nCol = 4;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}else if(nivel == 3) {
			nCol = 5;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}else if(nivel == 4) {
			nCol = 6;
			nFil = 4;
			cartX = 98;
			cartY = 133;
		}
		
		try {
			//Cargo todas las imagenes como iconos
			cardBack = new ImageIcon( Game.class.getResource("multimedia/cartas/cardBack_red5.png").toURI().toURL() );
			card_hearts_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsA.png").toURI().toURL() );
			card_hearts_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsQ.png").toURI().toURL() );
			card_hearts_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsK.png").toURI().toURL() );
			card_clubs_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsA.png").toURI().toURL() );
			card_clubs_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsQ.png").toURI().toURL() );
			card_clubs_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsK.png").toURI().toURL() );
			card_clubs_J = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsJ.png").toURI().toURL() );
			card_hearts_J = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsJ.png").toURI().toURL() );
			card_diamonds_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsA.png").toURI().toURL() );
			card_diamonds_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsK.png").toURI().toURL() );
			card_diamonds_J = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsJ.png").toURI().toURL() );
			card_diamonds_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsQ.png").toURI().toURL() );
			
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
		try {   
			customFontFin = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuente.ttf"));
			customFontFin = customFontFin.deriveFont(Font.PLAIN,100);				
		}catch(Exception e){	
			System.out.println("Problema con la fuente Minigamble");
		}
		
		
		// Paso todos los iconos a imagenes
		card_hearts_A_IMG = card_hearts_A.getImage();
		card_hearts_Q_IMG = card_hearts_Q.getImage();
		card_hearts_K_IMG = card_hearts_K.getImage();
		card_clubs_A_IMG = card_clubs_A.getImage();
		card_clubs_Q_IMG = card_clubs_Q.getImage();
		card_clubs_K_IMG = card_clubs_K.getImage();
		card_clubs_J_IMG = card_clubs_J.getImage();
		card_hearts_J_IMG = card_hearts_J.getImage();
		card_diamonds_A_IMG = card_diamonds_A.getImage();
		card_diamonds_K_IMG = card_diamonds_K.getImage();
		card_diamonds_J_IMG = card_diamonds_J.getImage();
		card_diamonds_Q_IMG = card_diamonds_Q.getImage();
		
		
		cardBackIMG = cardBack.getImage();
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();
		
		
		
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
		allCards.add(c21);
		allCards.add(c22);
		allCards.add(c23);
		allCards.add(c24);
		
		
		selectCards  =  allCards.subList(0, nCol*nFil);
		
		
		Collections.shuffle(selectCards);
		
		for (Carta c : selectCards) {
			System.out.println(c.getId());
		}
				
	}
	
	public Image getImagenCarta(int index) {
		
		if(selectCards.get(index).getId() == "card_hearts_A") {
			return card_hearts_A_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_Q") {
			return card_hearts_Q_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_K") {
			return card_hearts_K_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_A") {
			return card_clubs_A_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_Q") {
			return card_clubs_Q_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_K") {
			return card_clubs_K_IMG;
		}else if(selectCards.get(index).getId() == "card_hearts_J") {
			return card_hearts_J_IMG;
		}else if(selectCards.get(index).getId() == "card_clubs_J") {
			return card_clubs_J_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_A") {
			return card_diamonds_A_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_K") {
			return card_diamonds_K_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_J") {
			return card_diamonds_J_IMG;
		}else if(selectCards.get(index).getId() == "card_diamonds_Q") {
			return card_diamonds_Q_IMG;
		}
		return null;
		
	}
	
	
	public boolean todasLevantadas() {
		for(Carta c : selectCards) {
			if(!c.isArriba())
				return false;
		}
		return true;
	}
	
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
	
	private void delaySeg(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
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
			System.out.println("prueba");
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false){	// caso start == 1
				bStart_state = false;
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
							System.out.println(nCarta);
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
								selectCards.get(nCarta).setPresionada(false);
								sonidoCarta();
								delayMS(100);
								selectCards.get(nCarta).setArriba(true);
								click1=nCarta;
								System.out.println("ckick1 en carta " + nCarta);
							}
							
							System.out.println(nCarta);
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
								System.out.println(nCarta);
								nCarta++;
							}
						}
						
						
						if(click2 != -1) {
							
							
							if(allCards.get(click1).getId() == selectCards.get(click2).getId()){
								puntTotal += puntTemp;
								puntTemp=1000;
								
							}else {
								                            //delay de sec
								delaySeg(2);
								puntTemp = (int)Math.round(0.66*puntTemp);
								selectCards.get(click1).setArriba(false);
								selectCards.get(click2).setArriba(false);
							}
							
							click1=-1;
							click2=-1;
							
							if (todasLevantadas()) {
								delaySeg(2);
								start = 4;
								delaySeg(1);
								Game.partida  = new Partida( puntTotal ,0,null , null);
								
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
	
	public void render(Graphics g) {
	
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		

		
		
		if(start==1) {
			g.setFont(customFontBot);
			g.setColor(Color.BLACK);
			if(bStart_state == true) {					                   //caso start = 1
				g.drawImage(bStartIMG_True, 500, 294, null);
				g.drawString("Start", 540, 326);
			}else {									
				g.drawImage(bStartIMG_False, 500, 290, null);
				g.drawString("Start", 547, 322);
			}
		}
		
		
		if (start == 3) {
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 700);
			
			g.setFont(customFontBot);
			g.setColor(Color.BLACK);
			String strPunt = String.valueOf(puntTotal);
			
			int nCarta = 0;
			for (ArrayList<ArrayList<Integer>> filas : posCartas) {
				for(ArrayList<Integer> columnas : filas) {
					if(selectCards.get(nCarta).isArriba()) {
						g.drawImage(getImagenCarta(nCarta), columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}else {
						g.drawImage(cardBackIMG, columnas.get(0) , columnas.get(1) ,cartX, cartY, null);
					}
					nCarta++;
				}
			}
			
			
			
			g.drawString(strPunt, 1100, 40);
			
		}
		
		if(start == 4) {
			g.setFont(customFontFin);
			g.setColor(Color.BLACK);
			g.drawString("ENHORABUENA", 325, 300);
			g.setFont(customFontBot);
			String strPunt = String.valueOf(puntTotal);
			g.drawString(strPunt, 570, 400);
		}
	}	
}
