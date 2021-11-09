package minigamble;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;


public class Inicio extends MouseAdapter{
	
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon b1_false;		// botón 1
	private ImageIcon b1_true;
	private boolean b1_state = false;
	
	private ImageIcon b2_false;		// botón 2 
	private ImageIcon b2_true;
	private boolean b2_state = false;
	
	private int mox;				//Posición en la que se presiona el ratón
	private int moy;
	
	
	
	public void mousePressed(MouseEvent e) {
		mox = e.getX();	// guarda la posición en la que se presiona
		moy = e.getY();
		
		if( mouseOver(mox, moy, 500, 290, 190, 50) ){	// si se presiona encima del botón 1 se cambia su estado
			b1_state = true;
		}
		
		if( mouseOver(mox, moy, 500, 390, 190, 50) ){	// si se presiona encima del botón 2 se cambia su estado
			b2_state = true;
		}
		
	}
	
	public void mouseReleased(MouseEvent e) { 
		int mrx = e.getX();	// guarda la posición en la que se suelta
		int mry = e.getY();
		
		b1_state = false;	// cambia el estado del programa a levantado
		b2_state = false;
		
		if( mouseOver(mox, moy, 500, 390, 190, 50) && mouseOver(mrx, mry, 500, 394, 190, 50)){ // si se ha presionado y soltado encima del segundo boton termina el programa
			System.exit(1); 
		}	
		
	}	
	

	public boolean mouseOver(int mx, int my, int x, int y, int width, int heigth) {   // devuelve true si el ratón ha sido presionado dentro de un cuadrado 
		
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
	
	public void render(Graphics g) {
		Font title = new Font("arial", 1 ,50);  //Fuente del título
		Font fButton = new Font("arial", 1 ,30); //Fuente de los botones
		
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/background.png").toURI().toURL() );		//Cargo todas las imágenes como iconos
			b1_false = new ImageIcon( Game.class.getResource("multimedia/yellow_button2.png").toURI().toURL() );
			b1_true = new ImageIcon( Game.class.getResource("multimedia/yellow_button3.png").toURI().toURL() );
			b2_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			b2_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
		} catch (Exception e1) {
			
		}
		
		
		Image backgroundIMG = background.getImage();	// Paso todos los iconos a imágenes
		Image b1IMG_false = b1_false.getImage();
		Image b1IMG_true = b1_true.getImage();
		Image b2IMG_false = b2_false.getImage();
		Image b2IMG_true = b2_true.getImage();
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo
		
		g.setFont(fButton);
		if(b1_state == false) {					// Dibuja el botón 1 y texto del botón sin presionar
			g.drawImage(b1IMG_false, 500, 290, null);		
			g.drawString("Comenzar", 515, 322);
		}else {									// Dibuja el botón 1 y texto del botón presionado
			g.drawImage(b1IMG_true, 500, 294, null);
			g.drawString("Comenzar", 515, 326);
		}
		
		if(b2_state == false) {					// Dibuja el botón 2 y texto del botón sin presionar
			g.drawImage(b2IMG_false, 500, 390, null);
			g.drawString("Salir", 557, 422);
		}else {									// Dibuja el botón 2 y texto del botón presionado
			g.drawImage(b2IMG_true, 500, 394, null);
			g.drawString("Salir", 557, 426);
		}
				
		g.setFont(title);
		g.drawString("MINIGAMBLE", 430, 150); //Dibuja el título
		
		
		//g.drawRect(500, 290, 190, 50); 		//posicion del botón 1
		//g.drawRect(500, 390, 190, 50); 		//posicion del botón 2
	
	}
	

}
