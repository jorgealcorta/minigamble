package minigamble;

import java.util.ArrayList;
import java.lang.Math;


import minigamble.Game.ESTADO;

/**
 * Clase encargada de generar una nueva partida
 */
public class Partida {
	
	public static int puntuacionGeneral = 0;
	public static int vidas = 3;
	
	public static ArrayList<Minijuego> minisJugados = new ArrayList<Minijuego>();
		
	
	/**
	 * Constructor de Partida
	 * @param puntuacion Puntuación de la partida
	 * @param vidasRestadas Vidas que se restan  ¿¿¿???
	 * @param miniJugado Juego al que se está jugando
	 * @param jugador Nombre del jugador
	 * @param idPartida ID de la partida que se está jugando
	 */
	public Partida(int puntuacion, int vidasRestadas, Minijuego miniJugado, String jugador, int idPartida) { //meter arraylist como atributo
		
		System.out.println("puntuacion:" +puntuacionGeneral);
		System.out.println("vidas;"+vidas); 
		
		minisJugados.add(miniJugado);
		puntuacionGeneral += puntuacion;
		vidas -= vidasRestadas; //OJO, ESTA RESTANDO
		System.out.println(vidas);
		
		ArrayList<ESTADO> minijuegos = new ArrayList<ESTADO>();

		minijuegos.add(ESTADO.Game1);
		minijuegos.add(ESTADO.Game2);
		minijuegos.add(ESTADO.Game3);
		minijuegos.add(ESTADO.Game4);
		minijuegos.add(ESTADO.Game5);
		minijuegos.add(ESTADO.Game6);
		
		int rand = (int) (Math.random() * 3);
		
		while(Game.estadoJuego == minijuegos.get(rand)) {
			rand = (int) (Math.random() * 3);
			System.out.println(rand);
		}
		

		rand=2 ; //PARA HACER PRUEBAS, LUEGO BORRARRRRRRRRRRRRRRRRRRRRRRRR

		if(vidas<1 ) {
			Game.estadoJuego= ESTADO.Start;
		}else {				
			if(rand == 0) {
				Game.game1 = new Game1(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
							
			} else if(rand==1) {
				Game.game2 = new Game2(puntuacionGeneral);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==2) {
				Game.game3 = new Game3(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==3) {
				Game.game4 = new Game4(puntuacionGeneral);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
			}
		}
		
		
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
