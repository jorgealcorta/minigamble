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
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;


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
	
	// Vector que almacena las dianas generadas aleatoriamente.
	private CopyOnWriteArrayList<Diana> dianasCreadas = new CopyOnWriteArrayList<Diana>();
	
	// Vector que almacena las dianas que se encuentran en pantalla.
	private CopyOnWriteArrayList<Diana> dianasActivas = new CopyOnWriteArrayList<Diana>();
	
	// Vector que almacena todas las dianas que ya se han disparado, y ha pasado el tiempo para que desaparezcan mediante el hilo.
	private CopyOnWriteArrayList<Diana> dianasRotas = new CopyOnWriteArrayList<Diana>();
	
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
		
		// Cargar fuentes
		
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
		
		/*
		 * Se crean dianas aleatoriamente, primero se genera el size de la diana, y segun ese tamanyo se estableceran los
		 * limites para las coordenadas x e y de la diana, con el fin de que no se salgan de la ventana en ningun momento.
		 */
		
		for(int i=0; i<5; i++) {
			int rSize = (int)(Math.random() * 300) + 50;
			Diana dRandom = new Diana((int)(Math.random() * (1200 - rSize + 1)), (int)(Math.random() * (660-rSize + 1)), rSize, false);
			dianasCreadas.add(dRandom);
		}
				
	}
	

		
	
	// Metodo para lanzar el hilo que se encarga de insertar, con un delay, las dianas creadas aleatoriamente,
	// al Vector de las dianas activas que se dibujaran.

	public void runThreadActivas(){
		ThreadDianasActivas da = new ThreadDianasActivas(dianasCreadas, dianasActivas);
		Thread hda = new Thread(da);
		hda.start();
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
		
		if(Game.estadoJuego==Game.ESTADO.Game4) {
			// Guardamos la posicion en la que se pulsa el raton.
			mox = e.getX();
			moy = e.getY();
			
			//Reproducir sonido
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuacion de la ruta hasta el archivo de audio 1
//			String escopeta_filePath = filePath.concat()
			
			
			/*
			 * Secuencia de boton Start 
			 */
			
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
			
			/*
			 * Si el juego esta en start=2, verifica si el click esta sobre alguna de las dianas.
			 * Si lo esta, pone el atributo rota de la diana a true, y lanza el hilo de borrar diana con la(s) diana(s) acertada(s).
			 */
			
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
			
			//BOTON START
			
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
		
		//Pantalla de boton START
		
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
			
			// Cada frame se dibujan todas las dianas en dianasActivas, comprobando el booleano rota para decidir si dibujar la diana
			// entera o rota
			for(Diana d : dianasActivas) {
				if(!d.isRota()) {
					g.drawImage(diana_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
				}else if(d.isRota() && !dianasRotas.contains(d)){
					g.drawImage(dianaRota_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
				}else{
				}
			}
			
			//En cada frame se dibuja el punto de mira encima del raton
			g.drawImage(mira_IMG, mox-16, moy-16, 32, 32, null);
			
		}

	}

}