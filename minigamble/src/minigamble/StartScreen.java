package minigamble;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class StartScreen implements MouseMotionListener, MouseListener{
	
//	public  static Font customFont;
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon bLogIn_false;		// boton Log In
	private ImageIcon bLogIn_true;
	private boolean bLogIn_state = false;
	
	private ImageIcon bSignIn_false;		// boton Sign In
	private ImageIcon bSigIn_true;
	private boolean bSignIn_state = false;
	
	private ImageIcon bNoReg_false;		// boton No Registrarse 
	private ImageIcon bNoReg_true;
	private boolean bNoReg_state = false;
	
	private ImageIcon bBack_false;		// boton volver atras 
	private ImageIcon bBack_true;
	private boolean bBack_state = false;
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	
	public void mouseDragged(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Start) {				//si se esta en otro estado no hace nada
		
			mdx = e.getX();
			mdy = e.getY();
			
					
			if( mouseOver(mdx, mdy, 500, 190, 190, 50)== false ){	// boton Log In
				bLogIn_state = false;
			}
			
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false ){	// boton Sign In
				bSignIn_state = false;
			}
			
			if( mouseOver(mdx, mdy, 500, 290, 190, 50)== false ){	// boton No Registrarse 
				bNoReg_state = false;
			}
			
			if( mouseOver(mdx, mdy, 25, 625, 40, 30)== false ){		// boton volver atras
				bBack_state = false;
			}
			
			
		}
	}
	
	
	
	
	
	public void mousePressed(MouseEvent e) {
		if(Game.estadoJuego == Game.ESTADO.Start) {				//si se esta en otro estado no hace nada
			
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			if( mouseOver(mox, moy, 500, 190, 190, 50) ){	// si se presiona encima del boton 1 se cambia su estado
				bLogIn_state = true;
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				
			}
			
			if( mouseOver(mox, moy, 500, 290, 190, 50) ){	// si se presiona encima del boton 2 se cambia su estado
				bSignIn_state = true;
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
				bNoReg_state = true;
				try {																				//Reproduce el archivo de sonido 1
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
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
		if(Game.estadoJuego == Game.ESTADO.Start) {
			
			String filePath = new File("").getAbsolutePath();										// Ruta hasta el proyecto
			String s2_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click2.wav");	//Continuacio n de la ruta hasta el archivo de audio 2
			
			if(bLogIn_state == true){ // si se ha presionado y soltado encima del primero suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
			}
			
			if(bSignIn_state == true){ // si se ha presionado y soltado encima del segundo boton termina el programa y suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				Game.estadoJuego = Game.ESTADO.SignIn;			//Si se presiona el boton de back se cambia el estado a Inicio
			}
			
			if(bNoReg_state == true){ // si se ha presionado y soltado encima del segundo boton termina el programa y suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
			}
			
			if(bBack_state == true){ // si se ha presionado y soltado encima del segundo boton termina el programa y suena
				try {																				//Reproduce el archivo de sonido 2
			        Clip sonido = AudioSystem.getClip();
					AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s2_filePath));
			        sonido.open(ais);
			        sonido.start();
		        }catch(Exception e2) {
		        	System.out.println("error");
		        }
				Game.estadoJuego = Game.ESTADO.Inicio;			//Si se presiona el boton de back se cambia el estado a Inicio
			}
			
			bLogIn_state = false;	// cambia el estado del programa a levantado
			bSignIn_state = false;
			bNoReg_state = false;
			bBack_state = false;
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
		

		
		
		Font fButton = new Font("arial", 1 ,30); //Fuente de los botones
		
				
		
		
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/background.png").toURI().toURL() );			//Cargo todas las imagenes como iconos
			
			bLogIn_false = new ImageIcon( Game.class.getResource("multimedia/yellow_button2.png").toURI().toURL() );
			bLogIn_true = new ImageIcon( Game.class.getResource("multimedia/yellow_button3.png").toURI().toURL() );
			
			bSignIn_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bSigIn_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
			bSignIn_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bSigIn_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
			bNoReg_false = new ImageIcon( Game.class.getResource("multimedia/blue_button2.png").toURI().toURL() );
			bNoReg_true = new ImageIcon( Game.class.getResource("multimedia/blue_button3.png").toURI().toURL() );
			
			bBack_false = new ImageIcon( Game.class.getResource("multimedia/green_back1.png").toURI().toURL() );
			bBack_true = new ImageIcon( Game.class.getResource("multimedia/green_back2.png").toURI().toURL() );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		Image backgroundIMG = background.getImage();	// Paso todos los iconos a imágenes
		
		Image b1IMG_false = bLogIn_false.getImage();
		Image b1IMG_true = bLogIn_true.getImage();
		
		Image b2IMG_false = bSignIn_false.getImage();
		Image b2IMG_true = bSigIn_true.getImage();
		
		Image b3IMG_false = bNoReg_false.getImage();
		Image b3IMG_true = bNoReg_true.getImage();
		
		Image bBackIMG_false = bBack_false.getImage();
		Image bBackIMG_true = bBack_true.getImage();
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo
		
	
		
	try {
         
		Font customFont = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
		customFont=customFont.deriveFont(Font.PLAIN,20);
		g.setFont(customFont);
				
		if(bLogIn_state == true) {				// Dibuja el boton Log In y texto del botón presionado
			g.drawImage(b1IMG_true, 500, 194, null);
			g.drawString("Log In", 545, 226);			
		}else {								// Dibuja el boton Log In y texto del boton sin presionar
			g.drawImage(b1IMG_false, 500, 190, null);		
			g.drawString("Log In", 545, 222);		
		}
		
		if(bSignIn_state == true) {					// Dibuja el boton Sign In y texto del boton presionado
			g.drawImage(b2IMG_true, 500, 294, null);
			g.drawString("Sign In", 540, 326);
		}else {									// Dibuja el boton Sign In y texto del boton sin presionar
			g.drawImage(b2IMG_false, 500, 290, null);
			g.drawString("Sign In", 540, 322);
		}
		
		if(bNoReg_state == true) {					// Dibuja el boton No Reg y texto del boton presionado
			g.drawImage(b3IMG_false, 500, 394, null);
			g.drawString("Quick play", 507, 426);
		}else {									// Dibuja el boton No Reg y texto del boton sin presionar
			g.drawImage(b3IMG_true, 500, 390, null);
			g.drawString("Quick play", 507, 422);
		}
		
		if(bBack_state == true) {					// Dibuja el boton Back y texto del boton presionado
			g.drawImage(bBackIMG_true, 25, 628, null);
		}else {									// Dibuja el boton Back y texto del boton sin presionar
			g.drawImage(bBackIMG_false, 25, 625, null);
		}
				
	}catch(Exception e){	
		
		g.setFont(fButton);
		
		if(bLogIn_state == true) {				// Dibuja el boton Log In y texto del botón presionado
			g.drawImage(b1IMG_true, 500, 194, null);
			g.drawString("Log In", 545, 226);			
		}else {								// Dibuja el boton Log In y texto del boton sin presionar
			g.drawImage(b1IMG_false, 500, 190, null);		
			g.drawString("Log In", 545, 222);		
		}
		
		if(bSignIn_state == true) {					// Dibuja el boton Sign In y texto del boton presionado
			g.drawImage(b2IMG_true, 500, 294, null);
			g.drawString("Sign In", 545, 326);
		}else {									// Dibuja el boton Sign In y texto del boton sin presionar
			g.drawImage(b2IMG_false, 500, 290, null);
			g.drawString("Sign In", 545, 322);
		}
		
		if(bNoReg_state == true) {					// Dibuja el boton No Reg y texto del boton presionado
			g.drawImage(b3IMG_false, 500, 394, null);
			g.drawString("No Reg", 545, 426);
		}else {									// Dibuja el boton No Reg y texto del boton sin presionar
			g.drawImage(b3IMG_true, 500, 390, null);
			g.drawString("No Reg", 545, 422);
		}
		
		if(bBack_state == true) {					// Dibuja el boton Back y texto del boton presionado
			g.drawImage(bBackIMG_true, 25, 628, null);
		}else {									// Dibuja el boton Back y texto del boton sin presionar
			g.drawImage(bBackIMG_false, 25, 625, null);
		}
		
		
	}

	}



	


}
