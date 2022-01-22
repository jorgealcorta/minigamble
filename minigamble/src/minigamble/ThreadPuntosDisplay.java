package minigamble;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ThreadPuntosDisplay extends Thread {
	
	int puntos = 0;
	int puntLocal;
	public static Clip puntos2;
	
	
	public ThreadPuntosDisplay(int puntos, int puntLocal) {
		this.puntos = puntos;
		this.puntLocal = puntLocal;
	}

	@Override
	public void run() {
		puntos2();
		for(int i = puntos; i <= puntos+puntLocal; i++) {
			PantallaIntermedia.puntosDisplay = i;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		puntos2.close();
		puntos3();
	}
	
	/**
	 * Reproduce Mitad de la puntuacion
	 */
	private void puntos2() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/PantallaIntermedia/puntos2.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        puntos2 = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			puntos2.open(ais);
	        puntos2.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}
	
	/**
	 * Reproduce Comienzo de la puntuacion
	 */
	private void puntos3() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/PantallaIntermedia/puntos3.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        Clip puntos3 = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			puntos3.open(ais);
	        puntos3.start();
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}
}
