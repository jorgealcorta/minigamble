package minigamble;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadFlechasMain extends Thread{
	
	// CopyOnWriteArrayList de las flechas activas
	private CopyOnWriteArrayList<Flecha> activas = new CopyOnWriteArrayList<Flecha>();
	
	/** Constructor de la clase ThreadFlechasMain
	 * @param activas CopyOnWriteArrayList de las flechas activas
	 */
	public ThreadFlechasMain(CopyOnWriteArrayList<Flecha> activas) {
		this.activas = activas;
	}
	
	@Override
	public void run() {
		while(activas.size() != 0) {
		for(Flecha f : activas) {
			f.move(5);
		}
		try {
			Thread.sleep(60/Game6.nFlechas); //MARCA LA VELOCIDAD DE CAIDA
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
}
