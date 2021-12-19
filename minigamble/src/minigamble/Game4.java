package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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


//Voy a hacer un arraylist que guarde todas las coordenadas (arraylist de 2) de todas las dianas.
//Si al hacer click el arraylist de las coordenadas concuerda con alguna que esté en el rango de las dianas
//(es decir, centro +- radio, se suman los //puntos de haber roto una diana, se borran las coordenadas 
//del arraylist de coordenadas activas, y se dibuja dianaRota durante unos milisegundos. Tener cuidado,
//diana nueva no se puede dibujar dentro de diana existente.


//Crear un unico hilo que gestiona todas las dianas, cada diana tiene un timestamp de cuando se ha disparado. Momento creaciom, momento disparo, momento quitar, recorrer todas las dianas y jugar con los timestamps??


public class Game4 implements MouseMotionListener, MouseListener{ //Dianas
	
	private Image bStartIMG_True;
	private Image bStartIMG_False;
	
	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	private int start = 1; //1=START, 2= JUEGO
	
	private Font customFontBot;
	private Font customFontFin;
	
	private ImageIcon diana;
	private ImageIcon dianaRota;
	
	private ImageIcon mira;
	
	private Image diana_IMG;
	private Image dianaRota_IMG;
	private Image mira_IMG;
	
	private ArrayList<Diana> dianasCreadas = new ArrayList<Diana>();
	private ArrayList<Diana> dianasActivas = new ArrayList<Diana>();
	private ArrayList<Diana> dianasRotas = new ArrayList<Diana>(); // Contiene todas las dianas que ya se han disparado, y ha pasado el tiempo para que desaparezcan mediante el hilo.

	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	public Game4(int dificultad){
		
		try {
			//Cargo todas las imagenes como iconos
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
			diana = new ImageIcon(Game.class.getResource("multimedia/dianas/diana.png").toURI().toURL() );
			dianaRota = new ImageIcon(Game.class.getResource("multimedia/dianas/dianaRota.png").toURI().toURL() );
			
			mira = new ImageIcon(Game.class.getResource("multimedia/mira.png").toURI().toURL() );
			
		}catch(Exception e1) {
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
		
		//Paso todos los iconos a imágenes
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();
		
		diana_IMG = diana.getImage();
		dianaRota_IMG = dianaRota.getImage();
		
		mira_IMG = mira.getImage();
		
		//Ir creando dianas
		//xmin=0
		//xmax=1200-size
		//ymin=0
		//ymax=660-size
		//sizemin=200
		//sizemax=300
		//meter x y restringidas para que no se solapen?
		
		for(int i=0; i<5; i++) {
			//Min + (int)(Math.random() * ((Max - Min) + 1))
			//Diana dRandom = new Diana(30 + (int)(Math.random() * ((1170) + 1)), 30 + (int)(Math.random() * ((670) + 1)), 20 + (int)(Math.random() * ((50) + 1)));
			int rSize = (int)(Math.random() * 300) + 50;
			Diana dRandom = new Diana((int)(Math.random() * (1200 - rSize + 1)), (int)(Math.random() * (660-rSize + 1)), rSize, false);
			dianasCreadas.add(dRandom);
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

	
	public void runThreadActivas(){
		ThreadDianasActivas da = new ThreadDianasActivas(dianasCreadas, dianasActivas);
		Thread hda = new Thread(da);
		hda.start();
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
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(Game.estadoJuego==Game.ESTADO.Game4) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//ContinuaciÃ³n de la ruta hasta el archivo de audio 1
//			String escopeta_filePath = filePath.concat()
			
			
			
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
			
			if(start == 2) {
				for(Diana d : dianasActivas) {
					if(e.getX() >= d.getX() && e.getX() <= d.getX() + d.getSize() && e.getY() >= d.getY() && e.getY() <= d.getY() + d.getSize() && !d.isRota()) {
						System.out.println("acierto");
						d.setRota(true);
						
						ThreadBorrarDiana bd = new ThreadBorrarDiana(d, dianasActivas, dianasRotas);
						Thread hbd = new Thread(bd);
						hbd.start();
						
						try {																				
					        Clip sonido = AudioSystem.getClip();
							AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
					        sonido.open(ais);
					        sonido.start();
				        }catch(Exception e2) {
				        	System.out.println("error");
				        }
					}else{
						System.out.println("miss");
					}
				}
			}
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Game4) {
			
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacion de la ruta hasta el archivo de audio 2
		
			if (start == 1) {// caso start == 1
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
			start = 2;
			System.out.println(dianasCreadas);
			runThreadActivas();

			}
			
		}
		
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
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		if(start==1) {
			g.setFont(customFontBot);
			g.setColor(Color.BLACK);
			if(bStart_state == true) {					                   //caso start = 1
				g.drawImage(bStartIMG_True, 500, 294, null);
				g.drawString("Start", 540, 326);
			}else{
				g.drawImage(bStartIMG_False, 500, 290, null);
				g.drawString("Start", 547, 322);
			}
		}
		
		if(start == 2) {
			
			//g.drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);
			
			for(Diana d : dianasActivas) {
				if(!d.isRota()) {
					g.drawImage(diana_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
				}else if(d.isRota() && !dianasRotas.contains(d)){
					g.drawImage(dianaRota_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
				}else{
				}
			}

			g.drawImage(mira_IMG, mox-16, moy-16, 32, 32, null);
			
		}

	}

}