package minigamble;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Game3 implements MouseListener , MouseMotionListener {

	private Image lab1Img;
	private ImageIcon lab1Icon;
	
	private Image lab2Img;
	private ImageIcon lab2Icon;
	
	private Image bStartIMG_True;
	private Image bStartIMG_False;
	
	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	private Font customFontBot;
	
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	private ArrayList <Laberinto> allLabs = new ArrayList<Laberinto>();
	private Laberinto thisLab;
	
	private int start = 1;
	
	Robot robot;
	
	public Game3(int dificultad) {
	
		try {
		
		bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
		bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
		
		lab1Icon = new ImageIcon( Game.class.getResource("multimedia/laberintoPrueba.png").toURI().toURL() );
		lab2Icon = new ImageIcon( Game.class.getResource("multimedia/laberintoPrueba2.png").toURI().toURL() );
		
		
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
		
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();
		
		
		lab1Img = lab1Icon.getImage();
		lab2Img = lab2Icon.getImage();
		
		Laberinto lab1 = new Laberinto(lab1Img, 001 );
		Laberinto lab2 = new Laberinto(lab1Img, 002 );

		
		allLabs.add(lab1);
		allLabs.add(lab2);
		
		thisLab = getRandom(allLabs);		
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	

		
	
	public  Laberinto getRandom( ArrayList<Laberinto> array) {
	    int rnd = new Random().nextInt(array.size());
	    return array.get(rnd);
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
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(Game.estadoJuego==Game.ESTADO.Game3) {
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
				}
			bStart_state = false;
			robot.mouseMove(100, 100);
			start = 2;
			
			
			getRandLab();
			
			
			}
			
			
			
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game3 ) {
			if(start==2) {
				mdx = e.getX();
				mdy = e.getY();
				
				if(Color.BLACK != robot.getPixelColor(mdx, mdy)) {
					robot.mouseMove(600, 500);
					
				}
							
			}
		}
			
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}
	
	public void getRandLab() {
		
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		if ( start==1) {
			
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
		
		if (start==2) {
			
			g.drawImage(thisLab.getImage() ,0, 0, null);
			
			
		}
	
	}



















	
		
		
	
}