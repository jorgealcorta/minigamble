package minigamble;

import java.util.ArrayList;
import java.lang.Math;


import minigamble.Game.ESTADO;

/**
 * Clase encargada de generar una nueva partida
 */
public class Partida {
	
	public static int puntuacionGeneral = media.puntInicial;
	public static int vidas ;
	
	public static ArrayList<Integer> minisJugados = new ArrayList<Integer>();
		
	
	/**
	 * Constructor de Partida
	 * @param puntuacion Puntuación de la partida
	 * @param vidasRestadas Vidas que se restan  ¿¿¿???
	 * @param miniJugado Juego al que se está jugando
	 * @param jugador Nombre del jugador
	 * @param idPartida ID de la partida que se está jugando
	 */
	public Partida(int puntuacion, int vidasRestadas, int miniJugado, String jugador, int idPartida) { //meter arraylist como atributo
		
		
		
		if(!(vidas==1||vidas ==2 ||vidas ==3)) {
			vidas = media.numVidas;
			
		}
			
		
		minisJugados.add(miniJugado);
		puntuacionGeneral += puntuacion;
		vidas -= vidasRestadas; //OJO, ESTA RESTANDO
		System.out.println("nueva partida creada de vidas " +vidas + " y  puntuacion "+ puntuacionGeneral);
		
		ArrayList<ESTADO> minijuegos = new ArrayList<ESTADO>();

		minijuegos.add(ESTADO.Game1);
		minijuegos.add(ESTADO.Game2);
		minijuegos.add(ESTADO.Game3);
		minijuegos.add(ESTADO.Game4);
		minijuegos.add(ESTADO.Game5);
		minijuegos.add(ESTADO.Game6);
		minijuegos.add(ESTADO.Game7);
		minijuegos.add(ESTADO.PantallaIntermedia);
		
		int rand = (int) (Math.random() * 6);
		System.out.println("rand1");
		System.out.println(rand);
		
		while(PantallaIntermedia.miniJugado == rand) {
			rand = (int) (Math.random() * 6);
			System.out.println("rand2");
			System.out.println(rand);
		}
		


		rand=6	 ; //PARA HACER PRUEBAS, LUEGO BORRARRRRRRRRRRRRRRRRRRRRRRRR


		if(vidas<1 ) {
			Game.estadoJuego= ESTADO.Start;
			Game.again();
		}else {				
			if(rand == 0) {
				Game.game1 = new Game1(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
							
			} else if(rand==1) {
				Game.game2 = new Game2(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==2) {
				Game.game3 = new Game3(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==3) {
				Game.game4 = null;
				Game.game4 = new Game4(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==4) {
				Game.game5 = new Game5(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} else if(rand==5) {
				Game.game6 = new Game6(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			}  else if(rand==6) {
				Game.game7 = new Game7(puntuacionGeneral, jugador, idPartida);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
				
			} 
			
			else if(rand==7) {
				Game.pi = new PantallaIntermedia(1500, 500, 1, 5, "a", 12);
				Game.estadoJuego = minijuegos.get(rand);
				Game.eventoRaton();
			}
		}
		
		
	}
		
	
		
	
}
