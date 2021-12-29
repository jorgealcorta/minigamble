package minigamble;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

public class symbol  {
	
	private ImageIcon iconS1;
	private Image imageS1;
	
	private int xpos;
	private int ypos;
	
	public symbol() {
			
		xpos=550;
		ypos=250;
		
		
		
	}
	
		
	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	
	public Image getImageS1() {
		return imageS1;
	}

	public void setImageS1(Image imageS1) {
		this.imageS1 = imageS1;
	}

	public void move(int numPix) {
		if(this.ypos+numPix > 450) {
			this.ypos = this.ypos+numPix-400;
			this.imageS1 = Game5.getRandom();
			
		}
			this.ypos = this.ypos+numPix;
		
		
	}


}
