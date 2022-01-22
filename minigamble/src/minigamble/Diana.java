package minigamble;

public class Diana {
	
	int x; //Coordenada x
	int y; //Coordenada y
	int size; //Escala
	boolean rota = false;
	
	/**Constructor de Diana
	 * @param x coordenada x en la que se localizara la diana
	 * @param y coordenada y en la que se localizara la diana
	 * @param size tamanyo con el que se dibujara la diana
	 * @param rota booleano que definira si la diana esta rota o no
	 */
	public Diana(int x, int y, int size, boolean rota) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.rota = rota;
	}

	/**
	 * @return devuelve la coordenada X de una diana
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x cambia la coordenada X de una diana
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return devuelve la coordenada Y de una diana
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y cambia la coordenada y de una diana
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return devuelve el tamanyo de una diana
	 */
	public double getSize() {
		return size;
	}
	
	/**
	 * @param size cambia el tamanyo de una diana
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
	/**
	 * @return devuelve si una diana esta rota
	 */
	public boolean isRota() {
		return rota;
	}

	
	/**
	 * @param rota cambia si una diana esta rota o no
	 */
	public void setRota(boolean rota) {
		this.rota = rota;
	}

	/**
	 * Metodo toString para visualizar datos de una diana
	 */
	@Override
	public String toString() {
		return "Diana [x=" + x + ", y=" + y + ", size=" + size + ", rota=" + rota + "]";
	}

	
	
}