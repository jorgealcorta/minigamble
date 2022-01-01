package minigamble;

import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.ImageIcon;

public class symbol  {
	
	private int image;
	
	private int xpos;
	private int ypos;
	private boolean moving;
	
	public symbol() {
			
		xpos=550;
		ypos=50;
		image = getRandom();
		moving=false;
		
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

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
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
			if(this.ypos+numPix > 550) {
				this.ypos = this.ypos+numPix-600;
				this.image = getRandom();
				
			}else {
				this.ypos = this.ypos+numPix;
			}
		}
	}
	
	public static int getRandom() {
	    int rnd = new Random().nextInt(Game5.symbols.size());
	    return 0;
	}

}
