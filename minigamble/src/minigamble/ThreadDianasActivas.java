package minigamble;

import java.util.concurrent.CopyOnWriteArrayList;

import minigamble.Game.ESTADO;

public class ThreadDianasActivas extends Thread{
	
	// Utilizaremos los Arraylist dianasCreadas y dianasActivas en este hilo, las cuales insertaremos como parametros.
	CopyOnWriteArrayList<Diana> creadas = new CopyOnWriteArrayList<Diana>();
	CopyOnWriteArrayList<Diana> activas = new CopyOnWriteArrayList<Diana>();
	
	
	/** Constructor del Thread
	 * @param creadas sera el ArrayList dianasCreadas de Game4
	 * @param activas sera el ArrayList dianasActivas de Game4
	 */
	ThreadDianasActivas(CopyOnWriteArrayList<Diana> creadas, CopyOnWriteArrayList<Diana> activas){
		this.creadas= creadas;
		this.activas = activas;
	}

	/** Metodo run del hilo
	 * Las dianas creadas aleatoriamente se insertan en el arrayList de las dianas activas con un cierto delay,
	 * con el fin de que aparezcan en pantalla poco a poco.
	 */
	
	@Override
	public void run() {
		for(Diana d : creadas) {
			activas.add(d);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(activas.size() > 0) {
			activas.remove(0);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
		if(activas.size() == 0 && Game.estadoJuego==Game.ESTADO.Game4 && !Game4.todasRotas) {
			
			System.out.println("derrota por desaparecer todas");
			Game4.tiempoTotal = System.currentTimeMillis() - Game4.tiempoComienzo;
			
			BaseDatos.insertarGame4(Game4.idPartida, Game4.puntLocal, Game4.fallos, Game4.tiempoTotal, "false", Game4.dificultad);
			Game.pi = new PantallaIntermedia(Game4.puntos, Game4.puntLocal, 1, 3, Game4.jugador, Game4.idPartida);
			Game.estadoJuego = ESTADO.PantallaIntermedia;
			Game.eventoRaton();	
		}
		
		System.out.println("hilo dianas acabado");
		
			
	}
	
	
}
