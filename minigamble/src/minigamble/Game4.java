package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;

//Voy a hacer un arraylist que guarde todas las coordenadas (arraylist de 2) de todas las dianas.
//Si al hacer click el arraylist de las coordenadas concuerda con alguna que esté en el rango de las dianas
//(es decir, centro +- radio, se suman los //puntos de haber roto una diana, se borran las coordenadas 
//del arraylist de coordenadas activas, y se dibuja dianaRota durante unos milisegundos. Tener cuidado,
//diana nueva no se puede dibujar dentro de diana existente.


public class Game4  implements MouseMotionListener, MouseListener{ //Dianas
	
	private Font customFontBot;
	private Font customFontFin;
	
	private ImageIcon diana;
	private ImageIcon dianaRota;
	
	private ImageIcon mira;
	
	private Image diana_IMG;
	private Image dianaRota_IMG;
	private Image mira_IMG;
	
	private int start = 1;
	
	private ArrayList<Diana> dianasActivas = new ArrayList<Diana>();
	
	private int mox;				//Posicion en la que se presiona el raton
	private int moy;
	private int mdx;
	private int mdy;
	
	public Game4(int dificultad){
		
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
		
		try {
			//Cargo todas las imagenes como iconos
			diana = new ImageIcon(Game.class.getResource("multimedia/dianas/diana.png").toURI().toURL() );
			dianaRota = new ImageIcon(Game.class.getResource("multimedia/dianas/dianaRota.png").toURI().toURL() );
			
			mira = new ImageIcon(Game.class.getResource("multimedia/mira.png").toURI().toURL() );
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		//Paso todos los iconos a imágenes
		
		diana_IMG = diana.getImage();
		dianaRota_IMG = dianaRota.getImage();
		
		mira_IMG = mira.getImage();
		
		//Ir creando dianas
		
		while(start == 1) {
			dianasActivas.add(new Diana(300, 300, 300));
		}
		
	}
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mouseDragged(MouseEvent e) {		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mox = e.getX();	// guarda la posicion en la esta el raton
		moy = e.getY();
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		//g.drawImage(Image img, int x, int y, int width, int height, ImageObserver observer);
		
		for(Diana d : dianasActivas) {
			g.drawImage(diana_IMG, d.getX(), d.getY(), (int)d.getSize(), (int)d.getSize(), null);
		}
		g.drawImage(diana_IMG, 200, 200, 128, 128, null);		
		g.drawImage(mira_IMG, mox-16, moy-16, 32, 32, null);
	}
	
	

}