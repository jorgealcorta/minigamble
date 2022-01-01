package minigamble;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;

public class media {
	
	//COMPARTIDOS
	public static Image bStartIMG_True;
	public static ImageIcon bStart_true;
	
	public static Image bStartIMG_False;	
	public static ImageIcon bStart_false;	
	
	public static Font customFontBot;
	
	//GAME 3
	
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
	
	
	public media() {
		
		try {
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );	
		
			
			//game3	
			lab1Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab1.png").toURI().toURL() );
			lab2Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab2.png").toURI().toURL() );
			lab3Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab3.png").toURI().toURL() );
			endingIcon1 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab1.png").toURI().toURL() );
			endingIcon2 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab2.png").toURI().toURL() );
			
		
		
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
				
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();	
		
		
		
		//game3
		lab1Img = lab1Icon.getImage();
		lab2Img = lab2Icon.getImage();
		lab3Img = lab3Icon.getImage();
		
		endingImg1 = endingIcon1.getImage();
		endingImg2 = endingIcon2.getImage();
		
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
	}

}
