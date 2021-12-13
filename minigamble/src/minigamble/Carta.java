package minigamble;

public class Carta {
	
	private String id;
	private boolean arriba;
	private boolean presionada;
	
	public Carta(String id, boolean arriba, boolean presionada) {
		this.id = id;
		this.arriba = arriba;
		this.presionada = presionada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPresionada() {
		return presionada;
	}

	public void setPresionada(boolean presionada) {
		this.presionada = presionada;
	}

	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

}