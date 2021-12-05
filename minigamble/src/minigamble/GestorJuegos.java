package minigamble;

public class GestorJuegos {
	
	public static int puntuacion = 0;
	public static int vidas = 3;
	
	
	
	public static int getPuntuacion() {
		return puntuacion;
	}
	public static void setPuntuacion(int puntuacion) {
		GestorJuegos.puntuacion = puntuacion;
	}
	
	public static int getVidas() {
		return vidas;
	}
	public static void setVidas(int vidas) {
		GestorJuegos.vidas = vidas;
	}
	
	
	public static void GestorJuegos() {
		setPuntuacion(0);
		setVidas(3);
	}
	
	public static void GestorJuegos(int puntuacion, int vidas, boolean completado) {
		if(vidas != 0) {
			GestorJuegos();
		}
		
	}
	
}
