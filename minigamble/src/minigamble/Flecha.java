package minigamble;

public class Flecha {
	private int y = -128;
	private String dir;
	
	public Flecha(String dir) {
		this.y = -128;
		this.dir = dir;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public String getDir() {
		return dir;
	}
	
	//METODO MOVE
	
	public void move(int n) {
		this.setY(this.getY() + n);
	}


}
