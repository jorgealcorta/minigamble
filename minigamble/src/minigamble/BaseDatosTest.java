package minigamble;

import static org.junit.Assert.*;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseDatosTest {
	
	@Before
	public void setUp() throws Exception {
		BaseDatos.abrirConexion( "test.bd", true );
	}

	@After
	public void tearDown() throws Exception {
		BaseDatos.cerrarConexion();
	}
	
	@Test
	public void testGetPartidas() {
		HashMap<Integer, String> lP = BaseDatos.getPartidas();
		assertEquals( 9, lP.size() );
		int antId = -1;
		for (Integer id : lP.keySet()) {
			assertTrue( id > antId );
			antId = id;
		}
	}
	
	

}
