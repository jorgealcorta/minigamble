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

import minigamble.Game.ESTADO;

public class PantallaIntermedia implements KeyListener{
	
	
	private int puntos = 0;
	private int vidasRestadas;
	private String jugador;
	private int idPartida;
	private int puntLocal;
	private int rondas = 1;
	
	public static boolean hiloPuntosAcabado = false;
	public static int puntosDisplay = 0;
	
	public static int miniJugado;
	
	Thread hpd;
	Thread hvd;
	
	public VidaDisplay vida1 = new VidaDisplay(true, true, false);
	public VidaDisplay vida2 = new VidaDisplay(true, true, false);
	public VidaDisplay vida3 = new VidaDisplay(true, true, false);
	
	private Font fontPuntos;
	
	public PantallaIntermedia(int puntos, int puntLocal, int vidasRestadas, int miniJugado, String jugador, int idPartida) {
		
		this.puntos = puntos;
		this.vidasRestadas = vidasRestadas;
		this.jugador = jugador;
		this.idPartida = idPartida;
		this.puntLocal = puntLocal;
		
		hiloPuntosAcabado = false;
		puntosDisplay = puntos;
		
		PantallaIntermedia.miniJugado = miniJugado;

		
		if(Partida.vidas - vidasRestadas < 3 && Partida.vidas - vidasRestadas > 0) {
			if(Partida.vidas - vidasRestadas == 1) {
				vida2.setVida(false);
			}else if(Partida.vidas - vidasRestadas == 0) {
				vida1.setVida(false);
			}
			vida3.setVida(false);
		}
		
		ThreadPuntosDisplay pd = new ThreadPuntosDisplay(puntos, puntLocal);
		this.hpd = new Thread(pd);
		hpd.start();
		
		ThreadVidasDisplay vd = new ThreadVidasDisplay(this);
		this.hvd = new Thread(vd);
		hvd.start();
		
//		if(hiloPuntosAcabado) {
//			System.out.println("aaa");
//			//Game.partida  = new Partida(puntos, 1, null, jugador, idPartida);
//		}
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		if(key == 32 && Game.estadoJuego == ESTADO.PantallaIntermedia) {
			System.out.println("espacio");
			hpd.stop();
			if(puntosDisplay != puntos + puntLocal) {
				puntosDisplay = puntos + puntLocal;
				vida1.display = true;
				vida2.display = true;
				vida3.display = true;
			}else {
				System.out.println("segundo");
				hvd.interrupt();
				hpd.interrupt();
				Game.partida  = new Partida(puntos+puntLocal, vidasRestadas, miniJugado, jugador, idPartida);
			}
			
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
		
		fontPuntos = fontPuntos.deriveFont(Font.PLAIN,70);
		g.setFont(fontPuntos);
		FontMetrics metrics2 = g.getFontMetrics();
		
		if(puntosDisplay != puntos + puntLocal) {
			int skipWidth = metrics2.stringWidth(String.valueOf("SPACE TO SKIP"));
			g.drawString("SPACE TO SKIP",(1200/2) - (skipWidth/2), 550);
		}else {
			int skipWidth = metrics2.stringWidth(String.valueOf("SPACE FOR NEXT GAME"));
			g.drawString("SPACE FOR NEXT GAME",(1200/2) - (skipWidth/2), 550);
		}	
		

		
		if(vida1.display) {
			if(vida1.frente) {
				g.drawImage(media.vida_IMG, 250, 75, 200, 200, null);
			}else{
				g.drawImage(media.vidalado_IMG, 250, 75, 200, 200, null);
			}
		}
		
		if(vida2.display) {
			if(vida2.vida) {
				//ventanawidth/2 - fotowidth/2
				if(vida2.frente) {
					g.drawImage(media.vida_IMG, 500, 75, 200, 200, null);
				}else {
					g.drawImage(media.vidalado_IMG, 500, 75, 200, 200, null);
				}
			}else {
				g.drawImage(media.vidatrans_IMG, 500, 75, 200, 200, null);
			}
		}
		
		if(vida3.display) {
			if(vida3.vida) {
				if(vida3.frente) {
					g.drawImage(media.vida_IMG, 750, 75, 200, 200, null);
				}else {
					g.drawImage(media.vidalado_IMG, 750, 75, 200, 200, null);
				}
			}else {
				g.drawImage(media.vidatrans_IMG, 750, 75, 200, 200, null);
			}
		}
	}

}
