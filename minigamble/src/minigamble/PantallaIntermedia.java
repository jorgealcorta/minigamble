package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import minigamble.Game.ESTADO;

public class PantallaIntermedia implements KeyListener{
	
	
	private int puntos = 0;
	public static int vidasRestadas;
	private String jugador;
	private int idPartida;
	private int puntLocal;
	private int check =1;
	
	public static boolean hiloPuntosAcabado = false;
	public static int puntosDisplay = 0;
	
	public static int miniJugado;
	
	Thread hpd;
	Thread hvd;
	
	public VidaDisplay vida1 = new VidaDisplay(true, true, false);
	public VidaDisplay vida2 = new VidaDisplay(true, true, false);
	public VidaDisplay vida3 = new VidaDisplay(true, true, false);
	
	private Font fontPuntos;
	
	
	/**Constructor de pantallaIntermedia
	 * @param puntos de la partida antes de sumar el minijuego jugado previamente
	 * @param puntLocal puntos obtenidos en el juego anterior
	 * @param vidasRestadas int con valor 1 o 0 en funcion de si ha gaando o ha perdido.
	 * @param miniJugado id de el minujuego jugado para almacenamiento en partida
	 * @param jugador id del jugador para calmacenar en partida
	 * @param idPartida id de la partida actual
	 */
	
	public PantallaIntermedia(int puntos, int puntLocal, int vidasRestadas, int miniJugado, String jugador, int idPartida) {

		
		this.puntos = puntos;
		this.vidasRestadas = vidasRestadas;
		this.jugador = jugador;
		this.idPartida = idPartida;
		this.puntLocal = puntLocal;
		
		
				
		hiloPuntosAcabado = false;
		puntosDisplay = puntos;
		
		
		
		PantallaIntermedia.miniJugado = miniJugado;

		if(Partida.vidas - vidasRestadas == 3) { // en funcion de las vidas restantes se muestran mas o menos coraxones
			vida1.setVida(true);
			vida2.setVida(true);
			vida3.setVida(true);
		}else if(Partida.vidas - vidasRestadas == 2) {
			vida1.setVida(true);
			vida2.setVida(true);
			vida3.setVida(false);
		}else if(Partida.vidas - vidasRestadas == 1) {
			vida1.setVida(true);
			vida2.setVida(false);
			vida3.setVida(false);
		}else if(Partida.vidas - vidasRestadas <= 0) {
			vida1.setVida(false);
			vida2.setVida(false);
			vida3.setVida(false);
		}
		
		ThreadPuntosDisplay pd = new ThreadPuntosDisplay(puntos, puntLocal);
		this.hpd = new Thread(pd);
		hpd.start();
		
		ThreadVidasDisplay vd = new ThreadVidasDisplay(this);
		this.hvd = new Thread(vd);
		hvd.start();
		

	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (this.check ==1) {
			this.check--;

			int key = e.getKeyCode();
			
			if(key == 32 && Game.estadoJuego == ESTADO.PantallaIntermedia) { // se interrumpe el hilo y se pasa a partida para elegir otro juego
				System.out.println("espacio intermedio");
				while(hvd.isAlive()) {
					try {
						hvd.interrupt();
					}catch(Exception e1) {
					}
				}

				hpd.interrupt();
				ThreadPuntosDisplay.puntos2.close();
				
				System.out.println("creando partida con vidasRes"+ vidasRestadas);
				Game.partida  = new Partida(puntLocal, vidasRestadas, miniJugado, jugador, idPartida);
			}	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void render(Graphics g) {
		
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteVidas_img, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteVidas_img, 0, 0, 1200, 672, null);
		}
		g.setColor(Color.BLACK);

		
		fontPuntos = media.customFontFin.deriveFont(Font.PLAIN,150);
		
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
			if(vida1.vida) {
				//ventanawidth/2 - fotowidth/2
				if(vida1.frente) {
					g.drawImage(media.vida_IMG, 250, 75, 200, 200, null);
				}else {
					g.drawImage(media.vidalado_IMG, 250, 75, 200, 200, null);
				}
			}else {
				g.drawImage(media.vidatrans_IMG, 250, 95, 200, 180, null);
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
				g.drawImage(media.vidatrans_IMG, 500, 95, 200, 180, null);
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
				g.drawImage(media.vidatrans_IMG, 750, 95, 200, 180, null);
			}
		}
	}

}
