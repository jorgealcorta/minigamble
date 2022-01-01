package minigamble;

public class VidaDisplay {
	
	boolean vida = true;
	boolean frente = true;
	boolean display = false;
	
	public VidaDisplay(boolean vida, boolean frente, boolean display) {
		this.vida = vida;
		this.frente = frente;
		this.display = display;
	}

	public boolean isVida() {
		return vida;
	}

	public void setVida(boolean vida) {
		this.vida = vida;
	}

	public boolean isFrente() {
		return frente;
	}

	public void setFrente(boolean frente) {
		this.frente = frente;
	}
	
	
	
}
