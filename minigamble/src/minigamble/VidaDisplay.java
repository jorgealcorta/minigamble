package minigamble;

public class VidaDisplay {
	
	boolean vida = true;
	boolean frente = true;
	boolean display = false;
	
	/**
	 * @param vida booleano que gestiona si una vida sigue activa o no
	 * @param frente booleano utilizado para gestionar la animacion de cada vida
	 * @param display booleano utilizado para mostrar la vida o no
	 */
	public VidaDisplay(boolean vida, boolean frente, boolean display) {
		this.vida = vida;
		this.frente = frente;
		this.display = display;
	}

	/**
	 * @return devuelve si la vida esta activa
	 */
	public boolean isVida() {
		return vida;
	}

	/**
	 * @param vida cambia el estado de una vida a activa o inactiva
	 */
	public void setVida(boolean vida) {
		this.vida = vida;
	}

	/**
	 * @return devuelve si una vida esta en el primer estado de la animacion o no
	 */
	public boolean isFrente() {
		return frente;
	}

	/**
	 * @param frente cambia el estado de la animacion de la vida
	 */
	public void setFrente(boolean frente) {
		this.frente = frente;
	}
	
	
	
}
