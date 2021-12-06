package minigamble;

import java.awt.event.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;


public class Inicio   implements MouseMotionListener, MouseListener  {
	
//	public  static Font customFont;
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon b1_false;		// boton 1
	private ImageIcon b1_true;
	private boolean b1_state = false;
	
	private ImageIcon b2_false;		// boton 2 
	private ImageIcon b2_true;
	private boolean b2_state = false;
	
	private Image backgroundIMG;
	private Image b1IMG_false;
	private Image b1IMG_true;
	private Image b2IMG_false;
	private Image b2IMG_true;
	
	private Font customFontMG;
	private Font customFontBot;
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	public Inicio() {
		
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/fondoInicioRecortado.png").toURI().toURL() );		//Cargo todas las imagenes como iconos
			b1_false = new ImageIcon( Game.class.getResource("multimedia/yellow_button2.png").toURI().toURL() );
			b1_true = new ImageIcon( Game.class.getResource("multimedia/yellow_button3.png").toURI().toURL() );
			b2_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			b2_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
			} catch (Exception e1) {
			e1.printStackTrace();
			}
		
		backgroundIMG = background.getImage();	// Paso todos los iconos a imágenes
		b1IMG_false = b1_false.getImage();
		b1IMG_true = b1_true.getImage();
		b2IMG_false = b2_false.getImage();
		b2IMG_true = b2_true.getImage();
		
		try {
	         
			customFontMG = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuente.ttf"));
			customFontMG = customFontMG.deriveFont(Font.PLAIN,100);
			
						
		}catch(Exception e){	
			System.out.println("Problema con la fuente Minigamble");
		}
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
		
	}
	
	
	
	
	
	
	public void mouseDragged(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Inicio) {		//si se esta en otro estado no hace nada
		
			mdx = e.getX();
			mdy = e.getY();
			
					
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false ){	
				b1_state = false;
			}
			
			if( mouseOver(mdx, mdy, 500, 390, 190, 50)== false ){
				b2_state = false;
			}
		}
	}
	
	
	
	
	
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Inicio) {		//si se esta en otro estado no hace nada
			
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			if( mouseOver(mox, moy, 500, 290, 190, 50) ){	// si se presiona encima del boton 1 se cambia su estado
				b1_state = true;
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				
			}
			
			if( mouseOver(mox, moy, 500, 390, 190, 50) ){	// si se presiona encima del boton 2 se cambia su estado
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				b2_state = true;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Inicio) {		//si se esta en otro estado no hace nada
			
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacio n de la ruta hasta el archivo de audio 2
			
			if(b1_state == true){ // si se ha presionado y soltado encima del primero suena
				
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
	
				Game.estadoJuego = Game.ESTADO.Start;		// Cuando se presiona continuar pasa al estado Login
				
			}
			
			if(b2_state == true){ // si se ha presionado y soltado encima del segundo boton termina el programa y suena 
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				System.exit(1);
			}
			
			b1_state = false;	// cambia el estado del programa a levantado
			b2_state = false;
		}
		
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
		}
	}
	
	
	public void mouseMoved(MouseEvent e) {			
	}

	public void mouseClicked(MouseEvent e) {			
	}

	public void mouseEntered(MouseEvent e) {		
	}

	public void mouseExited(MouseEvent e) {		
	}

	
	
	public void render(Graphics g) {
		
		Font title = new Font("serif", 1 ,50);  //Fuente del título
		Font fButton = new Font("arial", 1 ,30); //Fuente de los botones
		
		
		
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo
		
	
		
		try {
	         
			g.setFont(customFontBot);
						
			if(b1_state == true) {				// Dibuja el boton 1 y texto del botón presionado
				g.drawImage(b1IMG_true, 500, 294, null);
				g.drawString("Comenzar", 512, 326);			
			}else {								// Dibuja el boton 1 y texto del boton sin presionar
				g.drawImage(b1IMG_false, 500, 290, null);		
				g.drawString("Comenzar", 512, 322);		
			}
			
			if(b2_state == true) {					// Dibuja el boton 2 y texto del boton presionado
				g.drawImage(b2IMG_true, 500, 394, null);
				g.drawString("Salir", 550, 426);
			}else {									// Dibuja el boton 2 y texto del boton sin presionar
				g.drawImage(b2IMG_false, 500, 390, null);
				g.drawString("Salir", 550, 422);
			}
		
		}catch(Exception e){	
			
			
			g.setFont(customFontBot);
			if(b1_state == true) {				// Dibuja el boton 1 y texto del botón presionado
				g.drawImage(b1IMG_true, 500, 294, null);
				g.drawString("Comenzar", 515, 326);			
			}else {								// Dibuja el boton 1 y texto del boton sin presionar
				g.drawImage(b1IMG_false, 500, 290, null);		
				g.drawString("Comenzar", 515, 322);		
			}
			if(b2_state == true) {					// Dibuja el boton 2 y texto del boton presionado
				g.drawImage(b2IMG_true, 500, 394, null);
				g.drawString("Salir", 557, 426);
			}else {									// Dibuja el boton 2 y texto del boton sin presionar
				g.drawImage(b2IMG_false, 500, 390, null);
				g.drawString("Salir", 557, 422);
			}
		
		}
				
		
		try {
			g.setFont(customFontMG);
			g.drawString("Minigamble", 370, 150); //Dibuja el titulo con una fuente personalizada
			
						
		}catch(Exception e){	
			
			g.setFont(title);
			g.drawString("Minigamble", 430, 150); //Dibuja el titulo en caso de que no se encuentre la fuente
		}
		
		
		
		//g.drawRect(500, 290, 190, 50); 		//posicion del boton 1
		//g.drawRect(500, 390, 190, 50); 		//posicion del boton 2
	
	}



	

}
