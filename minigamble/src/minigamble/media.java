package minigamble;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class media {
	
	//COMPARTIDOS
	public static Image bStartIMG_True;
	public static ImageIcon bStart_true;
	
	public static Image bStartIMG_False;	
	public static ImageIcon bStart_false;	
	
	public static Font customFontBot;
	
	
	//game1
	
	public static ImageIcon card_hearts_A;
	public static ImageIcon card_hearts_Q;
	public static ImageIcon card_hearts_K;
	public static ImageIcon card_clubs_A;
	public static ImageIcon card_clubs_Q;
	public static ImageIcon card_clubs_K;
	public static ImageIcon card_clubs_J;
	public static ImageIcon card_hearts_J;
	public static ImageIcon card_diamonds_A;
	public static ImageIcon card_diamonds_K;	
	public static ImageIcon cardBack;
	
	public static Image card_hearts_A_IMG;
	public static Image card_hearts_Q_IMG;
	public static Image card_hearts_K_IMG;
	public static Image card_clubs_A_IMG;
	public static Image card_clubs_Q_IMG;
	public static Image card_clubs_K_IMG;
	public static Image card_clubs_J_IMG;
	public static Image card_hearts_J_IMG;
	public static Image card_diamonds_A_IMG;
	public static Image card_diamonds_K_IMG;
	public static Image cardBackIMG;
	
	//game3
	
	public static  Image lab1Img;
	public static  ImageIcon lab1Icon;
	
	public static Image lab2Img;
	public static ImageIcon lab2Icon;
	
	public static  Image lab3Img;
	public static  ImageIcon lab3Icon;
	
	public static  Image endingImg1;
	public static  ImageIcon endingIcon1;
	
	public static  Image endingImg2;
	public static  ImageIcon endingIcon2;
	
	
	//game5
	public static ImageIcon iconS1;
	public static Image imageS1;
	
	public static ImageIcon iconS2;
	public static Image imageS2;

	public static ImageIcon iconS3;
	public static Image imageS3;

	public static ImageIcon iconS4;
	public static Image imageS4;
	
	public static ImageIcon arrowIc;
	public static Image arrowIm;
	
	
	
	public media() {
		
		try {
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );	
		
			
			//game1
			
			cardBack = new ImageIcon( Game.class.getResource("multimedia/cartas/cardBack_red5.png").toURI().toURL() );
			card_hearts_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsA.png").toURI().toURL() );
			card_hearts_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsQ.png").toURI().toURL() );
			card_hearts_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsK.png").toURI().toURL() );
			card_clubs_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsA.png").toURI().toURL() );
			card_clubs_Q = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsQ.png").toURI().toURL() );
			card_clubs_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsK.png").toURI().toURL() );
			card_clubs_J = new ImageIcon( Game.class.getResource("multimedia/cartas/cardClubsJ.png").toURI().toURL() );
			card_hearts_J = new ImageIcon( Game.class.getResource("multimedia/cartas/cardHeartsJ.png").toURI().toURL() );
			card_diamonds_A = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsA.png").toURI().toURL() );
			card_diamonds_K = new ImageIcon( Game.class.getResource("multimedia/cartas/cardDiamondsK.png").toURI().toURL() );
			
			//game3	
			lab1Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab1.png").toURI().toURL() );
			lab2Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab2.png").toURI().toURL() );
			lab3Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab3.png").toURI().toURL() );
			endingIcon1 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab1.png").toURI().toURL() );
			endingIcon2 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab2.png").toURI().toURL() );
			
			//game5
			
			iconS1= new ImageIcon( Game.class.getResource("multimedia/simbolos/heartS2.png").toURI().toURL() );
			iconS2= new ImageIcon( Game.class.getResource("multimedia/simbolos/lemonS2.png").toURI().toURL() );
			iconS3= new ImageIcon( Game.class.getResource("multimedia/simbolos/sevenS2.png").toURI().toURL() );
			iconS4= new ImageIcon( Game.class.getResource("multimedia/simbolos/grapeS2.png").toURI().toURL() );		
			
			arrowIc= new ImageIcon( Game.class.getResource("multimedia/simbolos/arrow.png").toURI().toURL() );
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
				
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();	
		
		
		//game1
		card_hearts_A_IMG = card_hearts_A.getImage();
		card_hearts_Q_IMG = card_hearts_Q.getImage();
		card_hearts_K_IMG = card_hearts_K.getImage();
		card_clubs_A_IMG = card_clubs_A.getImage();
		card_clubs_Q_IMG = card_clubs_Q.getImage();
		card_clubs_K_IMG = card_clubs_K.getImage();
		card_clubs_J_IMG = card_clubs_J.getImage();
		card_hearts_J_IMG = card_hearts_J.getImage();
		card_diamonds_A_IMG = card_diamonds_A.getImage();
		card_diamonds_K_IMG = card_diamonds_K.getImage();
		cardBackIMG = cardBack.getImage();
		
		
		//game3
		lab1Img = lab1Icon.getImage();
		lab2Img = lab2Icon.getImage();
		lab3Img = lab3Icon.getImage();
		
		endingImg1 = endingIcon1.getImage();
		endingImg2 = endingIcon2.getImage();
		
		
		//game5
		imageS1 = iconS1.getImage();
		imageS2 = iconS2.getImage();
		imageS3 = iconS3.getImage();
		imageS4 = iconS4.getImage();
		
		arrowIm = arrowIc.getImage();
		
		
		//fuente estandar botones
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
		

		
		
	}

}
