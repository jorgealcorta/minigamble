package minigamble;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ventanaPrincipal extends JFrame {
	
	
	private JButton bStart;
	private JPanel pPrincipal;
	private JFrame frame;
	private JLabel background;
	
	
	
	
	
	
	public ventanaPrincipal() {
		
		
		frame = new JFrame("mingamble.start");
		frame.setSize(1200, 700);
		
		background = new JLabel( new ImageIcon("/multimedia/background.png"));
		
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(background);
		
		
		frame.setVisible(true);
		pPrincipal = new JPanel();
		 
		
	}
	
};
