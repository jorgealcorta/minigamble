package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Game3 implements MouseListener {

	private Image bStartIMG_True;
	private Image bStartIMG_False;
	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	private Font customFontBot;
	
	
	
	
	
	private int start = 1;
	
	public Game3(int dificultad) {
	
		try {
		
		bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
		bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
		
		} catch (Exception e1) {
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
		
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();
		
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
	public void mousePressed(MouseEvent e) {
		
		if(Game.estadoJuego==Game.ESTADO.Game3) {
			mox = e.getX();	// guarda la posicion en la que se presiona
			moy = e.getY();
			
			String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
			String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/click1.wav");	//Continuación de la ruta hasta el archivo de audio 1
			
			
			
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
			
			
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	
	
	public void render(Graphics g) {
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		if ( start==1) {
			
			g.setFont(customFontBot);
			g.setColor(Color.BLACK);
			if(bStart_state == true) {					                   //caso start = 1
				g.drawImage(bStartIMG_True, 500, 294, null);
				g.drawString("Start", 540, 326);
			}else {									
				g.drawImage(bStartIMG_False, 500, 290, null);
				g.drawString("Start", 547, 322);
			}
		}
	
	}





	
		
		
	
}
