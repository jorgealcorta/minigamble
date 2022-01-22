package minigamble;

public class Flecha {
	private int y = -128;
	private String dir;
	
	/** Constructor de la clase flecha
	 * @param dir establece la direccion de la flecha: izq, dch, arr, abj
	 */ 
	public Flecha(String dir) {
		this.y = -128;
		this.dir = dir;
	}

	
	/**
	 * @return devuelve la coordenada Y de la flecha
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y cambia la coordenada Y de la flecha
	 */
	public void setY(int y) {
		this.y = y;
	}
	

	/**
	 * @return devuelve la direccion de la flecha
	 */
	public String getDir() {
		return dir;
	}
	
	//METODO MOVE
	
	/**
	 * @param n cambia la coordenada Y n unidades respecto a la posicion anterior de la flecha
	 */
	public void move(int n) {
		this.setY(this.getY() + n);
	}


}
