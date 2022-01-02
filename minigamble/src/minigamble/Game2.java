package minigamble;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Game2 implements KeyListener{
	
	private int start = 1; //1 = esperando, 2 = reproduciendo, 3 = jugando, 4 = ganado
	
	// Contador de flechas pulsadas por el usuario durante el periodo de juego.
	private int cuentaPulsaciones = -1;
	
	// Booleanos utilizados en render para elegir si dibujar las fichas transparentes o rellenas.
	private boolean coraDestacar = false;
	private boolean diamDestacar = false;
	private boolean picaDestacar = false;
	private boolean trebDestacar = false;
	
	// ArrayList empleado para almacenar los cuatro posibles palos.
	private ArrayList<String> palos = new ArrayList<String>();
	
	// ArrayList empleado para almacenar la combinacion de palos generada aleatoriamente.
	private ArrayList<String> palosCorrectos = new ArrayList<String>();
	
	// ArrayList empleado para almacenar la combinacion de palos introducida por el usuario, se reinicia en cada intento.
	private ArrayList<String> palosUsuario = new ArrayList<String>();

	private int puntos;
	private int idPartida;
	private String jugador;
	private int fallos;
	private long tiempoIni = System.currentTimeMillis();
	
	public Game2(int puntuacion, String nombreJugador, int idPart) {
			
			puntos = puntuacion;
			idPartida = idPart;
			jugador = nombreJugador;
			
			//Creamos el array de los cuatro palos
			palos.add("cora");
			palos.add("diam");
			palos.add("pica");
			palos.add("treb");
			
			
			//Creamos la combinacion correcta del simon says
			for(int i = 0 ; i < 5; i++) {
				palosCorrectos.add(palos.get((int) Math.floor(Math.random()*4)));
			}
			
			System.out.println(palosCorrectos);
			
			
					
		}

	/**
	 * Realiza un delay en Segundos
	 * @param n numero de segundos que se quiere hacer el delay
	 */
	
	private void delaySeg(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
	/**
	 * Realiza un delay en Milisegundo
	 * @param n numero de Milisegundo que se quiere hacer el delay
	 */
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
		}
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		/* 
		 * Si se ha pulsado el espacio cuando el juego est� en start=1, se inicia un for que recorre
		 * la lista de palos correctos, cambiando el booleano de destacar del palo que corresponda,
		 * durante un peque�o tiempo:
		 */
		 
		if(key == 32 && start==1) { //reproducir secuencia
			start = 2;
			for(String palo : palosCorrectos) {
				
				if(palo == "cora") {
					coraDestacar = true; 	
					delayMS(250);
					coraDestacar = false;
					delayMS(250);
				}
				else if(palo == "diam") {
					diamDestacar = true; 	
					delayMS(250);
					diamDestacar = false;
					delayMS(250);			
				}
				else if(palo == "pica") {
					picaDestacar = true; 	
					delayMS(250);
					picaDestacar = false;
					delayMS(250);
				}
				else if(palo == "treb") {
					trebDestacar = true; 	
					delayMS(250);
					trebDestacar = false;
					delayMS(250);
				}
	
			}
			
			start = 3;
		}
		
		/*
		 * Si el juego esta en start=3, se reaccionara ante el input de las cuatro flechas del teclado, cada cual
		 * corresponde a uno de los palos disponibles. En el keyPressed unicamente se gestionara el booleano
		 * de destacar, y mas adelante, en el keyPressed, se gestionara la pulsacion del palo.
		 */
		
		if(start == 3) {
			
			switch(key){
			
			case(37):
				diamDestacar = true;
				break;
			case(38):
				coraDestacar = true;		
				break;
			case(39):
				trebDestacar = true;
				break;
			case(40):
				picaDestacar = true;
				break;
			
			}
			
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		/*
		 * Si start=3 y se suelta una de las cuatro flechas, el booleano de esa flecha se pondra en false, dejando de destacarlo,
		 * a continuacion se agregara el palo correspondiente a la flecha pulsada al ArrayList del input y aumentara el
		 * contador de inputs.
		 */
		if(start == 3) {
			switch(key){
			
			case(37):
				diamDestacar = false;
				palosUsuario.add("diam");
				cuentaPulsaciones++;
				break;
			case(38):
				coraDestacar = false;
				palosUsuario.add("cora");
				cuentaPulsaciones++;
				break;
			case(39):
				trebDestacar = false;
				palosUsuario.add("treb");
				cuentaPulsaciones++;
				break;
			case(40):
				picaDestacar = false;
				palosUsuario.add("pica");
				cuentaPulsaciones++;
				break;
			
			}
			
			System.out.println(palosUsuario);
			
			/*
			 * En el caso de que el palo introducido por el usuario no coincida con el correspondiente a su posicion
			 * en el ArrayList correcto, el usuario habra cometido un fallo. Mientras este trozo de codigo no se ejecute,
			 * el usuario estar� haciendolo bien.
			 */
			
			if(palosUsuario.size() > 0 && palosUsuario.get(cuentaPulsaciones) != palosCorrectos.get(cuentaPulsaciones)) {
				System.out.println(palosUsuario);
				palosUsuario.removeAll(palosUsuario);
				cuentaPulsaciones = -1;
				start = 1;
				System.out.println("error");
			}
			
			/*
			 * En el caso de que el usuario haya introducido tantas pulsaciones como palos hay en el ArrayList correcto,
			 * el usuario habra introducido la combinacion correctamente sin que el bloque de encima lo haya interrumpido.
			 */
			
			if(cuentaPulsaciones == palosCorrectos.size() - 1 && palosUsuario.get(cuentaPulsaciones) == palosCorrectos.get(cuentaPulsaciones)) {
				start = 4;
				delaySeg(2);
				long tiempofin = System.currentTimeMillis() - tiempoIni;
				BaseDatos.insertarGame2(idPartida, puntos, fallos, tiempofin);
				Game.partida  = new Partida(puntos, 0, null, jugador, idPartida);
			}
			
		}	
	}
	
	/** Hace el render de los elementos
	 * @param g recibe Graphics de Game
	 */
	public void render(Graphics g) {
		g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		
		/*
		 * Si el juego esta en start=1, se dibujan todas las fichas transparentes y se muestra un texto que muestra al usuario
		 * que pulse la barra espaciadora para reproducir la combinacion correcta. 
		 */
		
		if(start == 1) {
			g.setFont(media.customFontBot);
			
			g.drawImage(media.corazonblanco_IMG, 520, 125, null);
			g.drawImage(media.diamanteblanco_IMG, 350	, 290, null);
			g.drawImage(media.picablanca_IMG, 520, 450, null);
			g.drawImage(media.trebolblanco_IMG, 690, 290, null);
			
			g.drawImage(media.crossarrow_IMG, 520, 285, null);
			
			g.setColor(Color.BLACK);
			g.drawString("PULSA ESPACIO PARA REPRODUCIR LA COMBINACION", 200, 70);
		}
		
		/*
		 * Si el juego esta en start=2, se dibujan todas las fichas, transparentes o destacadas, dependiendo del booleano destacar
		 * de cada ficha, ya que es en start=2 donde el bucle for este recorriendo la combinacion correcta y destacando los
		 * palos correspondientes.
		 */
		
		else if(start == 2) {
			g.setColor(Color.BLACK);
			if(!coraDestacar) {
				g.drawImage(media.corazonblanco_IMG, 520, 125, null);
			}else {
				g.drawImage(media.corazonnegro_IMG, 520, 125, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(media.diamanteblanco_IMG, 350, 290, null);
			}else {
				g.drawImage(media.diamantenegro_IMG, 350, 290, null);
			}
			
			if(!picaDestacar) {
				g.drawImage(media.picablanca_IMG, 520, 450, null);
			}else {
				g.drawImage(media.picanegra_IMG, 520, 450, null);
			}
			
			if(!trebDestacar) {
				g.drawImage(media.trebolblanco_IMG, 690, 290, null);
			}else {
				g.drawImage(media.trebolnegro_IMG, 690, 290, null);
			}
			
			g.drawImage(media.crossarrow_IMG, 520, 285, null);
	
		}
		
		/*
		 * Si el juego esta en start=3, el programa esta recibiendo el input. Queremos que la ficha se dibuje transparente en todo
		 * momento, y que cuando el usuario tenga la tecla correspondiente pulsada, la ficha se destaque.
		 */
		
		else if(start == 3) {
			g.setColor(Color.BLACK);
			if(!coraDestacar) {
				g.drawImage(media.corazonblanco_IMG, 520, 125, null);
			}else {
				g.drawImage(media.corazonnegro_IMG, 520, 125, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(media.diamanteblanco_IMG, 350, 290, null);
			}else {
				g.drawImage(media.diamantenegro_IMG, 350, 290, null);
			}
			
			if(!picaDestacar) {
				g.drawImage(media.picablanca_IMG, 520, 450, null);
			}else {
				g.drawImage(media.picanegra_IMG, 520, 450, null);
			}
			
			if(!trebDestacar) {
				g.drawImage(media.trebolblanco_IMG, 690, 290, null);
			}else {
				g.drawImage(media.trebolnegro_IMG, 690, 290, null);
			}
			
			g.drawImage(media.crossarrow_IMG, 520, 285, null);
	
		}
		
		else if(start == 4) {
			g.setFont(media.customFontBot);
			g.setColor(Color.BLACK);
			g.drawString("ENHORABUENA", 325, 300);
		}
	}
	
}
