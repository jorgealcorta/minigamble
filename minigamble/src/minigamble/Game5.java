package minigamble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Game5 implements Runnable {
 
	private ImageIcon iconS1;
	private Image imageS1;
	
	private ImageIcon iconS2;
	private Image imageS2;

	private ImageIcon iconS3;
	private Image imageS3;

	private ImageIcon iconS4;
	private Image imageS4;
	
	private static ArrayList<Image> symbols = new ArrayList<Image>();
	
	private int PuntTotal;
	private int idPartida;
	private String jugador;
	
	private symbol sym1;
	private symbol sym2;
	private symbol sym3;
	private symbol sym4;
	private symbol sym5;
	private symbol sym6;
	 
	public Game5(int dificultad, String nombreJugador, int idPart) {
		
		PuntTotal = dificultad;
		idPartida = idPart;
		jugador = nombreJugador;
		
		try {
			
			iconS1= new ImageIcon( Game.class.getResource("multimedia/simbolos/heartS.png").toURI().toURL() );
			iconS2= new ImageIcon( Game.class.getResource("multimedia/simbolos/lemonS.png").toURI().toURL() );
			iconS3= new ImageIcon( Game.class.getResource("multimedia/simbolos/sevenS.png").toURI().toURL() );
			iconS4= new ImageIcon( Game.class.getResource("multimedia/simbolos/grapeS.png").toURI().toURL() );

			
		
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO Auto-generated catch block
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
	
	public static Image getRandom() {
	    int rnd = new Random().nextInt(symbols.size());
	    return symbols.get(rnd);
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
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
				
		
		g.drawImage(sym1.getImageS1(), sym1.getXpos(), sym1.getYpos(), null);
		g.drawImage(sym2.getImageS1(), sym2.getXpos(), sym2.getYpos(), null);
		
		g.drawImage(sym3.getImageS1(), sym3.getXpos(), sym3.getYpos(), null);
		g.drawImage(sym4.getImageS1(), sym4.getXpos(), sym4.getYpos(), null);
		
		g.drawImage(sym5.getImageS1(), sym5.getXpos(), sym5.getYpos(), null);
		g.drawImage(sym6.getImageS1(), sym6.getXpos(), sym6.getYpos(), null);
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 250);
		g.fillRect(0, 450, 1200, 250);
		
	}


	
	
	
}
