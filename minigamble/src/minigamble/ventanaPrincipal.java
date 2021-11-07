package minigamble;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ventanaPrincipal extends JFrame {
	
	
	private JButton bStart;
	private JPanel pPrincipal;
	private JFrame frame;
	
	
	
	
	
	
	public ventanaPrincipal() {
		
		
		frame = new JFrame("mingamble.start");
		frame.setSize(800, 500);
		
		frame.pack();--------------------------------------------
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		pPrincipal = new JPanel();
		 
		
	}
	
};
