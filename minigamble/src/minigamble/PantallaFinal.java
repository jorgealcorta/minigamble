package minigamble;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PantallaFinal {
	
	private int puntos;
	private int maxPuntos;
	private String idUsuario;
	private int idPartida;
	
	private String t1;
	private String t2;
	
	private String j1;
	private String j2;
	private String j3;
	private String j4;
	private String j5;
	
	private ArrayList<String> puntuaciones;
	
	private Font fontPuntos;
	
	public PantallaFinal(int idP, String idU, int punt) {
		System.out.println("EEEEEEEEEEEEOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOEEEEEEEEEEEEEEEEEEEOOOOOOOOOOOOOO");
		idPartida = idP;
		idUsuario = idU;
		puntos = punt;
		
		maxPuntos = BaseDatos.obtenerMaxPunt(idUsuario);
		t1 = "Your score: " + String.valueOf(puntos);
		t2 = "Highest: " + String.valueOf(maxPuntos); 
		
		puntuaciones = BaseDatos.obtenerPuntuaciones();
		
		j1 = "1.- " + puntuaciones.get(0) + "   " + BaseDatos.obtenerMaxPunt(puntuaciones.get(0));
		j2 = "2.- " + puntuaciones.get(1) + "   " + BaseDatos.obtenerMaxPunt(puntuaciones.get(1));
		j3 = "3.- " + puntuaciones.get(2) + "   " + BaseDatos.obtenerMaxPunt(puntuaciones.get(2));
		j4 = "4.- " + puntuaciones.get(3) + "   " + BaseDatos.obtenerMaxPunt(puntuaciones.get(3));
		//j5 = "5.- " + indexes.get(5) + "   " + puntuaciones.get(indexes.get(4));
		
	}
	
	public void render(Graphics g) {
		
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteImg, 0, 0, 1200, 672, null);
		}
		
		fontPuntos = media.customFontFin.deriveFont(Font.PLAIN,75);
		g.setFont(fontPuntos);
		FontMetrics metrics = g.getFontMetrics();
		String p1 = "Your score: " + String.valueOf(puntos);
		int puntosWidth = metrics.stringWidth(p1);
		g.drawString(p1, (1200/2) - (puntosWidth/2), 100);
		
		FontMetrics metrics2 = g.getFontMetrics();
		int maxPuntosWidth = metrics2.stringWidth(t2);
		g.drawString( t2 ,(1200/2) - (maxPuntosWidth/2), 160);
		
		fontPuntos = media.customFontFin.deriveFont(Font.PLAIN,60);
		g.setFont(fontPuntos);
		FontMetrics metrics3 = g.getFontMetrics();
		int top = metrics3.stringWidth("top 5 players");
		g.drawString("top 5 players" ,(1200/2) - (top/2), 250);
		
		int jug1 = metrics3.stringWidth(j1);
		g.drawString(j1 ,(1200/2) - (jug1/2), 300);
		
		int jug2 = metrics3.stringWidth(j2);
		g.drawString(j2 ,(1200/2) - (jug2/2), 350);
		
		int jug3 = metrics3.stringWidth(j3);
		g.drawString(j3 ,(1200/2) - (jug3/2), 400);
		
		int jug4 = metrics3.stringWidth(j4);
		g.drawString(j4 ,(1200/2) - (jug4/2), 450);
		
		
		
		
	}
	
	

}
