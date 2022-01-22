package minigamble;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



/** 
 * Primera ventana en la que están los botones Empezar y Salir
 */
public class Inicio   implements MouseMotionListener, MouseListener  {
	
//	public  static Font customFont;
	
	private boolean b1_state = false;
	
	private boolean b2_state = false;
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	/**
	 * Constructor de la primera ventana
	 */
	public Inicio() {
		
		
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
	
	
	
	
	/**	Evalua si el ratón está sobre una región
	 * @param mx posición X del ratón
	 * @param my posición Y del ratón
	 * @param x	posición X en la que comienza la región
	 * @param y	posición Y en la que comienza la región
	 * @param width	anchura de la región
	 * @param heigth altura de la región
	 * @return True si el ratón está sobre esa región y False si no lo está
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

	
	
	/** Hace el render de los elementos
	 * @param g recibe Grephics de Game
	 */
	public void render(Graphics g) {
		
	
		
		g.drawImage(media.FondoFichas_img, 0, 0, null);   // Dibuja el fondo
		g.setFont(media.customFontBot);
					
		if(b1_state == true) {				// Dibuja el boton 1 y texto del botón presionado
			g.drawImage(media.bIMGYellow_True, 500, 294, null);
			g.drawString("Comenzar", 512, 326);			
		}else {								// Dibuja el boton 1 y texto del boton sin presionar
			g.drawImage(media.bIMGYellow_False, 500, 290, null);		
			g.drawString("Comenzar", 512, 322);		
		}
		
		if(b2_state == true) {					// Dibuja el boton 2 y texto del boton presionado
			g.drawImage(media.bStartIMG_True, 500, 394, null);
			g.drawString("Salir", 550, 426);
		}else {									// Dibuja el boton 2 y texto del boton sin presionar
			g.drawImage(media.bStartIMG_False, 500, 390, null);
			g.drawString("Salir", 550, 422);
		}
	
				
	
			
		g.drawImage(media.titulo_img, 40 , 75 , 1100, 124, null);				
				
	
	}



	

}
