package minigamble;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;

public class media {
	
	//COMPARTIDOS
	
	public static Image titulo_img;
	public static ImageIcon titulo;
	
	public static Image FondoFichas_img;
	public static ImageIcon Fondofichas;
	
	public static Image bStartIMG_True;
	public static ImageIcon bStart_true;
	
	public static Image bStartIMG_False;	
	public static ImageIcon bStart_false;	
	
	public static Image bIMGYellow_True;
	public static ImageIcon bYellow_True;
	
	public static Image bIMGYellow_False;
	public static ImageIcon bYellow_False;
	
	public static Image bIMGBlue_True;
	public static ImageIcon bBlue_True;
	
	public static Image bIMGBlue_False;
	public static ImageIcon bBlue_False;
	
	
	public static Image bBack_false_img;
	public static ImageIcon bBack_false;
	
	public static Image bBack_true_img;
	public static ImageIcon bBack_true;
	
	public static Image tapeteImg;	
	public static ImageIcon tapeteIcn;
	
	public static Font customFontBot;
	public static Font customFontFin;
	public static Font customFontTitle;
	
	public static int numVidas;
	public static int puntInicial;
	
	
	//SIGNIN
	
	public static ImageIcon text;
	public static Image textIMG;
		
	public static ImageIcon textWrite;
	public static Image textWriteIMG;
	
	public static ImageIcon textCorrect;
	public static Image textCorrectIMG;
	
	public static ImageIcon textError;
	public static Image textErrorIMG;
	
	//GAME1
	
	public static ImageIcon aNegra;
	public static ImageIcon aRoja;
	public static ImageIcon kNegra;
	public static ImageIcon kRoja;
	public static ImageIcon tresRojo;
	public static ImageIcon tresNegro;
	public static ImageIcon comodinRojo;
	public static ImageIcon comodinNegro;	
	public static ImageIcon reverso;
	
	public static Image aNegra_IMG;
	public static Image aRoja_IMG;
	public static Image kNegra_IMG;
	public static Image kRoja_IMG;
	public static Image tresRojo_IMG;
	public static Image tresNegro_IMG;
	public static Image comodinRojo_IMG;
	public static Image comodinNegro_IMG;
	public static Image reverso_IMG;
	
	//GAME2
	
	public static ImageIcon leftBlanco;
	public static ImageIcon leftNegro;
	public static ImageIcon upBlanco;
	public static ImageIcon upNegro;
	public static ImageIcon downBlanco;
	public static ImageIcon downNegro;
	public static ImageIcon rightBlanco;
	public static ImageIcon rightNegro;
	
	public static ImageIcon crossarrow;
	
	
	public static Image leftBlanco_img;
	public static Image leftNegro_img;
	public static Image upBlanco_img;
	public static Image upNegro_img;
	public static Image downBlanco_img;
	public static Image downNegro_img;
	public static Image rightBlanco_img;
	public static Image rightNegro_img;
	
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
	
	public static ImageIcon tapeteChainz;
	
	public static Image flechaabj_IMG;
	public static Image flechaabjtrans_IMG;
	
	public static Image flechaarr_IMG;
	public static Image flechaarrtrans_IMG;
	
	public static Image flechadch_IMG;
	public static Image flechadchtrans_IMG;
	
	public static Image flechaizq_IMG;
	public static Image flechaizqtrans_IMG;
	
	public static Image tapeteChainz_IMG;
	
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
	
	//PANTALLA FINAL
	
	public static ImageIcon panelTopJugadores;
	
	public static Image panelTopJugadores_IMG;
	
	
	public media() {
		
		try {
			
			//COMPARTIDOS
			
			titulo = new ImageIcon( Game.class.getResource("multimedia/titulo.png").toURI().toURL() );
			Fondofichas = new ImageIcon( Game.class.getResource("multimedia/fondoInicioRecortado.png").toURI().toURL() );
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );	
			bYellow_True = new ImageIcon( Game.class.getResource("multimedia/yellow_button3.png").toURI().toURL() );
			bYellow_False = new ImageIcon( Game.class.getResource("multimedia/yellow_button2.png").toURI().toURL() );
			bBlue_True = new ImageIcon( Game.class.getResource("multimedia/blue_button3.png").toURI().toURL() );
			bBlue_False = new ImageIcon( Game.class.getResource("multimedia/blue_button2.png").toURI().toURL() );
			bBack_true = new ImageIcon( Game.class.getResource("multimedia/green_back2.png").toURI().toURL() );
			bBack_false = new ImageIcon( Game.class.getResource("multimedia/green_back1.png").toURI().toURL() );
			
			tapeteIcn = new ImageIcon( Game.class.getResource("multimedia/tapete.png").toURI().toURL() );
			
			
			//SIGNIN
			
			text = new ImageIcon( Game.class.getResource("multimedia/greyTextPath.png").toURI().toURL() );
			textCorrect = new ImageIcon( Game.class.getResource("multimedia/greenTextPath.png").toURI().toURL() );
			textError = new ImageIcon( Game.class.getResource("multimedia/redTextPath.png").toURI().toURL() );
			textWrite = new ImageIcon( Game.class.getResource("multimedia/writeTextPath.png").toURI().toURL() );
			
			//GAME1
			
			reverso = new ImageIcon( Game.class.getResource("multimedia/cartas/reverso.png").toURI().toURL() );
			comodinNegro = new ImageIcon( Game.class.getResource("multimedia/cartas/comodinNegro.png").toURI().toURL() );
			comodinRojo = new ImageIcon( Game.class.getResource("multimedia/cartas/comodinRojo.png").toURI().toURL() );
			aNegra = new ImageIcon( Game.class.getResource("multimedia/cartas/aNegra.png").toURI().toURL() );
			aRoja = new ImageIcon( Game.class.getResource("multimedia/cartas/aRoja.png").toURI().toURL() );
			tresNegro = new ImageIcon( Game.class.getResource("multimedia/cartas/tresNegro.png").toURI().toURL() );
			tresRojo = new ImageIcon( Game.class.getResource("multimedia/cartas/tresRojo.png").toURI().toURL() );
			kNegra = new ImageIcon( Game.class.getResource("multimedia/cartas/kNegra.png").toURI().toURL() );
			kRoja = new ImageIcon( Game.class.getResource("multimedia/cartas/kRoja.png").toURI().toURL() );
			
			//GAME2
			
			leftBlanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaRoja.png").toURI().toURL() );
			leftNegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaNegra.png").toURI().toURL() );
			upBlanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaRojaUp.png").toURI().toURL() );
			upNegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaNegraUp.png").toURI().toURL() );
			rightBlanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaRojaRight.png").toURI().toURL() );
			rightNegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaNegraRight.png").toURI().toURL() );
			downBlanco = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaRojaDown.png").toURI().toURL() );
			downNegro = new ImageIcon( Game.class.getResource("multimedia/fichassimon/flechaNegraDown.png").toURI().toURL() );
			
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
			
			flechaabj = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/flechaDown.png").toURI().toURL() );
			flechaabjtrans = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/negativeDown.png").toURI().toURL() );

			flechaarr = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/flechaUp.png").toURI().toURL() );
			flechaarrtrans = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/negativeUp.png").toURI().toURL() );

			flechadch = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/flechaRight.png").toURI().toURL() );
			flechadchtrans = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/negativeRight.png").toURI().toURL() );

			flechaizq = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/flechaLeft.png").toURI().toURL() );
			flechaizqtrans = new ImageIcon(Game.class.getResource("multimedia/arrowsFalling/negativeLeft.png").toURI().toURL() );
			
			tapeteChainz = new ImageIcon(Game.class.getResource("multimedia/tapeteChainz.png").toURI().toURL() );
			
			//GAME7
			
			circleIcn = new ImageIcon(Game.class.getResource("multimedia/circle.png").toURI().toURL() );
			crossIcn = new ImageIcon(Game.class.getResource("multimedia/cross.png").toURI().toURL() );
			rowVertIcn = new ImageIcon(Game.class.getResource("multimedia/row.png").toURI().toURL() );
			rowHorzIcn = new ImageIcon(Game.class.getResource("multimedia/rowHorz.png").toURI().toURL() );
			
			//PANTALLA INTERMEDIA
			
			vida = new ImageIcon(Game.class.getResource("multimedia/vida.png").toURI().toURL() );
			vidatrans = new ImageIcon(Game.class.getResource("multimedia/vidatrans.png").toURI().toURL() );
			vidalado = new ImageIcon(Game.class.getResource("multimedia/vidalado.png").toURI().toURL() );
			
			// PANTALLA FINAL
			
			panelTopJugadores = new ImageIcon(Game.class.getResource("multimedia/pantallaFinal/grey_panel.png").toURI().toURL() );
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		//COMPARTIDOS
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();	
		bIMGYellow_True = bYellow_True.getImage();
		bIMGYellow_False = bYellow_False.getImage();
		bIMGBlue_True = bBlue_True.getImage();
		bIMGBlue_False = bBlue_False.getImage();
		
		tapeteImg = tapeteIcn.getImage();
		
		FondoFichas_img = Fondofichas.getImage();
		titulo_img = titulo.getImage();	
		
		//SIGNIN
		textIMG = text.getImage();
		textCorrectIMG = textCorrect.getImage();
		textErrorIMG = textError.getImage();
		textWriteIMG = textWrite.getImage();
		
		
		//GAME1
		
		aNegra_IMG = aNegra.getImage();
		aRoja_IMG = aRoja.getImage();
		kNegra_IMG = kNegra.getImage();
		kRoja_IMG = kRoja.getImage();
		tresNegro_IMG = tresNegro.getImage();
		tresRojo_IMG = tresRojo.getImage();
		comodinNegro_IMG = comodinNegro.getImage();
		comodinRojo_IMG = comodinRojo.getImage();
		reverso_IMG = reverso.getImage();
		
		//GAME2
		
		leftBlanco_img = leftBlanco.getImage();
		leftNegro_img = leftNegro.getImage();
		upBlanco_img = upBlanco.getImage();
		upNegro_img = upNegro.getImage();
		downBlanco_img = downBlanco.getImage();
		downNegro_img = downNegro.getImage();
		rightBlanco_img = rightBlanco.getImage();
		rightNegro_img = rightNegro.getImage();
		
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
		
		tapeteChainz_IMG = tapeteChainz.getImage();
		
		//GAME7
		
		circleImg = circleIcn.getImage();
		crossImg = crossIcn.getImage();
		rowHorzImg = rowHorzIcn.getImage();
		rowVertImg = rowVertIcn.getImage();
		
		//PANTALLA INTERMEDIA
		
		vida_IMG = vida.getImage();
		vidatrans_IMG = vidatrans.getImage();
		vidalado_IMG = vidalado.getImage();
		
		//PANTALLA FINAL
		
		panelTopJugadores_IMG = panelTopJugadores.getImage();
		
		//FONTS
		
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

		try {   
			customFontTitle = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/Pixelmania.ttf"));
			customFontTitle = customFontTitle.deriveFont(Font.PLAIN,75);
		}catch(Exception e){	
			System.out.println("Problema con la fuente Minigamble");
		}
		
		
		
		
		
		
		
		
		
		
		
		Properties properties = new Properties();
		InputStream is = null;
		
		try {
			is =  new FileInputStream("minigamble/src/minigamble/datos.properties");
			

			//FileReader reader = new FileReader("minigamble/src/minigamble/datos.properties");

			properties.load(is);
			
			numVidas=Integer.parseInt(properties.getProperty("vidas"));
			puntInicial = Integer.parseInt(properties.getProperty("puntInicial"));
			//			numVidas = Integer.parseInt(properties.getProperty("vidas")); //para conseguir el numero de telefono como int en vez de string
//			puntInicial = Integer.parseInt(properties.getProperty("puntInicial")); //prueba para ver si funciona con valores float 
//			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
