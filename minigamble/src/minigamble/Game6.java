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
	
	private String dirPosibles[] = {"izq", "arr", "abj", "dch"};
	
	private CopyOnWriteArrayList<Flecha> flechasCreadas = new CopyOnWriteArrayList<Flecha>();
	private CopyOnWriteArrayList<Flecha> flechasActivas = new CopyOnWriteArrayList<Flecha>();

	
	public Game6(int dificultad) {
		
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
		
		g.drawImage(media.flechaizqtrans_IMG, 250, 450, 128, 128, null);
		g.drawImage(media.flechaarrtrans_IMG, 450, 450, 128, 128, null);
		g.drawImage(media.flechaabjtrans_IMG, 650, 450, 128, 128, null);
		g.drawImage(media.flechadchtrans_IMG, 850, 450, 128, 128, null);
		
		for(Flecha f : flechasActivas) {
			if(f.getDir() == "izq" && f.getY()<700) {
				g.drawImage(media.flechaizq_IMG, 250, f.getY(), 128, 128, null);
			}else if (f.getDir() == "arr" && f.getY()<700) {
				g.drawImage(media.flechaarr_IMG, 450, f.getY(), 128, 128, null);
			}else if (f.getDir() == "abj" && f.getY()<700) {
				g.drawImage(media.flechaabj_IMG, 650, f.getY(), 128, 128, null);
			}else if (f.getDir() == "dch" && f.getY()<700) {
				g.drawImage(media.flechadch_IMG, 850, f.getY(), 128, 128, null);

			}
		}

	}
}
