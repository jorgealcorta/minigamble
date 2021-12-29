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

import javax.swing.ImageIcon;

public class Game5 implements Runnable , KeyListener{
 
	
	private Font customFontBot;
	
	private ImageIcon iconS1;
	private Image imageS1;
	
	private ImageIcon iconS2;
	private Image imageS2;

	private ImageIcon iconS3;
	private Image imageS3;

	private ImageIcon iconS4;
	private Image imageS4;
	
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
	 
	private int start=0;
	private int count=0;
	
	private int chos1;
	private int chos2;
	private int chos3;
	
	public Game5(int dificultad, String nombreJugador, int idPart) {
		
		PuntTotal = dificultad;
		idPartida = idPart;
		jugador = nombreJugador;
		
		try {
			customFontBot = Font.createFont(Font.TRUETYPE_FONT, Inicio.class.getResourceAsStream("fuentes/fuenteBot.ttf"));
			customFontBot=customFontBot.deriveFont(Font.PLAIN,40);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println("Error con la fuente Boton");
		}
		
		try {
			
			iconS1= new ImageIcon( Game.class.getResource("multimedia/simbolos/heartS.png").toURI().toURL() );
			iconS2= new ImageIcon( Game.class.getResource("multimedia/simbolos/lemonS.png").toURI().toURL() );
			iconS3= new ImageIcon( Game.class.getResource("multimedia/simbolos/sevenS.png").toURI().toURL() );
			iconS4= new ImageIcon( Game.class.getResource("multimedia/simbolos/grapeS.png").toURI().toURL() );
		
		} catch (MalformedURLException | URISyntaxException e) {
				e.printStackTrace();
		}
		
		imageS1 = iconS1.getImage();
		imageS2 = iconS2.getImage();
		imageS3 = iconS3.getImage();
		imageS4 = iconS4.getImage();
		
		symbols.add(imageS1);
		symbols.add(imageS2);
		symbols.add(imageS3);
		symbols.add(imageS4);
		
		
		
		sym1 = new symbol();
		sym2 = new symbol();
		sym1.setXpos(200);
		sym2.setXpos(200);
		sym2.setYpos(sym1.getYpos()-200);
		
		sym3 = new symbol();
		sym4 = new symbol();
		sym3.setXpos(500);
		sym4.setXpos(500);
		sym4.setYpos(sym1.getYpos()-200);
		
		sym5 = new symbol();
		sym6 = new symbol();
		sym5.setXpos(800);
		sym6.setXpos(800);
		sym6.setYpos(sym1.getYpos()-200);
		
		Thread t = new Thread(this);       
        t.start();
		
	}
	
	
	@Override
	public void run() {
		while(true) {			
			
			try {Thread.sleep(10);} 
			catch (InterruptedException e) 
			{e.printStackTrace();}
										
				sym1.move(20);
				sym2.move(20);
				sym3.move(20);
				sym4.move(20);
				sym5.move(20);
				sym6.move(20);
				}			
	}
	
	
	public void render(Graphics g) {
		
		if(start==0) {
			
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 700);
			g.setColor(Color.WHITE);
			g.fillRect(200, 250, 200, 200);
			g.fillRect(500, 250, 200, 200);
			g.fillRect(800, 250, 200, 200);
			g.setFont(customFontBot);
			g.drawString("Press space to play", 250, 180);
			
			
		} else if (start==1) {
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 700);
			g.setColor(Color.WHITE);
			g.fillRect(200, 250, 200, 200);
			g.fillRect(500, 250, 200, 200);
			g.fillRect(800, 250, 200, 200);
			g.setFont(customFontBot);		
			g.drawString("Press space to stop", 250, 180);
			
			g.drawImage(symbols.get(sym1.getImage()), sym1.getXpos(), sym1.getYpos(), null);
			g.drawImage(symbols.get(sym2.getImage()), sym2.getXpos(), sym2.getYpos(), null);
			
			g.drawImage(symbols.get(sym3.getImage()), sym3.getXpos(), sym3.getYpos(), null);
			g.drawImage(symbols.get(sym4.getImage()), sym4.getXpos(), sym4.getYpos(), null);
			
			g.drawImage(symbols.get(sym5.getImage()), sym5.getXpos(), sym5.getYpos(), null);
			g.drawImage(symbols.get(sym6.getImage()), sym6.getXpos(), sym6.getYpos(), null);
			
			g.setColor(Color.decode("#208b3a"));
			g.fillRect(0, 0, 1200, 250);
			g.fillRect(0, 450, 1200, 250);
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

			}
		} else if(start==1) {
			if(e.getKeyCode()==32) {
				if(count==0) {
					count++;
					sym1.setMoving(false);
					sym2.setMoving(false);
				}else if(count==1) {
					count++;
					sym3.setMoving(false);
					sym4.setMoving(false);
				}else if(count==2) {
					count++;
					sym5.setMoving(false);
					sym6.setMoving(false);
					
					if(150 <= sym1.getYpos() && sym1.getYpos()<350) {
						chos1=sym1.getImage();
					}else {
						chos1=sym2.getImage();
					}
					
					if(150 <= sym3.getYpos() && sym3.getYpos()<350) {
						chos2=sym3.getImage();
					}else {
						chos2=sym4.getImage();
					}
					
					if(150 <= sym5.getYpos() && sym5.getYpos()<350) {
						chos3=sym5.getImage();
					}else {
						chos3=sym6.getImage();
					}
					
					
					if(chos1==chos2 && chos2==chos3) {
						start=2;						
					} else {
						start=3;
					}
					
				}
			}
			
		}
		
	}

	

	
	
	
}
