package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Game6 implements KeyListener{	
	
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
	
	private String dirPosibles[] = {"izq", "arr", "abj", "dch"};
	
	private CopyOnWriteArrayList<Flecha> flechasCreadas = new CopyOnWriteArrayList<Flecha>();
	private CopyOnWriteArrayList<Flecha> flechasActivas = new CopyOnWriteArrayList<Flecha>();

	
	public Game6(int dificultad) {
		
		try {
			
			//Cargo todas las imagenes como iconos
						
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
		
		flechaabj_IMG = flechaabj.getImage();
		flechaabjtrans_IMG = flechaabjtrans.getImage();
		
		flechaarr_IMG = flechaarr.getImage();
		flechaarrtrans_IMG = flechaarrtrans.getImage();

		flechadch_IMG = flechadch.getImage();
		flechadchtrans_IMG = flechadchtrans.getImage();		
		
		flechaizq_IMG = flechaizq.getImage();
		flechaizqtrans_IMG = flechaizqtrans.getImage();
		
		// Crear ArrayList de flechas aleatorias
		for(int i = 0; i<10; i++) {
			String dirRandom = dirPosibles[new Random().nextInt(dirPosibles.length)];
			Flecha f = new Flecha(dirRandom);
			flechasCreadas.add(f);
		}
		
		System.out.println(flechasCreadas);
		
		runThreadFlechasActivas();
		runThreadFlechasMain();
	}
	
	public void runThreadFlechasActivas() {
		ThreadFlechasActivas fa = new ThreadFlechasActivas(flechasCreadas, flechasActivas);
		Thread hfa = new Thread(fa);
		hfa.start();
	}
	
	public void runThreadFlechasMain() {
		ThreadFlechasMain fm = new ThreadFlechasMain(flechasActivas);
		Thread hfm = new Thread(fm);
		hfm.start();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		System.out.println(key);
//		izq 37
//		arr 38
//		abj 40
//		dch 39
		for(Flecha f : flechasActivas) {
			//Damos margen de acierto con 64 pixeles de fallo arriba y abajo
			if(((key == 37 && f.getDir() == "izq") ||
				(key == 38 && f.getDir() == "arr") ||
				(key == 40 && f.getDir() == "abj") ||
				(key == 39 && f.getDir() == "dch")) && 
				f.getY()>386 && f.getY()<514) {
					flechasActivas.remove(f);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		
		g.drawImage(flechaizqtrans_IMG, 250, 450, 128, 128, null);
		g.drawImage(flechaarrtrans_IMG, 450, 450, 128, 128, null);
		g.drawImage(flechaabjtrans_IMG, 650, 450, 128, 128, null);
		g.drawImage(flechadchtrans_IMG, 850, 450, 128, 128, null);
		
		for(Flecha f : flechasActivas) {
			if(f.getDir() == "izq" && f.getY()<700) {
				g.drawImage(flechaizq_IMG, 250, f.getY(), 128, 128, null);
			}else if (f.getDir() == "arr" && f.getY()<700) {
				g.drawImage(flechaarr_IMG, 450, f.getY(), 128, 128, null);
			}else if (f.getDir() == "abj" && f.getY()<700) {
				g.drawImage(flechaabj_IMG, 650, f.getY(), 128, 128, null);
			}else if (f.getDir() == "dch" && f.getY()<700) {
				g.drawImage(flechadch_IMG, 850, f.getY(), 128, 128, null);

			}
		}

	}
}
