package minigamble;

public class ThreadVidasDisplay extends Thread {
	
	int vidas = 3;
	PantallaIntermedia pi;
	
	
	public ThreadVidasDisplay(int vidas, PantallaIntermedia pi) {
		this.vidas = vidas;
		this.pi = pi;
	}
	
	@Override
	public void run() {
		pi.vida1.display = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pi.vida2.display = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pi.vida3.display = true;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (!Thread.currentThread().isInterrupted()) {
			
			pi.vida1.frente = false;
			pi.vida2.frente = false;
			pi.vida3.frente = false;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pi.vida1.frente = true;
			pi.vida2.frente = true;
			pi.vida3.frente = true;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
				
	}

}
