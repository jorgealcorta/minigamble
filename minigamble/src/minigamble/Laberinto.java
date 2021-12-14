package minigamble;

import java.awt.Image;

public class Laberinto {

	private Image image;
	private int id;
	
	public Laberinto(Image image, int id) {

		this.image = image;
		this.id = id;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
