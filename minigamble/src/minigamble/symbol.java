package minigamble;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;

public class symbol  {
	
	private Image image;
	
	private int xpos;
	private int ypos;
	private boolean moving;
	
	public symbol() {
			
		xpos=550;
		ypos=250;
		image = Game5.getRandom();
		moving=true;
		
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
		return image;
	}

	public void setImageS1(Image image) {
		this.image = image;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void move(int numPix) {
		
		if(this.moving) {
			if(this.ypos+numPix > 450) {
				this.ypos = this.ypos+numPix-400;
				this.image = Game5.getRandom();
				
			}else {
				this.ypos = this.ypos+numPix;
			}
		}
	}


}
