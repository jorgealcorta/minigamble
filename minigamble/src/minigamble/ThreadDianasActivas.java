package minigamble;

import java.util.ArrayList;

public class ThreadDianasActivas extends Thread{
	
	ArrayList<Diana> creadas = new ArrayList<Diana>();
	ArrayList<Diana> activas = new ArrayList<Diana>();
	
	ThreadDianasActivas(ArrayList<Diana> creadas, ArrayList<Diana> activas){
		this.creadas= creadas;
		this.activas = activas;
	}

	@Override
	public void run() {
		System.out.println(activas);
		System.out.println(creadas);
		for(Diana d : creadas) {
			activas.add(d);
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(activas);
		}
	}
	
	
}
