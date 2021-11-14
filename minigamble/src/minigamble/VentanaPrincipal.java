package minigamble;


import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class VentanaPrincipal extends Canvas{
	
	private static final long serialVersionUID = 1L;
	ImageIcon logo;	
	final Taskbar taskbar = Taskbar.getTaskbar();
	
	
	public VentanaPrincipal(String title, Game game) {
		
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(1200,700));
		frame.setMaximumSize(new Dimension(1200,700));
		frame.setMinimumSize(new Dimension(1200,700));
		
		try {
			logo = new ImageIcon( Game.class.getResource("multimedia/chipRedWhite_border.png").toURI().toURL() );
			frame.setIconImage(logo.getImage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
            //set icon for mac os (and other systems which do support this method)
            taskbar.setIconImage(logo.getImage());
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }
		
		frame.setIconImage(logo.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que la X pare los hilos
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // Para que la ventana aparezca en el centro, en vez de arriba a la izquierda
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
		
	}

};
