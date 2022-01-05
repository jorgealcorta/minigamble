package minigamble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Clase encargada del juego 7 (tres en raya)
 */

public class Game7 implements MouseListener {
	
	private int[][] matrix = { {0,0,0},{0,0,0},{0,0,0}};
	private int mox;
	private int moy;
	
	private int start=0;	
	private int firstX=-1;
	private int firstY=-1;
	private long tiempoComienzo = System.currentTimeMillis();
	private long tiempoTotal;
	
	int idPartida;
	String jugador;
	int puntTotal;
		
	/**
	 * Constructor del juego 7
	 * @param puntuacion Puntuacion del juego segun la cual aparecerán más o menos cartas
	 * @param nombreJugador	nombre del jugador (se usa para la base de datos)
	 * @param idPart identificador de la partida (se usa para la base de datos)
	 */
	
	public Game7(int dificultad, String nombreJugador, int idPart) {
		
		puntTotal = dificultad;
		jugador = nombreJugador;
		idPartida = idPart;
		
	}
	
	
//	private int pair( int a, int b){     
//        int result =(int) (0.5 * (a + b) * (a + b + 1) + b);
//        return if (depair(result) == input) {
//            result //Return the result
//        } else {
//            throw IllegalStateException("Cantor.depair no longer provides " +
//                    "valid inverse of Cantor.pair, implementation is broken")
//        }
//    }
//
//    private Pair<Long, Long> depair(int z)  {  
//        val t = Math.floor((Math.sqrt((8 * z + 1).toDouble()) - 1) / 2).toInt().toLong()
//        val x = t * (t + 3) / 2 - z
//        val y = z - t * (t + 1) / 2
//        return Pair(x, y)
//    }
	
	
	
	
	
	/**
	 * Metodo checkRow comprueba si hay alguna posible combinacion de 3 en raya y devuelve un integer
	 * en funcion de si alguien ha ganado o no.
	 * @param chckMatrix es la matriz que debe ser comprobada
	 * @return un integer que es el valor del jugador que haya completado el 3 en raya (1 o -1) o devuelve 0 en caso de no haber gando nadie todavia.
	 */
	
	public int checkRow(int[][] chckMatrix) {
		
		if(chckMatrix[0][0]==chckMatrix[0][1] && chckMatrix[0][1]==chckMatrix[0][2]) {
			if(chckMatrix[0][0]==-1) {
				return -1;
			} else if(chckMatrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[1][0]==chckMatrix[1][1] && chckMatrix[1][1]==chckMatrix[1][2]) {
			if(chckMatrix[1][0]==-1) {
				return -1;
			} else if(chckMatrix[1][0]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[2][0]==chckMatrix[2][1] && chckMatrix[2][1]==chckMatrix[2][2]) {
			if(chckMatrix[2][0]==-1) {
				return -1;
			} else if(chckMatrix[2][0]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[0][0]==chckMatrix[1][0] && chckMatrix[1][0]==chckMatrix[2][0]) {
			if(chckMatrix[0][0]==-1) {
				return -1;
			} else if(chckMatrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[0][1]==chckMatrix[1][1] && chckMatrix[1][1]==chckMatrix[2][1]) {
			if(chckMatrix[0][1]==-1) {
				return -1;
			} else if(chckMatrix[0][1]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[0][2]==chckMatrix[1][2] && chckMatrix[1][2]==chckMatrix[2][2]) {
			if(chckMatrix[0][2]==-1) {
				return -1;
			} else if(chckMatrix[0][2]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[0][0]==chckMatrix[1][1] && chckMatrix[1][1]==chckMatrix[2][2]) {
			if(chckMatrix[0][0]==-1) {
				return -1;
			} else if(chckMatrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(chckMatrix[0][2]==chckMatrix[1][1] && chckMatrix[1][1]==chckMatrix[2][0]) {
			if(chckMatrix[0][2]==-1) {
				return -1;
			} else if(chckMatrix[0][2]==1) {
				return 1;
			} 
		}
		
		return 0;
		
	}
	
	
	
	/**
	 * Metodo que comprueba cuantas posiciones libres (marcadas por 0) quedan todavia en el juego
	 * @param cntMatrix es la matriz de la que queremos saber el numero de 0 
	 * @return un int que puede variar del 0 al 9, indicando el numero de posiciones libres y por lo tanto de juagadss disponibles
	 */
	
	private int count(int[][] cntMatrix) {		
		int count =0;
		for(int i=0; i<=2 ; i++) {
			for(int j=0; j<=2; j++) {
				if(cntMatrix[i][j]==0) {
					count++;
				}				
			}
		}
		return count;
	}
	
	
	
	/**
	 * Metodo que devuelve una matriz nueva exactamente igual a la que recibe
	 * @param copyMatrix la matriz que deseamos copiar
	 * @return Una matriz nueva igual a la que recibe
	 */
	
	private int[][] copied(int[][] copyMatrix){
		
		int[][] copy = { {0,0,0},{0,0,0},{0,0,0}};
		
		for(int i=0; i<=2 ; i++) {
			for(int j=0; j<=2; j++) {
				copy[i][j] = copyMatrix[i][j];				
			}
		}
		return copy;
	}
		
		
	/**
	 * Metodo recursivo que devuelve cual es la probabilidad de que cada usuariho gane en una matriz
	 * @param matProb la matriz de la que queremos saber su probabilidad
	 * @param alpha parametro numerico que se va incrementndo en cada llamada recursiva. 
	 * Sirve para llevar la cuenta de a qu� jugador le toca mover en cada llamada a la funcion pudiendo ser par (turno de la maquina) o impar (turno del jugador)
	 * @return un double que epresenta la probabilidad de ganar. El valor puede variar entre 1 (probabilidad segura de que gane el usuario) o -1 (probabilidad segura de que gane la maquina)
	 */
	
	public double probab(int[][] matProb, int alpha) {
		
		System.out.println("count matr like: " + count(matProb));
		System.out.println("check like: " + checkRow(matProb));
		
		if(count(matProb) == 0) {
			return checkRow(matProb);			
			
		} else if (checkRow(matProb)!=0) {
			return checkRow(matProb);
						
		} else {
			
			double allsum=0;
			
			for(int i=0; i<=2; i++) {
				for(int j=0; j<=2; j++) {
					if(matProb[i][j]==0) {
						
						System.out.println("zero in " +j+" "+ i);
						
						int[][] newMatrix = copied(matProb);
						
						if(alpha % 2 == 0) {
							newMatrix[i][j] = 1;
						}else {
							newMatrix[i][j] = -1;							
						}
						
												
						allsum = allsum + (probab(newMatrix, alpha+1) /count(matProb));
					}
				}
				
			}
			return allsum;
		}
	}
	
		
	/**
	 * Metodo que devuelve el indice del valor minimo de un arraylist
	 * @param array del que queremos hayar su minimo
	 * @return int representando el indicie de la posicion del minimo
	 */
	
	private int getMinIndex(ArrayList<Double> array) {
		
		Double min=(double) 1000;
		int minIndex=-1;
		for (int i=0; i<array.size(); i++) {
			if(array.get(i)<min) {
				min=array.get(i);
				minIndex=i;
			}			
		}		
		return minIndex;
	}
	
	
	/**	Evalua si el raton esta sobre una region
	 * @param mx posicion X del raton
	 * @param my posicion Y del raton
	 * @param x	posicion X en la que comienza la region
	 * @param y	posicion Y en la que comienza la region
	 * @param width	anchura de la region
	 * @param heigth altura de la region
	 * @return True si el raton esta sobre esa regio y False si no lo esta
	 */
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int heigth) {   
		if(mx > x && mx < x + width) {
			if(my > y && my < y + heigth) {
				return true;			
			}else {
				return false;
			}
		}else {
			return false;
		}}
	 	
	/**
	 * Realiza un delay en el juego
	 * @param n numero de milisegundo que queremos  que dure el delay
	 */
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			b.printStackTrace();
		}		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(start==0){
			mox = e.getX();	
			moy = e.getY();
			
			if(mouseOver(mox, moy, 0, 0, 100, 100)){
					start=1;
			}
		}
		

					
		if(start==1){
			mox = e.getX();	
			moy = e.getY();
			
			for(int i=200; i<=400; i=i+100) {			
				for (int j=450; j<=650; j=j+100) {	
					
					if(mouseOver(mox, moy, j, i, 100, 100)){
						if(matrix[(i-200)/100][(j-450)/100]==0) {
							
							matrix[(i-200)/100][(j-450)/100]= 1;
							if(firstX == -1 && firstY==-1) {
								firstX = (j-450)/100;
								firstY = (i-200)/100;
							}
							
							if(checkRow(matrix)==1) {
								delayMS(500);
								start=2;
								
								tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
								BaseDatos.insertarGame7(idPartida, 500 , tiempoTotal, firstX, firstY );
								Game.partida  = new Partida( 500 ,0 , 6	, jugador, idPartida);
								
							} else if(count(matrix)==0){
								delayMS(500);
								start=4;
								
								tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
								BaseDatos.insertarGame7(idPartida, 100 , tiempoTotal, firstX, firstY );
								Game.partida  = new Partida( 100 ,0 , 6	, jugador, idPartida);
							}else {
								
								ArrayList<Integer> positions= new ArrayList<Integer>();
								ArrayList<Double> probabilities= new ArrayList<Double>();
								
								
								for(int a =0; a<=2; a++) {
									for(int b=0; b<=2; b++) {
										
										if(matrix[a][b]==0) {
											
											positions.add(a);
											positions.add(b);
											
											int[][] newMatrix= copied(matrix);
											newMatrix[a][b] = -1;											
											double probab1 = probab(newMatrix, 2);											
											probabilities.add(probab1);
										}
										
									}
								}
								
																
								for (int x=0; x<probabilities.size() ; x++) {
									System.out.println("posib of "+ probabilities.get(x) + " for position " + positions.get(x*2) + positions.get(x*2+1));									
								}
								
								
								int bestMove = getMinIndex(probabilities);
								System.out.println("best move is " + bestMove);
								int a = positions.get(bestMove*2);
								int b = positions.get(bestMove*2+1);
								
								matrix[a][b] = -1;
									
								
								if(checkRow(matrix) == -1) {
									delayMS(500);
									start=3;
									tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
									BaseDatos.insertarGame7(idPartida, 0 , tiempoTotal, firstX, firstY );
									Game.partida  = new Partida( 0 ,1 , 6	, jugador, idPartida);
								}
								if(count(matrix)==0) {
									delayMS(500);
									start=4;
									
									tiempoTotal = System.currentTimeMillis() - tiempoComienzo;
									BaseDatos.insertarGame7(idPartida, 100 , tiempoTotal, firstX, firstY );
									Game.partida  = new Partida( 100 ,0 , 6	, jugador, idPartida);
								}																
							}
						}
					}
				}				
			}		
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
		

	
	public void render(Graphics g){
		
		if(start==0) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			g.drawRect(0, 0, 100, 100);
			
		}
			
		if(start==1) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			
			g.drawImage(media.rowVertImg, 547, 180, 5, 340,null);
			g.drawImage(media.rowVertImg, 647, 180, 5, 340,null);
			g.drawImage(media.rowHorzImg, 430, 297, 340, 5,null);
			g.drawImage(media.rowHorzImg, 430, 397, 340, 5,null);
			
			
			for(int i=200; i<=400; i=i+100) {			
				for (int j=450; j<=650; j=j+100) {
					
					if(matrix[(i-200)/100][(j-450)/100] == 1) {
						g.drawImage(media.circleImg ,j+10 , i+10, 80, 80, null);
					} else if(matrix[(i-200)/100][(j-450)/100]== -1) {
						
						g.drawImage(media.crossImg ,j , i, 100, 100, null);
					}
				}
			}
		}
		
		if(start==2) {
			g.setFont(media.customFontBot);
			g.drawString("You won", 200, 200);
		}
		
		if(start==3) {
			g.setFont(media.customFontBot);
			g.drawString("I won", 200, 200);
		}
		
		if(start==4) {
			g.setFont(media.customFontBot);
			g.drawString("Draw", 200, 200);
		}
		
	}
}

