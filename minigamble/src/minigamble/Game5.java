package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

import minigamble.Game.ESTADO;

/**
 * @author Nicol�s
 *
 */

public class Game5 implements Runnable , KeyListener{


	private Font customFontG5;

	public static ArrayList<Image> symbols = new ArrayList<Image>();

	private int PuntTotal;
	private int idPartida;
	private String jugador;

	private symbol sym1;
	private symbol sym2;
	private symbol sym3;
	private symbol sym4;
	private symbol sym5;
	private symbol sym6;
	private symbol sym7;
	private symbol sym8;
	private symbol sym9;

	private int start=0;
	private int count=0;
	private String elegido;
	private int speed;

	private int chos1;
	private int chos2;
	private int chos3;

	public Game5(int dificultad, String nombreJugador, int idPart) {

		PuntTotal = dificultad;
		idPartida = idPart;
		jugador = nombreJugador;

		if(PuntTotal <2000) {
			speed=7;
		} else if (PuntTotal<4000) {
			speed = 21;
		} else if (PuntTotal<6000) {
			speed = 34;
		} else if (PuntTotal<6000) {
			speed = 42;
		}

		symbols.add(media.imageS1);
		symbols.add(media.imageS2);
		symbols.add(media.imageS3);
		symbols.add(media.imageS4);

		sym1 = new symbol();
		sym2 = new symbol();
		sym3 = new symbol();
		sym1.setXpos(200);
		sym2.setXpos(200);
		sym3.setXpos(200);
		sym2.setYpos(sym1.getYpos()-200);
		sym3.setYpos(sym2.getYpos()-200);

		sym4 = new symbol();
		sym5 = new symbol();
		sym6 = new symbol();
		sym4.setXpos(500);
		sym5.setXpos(500);
		sym6.setXpos(500);
		sym5.setYpos(sym4.getYpos()-200);
		sym6.setYpos(sym5.getYpos()-200);

		sym7 = new symbol();
		sym8 = new symbol();
		sym9 = new symbol();
		sym7.setXpos(800);
		sym8.setXpos(800);
		sym9.setXpos(800);
		sym8.setYpos(sym7.getYpos()-200);
		sym9.setYpos(sym8.getYpos()-200);

		Thread t = new Thread(this);
        t.start();

	}


	/**
	 * Hilo que realiza la animacion de movimiento de la maquina targaperras en funcion del parametro speed
	 */
	@Override
	public void run() {
		while(true) {

			try {Thread.sleep(10);}
			catch (InterruptedException e)
			{e.printStackTrace();}
				sym1.move(speed);
				sym2.move(speed);
				sym3.move(speed);
				sym4.move(speed);
				sym5.move(speed);
				sym6.move(speed);
				sym7.move(speed);
				sym8.move(speed);
				sym9.move(speed);
			}
	}



	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(start==0) {
			if(e.getKeyCode()==32) {
				start=1;
				sym1.setMoving(true);
				sym2.setMoving(true);
				sym3.setMoving(true);
				sym4.setMoving(true);
				sym5.setMoving(true);
				sym6.setMoving(true);
				sym7.setMoving(true);
				sym8.setMoving(true);
				sym9.setMoving(true);
			}
		} else if(start==1) {
			if(e.getKeyCode()==32) {
				if(count==0) {
					count++;
					sym1.setMoving(false);
					sym2.setMoving(false);
					sym3.setMoving(false);

					if(150 <= sym1.getYpos() && sym1.getYpos()<350) {
						chos1=sym1.getImage();
					}else if(150 <= sym2.getYpos() && sym2.getYpos()<350) {
						chos1=sym2.getImage();
					} else {
						chos1=sym3.getImage();
					}
					System.out.println(chos1);

					if(chos1 == 0) {
						elegido="heart";
					} else if (chos1 == 1) {
						elegido="lemon";
					} else if (chos1 == 2) {
						elegido="seven";
					} else if (chos1 == 3) {
						elegido="grape";
					}

				}else if(count==1) {
					count++;
					sym4.setMoving(false);
					sym5.setMoving(false);
					sym6.setMoving(false);


					if(150 <= sym4.getYpos() && sym4.getYpos()<350) {
						chos2=sym4.getImage();
					}else if(150 <= sym5.getYpos() && sym5.getYpos()<350) {
						chos2=sym5.getImage();
					} else {
						chos2=sym6.getImage();
					}
					System.out.println(chos2);

				}else if(count==2) {
					{
					count++;
					sym7.setMoving(false);
					sym8.setMoving(false);
					sym9.setMoving(false);

					if(150 <= sym7.getYpos() && sym7.getYpos()<350) {
						chos3=sym7.getImage();
					}else if(150 <= sym8.getYpos() && sym8.getYpos()<350) {
						chos3=sym8.getImage();
					} else {
						chos3=sym9.getImage();
					}
					System.out.println(chos3);


					if(chos1==chos2 && chos2==chos3) {
						start=3;
						BaseDatos.insertarGame5(idPartida, 1000, 1, elegido);

						Game.pi = new PantallaIntermedia(PuntTotal, 1000, 0, 4, jugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();

						//Game.partida  = new Partida( 1000 ,0 , 4, jugador, idPartida);
					} else {
						start=2;
						BaseDatos.insertarGame5(idPartida, 0, 0, elegido);

						Game.pi = new PantallaIntermedia(PuntTotal, 0, 1, 4, jugador, idPartida);
						Game.estadoJuego = ESTADO.PantallaIntermedia;
						Game.eventoRaton();

						//Game.partida  = new Partida( 0 ,1 , 4, jugador, idPartida);

					}

					}
				}
			}

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


	public void render(Graphics g) {

		if(start==0) {

			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			g.setColor(Color.WHITE);
			g.fillRect(200, 200, 200, 300);
			g.fillRect(500, 200, 200, 300);
			g.fillRect(800, 200, 200, 300);
			g.setFont(media.customFontBot);
			g.drawString("Press space to play", 250, 100);
			g.drawImage(media.arrowIm, 140, 300, 80, 80, null);
			g.drawImage(media.arrowIm, 1060, 300, -80, 80, null);


		} else if (start==1) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			g.setColor(Color.WHITE);
			g.fillRect(200, 150, 200, 400);
			g.fillRect(500, 150, 200, 400);
			g.fillRect(800, 150, 200, 400);


			g.drawImage(symbols.get(sym1.getImage()), sym1.getXpos(), sym1.getYpos(), null);
			g.drawImage(symbols.get(sym2.getImage()), sym2.getXpos(), sym2.getYpos(), null);
			g.drawImage(symbols.get(sym3.getImage()), sym3.getXpos(), sym3.getYpos(), null);

			g.drawImage(symbols.get(sym4.getImage()), sym4.getXpos(), sym4.getYpos(), null);
			g.drawImage(symbols.get(sym5.getImage()), sym5.getXpos(), sym5.getYpos(), null);
			g.drawImage(symbols.get(sym6.getImage()), sym6.getXpos(), sym6.getYpos(), null);

			g.drawImage(symbols.get(sym7.getImage()), sym7.getXpos(), sym7.getYpos(), null);
			g.drawImage(symbols.get(sym8.getImage()), sym8.getXpos(), sym8.getYpos(), null);
			g.drawImage(symbols.get(sym9.getImage()), sym9.getXpos(), sym9.getYpos(), null);

			g.drawImage(media.tapeteSupImg, 0, 0, 1184, 200, null);
			g.drawImage(media.tapeteInfImg, 0, 500, 1184, 163, null);
			g.setColor(Color.WHITE);
			g.setFont(media.customFontBot);
			g.drawString("Press space to stop", 250, 100);
			g.drawImage(media.arrowIm, 140, 300, 80, 80, null);
			g.drawImage(media.arrowIm, 1060, 300, -80, 80, null);

		} else if(start==2) {

			delayMS(200);
			customFontG5=media.customFontBot.deriveFont(Font.PLAIN,50);
			g.setFont(customFontG5);
			g.setColor(Color.BLACK);
			g.drawString("Sorry too bad (", 100, 300);

		} else if(start == 3) {

			delayMS(200);
			customFontG5=media.customFontBot.deriveFont(Font.PLAIN,50);
			g.setFont(customFontG5);
			g.setColor(Color.BLACK);
			g.drawString("YAY, YOU WON!!", 5+0, 300);

		}
	}



}
