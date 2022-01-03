package minigamble;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game7 implements MouseListener {
	
	private int[][] matrix = { {0,0,0},{0,0,0},{0,0,0}};
	private int mox;
	private int moy;
	
	private int start=0;
		
		
	
	public Game7(int dificultad, String nombreJugador, int idPart) {
		
		
		
	}
	
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
	
	private int[][] copied(int[][] copyMatrix){
		
		int[][] copy = { {0,0,0},{0,0,0},{0,0,0}};
		
		for(int i=0; i<=2 ; i++) {
			for(int j=0; j<=2; j++) {
				copy[i][j] = copyMatrix[i][j];				
			}
		}
		return copy;
	}
	
	
	
	public void nextMove() {
		
		
	}
	
	public void render(Graphics g){
		
		if(start==0) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			g.drawRect(0, 0, 100, 100);
			
		}
			
		if(start==1) {
			g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
			g.drawLine(550, 200, 550, 500);
			g.drawLine(650, 200, 650, 500);
			g.drawLine(450, 300, 750, 300);
			g.drawLine(450, 400, 750, 400);
			
			for(int i=200; i<=400; i=i+100) {			
				for (int j=450; j<=650; j=j+100) {
					
					if(matrix[(i-200)/100][(j-450)/100] == 1) {
						g.drawImage(media.circleImg ,j , i, 100, 100, null);
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

	
	public double probab(int[][] matProb, int alpha) {
		
		System.out.println("count matr like: " + count(matProb));
		System.out.println("check like: " + checkRow(matProb));
		
		if(count(matProb) == 0) {
			System.out.println("case 1");
			return checkRow(matProb);			
			
		} else if (checkRow(matProb)!=0) {
			System.out.println("case 2 returning" );
			return checkRow(matProb);
			
			
		} else {
			
			System.out.println("Case else");
				
			double allsum=0;
			int numR=0;
			
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
						
						System.out.println("returned from posib "+ numR);
						System.out.println("alsum  post sum like " + allsum );
						
					}
				}
				
			}
			return allsum;
		}
	}
	
	
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
							
							if(checkRow(matrix)==1) {
								start=2;
							} else if(count(matrix)==0){
								start=4;
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
											
											System.out.println("Case now added a probablilty of " + probab1 );
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
									
								delayMS(200);
								if(checkRow(matrix) == -1) {
									start=3;
								}
								if(count(matrix)==0) {
									start=4;
								}
																
							}
							
							
						}
					}
					
				
				}
				
			}
		
		}
	}
	
	private void delayMS(int n) {
		try {
			TimeUnit.MILLISECONDS.sleep(n);
		} catch (InterruptedException b) {
			// TODO Auto-generated catch block
			b.printStackTrace();
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

}
