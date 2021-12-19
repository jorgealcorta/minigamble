package minigamble;

import java.util.ArrayList;

public class ThreadBorrarDiana extends Thread{
	
	Diana rota;
	ArrayList<Diana> activas; //sustituir arraylist pro vector, vector esta sincronizad, hasta que un hilo no acaba su ejecucion con ella, el otro hilo no empieza, consistencia sincrona
	ArrayList<Diana> rotas;
		
	ThreadBorrarDiana(Diana rota, ArrayList<Diana> activas, ArrayList<Diana> rotas){
		this.rota = rota;
		this.activas = activas;
		this.rotas = rotas;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rotas.add(rota);
		
//		Aqu� poner para que no se dibuje (meter en array? setRota?)
	}
	
	
		

}
