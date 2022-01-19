package minigamble;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
	
	
}
