package minigamble;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/** 
 * Ventana encargada de empezar el juego con jugadores nuevos: 
 * Textfield para nombre no registrado
 * Textfield para contrasena
 * Botón para comenzar si usuario y contraseña son correctos
 * Botn para volver atras
 */
public class SignIn implements MouseMotionListener, MouseListener, KeyListener{
	

	
	private int usuario_state = 1;		// Estado del usuario -> 1:defecto, 2:Correcto, 3:incorrecto, 0:Habilitado para escribir
	private int contrasena_state = 1;
	
	private boolean bstart_state = false;
	
	private String usuario = "";	// String con el nombre de usuario
	private String contrasena = "";
	private String contrasena_oculta = "";
	
	
	private boolean bBack_state = false;
		
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	/**
	 * Constructor de SignIn
	 */
	public SignIn() {		
	}
	

	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.SignIn) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuacion de la ruta hasta el archivo de audio 1
			
			
			if( mouseOver(mox, moy, 500, 390, 190, 50)  && usuario_state == 2 && contrasena_state == 2 ){	// si se presiona encima del boton start se cambia su estado
				bstart_state = true;
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				
			}
			
			if( mouseOver(mox, moy, 500, 195, 190, 45) ){	// si se presiona encima del campo de texto
				usuario_state = 0;								// cambia el estado a hablilitado para escribir
				contrasena_state = 1;
				contrasena = "";
				contrasena_oculta = "";
				
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
				if(usuario.length()>1 && BaseDatos.existeNombre(usuario) == false) {
					usuario_state = 2;				// !!!! de momento cambia el estado a correcto pero hay que hacer que evalue el string y mire si es correcto o incorrecto
				}else {
					usuario_state = 3;
				}			
			}
			
						
			if (usuario_state == 2) {
				if( mouseOver(mox, moy, 500, 295, 190, 45) ){	// si se presiona encima del campo de texto
					contrasena_state = 0;								// cambia el estado a hablilitado para escribir
					try {																				
				        Clip sonido = AudioSystem.getClip();
						AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
				        sonido.open(ais);
				        sonido.start();
			        }catch(Exception e2) {
			        	System.out.println("error");
			        }
					}
				
				if( !mouseOver(mox, moy, 500, 295, 190, 45) ){	// si se presiona fuera del campo de texto
					if(contrasena.length()>=5 && BaseDatos.existeNombre(usuario) == false) {
						contrasena_state = 2;			// !!!! de momento cambia el estado a correcto pero hay que hacer que evalue el string y mire si es correcto o incorrecto
					}else {
						contrasena_state = 3;
					}
				}	
			}
			
						
			if( mouseOver(mox, moy, 25, 50, 40, 30) ){	// si se presiona encima del boton back se cambia su estado
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
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacion de la ruta hasta el archivo de audio 2
			
			if(bstart_state == true){ // si se ha presionado y soltado encima del primero suena
				try {				  //Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				Game.cancion.close();
				BaseDatos.insertarJugador(usuario, contrasena);
				int idPart = BaseDatos.insertarPartida(usuario);
				usuario_state = 1;
				contrasena_state = 1;
				Partida.vidas = 3;
				Game.cancion.close();
				Game.partida.puntuacionGeneral = 0;
				Game.partida  = new Partida(0,0,0, usuario, idPart);
				contrasena = "";
				contrasena_oculta = "";
				usuario = "";
			}
						
			if(bBack_state == true){																// si se ha presionado y soltado encima del segundo boton termina el programa y suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				usuario_state = 1;
				contrasena_state = 1;
				contrasena = "";
				contrasena_oculta = "";
				usuario = "";
				Game.estadoJuego = Game.ESTADO.Start;											//Si se presiona el boton de back se cambia el estado a Inicio
			}
			
			bBack_state = false;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (Game.estadoJuego == Game.ESTADO.SignIn && usuario_state == 0) {	// Si el estado del texto está habilitado (0) y se está en la pantalla SignIn
			if (usuario.length() <=7) {			//Tiene que ser menor o igual a 7 caracteres
				//System.out.println(e);
				usuario = usuario + e.getKeyChar();
				usuario = usuario.replaceAll("[^a-zA-Z0-9]", "");	// Elimina los caracteres que no sean letras o numeros
				usuario = usuario.toLowerCase();
				//System.out.println(usuario);
			}
			if (usuario != null && usuario.length() > 0 && e.getKeyCode() == 8 ) {	// Tecla de borrar (No nulo, mayor a 0 y  el codigo de la tecla borrar)
				usuario = usuario.substring(0, usuario.length() - 1);	// Borra el último caracter
		    }
			if (e.getKeyCode() == 10) {		// Tecla enter
				if(usuario.length()>1 && BaseDatos.existeNombre(usuario) == false) {
					usuario_state = 2;				
				}else {
					usuario_state = 3;
				}
		    }
			
		}
		
		if (Game.estadoJuego == Game.ESTADO.SignIn && contrasena_state == 0) {	// Si el estado del texto está habilitado (0) y se está en la pantalla SignIn
			if (contrasena.length() <=7) {			//Tiene que ser menor o igual a 7 caracteres
				//System.out.println(e);
				contrasena = contrasena + e.getKeyChar();
				contrasena = contrasena.replaceAll("[^a-zA-Z0-9]", "");	// Elimina los caracteres que no sean letras o numeros
				contrasena_oculta = contrasena.replaceAll("[!(^a-zA-Z0-9)]", "*");	// Elimina los caracteres que no sean letras o numeros
				//System.out.println(contrasena_oculta);
			}
			if (contrasena != null && contrasena.length() > 0 && e.getKeyCode() == 8 ) {	// Tecla de borrar (No nulo, mayor a 0 y  el codigo de la tecla borrar)
				contrasena = contrasena.substring(0, contrasena.length() - 1);	// Borra el último caracter
				contrasena_oculta = contrasena.replaceAll("[!(^a-zA-Z0-9)]", "*");	// Elimina los caracteres que no sean letras o numeros
		    }
			if (e.getKeyCode() == 10) {		// Tecla enter
				if(contrasena.length() >= 5) {
					contrasena_state = 2;				
				}else {
					contrasena_state = 3;
				}
		    }
			
		}
		
	}
	

	
	
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
		mdx = e.getX();
		mdy = e.getY();
		if( mouseOver(mdx, mdy, 500, 390, 190, 50)== false ){	// boton No Registrarse 
			bstart_state = false;
		}
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
	/** Hace el render de los elementos
	 * @param g recibe Grephics de Game
	 */
	public void render(Graphics g) {
		
		
		
		
		g.drawImage(media.FondoFichas_img, 0, 0, null);   // Dibuja el fondo

	
		g.setFont(media.customFontBot);
		if(usuario_state == 1) {									// Dibuja la imagen texto por defecto y texto del string usuario
			g.drawImage(media.textIMG, 500, 194, null);
			g.drawString("User", 547, 227);			
		}else if(usuario_state == 2) {								// Dibuja la imagen texto correcto y texto del string usuario
			g.drawImage(media.textCorrectIMG, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}else if(usuario_state == 3) {								// Dibuja la imagen texto incorrecto y texto del string usuario
			g.drawImage(media.textErrorIMG, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}else {													// Dibuja la imagen texto habilitado y texto del string usuario
			g.drawImage(media.textWriteIMG, 500, 194, null);		
			g.drawString(usuario, 507, 227);		
		}
		
		if(usuario_state == 2) {
			if(contrasena_state == 1) {									// Dibuja la imagen texto por defecto y texto del string usuario
				g.drawImage(media.textIMG, 500, 294, null);
				g.drawString("Password", 510, 327);			
			}else if(contrasena_state == 2) {								// Dibuja la imagen texto correcto y texto del string usuario
				g.drawImage(media.textCorrectIMG, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}else if(contrasena_state == 3) {								// Dibuja la imagen texto incorrecto y texto del string usuario
				g.drawImage(media.textErrorIMG, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}else {													// Dibuja la imagen texto habilitado y texto del string usuario
				g.drawImage(media.textWriteIMG, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}
		}
		
		if(usuario_state == 2 && contrasena_state == 2) {
			if(bstart_state == true) {				// Dibuja el boton Log In y texto del botón presionado
				g.drawImage(media.bStartIMG_True, 500, 394, null);
				g.drawString("Start", 545, 426);			
			}else {								// Dibuja el boton Log In y texto del boton sin presionar
				g.drawImage(media.bStartIMG_False, 500, 390, null);		
				g.drawString("Start", 545, 422);		
			}
			
			
		}
		
		
		
		if(bBack_state == true) {					// Dibuja el boton Back y texto del boton presionado
			g.drawImage(media.bBack_true_img, 25, 54, null);
		}else {									// Dibuja el boton Back y texto del boton sin presionar
			g.drawImage(media.bBack_false_img, 25, 50, null);
		}
		
	}
		
		
}
