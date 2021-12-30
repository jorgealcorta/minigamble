package minigamble;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadFlechasMain extends Thread{
	
	private CopyOnWriteArrayList<Flecha> activas = new CopyOnWriteArrayList<Flecha>();
	
	public ThreadFlechasMain(CopyOnWriteArrayList<Flecha> activas) {
		this.activas = activas;
	}
	
	@Override
	public void run() {
		while(activas.size() != 0) {
		for(Flecha f : activas) {
			f.move(10);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
}
