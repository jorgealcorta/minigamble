package minigamble;

import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;

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
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	private int mrx;
	private int mry;
	
	
	public void mouseDragged(MouseEvent e) {
		mdx = e.getX();
		mdy = e.getY();
		
				
		if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false ){	
			b1_state = false;
		}
		
		if( mouseOver(mdx, mdy, 500, 390, 190, 50)== false ){
			b2_state = false;
		}
	}
	
	
	
	
	
	public void mousePressed(MouseEvent e) {
		mox = e.getX();	// guarda la posición en la que se presiona
		moy = e.getY();
		
		if( mouseOver(mox, moy, 500, 290, 190, 50) ){	// si se presiona encima del boton 1 se cambia su estado
			b1_state = true;
		}
		
		if( mouseOver(mox, moy, 500, 390, 190, 50) ){	// si se presiona encima del boton 2 se cambia su estado
			b2_state = true;
		}
		
	}
	
	public void mouseReleased(MouseEvent e) { 
		mrx = e.getX();	// guarda la posicion en la que se suelta
		mry = e.getY();
		
		if(b2_state == true){ // si se ha presionado y soltado encima del segundo boton termina el programa
			System.exit(1); 
		}
		
		b1_state = false;	// cambia el estado del programa a levantado
		b2_state = false;
		
		
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
		
//		
//		try {
//			/*InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("superstar_memesbruh03.ttf");*/             
//			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("superstar_memesbruh03.ttf")).deriveFont(Font.PLAIN,12);
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(customFont);					
//			
//		}catch(Exception e){			
//		}
//		
		
		Font title = new Font("serif", 1 ,50);  //Fuente del título
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
		if(b1_state == true) {				// Dibuja el botón 1 y texto del botón presionado
			g.drawImage(b1IMG_true, 500, 294, null);
			g.drawString("Comenzar", 515, 326);			
		}else {								// Dibuja el botón 1 y texto del botón sin presionar
			g.drawImage(b1IMG_false, 500, 290, null);		
			g.drawString("Comenzar", 515, 322);		
		}
		
		if(b2_state == true) {					// Dibuja el botón 2 y texto del botón presionado
			g.drawImage(b2IMG_true, 500, 394, null);
			g.drawString("Salir", 557, 426);
		}else {									// Dibuja el botón 2 y texto del botón sin presionar
			g.drawImage(b2IMG_false, 500, 390, null);
			g.drawString("Salir", 557, 422);
		}
				
		
		g.setFont(title);
		g.drawString("Minigamble", 430, 150); //Dibuja el título
		
		
		
		//g.drawRect(500, 290, 190, 50); 		//posicion del botón 1
		//g.drawRect(500, 390, 190, 50); 		//posicion del botón 2
	
	}



	

}
