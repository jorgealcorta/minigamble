package minigamble;

import java.util.ArrayList;

public class ThreadDianasActivas extends Thread{
	
	// Utilizaremos los Arraylist dianasCreadas y dianasActivas en este hilo, las cuales insertaremos como parametros.
	ArrayList<Diana> creadas = new ArrayList<Diana>();
	ArrayList<Diana> activas = new ArrayList<Diana>();
	
	
	/** Constructor del Thread
	 * @param creadas sera el ArrayList dianasCreadas de Game4
	 * @param activas sera el ArrayList dianasActivas de Game4
	 */
	ThreadDianasActivas(ArrayList<Diana> creadas, ArrayList<Diana> activas){
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
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
