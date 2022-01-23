package minigamble;

import java.util.concurrent.CopyOnWriteArrayList;

import minigamble.Game.ESTADO;

public class ThreadFlechasActivas extends Thread{
	
	// CopyOnWriteArrayList de las flechas creadas
	private CopyOnWriteArrayList<Flecha> creadas = new CopyOnWriteArrayList<Flecha>();
	
	// CopyOnWriteArrayList de las flechas activas
	private CopyOnWriteArrayList<Flecha> activas = new CopyOnWriteArrayList<Flecha>();
	
	/** Constructor de la clase ThreadFlechasActivas
	 * @param creadas CopyOnWriteArrayList de las flechas creadas
	 * @param activas CopyOnWriteArrayList de las flechas activas
	 */
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
		}while(activas.size() > 0) {
			for(Flecha f : creadas) {
				if(f.getY() > 700) {
					activas.remove(f);
					Game6.todasPulsadas = false;
				}
			}
		}
		
		
		if(activas.size() == 0 && Game.estadoJuego==Game.ESTADO.Game6 && !Game6.todasPulsadas ) {
			
			Game6.tiempoTotal = System.currentTimeMillis() - Game6.tiempoComienzo;
			
			Game.game6.check = 0;
			BaseDatos.insertarGame6(Game6.idPartida, Game6.puntLocal, Game6.fallos, Game6.tiempoTotal, "false", Game6.dificultad);
			System.out.println("vidas menos una por deja rpasar");
			Game.pi = new PantallaIntermedia(Game6.puntos, Game6.puntLocal, 1, 3, Game6.idJugador, Game6.idPartida);
			Game.estadoJuego = ESTADO.PantallaIntermedia;
			Game.eventoRaton();	
			
		}
	}

}
