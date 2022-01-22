package minigamble;

import java.awt.Image;

public class Laberinto {

	private Image image;
	private int id;
	
	/**
	 * @param image imagen del laberinto
	 * @param id identificador del laberinto
	 */
	public Laberinto(Image image, int id) {

		this.image = image;
		this.id = id;
	}

	/**
	 * @return devuelve la imagen del laberinto
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image cambia la imagen del laberinto
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return devuelve el identificador del laberinto
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id devuelve el identificador del laberinto
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
