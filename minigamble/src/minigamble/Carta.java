package minigamble;

/**
 * Clase encargada de las cartas
 *
 */
public class Carta {
	
	private String id;
	private boolean arriba;
	private boolean presionada;
	
	/**
	 * Constructor de las cartas
	 * @param id Identificador que se le da a la carta
	 * @param arriba Boolean indicando si una carta está bocaarriba o no
	 * @param presionada Boolean que indica si una carta está presionada o no
	 */
	public Carta(String id, boolean arriba, boolean presionada) {
		this.id = id;
		this.arriba = arriba;
		this.presionada = presionada;
	}

	/**
	 * @return devuelve en ID de una carta
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Cambia el id de una carta
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Devuelve si una carta está presionada o no
	 */
	public boolean isPresionada() {
		return presionada;
	}

	/**
	 * @param presionada Cambia el estado de presión de una carta
	 */
	public void setPresionada(boolean presionada) {
		this.presionada = presionada;
	}

	/**
	 * @return Devuelve si una carta está levantada
	 */
	public boolean isArriba() {
		return arriba;
	}

	/**
	 * @param arriba Cambia el estado de volteo de una carta
	 */
	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

}