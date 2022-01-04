package minigamble;

public class ThreadPuntosDisplay extends Thread {
	
	int puntos = 0;
	
	public ThreadPuntosDisplay(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public void run() {
		
		for(int i = 0; i <= puntos; i++) {
			PantallaIntermedia.puntosDisplay = i;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
