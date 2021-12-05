package minigamble;

public class Carta {
	
	private String id;
	private boolean arriba;
	
	public Carta(String id, boolean arriba) {
		this.id = id;
		this.arriba = arriba;
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

}