package minigamble;

public class ThreadPuntosDisplay extends Thread {
	
	int puntos = 0;
	PantallaIntermedia pi;
	
	public ThreadPuntosDisplay(int puntos, PantallaIntermedia pi) {
		
		this.puntos = puntos;
		this.pi = pi;
		
	}

	@Override
	public void run() {
		
		for(int i = 0; i <= puntos; i++) {
			pi.setPuntosDisplay(i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		pi.hiloPuntosAcabado = true;	

	}
}
