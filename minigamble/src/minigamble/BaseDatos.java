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
				
				sent = "DROP TABLE IF EXISTS partida";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE partida (id INTEGER PRIMARY KEY AUTOINCREMENT ,nombre varchar(8));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game1";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game1 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int);";
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
	
	public static int insertarPartida( String jugador) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into partida (nombre) values ('" + jugador + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return -1;  // Error en inserción
			ResultSet rrss = statement.getGeneratedKeys();  // Genera un resultset ficticio con las claves generadas del último comando
			rrss.next();  // Avanza a la única fila 
			int pk = rrss.getInt( 1 );  // Coge la única columna (la primary key autogenerada)
			return(pk);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return -1;
		}
	}
	
	public static boolean insertarGame1( int idPartida, int puntuacion) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game1 (idPartida, puntuacion) values (" + idPartida + ", " + puntuacion + ");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static boolean existeNombre(String n) {
	
		try (Statement statement = conexion.createStatement()) {
			ArrayList<String> ret = new ArrayList<>();
			String sent = "select nombre from jugador;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				String nombre = rs.getString("nombre");
				ret.add( nombre);
			}
			for(String nombre : ret) {
				if(nombre.equals(n)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return true;
		}
	}
	
	public static boolean comparaContrasena(String n, String p) {
		
		try (Statement statement = conexion.createStatement()) {
			ArrayList<ArrayList<String>> ret = new ArrayList<>();
			String sent = "select * from jugador;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				ArrayList<String> j = new ArrayList<String>();
				String nombre = rs.getString("nombre");
				String contrasena = rs.getString("password");
				j.add(nombre);
				j.add(contrasena);
//				System.out.println(nombre);
//				System.out.println(contrasena);
				ret.add(j);
			}
			for(ArrayList<String> jugador : ret) {
				if(jugador.get(0).equals(n)) {
					if(jugador.get(1).equals(p)) {
						return true;
					}
					return false;
				}
			}
			return false;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return true;
		}
	}
	
	
	
	
	
}
