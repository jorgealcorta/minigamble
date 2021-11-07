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
		
		
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		try { 
			background = new JLabel( new ImageIcon( ventanaPrincipal.class.getResource("multimedia/background.png").toURI().toURL() ) );
			bStart = new JButton( new ImageIcon( ventanaPrincipal.class.getResource("multimedia/yellow_button1.png").toURI().toURL() ) );
		} catch (Exception e1) {  // Si hay error, botones texto
			bStart = new JButton( "Start" );
			background = new JLabel();
		}
		
		
		frame.add(background);
		//frame.add(bStart, BorderLayout.CENTER);jajajaj
		frame.setVisible(true);
		pPrincipal = new JPanel();
		 
		
	}
	
};
