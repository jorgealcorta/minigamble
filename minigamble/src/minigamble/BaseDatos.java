package minigamble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
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
				sent = "CREATE TABLE game1 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, primera_carta varchar(20), tiempo_primera_carta bigint, tiempo_total bigint, superado varchar(5) , dificultad int );";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game2";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game2 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, tiempo_total bigint);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate(sent);
			
				sent = "DROP TABLE IF EXISTS game3";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game3 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, tiempo_total bigint, laberinto int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game4";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game4 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, tiempo_total bigint);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
			
				sent = "DROP TABLE IF EXISTS game5";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game5 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, premio int, symbol varchar(10));";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game6";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game6 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, fallos int, tiempo_total bigint);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				sent = "DROP TABLE IF EXISTS game7";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				sent = "CREATE TABLE game7 (id INTEGER PRIMARY KEY AUTOINCREMENT ,idPartida int, puntuacion int, tiempo_total bigint , fristX int, firstY int);";
				logger.log( Level.INFO, "Statement: " + sent );
				statement.executeUpdate( sent );
				
				try {
					Scanner scanner = new Scanner( BaseDatos.class.getResourceAsStream("inicio/game1_inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into game1 (id, idPartida, puntuacion, fallos, primera_carta, tiempo_primera_carta, tiempo_total, superado, dificultad) values (" + datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3] + ", '" + datos[4] + "', " + datos[5] + ", " + datos[6] + ", 'true', 1);";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("inicio/partida_inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into partida (id, fecha, nombre) values (" + datos[0] + ", " + datos[1] + ", '" + datos[2] + "' );";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
					scanner = new Scanner( BaseDatos.class.getResourceAsStream("inicio/jugadores_inic.txt") );
					while (scanner.hasNextLine()) {
						String linea = scanner.nextLine();
						String[] datos = linea.split( "\t" );
						sent = "insert into jugador (nombre, password) values ('" + datos[0] + "', '" + Hash.md5(datos[1]) + "');";
						logger.log( Level.INFO, "Statement: " + sent );
						statement.executeUpdate( sent );
					}
					scanner.close();
				} catch(Exception e) {
					logger.log( Level.SEVERE, "Excepción", e );
				}
			
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
	 * @param password Contraseña del jugador cifrada mediante {@link minigamble.Hash#md5(String)}
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
	
	/** Inserta un Game1 en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param idPartida ID de la partida que se está jugando
	 * @param puntuacion Puntuacion obtenida en Game1
	 * @param fallos Numero de fallos cometidos
	 * @param primeraCarta Primera carta levantada
	 * @param tiempoPrimCar Tiempo hasta levantar la primera carta
	 * @param tiempoTot Tiempo total en terminar el juego
	 * @return devuelve True si se hace correctamente
	 */
	public static boolean insertarGame1( int idPartida, int puntuacion, int fallos, String primeraCarta, long tiempoPrimCar, long tiempoTot, String superado, int dificultad) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game1 (idPartida, puntuacion, fallos, primera_carta, tiempo_primera_carta, tiempo_total, superado, dificultad) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", '"+ primeraCarta +"', " + tiempoPrimCar + ", " + tiempoTot + ", '" + superado + "', " + dificultad +" );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static boolean insertarGame2( int idPartida, int puntuacion, int fallos, long tiempoTot) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game2 (idPartida, puntuacion, fallos, tiempo_total ) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", " + tiempoTot + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta un Game3 en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param idPartida ID de la partida que se esta jugando
	 * @param puntuacion Puntuacion obtenida en Game3
	 * @param fallos Numero de fallos cometidos
	 * @param tiempoTot Tiempo total en terminar el juego
	 * @return devuelve True si se hace correctamente
	 */
	
	public static boolean insertarGame3( int idPartida, int puntuacion, int fallos, long tiempoTot, int laberinto) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game3 (idPartida, puntuacion, fallos, tiempo_total, laberinto) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", " + tiempoTot +", "+ laberinto + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static boolean insertarGame4( int idPartida, int puntuacion, int fallos, long tiempoTot) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game4 (idPartida, puntuacion, fallos, tiempo_total ) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", " + tiempoTot + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta un Game5 en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param idPartida ID de la partida que se esta jugando
	 * @param puntuacion Puntuacion obtenida en Game5
	 * @param premio Adquiere valor 1 si ha conseguido hacer 3 en linea, 0 en caso de que no
	 * @param symbol String que indica qu� simbolo ha elegido el primero.
	 * @return devuelve True si se hace correctamente
	 */
	
	public static boolean insertarGame5( int idPartida, int puntuacion, int premio, String symbol) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game5 (idPartida, puntuacion, premio, symbol ) values (" + idPartida + ", " + puntuacion + ", " + premio + " , " + symbol + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static boolean insertarGame6( int idPartida, int puntuacion, int fallos, long tiempoTot) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game6 (idPartida, puntuacion, fallos, tiempo_total ) values (" + idPartida + ", " + puntuacion + ", "+ fallos + ", " + tiempoTot + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	/** Inserta un Game7 en la base de datos previamente abierta con {@link #abrirConexion(String, boolean)}
	 * @param idPartida ID de la partida que se esta jugando
	 * @param puntuacion Puntuacion obtenida en Game3
	 * @param tiempoTot Tiempo total en terminar el juego
	 * @param firstX coordenada x del primer movimiento realizado
	 * @param firstY coordenada y del primer movimiento realizado
	 * @return devuelve True si se hace correctamente
	 */
	
	public static boolean insertarGame7( int idPartida, int puntuacion, long tiempoTot, int firstX, int firstY) {
		try (Statement statement = conexion.createStatement()) {
			String sent = "insert into game6 (idPartida, puntuacion, fallos, tiempo_total ) values (" + idPartida + ", " + puntuacion + ", "+ tiempoTot + ", " + firstX + ", " + firstY + " );";											
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = statement.executeUpdate( sent );
			if (insertados!=1) return false;  // Error en insercion
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
