package minigamble;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadFlechasActivas extends Thread{
	
	private CopyOnWriteArrayList<Flecha> creadas = new CopyOnWriteArrayList<Flecha>();
	private CopyOnWriteArrayList<Flecha> activas = new CopyOnWriteArrayList<Flecha>();
	
	public ThreadFlechasActivas(CopyOnWriteArrayList<Flecha> creadas, CopyOnWriteArrayList<Flecha> activas) {
		this.creadas = creadas;
		this.activas = activas;
	}
	
	@Override
	public void run() {
		for(Flecha f : creadas) {
			activas.add(f);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
