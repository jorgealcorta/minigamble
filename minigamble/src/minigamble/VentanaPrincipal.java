package minigamble;


import java.awt.*;
import javax.swing.JFrame;



public class VentanaPrincipal extends Canvas{
	
	private static final long serialVersionUID = 1L;

	public VentanaPrincipal(String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(1200,700));
		frame.setMaximumSize(new Dimension(1200,700));
		frame.setMinimumSize(new Dimension(1200,700));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que la X pare los hilos
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // Para que la ventana aparezca en el centro, en vez de arriba a la izquierda
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
		
	}

};
