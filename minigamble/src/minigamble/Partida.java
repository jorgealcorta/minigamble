package minigamble;

import java.util.ArrayList;
import java.lang.Math;


import minigamble.Game.ESTADO;

public class Partida {
	
	public static int puntuacionGeneral = 0;
	public static int vidas = 3;
	
	public static ArrayList<Minijuego> minisJugados = new ArrayList<Minijuego>();
		
	
	public Partida(int puntuacion, int vidasRestadas, String idUsuario, Minijuego miniJugado) { //meter arraylist como atributo
		
		minisJugados.add(miniJugado);
		puntuacionGeneral += puntuacion;
		vidas -= vidasRestadas; //OJO, ESTA RESTANDO
		
		ArrayList<ESTADO> minijuegos = new ArrayList<ESTADO>();

		minijuegos.add(ESTADO.Game1);
		minijuegos.add(ESTADO.Game2);
		minijuegos.add(ESTADO.Game3);
		minijuegos.add(ESTADO.Game4);
		minijuegos.add(ESTADO.Game5);
		minijuegos.add(ESTADO.Game6);
		
		int indexAleatorio = (int) (Math.random() * 5);
		
		Game.estadoJuego = minijuegos.get(indexAleatorio);
		
		
	}
		
	
	/*
	
	public Partida(puntuacion, vidas, arraylst[juego], idUsuariho) {
	
		randon = new rand = juego x
		
		juego = juegox.run()
		
		puntuntuacion = puntucion+juego
		vidss=nivad-juego
		arraylist+juego
		
		if tuienevidas
			partida(nuevpun newvidas newarraylist)
			
		else
			
			
				
	}
	
	*/
	
	
}
