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
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Game2 implements KeyListener{
	
	private int start = 1; //1 = esperando, 2 = reproduciendo, 3 = jugando, 4 = ganado
	
	private int cuentaPulsaciones = -1;
	
	private boolean coraDestacar = false;
	private boolean diamDestacar = false;
	private boolean picaDestacar = false;
	private boolean trebDestacar = false;

	
	private Font customFontBot;
	private Font customFontFin;
	
	
	private ImageIcon corazonblanco;
	private ImageIcon corazonnegro;
	private ImageIcon diamanteblanco;
	private ImageIcon diamantenegro;
	private ImageIcon picablanca;
	private ImageIcon picanegra;
	private ImageIcon trebolblanco;
	private ImageIcon trebolnegro;
	
	private ImageIcon crossarrow;
	
	private Image corazonblanco_IMG;
	private Image corazonnegro_IMG;
	private Image diamanteblanco_IMG;
	private Image diamantenegro_IMG;
	private Image picablanca_IMG;
	private Image picanegra_IMG;
	private Image trebolblanco_IMG;
	private Image trebolnegro_IMG;
	
	private Image crossarrow_IMG;
	
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
				
				crossarrow = new ImageIcon( Game.class.getResource("multimedia/crossarrowpixel.png").toURI().toURL() );
	
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
			
			crossarrow_IMG = crossarrow.getImage();
			
			
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
			
			
			//Creamos la combinacion correcta del simon says
			for(int i = 0 ; i < 5; i++) {
				palosCorrectos.add(palos.get((int) Math.floor(Math.random()*4)));
			}
			
			System.out.println(palosCorrectos);
			
			
					
		}

	private void delaySeg(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == 32 && start==1) { //reproducir secuencia
			start = 2;
			for(String palo : palosCorrectos) {
				
				if(palo == "cora") {
					coraDestacar = true; 	
					delayMS(250);
					coraDestacar = false;
					delayMS(250);
				}
				else if(palo == "diam") {
					diamDestacar = true; 	
					delayMS(250);
					diamDestacar = false;
					delayMS(250);			
				}
				else if(palo == "pica") {
					picaDestacar = true; 	
					delayMS(250);
					picaDestacar = false;
					delayMS(250);
				}
				else if(palo == "treb") {
					trebDestacar = true; 	
					delayMS(250);
					trebDestacar = false;
					delayMS(250);
				}
	
			}
			
			start = 3;
		}
		
		if(start == 3) {
			
			switch(key){
			
			case(37):
				diamDestacar = true;
				break;
			case(38):
				coraDestacar = true;		
				break;
			case(39):
				trebDestacar = true;
				break;
			case(40):
				picaDestacar = true;
				break;
			
			}
			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(start == 3) {
			switch(key){
			
			case(37):
				diamDestacar = false;
				palosUsuario.add("diam");
				cuentaPulsaciones++;
				break;
			case(38):
				coraDestacar = false;
				palosUsuario.add("cora");
				cuentaPulsaciones++;
				break;
			case(39):
				trebDestacar = false;
				palosUsuario.add("treb");
				cuentaPulsaciones++;
				break;
			case(40):
				picaDestacar = false;
				palosUsuario.add("pica");
				cuentaPulsaciones++;
				break;
			
			}
			
			System.out.println(palosUsuario);
			
	
			if(palosUsuario.size() > 0 && palosUsuario.get(cuentaPulsaciones) != palosCorrectos.get(cuentaPulsaciones)) {
				System.out.println(palosUsuario);
				palosUsuario.removeAll(palosUsuario);
				cuentaPulsaciones = -1;
				start = 1;
				System.out.println("error");
			}
			
			if(cuentaPulsaciones == palosCorrectos.size() - 1 && palosUsuario.get(cuentaPulsaciones) == palosCorrectos.get(cuentaPulsaciones)) {
				start = 4;
				delaySeg(2);
				Game.partida  = new Partida(0,0,null , null, null, 0);
			}
			
		}	
	}
	
	public void render(Graphics g) {
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		
		if(start == 1) {
			g.setFont(customFontBot);
			
			g.drawImage(corazonblanco_IMG, 520, 125, null);
			g.drawImage(diamanteblanco_IMG, 350	, 290, null);
			g.drawImage(picablanca_IMG, 520, 450, null);
			g.drawImage(trebolblanco_IMG, 690, 290, null);
			
			g.drawImage(crossarrow_IMG, 520, 285, null);
			
			g.setColor(Color.BLACK);
			g.drawString("PULSA ESPACIO PARA REPRODUCIR LA COMBINACION", 200, 60);
		}
		
		else if(start == 2) {
			g.setColor(Color.BLACK);
			if(!coraDestacar) {
				g.drawImage(corazonblanco_IMG, 520, 125, null);
			}else {
				g.drawImage(corazonnegro_IMG, 520, 125, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(diamanteblanco_IMG, 350, 290, null);
			}else {
				g.drawImage(diamantenegro_IMG, 350, 290, null);
			}
			
			if(!picaDestacar) {
				g.drawImage(picablanca_IMG, 520, 450, null);
			}else {
				g.drawImage(picanegra_IMG, 520, 450, null);
			}
			
			if(!trebDestacar) {
				g.drawImage(trebolblanco_IMG, 690, 290, null);
			}else {
				g.drawImage(trebolnegro_IMG, 690, 290, null);
			}
			
			g.drawImage(crossarrow_IMG, 520, 285, null);
	
		}
		
		else if(start == 3) {
			g.setColor(Color.BLACK);
			if(!coraDestacar) {
				g.drawImage(corazonblanco_IMG, 520, 125, null);
			}else {
				g.drawImage(corazonnegro_IMG, 520, 125, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(diamanteblanco_IMG, 350, 290, null);
			}else {
				g.drawImage(diamantenegro_IMG, 350, 290, null);
			}
			
			if(!picaDestacar) {
				g.drawImage(picablanca_IMG, 520, 450, null);
			}else {
				g.drawImage(picanegra_IMG, 520, 450, null);
			}
			
			if(!trebDestacar) {
				g.drawImage(trebolblanco_IMG, 690, 290, null);
			}else {
				g.drawImage(trebolnegro_IMG, 690, 290, null);
			}
			
			g.drawImage(crossarrow_IMG, 520, 285, null);
	
		}
		
		else if(start == 4) {
			g.setFont(customFontFin);
			g.setColor(Color.BLACK);
			g.drawString("ENHORABUENA", 325, 300);
		}
	}
	
}
