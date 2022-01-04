package minigamble;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("derrota por desaparecer todas");
		Game4.tiempoTotal = System.currentTimeMillis() - Game4.tiempoComienzo;
		Game.partida  = new Partida(Game4.puntos + Game4.puntLocal, 1, null, Game4.jugador, Game4.idPartida);
		
	}
	
	
}
