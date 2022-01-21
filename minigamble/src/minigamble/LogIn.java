package minigamble;

import java.awt.Font;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

/** 
 * Ventana encargada de empezar el juego con jugadores ya registrados: 
 * <h2>Textfield para nombre</h2>
 * <h2>Textfield para contraseña</h2>
 * <h2>Botón para comenzar si usuario y contraseña son correctos</h2>
 * <h2>Botón para volver atrás</h2>
 */
public class LogIn implements MouseMotionListener, MouseListener, KeyListener{
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon text;         // Imagen del texto por defecto
	private ImageIcon textCorrect;	// Imagen del texto valido
	private ImageIcon textError;	// Imagen del texto no valido
	private ImageIcon textWrite;	// Imagen del texto habilitado para escribir
	private ImageIcon bBack_false;		// boton volver atras 
	private ImageIcon bBack_true;
	
	private ImageIcon bStart_false;
	private ImageIcon bStart_true;
	
	
	private Image backgroundIMG;
	private Image textIMG_grey;
	private Image textIMG_green;
	private Image textIMG_red;
	private Image textIMG_write;
	private Image bBackIMG_false;
	private Image bBackIMG_true;
	
	private Image bStartIMG_false;
	private Image bStartIMG_true;
	
	private Font customFontBot;

	
	private int usuario_state = 1;		// Estado del usuario -> 1:defecto, 2:Correcto, 3:incorrecto, 0:Habilitado para escribir
	private int contrasena_state = 1;
	
	private boolean bstart_state = false;
	
	private String usuario = "";	// String con el nombre de usuario
	private String contrasena = "";
	private String contrasena_oculta = "";
	
	
	private boolean bBack_state = false;
	
	private Font texto;
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	/**
	 * constructor de LogIn 
	 */
	public LogIn() {
		
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/fondoInicioRecortado.png").toURI().toURL() );			//Cargo todas las imagenes como iconos
			
			text = new ImageIcon( Game.class.getResource("multimedia/greyTextPath.png").toURI().toURL() );
			textCorrect = new ImageIcon( Game.class.getResource("multimedia/greenTextPath.png").toURI().toURL() );
			textError = new ImageIcon( Game.class.getResource("multimedia/redTextPath.png").toURI().toURL() );
			textWrite = new ImageIcon( Game.class.getResource("multimedia/writeTextPath.png").toURI().toURL() );
			bBack_false = new ImageIcon( Game.class.getResource("multimedia/green_back1.png").toURI().toURL() );
			bBack_true = new ImageIcon( Game.class.getResource("multimedia/green_back2.png").toURI().toURL() );
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/blue_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/blue_button3.png").toURI().toURL() );
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		backgroundIMG = background.getImage();	// Paso todos los iconos a imagenes
		
		textIMG_grey = text.getImage();
		textIMG_green = textCorrect.getImage();
		textIMG_red = textError.getImage();
		textIMG_write= textWrite.getImage();
		bBackIMG_false = bBack_false.getImage();
		bBackIMG_true = bBack_true.getImage();
		
		bStartIMG_false = bStart_false.getImage();
		bStartIMG_true = bStart_true.getImage();
		
		texto = new Font("arial", 1 ,30); //Fuente del campo de texto
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void mouseClicked(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.LogIn) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			
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
				if(usuario.length()>1 && BaseDatos.existeNombre(usuario) == true) {
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
					if(usuario.length()>1 && BaseDatos.existeNombre(usuario) == true) {		
						if(BaseDatos.comparaContrasena(usuario, contrasena)){
							contrasena_state = 2;	
						}else {
							contrasena_state = 3;	
						}
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
		if(Game.estadoJuego == Game.ESTADO.LogIn) {
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacio n de la ruta hasta el archivo de audio 2
			
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
				Game.partida  = new Partida(0,0,0, usuario, idPart);
				
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
		
		if (Game.estadoJuego == Game.ESTADO.LogIn && usuario_state == 0) {	// Si el estado del texto está habilitado (0) y se está en la pantalla LogIn
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
				if(usuario.length()>1 && BaseDatos.existeNombre(usuario) == true) {
					usuario_state = 2;				
				}else {
					usuario_state = 3;
				}
		    }
			
		}
		
		if (Game.estadoJuego == Game.ESTADO.LogIn && contrasena_state == 0) {	// Si el estado del texto está habilitado (0) y se está en la pantalla LogIn
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
					if(BaseDatos.comparaContrasena(usuario, contrasena)) {
						contrasena_state = 2;	
					}else {
						contrasena_state = 3;
					}
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
		
		
		
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo

		g.setFont(texto);
		g.setFont(customFontBot);
		if(usuario_state == 1) {									// Dibuja la imagen texto por defecto y texto del string usuario
			g.drawImage(textIMG_grey, 500, 194, null);
			g.drawString("User", 554, 227);			
		}else if(usuario_state == 2) {								// Dibuja la imagen texto correcto y texto del string usuario
			g.drawImage(textIMG_green, 500, 194, null);		
			g.drawString(usuario, 517, 227);		
		}else if(usuario_state == 3) {								// Dibuja la imagen texto incorrecto y texto del string usuario
			g.drawImage(textIMG_red, 500, 194, null);		
			g.drawString(usuario, 517, 227);		
		}else {													// Dibuja la imagen texto habilitado y texto del string usuario
			g.drawImage(textIMG_write, 500, 194, null);		
			g.drawString(usuario, 517, 227);		
		}
		
		if(usuario_state == 2) {
			if(contrasena_state == 1) {									// Dibuja la imagen texto por defecto y texto del string usuario
				g.drawImage(textIMG_grey, 500, 294, null);
				g.drawString("Password", 510, 327);			
			}else if(contrasena_state == 2) {								// Dibuja la imagen texto correcto y texto del string usuario
				g.drawImage(textIMG_green, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}else if(contrasena_state == 3) {								// Dibuja la imagen texto incorrecto y texto del string usuario
				g.drawImage(textIMG_red, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}else {													// Dibuja la imagen texto habilitado y texto del string usuario
				g.drawImage(textIMG_write, 500, 294, null);		
				g.drawString(contrasena_oculta, 515, 327);		
			}
		}
		
		if(usuario_state == 2 && contrasena_state == 2) {
			if(bstart_state == true) {				// Dibuja el boton Log In y texto del botón presionado
				g.drawImage(bStartIMG_true, 500, 394, null);
				g.drawString("Start", 545, 426);			
			}else {								// Dibuja el boton Log In y texto del boton sin presionar
				g.drawImage(bStartIMG_false, 500, 390, null);		
				g.drawString("Start", 545, 422);		
			}
			
			
		}
		
		
		
		if(bBack_state == true) {					// Dibuja el boton Back y texto del boton presionado
			g.drawImage(bBackIMG_true, 25, 54, null);
		}else {									// Dibuja el boton Back y texto del boton sin presionar
			g.drawImage(bBackIMG_false, 25, 50, null);
		}
		
	}
		
		
}
