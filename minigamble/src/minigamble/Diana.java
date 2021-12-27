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

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean isRota() {
		return rota;
	}

	public void setRota(boolean rota) {
		this.rota = rota;
	}

	@Override
	public String toString() {
		return "Diana [x=" + x + ", y=" + y + ", size=" + size + ", rota=" + rota + "]";
	}

	
	
}