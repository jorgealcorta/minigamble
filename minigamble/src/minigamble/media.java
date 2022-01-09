package minigamble;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	
	public static Image tapeteImg;	
	public static ImageIcon tapeteIcn;
	
	public static Font customFontBot;
	public static Font customFontFin;
	
	public static int numVidas;
	public static int puntInicial;
	
	//GAME1
	
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
	
	//GAME2
	
	public static ImageIcon corazonblanco;
	public static ImageIcon corazonnegro;
	public static ImageIcon diamanteblanco;
	public static ImageIcon diamantenegro;
	public static ImageIcon picablanca;
	public static ImageIcon picanegra;
	public static ImageIcon trebolblanco;
	public static ImageIcon trebolnegro;
	
	public static ImageIcon crossarrow;
	
	
	public static Image corazonblanco_IMG;
	public static Image corazonnegro_IMG;
	public static Image diamanteblanco_IMG;
	public static Image diamantenegro_IMG;
	public static Image picablanca_IMG;
	public static Image picanegra_IMG;
	public static Image trebolblanco_IMG;
	public static Image trebolnegro_IMG;
	
	public static Image crossarrow_IMG;
	
	//GAME3
	
	public static Image lab1Img;
	public static ImageIcon lab1Icon;
	
	public static Image lab2Img;
	public static ImageIcon lab2Icon;
	
	public static Image lab3Img;
	public static ImageIcon lab3Icon;
	
	public static Image lab4Img;
	public static ImageIcon lab4Icon;
	
	public static Image endingImg1;
	public static ImageIcon endingIcon1;
	
	public static Image endingImg2;
	public static ImageIcon endingIcon2;
	
	//GAME4
	
	public static ImageIcon diana;
	public static ImageIcon dianaRota;
	
	public static ImageIcon mira;
	
	public static Image diana_IMG;
	public static Image dianaRota_IMG;
	
	public static Image mira_IMG;
	
	//GAME5
	
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
	
	public static Image tapeteSupImg;	
	public static ImageIcon tapeteSupIcn;
	
	public static Image tapeteInfImg;	
	public static ImageIcon tapeteInfIcn;
	
	//GAME6
	
	public static ImageIcon flechaabj;
	public static ImageIcon flechaabjtrans;
	
	public static ImageIcon flechaarr;
	public static ImageIcon flechaarrtrans;
	
	public static ImageIcon flechadch;
	public static ImageIcon flechadchtrans;
	
	public static ImageIcon flechaizq;
	public static ImageIcon flechaizqtrans;
	
	
	public static Image flechaabj_IMG;
	public static Image flechaabjtrans_IMG;
	
	public static Image flechaarr_IMG;
	public static Image flechaarrtrans_IMG;
	
	public static Image flechadch_IMG;
	public static Image flechadchtrans_IMG;
	
	public static Image flechaizq_IMG;
	public static Image flechaizqtrans_IMG;
	
	//GAME7
	
	public static ImageIcon circleIcn;
	public static Image circleImg;
	
	public static ImageIcon crossIcn;
	public static Image crossImg;
	
	public static ImageIcon rowVertIcn;
	public static Image rowVertImg;
	
	public static ImageIcon rowHorzIcn;
	public static Image rowHorzImg;
	
	//PANTALLA INTERMEDIA
	
	public static ImageIcon vida;
	public static ImageIcon vidatrans;
	public static ImageIcon vidalado;
	
	public static Image vida_IMG;
	public static Image vidatrans_IMG;
	public static Image vidalado_IMG;
	
	public media() {
		
		try {
			
			//COMPARTIDOS
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );	
			tapeteIcn = new ImageIcon( Game.class.getResource("multimedia/tapete.png").toURI().toURL() );
			
			//GAME1
			
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
			
			//GAME2
			
			corazonblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/corazonblanco.png").toURI().toURL() );
			corazonnegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/corazonnegro.png").toURI().toURL() );
			diamanteblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/diamanteblanco.png").toURI().toURL() );
			diamantenegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/diamantenegro.png").toURI().toURL() );
			picablanca = new ImageIcon( Game.class.getResource("multimedia/fichassimon/picablanca.png").toURI().toURL() );
			picanegra = new ImageIcon( Game.class.getResource("multimedia/fichassimon/picanegra.png").toURI().toURL() );
			trebolblanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/trebolblanco.png").toURI().toURL() );
			trebolnegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/trebolnegro.png").toURI().toURL() );
			
			crossarrow = new ImageIcon( Game.class.getResource("multimedia/crossarrowpixel.png").toURI().toURL() );
			
			//GAME3
			
			lab1Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab1.png").toURI().toURL() );
			lab2Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab2.png").toURI().toURL() );
			lab3Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab3.png").toURI().toURL() );
			lab4Icon = new ImageIcon( Game.class.getResource("multimedia/laberintos/lab4.png").toURI().toURL() );
			endingIcon1 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab1.png").toURI().toURL() );
			endingIcon2 = new ImageIcon( Game.class.getResource("multimedia/laberintos/endlab2.png").toURI().toURL() );
			
			//GAME4
			
			diana = new ImageIcon(Game.class.getResource("multimedia/dianas/diana.png").toURI().toURL() );
			dianaRota = new ImageIcon(Game.class.getResource("multimedia/dianas/dianaRota.png").toURI().toURL() );
			
			mira = new ImageIcon(Game.class.getResource("multimedia/mira.png").toURI().toURL() );
			
			//GAME5
			
			iconS1= new ImageIcon( Game.class.getResource("multimedia/simbolos/heartS2.png").toURI().toURL() );
			iconS2= new ImageIcon( Game.class.getResource("multimedia/simbolos/lemonS2.png").toURI().toURL() );
			iconS3= new ImageIcon( Game.class.getResource("multimedia/simbolos/sevenS2.png").toURI().toURL() );
			iconS4= new ImageIcon( Game.class.getResource("multimedia/simbolos/grapeS2.png").toURI().toURL() );		
			
			arrowIc= new ImageIcon( Game.class.getResource("multimedia/simbolos/arrow.png").toURI().toURL() );
			tapeteSupIcn = new ImageIcon( Game.class.getResource("multimedia/tapeteSuperior.png").toURI().toURL() );
			tapeteInfIcn = new ImageIcon( Game.class.getResource("multimedia/tapeteInferior.png").toURI().toURL() );
			
			//GAME6
			
			flechaabj = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaabj.png").toURI().toURL() );
			flechaabjtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaabjtrans.png").toURI().toURL() );

			flechaarr = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaarr.png").toURI().toURL() );
			flechaarrtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaarrtrans.png").toURI().toURL() );

			flechadch = new ImageIcon(Game.class.getResource("multimedia/flechas/flechadch.png").toURI().toURL() );
			flechadchtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechadchtrans.png").toURI().toURL() );

			flechaizq = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaizq.png").toURI().toURL() );
			flechaizqtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaizqtrans.png").toURI().toURL() );
			
			//GAME7
			
			circleIcn = new ImageIcon(Game.class.getResource("multimedia/circle.png").toURI().toURL() );
			crossIcn = new ImageIcon(Game.class.getResource("multimedia/cross.png").toURI().toURL() );
			rowVertIcn = new ImageIcon(Game.class.getResource("multimedia/row.png").toURI().toURL() );
			rowHorzIcn = new ImageIcon(Game.class.getResource("multimedia/rowHorz.png").toURI().toURL() );
			
			//PANTALLA INTERMEDIA
			
			vida = new ImageIcon(Game.class.getResource("multimedia/vida.png").toURI().toURL() );
			vidatrans = new ImageIcon(Game.class.getResource("multimedia/vidatrans.png").toURI().toURL() );
			vidalado = new ImageIcon(Game.class.getResource("multimedia/vidalado.png").toURI().toURL() );
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		//COMPARTIDOS
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();	
		tapeteImg = tapeteIcn.getImage();
				
		
		
		//GAME1
		
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
		
		//GAME2
		
		corazonblanco_IMG = corazonblanco.getImage();
		corazonnegro_IMG = corazonnegro.getImage();
		diamanteblanco_IMG = diamanteblanco.getImage();
		diamantenegro_IMG = diamantenegro.getImage();
		picablanca_IMG = picablanca.getImage();
		picanegra_IMG = picanegra.getImage();
		trebolblanco_IMG = trebolblanco.getImage();
		trebolnegro_IMG = trebolnegro.getImage();
		
		crossarrow_IMG = crossarrow.getImage();
		
		
		//GAME3
		
		lab1Img = lab1Icon.getImage();
		lab2Img = lab2Icon.getImage();
		lab3Img = lab3Icon.getImage();
		lab4Img = lab4Icon.getImage();
		
		endingImg1 = endingIcon1.getImage();
		endingImg2 = endingIcon2.getImage();
		
		//GAME4
		
		diana_IMG = diana.getImage();
		dianaRota_IMG = dianaRota.getImage();
		
		mira_IMG = mira.getImage();
		
		
		//GAME5
		
		imageS1 = iconS1.getImage();
		imageS2 = iconS2.getImage();
		imageS3 = iconS3.getImage();
		imageS4 = iconS4.getImage();
		
		arrowIm = arrowIc.getImage();
		tapeteSupImg = tapeteSupIcn.getImage();		
		tapeteInfImg = tapeteInfIcn.getImage();
		
		//GAME6
		
		flechaabj_IMG = flechaabj.getImage();
		flechaabjtrans_IMG = flechaabjtrans.getImage();
		
		flechaarr_IMG = flechaarr.getImage();
		flechaarrtrans_IMG = flechaarrtrans.getImage();

		flechadch_IMG = flechadch.getImage();
		flechadchtrans_IMG = flechadchtrans.getImage();		
		
		flechaizq_IMG = flechaizq.getImage();
		flechaizqtrans_IMG = flechaizqtrans.getImage();
		
		//GAME7
		
		circleImg = circleIcn.getImage();
		crossImg = crossIcn.getImage();
		rowHorzImg = rowHorzIcn.getImage();
		rowVertImg = rowVertIcn.getImage();
		
		//PANTALLA INTERMEDIA
		
		vida_IMG = vida.getImage();
		vidatrans_IMG = vidatrans.getImage();
		vidalado_IMG = vidalado.getImage();
		
		
		//FONTS
		
		//FUENTE ESTANDAR BOTONES
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,20);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
		try {   
			customFontFin = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuente.ttf"));
			customFontFin = customFontFin.deriveFont(Font.PLAIN,100);
		}catch(Exception e){	
			System.out.println("Problema con la fuente Minigamble");
		}

		
		Properties properties = new Properties();
		InputStream is = null;
		
		try {

			FileReader reader = new FileReader("minigamble/src/minigamble/datos.properties");

//			properties.load(is);
//			
//			
//			numVidas = Integer.parseInt(properties.getProperty("vidas")); //para conseguir el numero de telefono como int en vez de string
//			puntInicial = Integer.parseInt(properties.getProperty("puntInicial")); //prueba para ver si funciona con valores float 
//			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
