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
	
	private ArrayList<Image> symbols = new ArrayList<Image>();
	
	private int PuntTotal;
	private int idPartida;
	private String jugador;
	
	private symbol sym1;
	private symbol sym2;
	
	 
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
		sym1.setImageS1(getRandom(symbols));
		
		Thread t = new Thread(this);       
        t.start();
		
	}
	
	public Image getRandom( ArrayList<Image> symbols) {
	    int rnd = new Random().nextInt(symbols.size());
	    return symbols.get(rnd);
	}
	
	@Override
	public void run() {
		while(true) {
				
				try {Thread.sleep(10);} 
				catch (InterruptedException e) 
				{e.printStackTrace();}
				
				sym1.setYpos(sym1.getYpos()+2);
				
				
			}			
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.decode("#208b3a"));
		g.fillRect(0, 0, 1200, 700);
		g.setColor(Color.RED);
		g.drawRect(550 ,100, 200, 400);
		g.drawImage(sym1.getImageS1(), sym1.getXpos(), sym1.getYpos(), null);
		
		
		
		
	}


	
	
	
}
