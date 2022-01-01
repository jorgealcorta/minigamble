package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;

public class PantallaIntermedia implements KeyListener{
	
	private ImageIcon vida;
	private ImageIcon vidatrans;
	private ImageIcon vidalado;
	
	private Image vida_IMG;
	private Image vidatrans_IMG;
	private Image vidalado_IMG;
	
	private int puntos = 0;
	private int puntosDisplay = 0;
	private int vidas = 3;
	private int rondas = 1;
	
	public boolean hiloPuntosAcabado = false;
	
	Thread hpd;
	Thread hvd;
	
	public VidaDisplay vida1 = new VidaDisplay(true, true, false);
	public VidaDisplay vida2 = new VidaDisplay(true, true, false);
	public VidaDisplay vida3 = new VidaDisplay(true, true, false);
	
	private Font fontPuntos;
	
	public PantallaIntermedia(int dificultad, int vidas, int rondas) {
		
		hiloPuntosAcabado = false;
		
		try {
									
			vida = new ImageIcon(Game.class.getResource("multimedia/vida.png").toURI().toURL() );
			vidatrans = new ImageIcon(Game.class.getResource("multimedia/vidatrans.png").toURI().toURL() );
			vidalado = new ImageIcon(Game.class.getResource("multimedia/vidalado.png").toURI().toURL() );

			
			
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		vida_IMG = vida.getImage();
		vidatrans_IMG = vidatrans.getImage();
		vidalado_IMG = vidalado.getImage();
		
		this.puntosDisplay = 0;
		this.puntos = dificultad;
		this.vidas = vidas;
		this.rondas = rondas;
		
		if(vidas < 3 && vidas > 0) {
			if(vidas == 1) {
				vida2.setVida(false);;
			}
			vida3.setVida(false);;
		}
		
		runThreadPuntosDisplay();
		runThreadVidasDisplay();
	}
	
	public void runThreadPuntosDisplay() {
		ThreadPuntosDisplay pd = new ThreadPuntosDisplay(puntos, this);
		Thread hpd = new Thread(pd);
		hpd.start();
	}
	
	public void runThreadVidasDisplay() {
		ThreadVidasDisplay vd = new ThreadVidasDisplay(vidas, this);
		Thread hvd = new Thread(vd);
		hvd.start();
	}


	public void setPuntosDisplay(int puntosDisplay) {
		this.puntosDisplay = puntosDisplay;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == 44) {
			System.out.println("espacio");
			if(!hpd.isInterrupted()) {
				hpd.interrupt();
			}
			
			puntosDisplay = puntos;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		g.setColor(Color.BLACK);

		
		//CAMBIAR ESTO A MEDIA
		
		try {
			fontPuntos = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuente.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fontPuntos = fontPuntos.deriveFont(Font.PLAIN,150);
		
		g.setFont(fontPuntos);
		
		FontMetrics metrics = g.getFontMetrics();
		int puntosWidth = metrics.stringWidth(String.valueOf(puntosDisplay));
		g.drawString(String.valueOf(puntosDisplay),(1200/2) - (puntosWidth/2), 440);
		
		if(vida1.display) {
			if(vida1.frente) {
				g.drawImage(vida_IMG, 250, 75, 200, 200, null);
			}else{
				g.drawImage(vidalado_IMG, 250, 75, 200, 200, null);
			}
		}
		
		if(vida2.display) {
			if(vida2.vida) {
				//ventanawidth/2 - fotowidth/2
				if(vida2.frente) {
					g.drawImage(vida_IMG, 500, 75, 200, 200, null);
				}else {
					g.drawImage(vidalado_IMG, 500, 75, 200, 200, null);
				}
			}else {
				g.drawImage(vidatrans_IMG, 500, 75, 200, 200, null);
			}
		}
		
		if(vida3.display) {
			if(vida3.vida) {
				if(vida3.frente) {
					g.drawImage(vida_IMG, 750, 75, 200, 200, null);
				}else {
					g.drawImage(vidalado_IMG, 750, 75, 200, 200, null);
				}
			}else {
				g.drawImage(vidatrans_IMG, 750, 75, 200, 200, null);
			}
		}
	}

}
