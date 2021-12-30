package minigamble;

public class Flecha {
	int y = -128;
	String dir;
	
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

	@Override
	public String toString() {
		return "Flecha [y=" + y + ", dir=" + dir + "]";
	}
	
	public void move(int n) {
		this.setY(this.getY() + n);
	}

	public String getDir() {
		return dir;
	}

}
