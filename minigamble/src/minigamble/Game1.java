package minigamble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;


import javax.swing.ImageIcon;

public class Game1 { // Memorizar cartas
	private ImageIcon background;	// Fondo
	private ImageIcon card_hearts_A;
	private ImageIcon card_hearts_Q;
	private ImageIcon card_hearts_K;
	private ImageIcon card_clubs_A;
	private ImageIcon card_clubs_Q;
	private ImageIcon card_clubs_K;
	
	private Image card_hearts_A_IMG;
	private Image card_hearts_Q_IMG;
	private Image card_hearts_K_IMG;
	private Image card_clubs_A_IMG;
	private Image card_clubs_Q_IMG;
	private Image card_clubs_K_IMG;

	private ArrayList<String> allCards = new ArrayList<String>();
	
	
	
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
	
	public Image getImagenCarta(int index) {
		
		if(allCards.get(index) == "card_hearts_A") {
			return card_hearts_A_IMG;
		}else if(allCards.get(index) == "card_hearts_Q") {
			return card_hearts_Q_IMG;
		}else if(allCards.get(index) == "card_hearts_K") {
			return card_hearts_K_IMG;
		}else if(allCards.get(index) == "card_clubs_A") {
			return card_clubs_A_IMG;
		}else if(allCards.get(index) == "card_clubs_Q") {
			return card_clubs_Q_IMG;
		}else{
			return card_clubs_K_IMG;
		}
		
	}
	
	public void render(Graphics g) {
		try {
			background = new ImageIcon( Game.class.getResource("multimedia/fondoInicioRecortado.png").toURI().toURL() );			//Cargo todas las imagenes como iconos
			card_hearts_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsA.png").toURI().toURL() );
			card_hearts_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsQ.png").toURI().toURL() );
			card_hearts_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsK.png").toURI().toURL() );
			card_clubs_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsA.png").toURI().toURL() );
			card_clubs_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsQ.png").toURI().toURL() );
			card_clubs_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsK.png").toURI().toURL() );
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		Image backgroundIMG = background.getImage();	// Paso todos los iconos a imagenes
		card_hearts_A_IMG = card_hearts_A.getImage();
		card_hearts_Q_IMG = card_hearts_Q.getImage();
		card_hearts_K_IMG = card_hearts_K.getImage();
		card_clubs_A_IMG = card_clubs_A.getImage();
		card_clubs_Q_IMG = card_clubs_Q.getImage();
		card_clubs_K_IMG = card_clubs_K.getImage();
		
		
		g.drawImage(backgroundIMG, 0, 0, null);   // Dibuja el fondo
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		g.drawImage(getImagenCarta(0), (1*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
		g.drawImage(getImagenCarta(1), (2*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
		g.drawImage(getImagenCarta(2), (3*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
		g.drawImage(getImagenCarta(3), (4*(1200/5))-(140/2), (1*(700/4))-(190/2)-20, null);
		g.drawImage(getImagenCarta(4), (1*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
		g.drawImage(getImagenCarta(5), (2*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
		g.drawImage(getImagenCarta(6), (3*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
		g.drawImage(getImagenCarta(7), (4*(1200/5))-(140/2), (2*(700/4))-(190/2), null);
		g.drawImage(getImagenCarta(8), (1*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
		g.drawImage(getImagenCarta(9), (2*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
		g.drawImage(getImagenCarta(10), (3*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
		g.drawImage(getImagenCarta(11), (4*(1200/5))-(140/2), (3*(700/4))-(190/2)+20, null);
		
		
		
		
	}	
}
