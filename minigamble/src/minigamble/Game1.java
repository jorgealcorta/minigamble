package minigamble;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.ImageIcon;

public class Game1 { // Memorizar cartas
	private ImageIcon background;	// Fondo

	ArrayList<String> allCards = new ArrayList<String>();
	ArrayList<String> cartasDesordenadas = new ArrayList<String>();
	
	public Game1(int dificultad) {
		// TODO Auto-generated constructor stub
		allCards.add("card_hearts_A");
		allCards.add("card_hearts_Q");
		allCards.add("card_hearts_K");
		allCards.add("card_clubs_A");
		allCards.add("card_clubs_Q");
		allCards.add("card_clubs_K");
		allCards.add("card_hearts_A");
		allCards.add("card_hearts_Q");
		allCards.add("card_hearts_K");
		allCards.add("card_clubs_A");
		allCards.add("card_clubs_Q");
		allCards.add("card_clubs_K");
		
		Collections.shuffle(allCards);
		
		
		
	}
	
	public void render(Graphics g) {
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/fondoInicioRecortado.png").toURI().toURL() );			//Cargo todas las imagenes como iconos
			card_hearts_A = new ImageIcon( Game.class.getResource("multimedia/cartas/card_hearts_A.png").toURI().toURL() );
			card_hearts_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/card_hearts_Q.png").toURI().toURL() );
			card_hearts_K = new ImageIcon( Game.class.getResource("multimedia/cartas/card_hearts_K.png").toURI().toURL() );
			card_clubs_A = new ImageIcon( Game.class.getResource("multimedia/cartas/card_clubs_A.png").toURI().toURL() );
			card_clubs_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/card_clubs_Q.png").toURI().toURL() );
			card_clubs_K = new ImageIcon( Game.class.getResource("multimedia/cartas/card_clubs_K.png").toURI().toURL() );
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		Image backgroundIMG = background.getImage();	// Paso todos los iconos a imagenes
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo

		
		
	}	
}
