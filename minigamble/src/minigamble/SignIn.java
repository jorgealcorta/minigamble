package minigamble;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class SignIn implements MouseMotionListener, MouseListener, KeyListener{
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon text;         // Imagen del texto por defecto
	private ImageIcon textCorrect;	// Imagen del texto valido
	private ImageIcon textError;	// Imagen del texto no valido
	private ImageIcon textWrite;	// Imagen del texto habilitado para escribir
	private ImageIcon bBack_false;		// boton volver atras 
	private ImageIcon bBack_true;
	
	private int text_state = 1;		// Estado del texto -> 1:defecto, 2:Correcto, 3:incorrecto, 0:Habilitado para escribir
	private String usuario = "";	// String con el nombre de usuario
	
	private boolean bBack_state = false;
	
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	
	


	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.SignIn) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			if( mouseOver(mox, moy, 500, 195, 190, 45) ){	// si se presiona encima del campo de texto
				text_state = 0;								// cambia el estado a hablilitado para escribir
				try {																				
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				}
			
			if( !mouseOver(mox, moy, 500, 195, 190, 45) ){	// si se presiona fuera del campo de texto
				text_state = 2;			// !!!! de momento cambia el estado a correcto pero hay que hacer que evalue el string y mire si es correcto o incorrecto
			}
			
			if( mouseOver(mox, moy, 25, 625, 40, 30) ){	// si se presiona encima del boton 2 se cambia su estado
				bBack_state = true;
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
			
				
				
			}
		}
	}
	
	
	public void mouseReleased(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.SignIn) {
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacio n de la ruta hasta el archivo de audio 2
			
			if(bBack_state == true){																// si se ha presionado y soltado encima del segundo boton termina el programa y suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				Game.estadoJuego = Game.ESTADO.Start;											//Si se presiona el boton de back se cambia el estado a Inicio
			}
			
			bBack_state = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (Game.estadoJuego == Game.ESTADO.SignIn && text_state == 0) {	// Si el estado del texto está habilitado (0) y se está en la pantalla SignIn
			if (usuario.length() <=7) {			//Tiene que ser menor o igual a 7 caracteres
				//System.out.println(e);
				usuario = usuario + e.getKeyChar();
				usuario = usuario.replaceAll("[^a-zA-Z0-9]", "");	// Elimina los caracteres que no sean letras o numeros
				//System.out.println(usuario);
			}
			if (usuario != null && usuario.length() > 0 && e.getKeyCode() == 8 ) {	// Tecla de borrar (No nulo, mayor a 0 y  el codigo de la tecla borrar)
				usuario = usuario.substring(0, usuario.length() - 1);	// Borra el último caracter
		    }
			if (e.getKeyCode() == 10) {		// Tecla enter
				text_state = 2;				// !!!! de momento cambia el estado a correcto pero hay que hacer que evalue el string y mire si es correcto o incorrecto
		    }
			
		}
	}
	
	
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
		public void render(Graphics g) {
		Font texto = new Font("arial", 1 ,30); //Fuente del campo de texto
		
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/background.png").toURI().toURL() );			//Cargo todas las imagenes como iconos
			
			text = new ImageIcon( Game.class.getResource("multimedia/greyTextPath.png").toURI().toURL() );
			textCorrect = new ImageIcon( Game.class.getResource("multimedia/greenTextPath.png").toURI().toURL() );
			textError = new ImageIcon( Game.class.getResource("multimedia/redTextPath.png").toURI().toURL() );
			textWrite = new ImageIcon( Game.class.getResource("multimedia/writeTextPath.png").toURI().toURL() );
			bBack_false = new ImageIcon( Game.class.getResource("multimedia/green_back1.png").toURI().toURL() );
			bBack_true = new ImageIcon( Game.class.getResource("multimedia/green_back2.png").toURI().toURL() );
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Image backgroundIMG = background.getImage();	// Paso todos los iconos a imagenes
		
		Image textIMG_grey = text.getImage();
		Image textIMG_green = textCorrect.getImage();
		Image textIMG_red = textError.getImage();
		Image textIMG_write= textWrite.getImage();
		Image bBackIMG_false = bBack_false.getImage();
		Image bBackIMG_true = bBack_true.getImage();
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo

		g.setFont(texto);
		if(text_state == 1) {									// Dibuja la imagen texto por defecto y texto del string usuario
			g.drawImage(textIMG_grey, 500, 194, null);
			g.drawString(usuario, 507, 227);			
		}else if(text_state == 2) {								// Dibuja la imagen texto correcto y texto del string usuario
			g.drawImage(textIMG_green, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}else if(text_state == 3) {								// Dibuja la imagen texto incorrecto y texto del string usuario
			g.drawImage(textIMG_red, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}else {													// Dibuja la imagen texto habilitado y texto del string usuario
			g.drawImage(textIMG_write, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}
		if(bBack_state == true) {					// Dibuja el boton Back y texto del boton presionado
			g.drawImage(bBackIMG_true, 25, 628, null);
		}else {									// Dibuja el boton Back y texto del boton sin presionar
			g.drawImage(bBackIMG_false, 25, 625, null);
		}
		
	}
		
		
}
