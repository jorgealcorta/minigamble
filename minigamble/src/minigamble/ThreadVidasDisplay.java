package minigamble;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class ThreadVidasDisplay extends Thread {

	PantallaIntermedia pi;
	
	
	public ThreadVidasDisplay(PantallaIntermedia pi) {

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
			latido1();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			latido2();
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
	
	/**
	 * Reproduce latido 1
	 */
	private void latido1() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/PantallaIntermedia/latido5.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			sonido.open(ais);
			FloatControl gainControl = 
	        	    (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
			sonido.start();
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}
	
	/**
	 * Reproduce latido 2
	 */
	private void latido2() {
		String filePath = new File("").getAbsolutePath();				// Ruta hasta el proyecto
		String s1_filePath = filePath.concat("/minigamble/src/minigamble/sonido/PantallaIntermedia/latido6.wav");	//Continuación de la ruta hasta el archivo de audio 1
		try {																				
	        Clip sonido = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(s1_filePath));
			sonido.open(ais);
			FloatControl gainControl = 
	        	    (FloatControl) sonido.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
			sonido.start();
        }catch(Exception e2) {
        	System.out.println("error");
        }
		
	}

}
