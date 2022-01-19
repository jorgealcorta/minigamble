package minigamble;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDatosTest {
	
	@Before
	public void setUp() throws Exception {
		BaseDatos.abrirConexion( "test.db", true );
	}

	@After
	public void tearDown() throws Exception {
		BaseDatos.cerrarConexion();
	}
	
	@Test
	public void testGetPartidas() {
		HashMap<Integer, String> lP = BaseDatos.getPartidas();
		assertEquals( 9, lP.size() );
		int antId = 0;
		for (Integer id : lP.keySet()) {
			assertTrue( id > antId );
			antId = id;
		}
	}
	
	@Test
	public void testInsertarPartida() {
		HashMap<Integer, String> lP = BaseDatos.getPartidas();
		int antId = 0;
		for (Integer id : lP.keySet()) {
			assertTrue( id > antId );
			antId = id;
		}
		assertTrue( BaseDatos.insertarPartida("Juan") >= antId);
	}

	@Test
	public void testExisteNombre(){
		String nombreQueExiste = "admin";
		String nombreQueNoExiste = "juan";
		
		assertTrue(BaseDatos.existeNombre(nombreQueExiste));
		
		assertFalse(BaseDatos.existeNombre(nombreQueNoExiste));
		// El nombre "juan no se encuentra incluido en el .txt que rellena la BBDD al pricipio"
	}
	
	@Test
	public void testCompararContrasena() {
		
		String nombreCorrecto = "admin";
		String nombreIncorrecto = "ADMIN";
		
		String contrasenaCorrecta = "admin";
		String contrasenaIncorrecta = "ADMIN";
		
		assertTrue(BaseDatos.comparaContrasena(nombreCorrecto, contrasenaCorrecta));
		
		assertFalse(BaseDatos.comparaContrasena(nombreIncorrecto, contrasenaCorrecta));
		
		assertFalse(BaseDatos.comparaContrasena(nombreCorrecto, contrasenaIncorrecta));
		
		assertFalse(BaseDatos.comparaContrasena(nombreIncorrecto, contrasenaIncorrecta));
		
		
		
	}
	
	
	@Test
	public void testGetJugadores() {
		
		HashMap<String, String> lJ = BaseDatos.getJugadores();
		
		assertEquals( 4, lJ.size());
		
		for (String nombre : lJ.keySet()) {
			String contrasena = lJ.get(nombre);
			
			assertTrue(contrasena.length() == 32);
		}
		
	}
	
	
	@Test
	public void testInsertarJugador() {
		HashMap<String, String> lJ = BaseDatos.getJugadores();
		int antSize = lJ.size();

		assertTrue( BaseDatos.insertarJugador("prueba", "prueba123"));
		
		lJ = BaseDatos.getJugadores();
		
		assertTrue(lJ.size() > antSize);
		
	}
	
	
}
