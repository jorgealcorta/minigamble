package minigamble;

import java.util.ArrayList;

public class ThreadBorrarDiana extends Thread{
	
	Diana rota;
	ArrayList<Diana> activas; //sustituir arraylist pro vector, vector esta sincronizad, hasta que un hilo no acaba su ejecucion con ella, el otro hilo no empieza, consistencia sincrona
	ArrayList<Diana> rotas;
		
	/**
	 * @param rota objeto de la clase diana, introducida en Game4
	 * @param activas ArrayList de dianas activas en pantalla
	 * @param rotas ArrayList de dianas rotas
	 */
	ThreadBorrarDiana(Diana rota, ArrayList<Diana> activas, ArrayList<Diana> rotas){
		this.rota = rota;
		this.activas = activas;
		this.rotas = rotas;
	}

	/** Metodo run del hilo
	 * Espera 300ms, durante los cuales la diana rota se muestra en pantalla, ya que no esta en el ArrayList de rotas,
	 * pero no es disparable, ya que su booleano rota esta en true.
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rotas.add(rota);
		
	}
	
	
		

}
