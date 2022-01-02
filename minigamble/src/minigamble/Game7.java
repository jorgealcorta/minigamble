package minigamble;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game7 implements MouseListener {
	
	private int[][] matrix = { {-1,-1,-1},{-1,-1,-1},{-1,-1,-1}	};
		
	
	public Game7(int dificultad, String nombreJugador, int idPart) {
		
		
	
	}
	
	public int checkRow() {
		
		if(matrix[0][0]==matrix[0][1] && matrix[0][1]==matrix[0][2]) {
			if(matrix[0][0]==0) {
				return 0;
			} else if(matrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(matrix[1][0]==matrix[1][1] && matrix[1][1]==matrix[1][2]) {
			if(matrix[1][0]==0) {
				return 0;
			} else if(matrix[1][0]==1) {
				return 1;
			} 
		}
		
		if(matrix[2][0]==matrix[2][1] && matrix[2][1]==matrix[2][2]) {
			if(matrix[2][0]==0) {
				return 0;
			} else if(matrix[2][0]==1) {
				return 1;
			} 
		}
		
		if(matrix[0][0]==matrix[1][0] && matrix[1][0]==matrix[2][0]) {
			if(matrix[0][0]==0) {
				return 0;
			} else if(matrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(matrix[0][1]==matrix[1][1] && matrix[1][1]==matrix[2][1]) {
			if(matrix[0][1]==0) {
				return 0;
			} else if(matrix[0][1]==1) {
				return 1;
			} 
		}
		
		if(matrix[0][2]==matrix[1][2] && matrix[1][2]==matrix[2][2]) {
			if(matrix[0][2]==0) {
				return 0;
			} else if(matrix[0][2]==1) {
				return 1;
			} 
		}
		
		if(matrix[0][0]==matrix[1][1] && matrix[1][1]==matrix[2][2]) {
			if(matrix[0][0]==0) {
				return 0;
			} else if(matrix[0][0]==1) {
				return 1;
			} 
		}
		
		if(matrix[0][2]==matrix[1][1] && matrix[1][1]==matrix[2][0]) {
			if(matrix[0][2]==0) {
				return 0;
			} else if(matrix[0][2]==1) {
				return 1;
			} 
		}
		
		return -1;
		
	}
	
	
	
	
	public void render(Graphics g){
		
		g.drawImage(media.tapeteImg, 0, 0, 1184, 663, null);
		g.drawLine(550, 200, 550, 500);
		g.drawLine(650, 200, 650, 500);
		g.drawLine(450, 300, 750, 300);
		g.drawLine(450, 400, 750, 400);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
