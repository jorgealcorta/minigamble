package minigamble;

public class ThreadPuntosDisplay extends Thread {
	
	int puntos = 0;
	int puntLocal;
	
	public ThreadPuntosDisplay(int puntos, int puntLocal) {
		this.puntos = puntos;
		this.puntLocal = puntLocal;
	}

	@Override
	public void run() {
		
		for(int i = puntos; i <= puntos+puntLocal; i++) {
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
