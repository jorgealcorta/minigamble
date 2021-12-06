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
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Game1  implements MouseMotionListener, MouseListener { // Memorizar cartas
	
	
	private ImageIcon background;	// Fondo
	private ImageIcon card_hearts_A;
	private ImageIcon card_hearts_Q;
	private ImageIcon card_hearts_K;
	private ImageIcon card_clubs_A;
	private ImageIcon card_clubs_Q;
	private ImageIcon card_clubs_K;
	private ImageIcon cardBack;
	
	private Image card_hearts_A_IMG;
	private Image card_hearts_Q_IMG;
	private Image card_hearts_K_IMG;
	private Image card_clubs_A_IMG;
	private Image card_clubs_Q_IMG;
	private Image card_clubs_K_IMG;

	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	private ArrayList<Carta> allCards = new ArrayList<Carta>();
	
	private Carta c1 = new Carta("card_hearts_A", false);
	private Carta c2 = new Carta("card_hearts_Q", false);
	private Carta c3 = new Carta("card_hearts_K", false);
	private Carta c4 = new Carta("card_clubs_A", false);
	private Carta c5 = new Carta("card_clubs_Q", false);
	private Carta c6 = new Carta("card_clubs_K", false);
	
	private int start = 1;
	private int click1 = -1;
	private int click2 = -1;
	private int puntTotal = 0;
	private int puntTemp = 1000;
	

	
	
	public Game1(int dificultad) {

		
		
		allCards.add(c1);
		allCards.add(c2);
		allCards.add(c3);
		allCards.add(c4);
		allCards.add(c5);
		allCards.add(c6);
		allCards.add(c1);
		allCards.add(c2);
		allCards.add(c3);
		allCards.add(c4);
		allCards.add(c5);
		allCards.add(c6);
		
		
		Collections.shuffle(allCards);
				
	}
	
	public Image getImagenCarta(int index) {
		
		if(allCards.get(index).getId() == "card_hearts_A") {
			return card_hearts_A_IMG;
		}else if(allCards.get(index).getId() == "card_hearts_Q") {
			return card_hearts_Q_IMG;
		}else if(allCards.get(index).getId() == "card_hearts_K") {
			return card_hearts_K_IMG;
		}else if(allCards.get(index).getId() == "card_clubs_A") {
			return card_clubs_A_IMG;
		}else if(allCards.get(index).getId() == "card_clubs_Q") {
			return card_clubs_Q_IMG;
		}else{
			return card_clubs_K_IMG;
		}
		
	}
	
	
	
	
	
	
	
	public void mouseDragged(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game1 ) {				
		
			mdx = e.getX();
			mdy = e.getY();
			
					
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false && start == 1){	// caso start == 1
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
				start = 2;                                            //cambia a start=2
				
			}
			bStart_state = false;
			}
			
			
			
		}
		
	}	
	
	

public void mouseClicked(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game1) {				//si se esta en otro estado no hace nada
			
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
						
			if( start==3 ){				
				
				if(click1==-1) {																				//CLICK1
					if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, 140, 190)) {  //pos1
						if(!allCards.get(0).isArriba()) {
							allCards.get(0).setArriba(true);
							click1=0;
							System.out.println("ckick1 en carta 1");
							
						}
						
					}else if (mouseOver(moy, mox, (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {//pos2
						if(!allCards.get(1).isArriba()) {
							allCards.get(1).setArriba(true);
							click1=1;
							System.out.println("ckick1 en carta 2");
						}
						System.out.println("ckick1 en carta 2");
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {  //pos3
						if(!allCards.get(2).isArriba()) {
							allCards.get(2).setArriba(true);
							click1=2;
							System.out.println("ckick1 en carta 3");
						}
											
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {  //pos4
						if(!allCards.get(3).isArriba()) {
							allCards.get(3).setArriba(true);
							click1=3;
							System.out.println("ckick1 en carta 4");
						}
					}else if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {   //pos5
						if(!allCards.get(4).isArriba()) {
							allCards.get(4).setArriba(true);
							click1=4;
							System.out.println("ckick1 en carta 5");
						}						
						
					}else if(mouseOver(moy, mox, (2*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos6
						if(!allCards.get(5).isArriba()) {
							allCards.get(5).setArriba(true);
							click1=5;
							System.out.println("ckick1 en carta 6");
						}
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos7
						if(!allCards.get(6).isArriba()) {
							allCards.get(6).setArriba(true);
							click1=6;
							System.out.println("ckick1 en carta 7");
						}
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos8
						if(!allCards.get(7).isArriba()) {
							allCards.get(7).setArriba(true);
							click1=7;
							System.out.println("ckick1 en carta 8");
						}
						
					}else if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (3*(700/4))-(190/2)-20,  140, 190)) {  //pos9
						if(!allCards.get(8).isArriba()) {
							allCards.get(8).setArriba(true);
							click1=8;
							System.out.println("ckick1 en carta 9");
						}
						
					}else if(mouseOver(moy, mox, (2*(1200/5))-(140/2), (3*(700/4))-(190/2)-20,  140, 190)) {  //pos10
						if(!allCards.get(9).isArriba()) {
							allCards.get(9).setArriba(true);
							click1=9;
							System.out.println("ckick1 en carta 10");
						}
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (3*(700/4))-(190/2)-20, click2, click1)) {  //pos11
						if(!allCards.get(10).isArriba()) {
							allCards.get(10).setArriba(true);
							click1=10;
							System.out.println("ckick1 en carta 11");
						}
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (3*(700/4))-(190/2)-20, click2, click1)) {  //pos12
						if(!allCards.get(11).isArriba()) {
							allCards.get(11).setArriba(true);
							click1=11;
							System.out.println("ckick1 en carta 12");
						}						
					}
				
				}else {																											//CLICK2
					if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, 140, 190)) {  //pos1
						if(!allCards.get(0).isArriba()) {
							allCards.get(0).setArriba(true);
							click2=0;
							System.out.println("ckick2 en carta 1");
						}
												
						
					}else if (mouseOver(moy, mox, (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {//pos2
						if(!allCards.get(1).isArriba()) {
							allCards.get(1).setArriba(true);
							click2=1;
							System.out.println("ckick2 en carta 2");
						}
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {  //pos3
						if(!allCards.get(2).isArriba()) {
							allCards.get(2).setArriba(true);
							click2=2;
							System.out.println("ckick2 en carta 3");
						}
											
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20,  140, 190)) {  //pos4
						if(!allCards.get(3).isArriba()) {
							allCards.get(3).setArriba(true);
							click2=3;
							System.out.println("ckick2 en carta 4");
						}
					}else if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {   //pos5
						if(!allCards.get(4).isArriba()) {
							allCards.get(4).setArriba(true);
							click2=4;
							System.out.println("ckick2 en carta 5");
						}						
						
					}else if(mouseOver(moy, mox, (2*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos6
						if(!allCards.get(5).isArriba()) {
							allCards.get(5).setArriba(true);
							click2=5;
							System.out.println("ckick2 en carta 6");
						}
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos7
						if(!allCards.get(6).isArriba()) {
							allCards.get(6).setArriba(true);
							click2=6;
							System.out.println("ckick2 en carta 7");
						}
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (2*(700/4))-(190/2)-20,  140, 190)) {  //pos8
						if(!allCards.get(7).isArriba()) {
							allCards.get(7).setArriba(true);
							click2=7;
							System.out.println("ckick2 en carta 8");
						}
						
					}else if(mouseOver(moy, mox, (1*(1200/5))-(140/2), (3*(700/4))-(190/2)-20,  140, 190)) {  //pos9
						if(!allCards.get(8).isArriba()) {
							allCards.get(8).setArriba(true);
							click2=8;
							System.out.println("ckick2 en carta 9");
						}
						
					}else if(mouseOver(moy, mox, (2*(1200/5))-(140/2), (3*(700/4))-(190/2)-20,  140, 190)) {  //pos10
						if(!allCards.get(9).isArriba()) {
							allCards.get(9).setArriba(true);
							click2=9;
							System.out.println("ckick2 en carta 10");
						}
						
					}else if(mouseOver(moy, mox, (3*(1200/5))-(140/2), (3*(700/4))-(190/2)-20, click2, click1)) {  //pos11
						if(!allCards.get(10).isArriba()) {
							allCards.get(10).setArriba(true);
							click2=10;
							System.out.println("ckick2 en carta 11");
						}
						
					}else if(mouseOver(moy, mox, (4*(1200/5))-(140/2), (3*(700/4))-(190/2)-20, click2, click1)) {  //pos12
						if(!allCards.get(11).isArriba()) {
							allCards.get(11).setArriba(true);
							click2=11;
							System.out.println("ckick2 en carta 12");
						}						
					}
					
					
					                                                                                              //delay de sec
					if(allCards.get(click1).getId()==allCards.get(click2).getId()){
						puntTotal += puntTemp;
						puntTemp=1000;
						
					}else {
						puntTemp = (int)Math.round(0.66*puntTemp);
						allCards.get(click1).setArriba(false);
						allCards.get(click2).setArriba(false);
					}
					
					click1=-1;
					click2=-1;
				}				
				
			}
			
		}
		
		
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
	
		
		try {
			//Cargo todas las imagenes como iconos
			cardBack = new ImageIcon( Game.class.getResource("multimedia/cartas/cardBack_red5.png").toURI().toURL() );
			card_hearts_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsA.png").toURI().toURL() );
			card_hearts_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsQ.png").toURI().toURL() );
			card_hearts_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsK.png").toURI().toURL() );
			card_clubs_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsA.png").toURI().toURL() );
			card_clubs_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsQ.png").toURI().toURL() );
			card_clubs_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsK.png").toURI().toURL() );
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		// Paso todos los iconos a imagenes
		card_hearts_A_IMG = card_hearts_A.getImage();
		card_hearts_Q_IMG = card_hearts_Q.getImage();
		card_hearts_K_IMG = card_hearts_K.getImage();
		card_clubs_A_IMG = card_clubs_A.getImage();
		card_clubs_Q_IMG = card_clubs_Q.getImage();
		card_clubs_K_IMG = card_clubs_K.getImage();
		Image cardBackIMG = cardBack.getImage();
		
		Image bStartIMG_True = bStart_true.getImage();
		Image bStartIMG_False = bStart_false.getImage();
		
		
	
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		
		
		if(start==1) {
			if(bStart_state == true) {					                   //caso start = 1
				g.drawImage(bStartIMG_True, 500, 294, null);
				g.drawString("Sign In", 540, 326);
			}else {									
				g.drawImage(bStartIMG_False, 500, 290, null);
				g.drawString("prueba", 540, 322);
			}
		}
		
		
		
		if (start==2) {
			
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 700);
			g.drawImage(getImagenCarta(0), (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			g.drawImage(getImagenCarta(1), (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			g.drawImage(getImagenCarta(2), (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			g.drawImage(getImagenCarta(3), (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			g.drawImage(getImagenCarta(4), (1*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			g.drawImage(getImagenCarta(5), (2*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			g.drawImage(getImagenCarta(6), (3*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			g.drawImage(getImagenCarta(7), (4*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			g.drawImage(getImagenCarta(8), (1*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			g.drawImage(getImagenCarta(9), (2*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			g.drawImage(getImagenCarta(10), (3*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			g.drawImage(getImagenCarta(11), (4*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			System.out.println("terminacontador");
			start = 3;
			
		}
		
		if (start == 3) {
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 700);
			
			if(allCards.get(0).isArriba()) {															//carta1
				g.drawImage(getImagenCarta(0), (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);			
			}else {
				g.drawImage(cardBackIMG, (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			}
			
			if(allCards.get(1).isArriba()) {
				g.drawImage(getImagenCarta(1), (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);		//carta2	
			}else {
				g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			}
			
			if(allCards.get(2).isArriba()) {
				g.drawImage(getImagenCarta(2), (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);		//carta3	
			}else {
				g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			}
			
			if(allCards.get(3).isArriba()) {
				g.drawImage(getImagenCarta(3), (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);		//carta4	
			}else {
				g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
			}
			
			if(allCards.get(4).isArriba()) {															//carta5
				g.drawImage(getImagenCarta(4), (1*(1200/5))-(140/2), (2*(700/4))-(190/2), null);			
			}else {
				g.drawImage(cardBackIMG, (1*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			}
			
			if(allCards.get(5).isArriba()) {															//carta6
				g.drawImage(getImagenCarta(5), (2*(1200/5))-(140/2), (2*(700/4))-(190/2), null);			
			}else {
				g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			}
			
			if(allCards.get(6).isArriba()) {															//carta7
				g.drawImage(getImagenCarta(6), (3*(1200/5))-(140/2), (2*(700/4))-(190/2), null);			
			}else {
				g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			}
			
			if(allCards.get(7).isArriba()) {															//carta8
				g.drawImage(getImagenCarta(7), (4*(1200/5))-(140/2), (2*(700/4))-(190/2), null);			
			}else {
				g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
			}
			
			if(allCards.get(8).isArriba()) {															//carta9
				g.drawImage(getImagenCarta(8), (1*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);			
			}else {
				g.drawImage(cardBackIMG, (1*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			}
			
			if(allCards.get(9).isArriba()) {															//carta10
				g.drawImage(getImagenCarta(9), (2*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);			
			}else {
				g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			}
			
			if(allCards.get(10).isArriba()) {															//carta11
				g.drawImage(getImagenCarta(10), (3*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);			
			}else {
				g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			}
			
			if(allCards.get(11).isArriba()) {															//carta12
				g.drawImage(getImagenCarta(11), (4*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);			
			}else {
				g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			}
			
//			g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
//			g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
//			g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
//			g.drawImage(cardBackIMG, (1*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
//			g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
//			g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
//			g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
//			g.drawImage(cardBackIMG, (1*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
//			g.drawImage(cardBackIMG, (2*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
//			g.drawImage(cardBackIMG, (3*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
//			g.drawImage(cardBackIMG, (4*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
			
			
		}
		
		
	}	
}
