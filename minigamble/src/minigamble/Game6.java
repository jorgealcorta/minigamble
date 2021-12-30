package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Game6 implements KeyListener{
	
	private Image bStartIMG_True;
	private Image bStartIMG_False;
	
	
	private ImageIcon bStart_false;		// boton Start
	private ImageIcon bStart_true;
	private boolean bStart_state = false;
	
	
	private int start = 1;
	
	private Font customFontBot;
	private Font customFontFin;
	
	private ImageIcon flechaabj;
	private ImageIcon flechaabjtrans;
	
	private ImageIcon flechaarr;
	private ImageIcon flechaarrtrans;
	
	private ImageIcon flechadch;
	private ImageIcon flechadchtrans;
	
	private ImageIcon flechaizq;
	private ImageIcon flechaizqtrans;
	
	
	private Image flechaabj_IMG;
	private Image flechaabjtrans_IMG;
	
	private Image flechaarr_IMG;
	private Image flechaarrtrans_IMG;
	
	private Image flechadch_IMG;
	private Image flechadchtrans_IMG;
	
	private Image flechaizq_IMG;
	private Image flechaizqtrans_IMG;
	
	
	private CopyOnWriteArrayList<Flecha> flechasCreadas = new CopyOnWriteArrayList<Flecha>();
	
	public Game6(int dificultad) {
		
try {
			
			//Cargo todas las imagenes como iconos
			
			bStart_false = new ImageIcon( Game.class.getResource("multimedia/red_button2.png").toURI().toURL() );
			bStart_true = new ImageIcon( Game.class.getResource("multimedia/red_button3.png").toURI().toURL() );
			
			flechaabj = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaabj.png").toURI().toURL() );
			flechaabjtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaabjtrans.png").toURI().toURL() );

			flechaarr = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaarr.png").toURI().toURL() );
			flechaarrtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaarrtrans.png").toURI().toURL() );

			flechadch = new ImageIcon(Game.class.getResource("multimedia/flechas/flechadch.png").toURI().toURL() );
			flechadchtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechadchtrans.png").toURI().toURL() );

			flechaizq = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaizq.png").toURI().toURL() );
			flechaizqtrans = new ImageIcon(Game.class.getResource("multimedia/flechas/flechaizqtrans.png").toURI().toURL() );

			
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		// Cargar fuentes
		
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
		
		bStartIMG_True = bStart_true.getImage();
		bStartIMG_False = bStart_false.getImage();
		
		flechaabj_IMG = flechaabj.getImage();
		flechaabjtrans_IMG = flechaabjtrans.getImage();
		
		flechaarr_IMG = flechaarr.getImage();
		flechaarrtrans_IMG = flechaarrtrans.getImage();

		flechadch_IMG = flechadch.getImage();
		flechadchtrans_IMG = flechadchtrans.getImage();		
		
		flechaizq_IMG = flechaizq.getImage();
		flechaizqtrans_IMG = flechaizqtrans.getImage();		
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
