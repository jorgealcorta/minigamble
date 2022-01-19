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

import minigamble.Game.ESTADO;

public class Game2 implements KeyListener{
	
	private int start = 1; //1 = esperando, 2 = reproduciendo, 3 = jugando, 4 = ganado
	
	// Contador de vidas perdidas
	private int vidasRestadas = 0;
	
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
	private int puntLocal = 500;
	private int idPartida;
	private String jugador;
	private int fallos;
	private long tiempoIni = System.currentTimeMillis();
	
	// Longitud de la combinacion en la que se basa el juego
	private int nPalos;
	
	private int dificultad = 1;
	private String superado = "true";
	
	/**
	 * Constructor de la clase Game2
	 * @param puntuacion acumulada
	 * @param nombreJugador
	 * @param idPart identificador de la partida
	 */
	public Game2(int puntuacion, String nombreJugador, int idPart) {
			
			puntos = puntuacion;
			puntLocal = 500;
			idPartida = idPart;
			jugador = nombreJugador;
			
			// Establecemos la longitud de la combinacion dependiendo de puntuacion acumulada, aumentando la dificultad a la par de la puntuacion.
			
			if(puntos >= 0 && puntos < 1500) {
				nPalos = 3;
				dificultad = 1;
			}else if(puntos >= 1500 && puntos < 3000) {
				nPalos = 5;
				dificultad = 2;
			}else if(puntos >= 3000 && puntos < 4500) {
				nPalos = 7;
				dificultad = 3;
			}else if(puntos >= 4500) {
				nPalos = 9;
				dificultad = 4;
			}
			
			//Creamos el array de los cuatro palos posibles
			palos.add("cora");
			palos.add("diam");
			palos.add("pica");
			palos.add("treb");
			
			
			//Creamos la combinacion correcta del simon says
			for(int i = 0 ; i < nPalos; i++) {
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
			Thread tSecuencia = new Thread() {
				public void run() {
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
				
				};
				
				tSecuencia.start();

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
			
			if(puntLocal > 0 && palosUsuario.size() > 0 && palosUsuario.get(cuentaPulsaciones) != palosCorrectos.get(cuentaPulsaciones)) {
				System.out.println(palosUsuario);
				palosUsuario.removeAll(palosUsuario);
				cuentaPulsaciones = -1;
				start = 1;
				puntLocal -= 100;
				fallos++;
				System.out.println("error");
			}else if(puntLocal <= 0) {
				start = 4;
				delaySeg(2);
				long tiempofin = System.currentTimeMillis() - tiempoIni;
				BaseDatos.insertarGame2(idPartida, 0, fallos, tiempofin, "falso" , dificultad);
				
				Game.pi = new PantallaIntermedia(puntos, 0, 1, 1, jugador, idPartida);
				Game.estadoJuego = ESTADO.PantallaIntermedia;
				Game.eventoRaton();
			}
			
			/*
			 * En el caso de que el usuario haya introducido tantas pulsaciones como palos hay en el ArrayList correcto,
			 * el usuario habra introducido la combinacion correctamente sin que el bloque de encima lo haya interrumpido.
			 */
			
			if(cuentaPulsaciones == palosCorrectos.size() - 1 && palosUsuario.get(cuentaPulsaciones) == palosCorrectos.get(cuentaPulsaciones)) {
				start = 4;
				delaySeg(2);
				long tiempofin = System.currentTimeMillis() - tiempoIni;
				
				
				
				if(fallos == 0) {
					vidasRestadas = 0;
					superado = "true";
				}else if(fallos>0 && fallos <1) {
					vidasRestadas = 1;
					superado = "false";
				}else if(fallos>1) {
					vidasRestadas = 1;
					superado = "false";
				}
				
				BaseDatos.insertarGame2(idPartida, puntLocal, fallos, tiempofin, superado , dificultad);
				Game.pi = new PantallaIntermedia(puntos, puntLocal, vidasRestadas, 1, jugador, idPartida);
				Game.estadoJuego = ESTADO.PantallaIntermedia;
				Game.eventoRaton();
			}
			
		}	
	}
	
	/** Hace el render de los elementos
	 * @param g recibe Graphics de Game
	 */
	
	public void render(Graphics g) {
		
		if(VentanaPrincipal.soWindows) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		}else {
			g.drawImage(media.tapeteImg, 0, 0, 1200, 672, null);
		}
		
		/*
		 * Si el juego esta en start=1, se dibujan todas las fichas transparentes y se muestra un texto que muestra al usuario
		 * que pulse la barra espaciadora para reproducir la combinacion correcta. 
		 */
		
		if(start == 1) {
			g.setFont(media.customFontBot);
			
			g.drawImage(media.upNegro_img, 520, 125, 128, 128, null);
			g.drawImage(media.leftNegro_img, 350, 290, 128, 128, null);
			g.drawImage(media.downNegro_img, 520, 450, 128, 128, null);
			g.drawImage(media.rightNegro_img, 690, 290, 128, 128, null);
			
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
				g.drawImage(media.upNegro_img, 520, 125,  128, 128, null);
			}else {
				g.drawImage(media.upBlanco_img, 520, 125, 128, 128, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(media.leftNegro_img, 350, 290, 128, 128, null);
			}else {
				g.drawImage(media.leftBlanco_img, 350, 290, 128, 128, null);
			}
			
			if(!picaDestacar) {
				g.drawImage(media.downNegro_img, 520, 450, 128, 128, null);
			}else {
				g.drawImage(media.downBlanco_img, 520, 450, 128, 128, null);
			}
			
			if(!trebDestacar) {
				g.drawImage(media.rightNegro_img, 690, 290, 128, 128, null);
			}else {
				g.drawImage(media.rightBlanco_img, 690, 290, 128, 128, null);
			}
			
			g.drawImage(media.crossarrow_IMG, 520, 285, 128, 128, null);
	
		}
		
		/*
		 * Si el juego esta en start=3, el programa esta recibiendo el input. Queremos que la ficha se dibuje transparente en todo
		 * momento, y que cuando el usuario tenga la tecla correspondiente pulsada, la ficha se destaque.
		 */
		
		else if(start == 3) {
			g.setColor(Color.BLACK);
			if(!coraDestacar) {
				g.drawImage(media.upNegro_img, 520, 125, 128, 128, null);
			}else {
				g.drawImage(media.upBlanco_img, 520, 125, 128, 128, null);
			}
			
			if(!diamDestacar) {
				g.drawImage(media.leftNegro_img, 350, 290,  128, 128,null);
			}else {
				g.drawImage(media.leftBlanco_img, 350, 290,  128, 128,null);
			}
			
			if(!picaDestacar) {
				g.drawImage(media.downNegro_img, 520, 450,  128, 128,null);
			}else {
				g.drawImage(media.downBlanco_img, 520, 450,  128, 128,null);
			}
			
			if(!trebDestacar) {
				g.drawImage(media.rightNegro_img, 690, 290, 128, 128, null);
			}else {
				g.drawImage(media.rightBlanco_img, 690, 290, 128, 128, null);
			}
			
			g.drawImage(media.crossarrow_IMG, 520, 285, null);
	
		}
		
		// start = 4 implica que el usuario ha superado el juego
		
		else if(start == 4) {
			g.setFont(media.customFontBot);
			g.setColor(Color.BLACK);
			g.drawString("ENHORABUENA", 325, 300);
		}
	}
	
}
