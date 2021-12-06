package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

public class Game2 implements KeyListener{
	
	private int start = 1;
	
	private Font customFontBot;
	private Font customFontFin;
	
	private ImageIcon background;	// Fondo
	
	private ImageIcon corazonblanco;
	private ImageIcon corazonnegro;
	private ImageIcon diamanteblanco;
	private ImageIcon diamantenegro;
	private ImageIcon picablanca;
	private ImageIcon picanegra;
	private ImageIcon trebolblanco;
	private ImageIcon trebolnegro;
	
	private Image corazonblanco_IMG;
	private Image corazonnegro_IMG;
	private Image diamanteblanco_IMG;
	private Image diamantenegro_IMG;
	private Image picablanca_IMG;
	private Image picanegra_IMG;
	private Image trebolblanco_IMG;
	private Image trebolnegro_IMG;
	
	private ArrayList<String> palos = new ArrayList<String>();
	private ArrayList<String> palosCorrectos = new ArrayList<String>();
	private ArrayList<String> palosUsuario = new ArrayList<String>();


	
public Game2(int dificultad) {
		
		try {
			//Cargo todas las imagenes como iconos
			corazonblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/corazonblanco.png").toURI().toURL() );
			corazonnegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/corazonnegro.png").toURI().toURL() );
			diamanteblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/diamanteblanco.png").toURI().toURL() );
			diamantenegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/diamantenegro.png").toURI().toURL() );
			picablanca = new ImageIcon( Game.class.getResource("multimedia/fichassimon/picablanca.png").toURI().toURL() );
			picanegra = new ImageIcon( Game.class.getResource("multimedia/fichassimon/picanegra.png").toURI().toURL() );
			trebolblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/trebolblanco.png").toURI().toURL() );
			trebolnegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/trebolnegro.png").toURI().toURL() );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		// Paso todos los iconos a imagenes
		corazonblanco_IMG = corazonblanco.getImage();
		corazonnegro_IMG = corazonnegro.getImage();
		diamanteblanco_IMG = diamanteblanco.getImage();
		diamantenegro_IMG = diamantenegro.getImage();
		picablanca_IMG = picablanca.getImage();
		picanegra_IMG = picanegra.getImage();
		trebolblanco_IMG = trebolblanco.getImage();
		trebolnegro_IMG = trebolnegro.getImage();
		
		//Cargamos fuentes
		
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
		
		//Creamos el array de los cuatro palos
		palos.add("cora");
		palos.add("diam");
		palos.add("pica");
		palos.add("treb");
		
		System.out.println(palos);
		
		
		//Creamos la combinacion correcta del simon says
		for(int i = 0 ; i < 5; i++) {
			palosCorrectos.add(palos.get((int) Math.floor(Math.random()*4)));
		}
		
		System.out.println(palosCorrectos);
		
		
				
	}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

public void render(Graphics g) {
	g.setColor(Color.decode("#208b3a"));
	g.fillRect(0, 0, 1200, 700);
}

}
