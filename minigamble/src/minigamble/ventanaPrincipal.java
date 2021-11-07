package minigamble;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;


public class ventanaPrincipal extends JFrame {
	
	
	private JButton bStart;
	private JPanel pPrincipal;
	private JFrame frame;
	private ImageIcon background;
	
	
	
	
	
	
	public ventanaPrincipal() {
		
		
		frame = new JFrame("minigamble.start");
		frame.setSize(1200, 700);
		
		
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		try { 
			
			background = new ImageIcon( ventanaPrincipal.class.getResource("multimedia/background.png").toURI().toURL() ); 
			bStart = new JButton( new ImageIcon( ventanaPrincipal.class.getResource("multimedia/yellow_button1.png").toURI().toURL() ) );
		} catch (Exception e1) {  // Si hay error, botones texto
			bStart = new JButton( "Start" );
			JLabel background = new JLabel();
		}
		
		
		Image auxImg = background.getImage();
		Image fBackground = auxImg.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bIcon = new ImageIcon(fBackground);
		JLabel bLabel = new JLabel(bIcon);
		
		//ImageIcon final  = new ImageIcon(dBackground);
		
		frame.add(bStart, BorderLayout.CENTER);
		frame.add(bLabel);
		frame.setVisible(true);
		pPrincipal = new JPanel();
		 
		
	}
	
};
