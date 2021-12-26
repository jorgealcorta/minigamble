package minigamble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la gestión de la base de datos de los juegos y usuarios
 */
public class BaseDatos {

	private static Connection conexion;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	/** Abre conexión con la base de datos
	 * @param nombreBD	Nombre del fichero de base de datos (minigamble)
	 * @param reiniciaBD	true si se quiere reiniciar la base de datos solo se borran los datos almazenados
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
				sent = "CREATE TABLE partida (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha bigint, nombre varchar(8));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game1";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game1 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, primera_carta varchar(20), tiempo_primera_carta bigint, tiempo_total bigint);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
			}
			return true;
		} catch(Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}	
	
	/** Cierra la conexión abierta de base de datos ({@link #abrirConexion(String, boolean)})
	 */
	public static void cerrarConexion() {
		try {
			logger.log( Level.INFO, "Cerrando conexión" );
			conexion.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	
	/**	Inserta un Jugador en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param nombre Nombre del jugador que se quiere insertar
	 * @param password Contraseña del jugador cifrada mediante {@link minigamble.Hash#getHash(String, String)}
	 * @return Devuelve True si se inserta correctamente
	 */
	public static boolean insertarJugador( String nombre, String password) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into jugador (nombre, password) values ('" + nombre + "','" + password + "');";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta una Partida en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param jugador Nombre del jugador que juega la partida
	 * @return Devuelve True si se inserta correctamente
	 */
	public static int insertarPartida( String jugador) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into partida (nombre, fecha) values ('" + jugador + "', " + System.currentTimeMillis() +");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return -1;
			ResultSet rrss = statement.getGeneratedKeys();
			rrss.next();
			int pk = rrss.getInt( 1 );
			return(pk);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return -1;
		}
	}
	
	/** Inserta una Game1 en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param idPartida ID de la partida que se está jugando
	 * @param puntuacion Puntuacion obtenida en Game1
	 * @param fallos Numero de fallos cometidos
	 * @param primeraCarta Primera carta levantada
	 * @param tiempoPrimCar Tiempo hasta levantar la primera carta
	 * @param tiempoTot Tiempo total en terminar el juego
	 * @return devuelve True si se hace correctamente
	 */
	public static boolean insertarGame1( int idPartida, int puntuacion, int fallos, String primeraCarta, long tiempoPrimCar, long tiempoTot) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game1 (idPartida, puntuacion, fallos, primera_carta, tiempo_primera_carta, tiempo_total ) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", '"+ primeraCarta +"', " + tiempoPrimCar + ", " + tiempoTot + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en inserción
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Comprueba si existe el nombre de un jugador en la base de datos
	 * @param n Nombre del jugador a encontrar
	 * @return Devuelve True si se encuentra y False si no
	 */
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
	
	/** Comprueba que dos contraseñas sean iguales para un jugador
	 * @param n Nombre del jugador 
	 * @param p Contraseña introducida (la que se va a comparar con la almacenada)
	 * @return True si son iguales y False si no lo son
	 */
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
