package minigamble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos (se borran sus contenidos si los tuviera y se crean datos por defecto)
	 * @return	true si la conexión ha sido correcta, false en caso contrario
	 */
	public static boolean abrirConexion( String nombreBD, boolean reiniciaBD ) {
		try {
			logger.log( Level.INFO, "Carga de librería org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			logger.log( Level.INFO, "Abriendo conexión con " + nombreBD );
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			if (reiniciaBD) {
				Statement statement = conexion.createStatement();
				String sent = "DROP TABLE IF EXISTS jugador";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE jugador (nombre varchar(8) PRIMARY KEY, password varchar(33));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}	
	
	/** Cierra la conexión abierta de base de datos ({@link #abrirConexion(String)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	public static boolean insertarJugador( String nombre, String password) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into jugador (nombre, password) values ('" + nombre + "','" + password + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			// Búsqueda de la fila insertada - para ello hay que recuperar la clave autogenerada. Hay varias maneras, vemos dos diferentes:
			// Se hace utilizando método del propio objeto statement
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static ArrayList<String> getNombres() {
	
		try (Statement statement = conexion.createStatement()) {
			ArrayList<String> ret = new ArrayList<>();
			String sent = "select nombre from jugador;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				String nombre = rs.getString("nombre");
				ret.add( nombre);
			}
			
			return ret;
			
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return null;
		}
	}
	
}
