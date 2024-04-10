package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorDB {
	
	public static void crearTablaViaje() {
        String sql = "CREATE TABLE IF NOT EXISTS Viaje (\n"
        		+ "        	    codigo_viaje TEXT PRIMARY KEY,\n"
        		+ "        	    fecha INTEGER,\n"
        		+ "        	    codigo_origen TEXT,\n"
        		+ "        	    codigo_destino TEXT,\n"
        		+ "        	    codigo_comp TEXT,\n"
        		+ "        	    precioBase REAL,\n"
        		+ "        	    FOREIGN KEY (codigo_origen) REFERENCES Estacion(codigo_origen),\n"
        		+ "        	    FOREIGN KEY (codigo_destino) REFERENCES Estacion(codigo_destino),\n"
        		+ "        	    FOREIGN KEY (codigo_comp) REFERENCES Company(codigo_comp)\n"
        		+ "        	);";
        


        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static void insertarViaje(String codigo_viaje, Long fecha, String codigo_origen, String codigo_destino, String codigo_comp, double precioBase) {
	    if (!existeViaje(codigo_viaje)) {
	        String sql = "INSERT INTO Viaje(codigo_viaje, fecha, codigo_origen, codigo_destino, codigo_comp, precioBase) VALUES(?,?,?,?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_viaje);
	            pstmt.setLong(2, fecha);
	            pstmt.setString(3, codigo_origen);
	            pstmt.setString(4, codigo_destino);
	            pstmt.setString(5, codigo_comp);
	            pstmt.setDouble(6, precioBase);
	            pstmt.executeUpdate();
	            System.out.println("Viaje insertado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("El viaje ya existe. No se puede insertar.");
	    }
	}
	
	
	 public static void eliminarViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "DELETE FROM Viaje WHERE codigo_viaje = ? ";
			 
			 try (Connection conn = ConexionDB.obtenerConexion();
		            PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            
		            pstmt.executeUpdate();
		            System.out.println("Viaje eliminado correctamente.");
		        } catch (Exception e) {
				e.printStackTrace();
			}
		 } else {
			 System.out.println("No se puede eliminar un viaje inexistente");
		 }
	 }

	 
	 public static void modificarViaje(String codigo_viaje, Long fecha, String codigo_origen, String codigo_destino, String codigo_comp, double precioBase) {
		 if (existeViaje(codigo_viaje)) {
		        String sql = "UPDATE Viaje SET codigo_viaje = ?, fecha = ?, codigo_origen = ?, codigo_destino = ?, codigo_comp = ?, precioBase = ? WHERE codigo_viaje = ?";
		        
		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codigo_viaje);
		            pstmt.setLong(2, fecha);
		            pstmt.setString(3, codigo_origen);
		            pstmt.setString(4, codigo_destino);
		            pstmt.setString(5, codigo_comp);
		            pstmt.setDouble(6, precioBase);
		            pstmt.executeUpdate();
		            System.out.println("Viaje modificado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("No se puede modificar un viaje inexistente.");
		    }
	 }  
	 
	 
	 public static void mostrarViajes() {
	        String sql = "SELECT * FROM Viaje";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("codigo del viaje: " + rs.getString("codigo_viaje") + "\tFecha: " + rs.getString("fecha"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 
	 
	 private static boolean existeViaje(String codigo_viaje) {
        String sql = "SELECT * FROM Viaje WHERE codigo_viaje = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo_viaje);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	 
	    public static void crearTablaAsiento() {
	        String sql = "CREATE TABLE IF NOT EXISTS Asiento (\n"
	                + "        	    asiento_id INTEGER PRIMARY KEY,\n"
	                + "        	    nombre_pasajero TEXT,\n"
	                + "        	    codigo_viaje TEXT,\n"
	                + "        	    FOREIGN KEY (codigo_viaje) REFERENCES Viaje(codigo_viaje)\n"
	                + "        	)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void insertarAsiento(int asiento_id, String nombre_pasajero, String codigo_viaje) {
	        String sql = "INSERT INTO Asiento(asiento_id, nombre_pasajero, codigo_viaje) VALUES(?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, asiento_id);
	            pstmt.setString(2, nombre_pasajero);
	            pstmt.setString(3, codigo_viaje);
	            pstmt.executeUpdate();
	            System.out.println("Asiento insertado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void modificarAsiento(int asiento_id, String nombre_pasajero, String codigo_viaje) {
	        String sql = "UPDATE Asiento SET nombre_pasajero = ?, codigo_viaje = ? WHERE asiento_id = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, nombre_pasajero);
	            pstmt.setString(2, codigo_viaje);
	            pstmt.setInt(3, asiento_id);
	            pstmt.executeUpdate();
	            System.out.println("Asiento modificado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void eliminarAsiento(int asiento_id) {
	        String sql = "DELETE FROM Asiento WHERE asiento_id = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, asiento_id);
	            pstmt.executeUpdate();
	            System.out.println("Asiento eliminado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    

	    public static void crearTablaCompany() {
	        String sql = "CREATE TABLE IF NOT EXISTS Company (\n"
	                + "        	    codigo_comp TEXT PRIMARY KEY,\n"
	                + "        	    nombre_comp TEXT,\n"
	                + "        	    codigo_medio TEXT,\n"
	                + "        	    FOREIGN KEY (codigo_medio) REFERENCES Medio(codigo_medio)\n"
	                + "        	)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void insertarCompany(String codigo_comp, String nombre_comp, String codigo_medio) {
	        String sql = "INSERT INTO Company(codigo_comp, nombre_comp, codigo_medio) VALUES(?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_comp);
	            pstmt.setString(2, nombre_comp);
	            pstmt.setString(3, codigo_medio);
	            pstmt.executeUpdate();
	            System.out.println("Company insertada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void modificarCompany(String codigo_comp, String nombre_comp, String codigo_medio) {
	        String sql = "UPDATE Company SET nombre_comp = ?, codigo_medio = ? WHERE codigo_comp = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, nombre_comp);
	            pstmt.setString(2, codigo_medio);
	            pstmt.setString(3, codigo_comp);
	            pstmt.executeUpdate();
	            System.out.println("Company modificada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void eliminarCompany(String codigo_comp) {
	        String sql = "DELETE FROM Company WHERE codigo_comp = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_comp);
	            pstmt.executeUpdate();
	            System.out.println("Company eliminada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void crearTablaMedio() {
	        String sql = "CREATE TABLE IF NOT EXISTS Medio (\n"
	                + "        	    codigo_medio TEXT PRIMARY KEY,\n"
	                + "        	    impuesto INTEGER,\n"
	                + "        	    codigo_viaje TEXT,\n"
	                + "        	    FOREIGN KEY (codigo_viaje) REFERENCES Viaje(codigo_viaje)\n"
	                + "        	)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void insertarMedio(String codigo_medio, Integer impuesto, String codigo_viaje) {
	        String sql = "INSERT INTO Medio(codigo_medio, impuesto, codigo_viaje) VALUES(?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_medio);
	            pstmt.setInt(2, impuesto);
	            pstmt.setString(3, codigo_viaje);
	            pstmt.executeUpdate();
	            System.out.println("Medio insertado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void modificarMedio(String codigo_medio, int impuesto, String codigo_viaje) {
	        String sql = "UPDATE Medio SET impuesto = ?, codigo_viaje = ? WHERE codigo_medio = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, impuesto);
	            pstmt.setString(2, codigo_viaje);
	            pstmt.setString(3, codigo_medio);
	            pstmt.executeUpdate();
	            System.out.println("Medio modificado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void eliminarMedio(String codigo_medio) {
	        String sql = "DELETE FROM Medio WHERE codigo_medio = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_medio);
	            pstmt.executeUpdate();
	            System.out.println("Medio eliminado correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	    public static void insertarEstacion(String codigo_estacion, String nombre_estacion, String ciudad_estacion, String pais_estacion) {
	        String sql = "INSERT INTO Estacion(codigo_estacion, nombre_estacion, ciudad_estacion, pais_estacion) VALUES(?,?,?,?)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_estacion);
	            pstmt.setString(2, nombre_estacion);
	            pstmt.setString(3, ciudad_estacion);
	            pstmt.setString(4, pais_estacion);
	            pstmt.executeUpdate();
	            System.out.println("Estación insertada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void modificarEstacion(String codigo_estacion, String nombre_estacion, String ciudad_estacion, String pais_estacion) {
	        String sql = "UPDATE Estacion SET nombre_estacion = ?, ciudad_estacion = ?, pais_estacion = ? WHERE codigo_estacion = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, nombre_estacion);
	            pstmt.setString(2, ciudad_estacion);
	            pstmt.setString(3, pais_estacion);
	            pstmt.setString(4, codigo_estacion);
	            pstmt.executeUpdate();
	            System.out.println("Estación modificada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void eliminarEstacion(String codigo_estacion) {
	        String sql = "DELETE FROM Estacion WHERE codigo_estacion = ?";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_estacion);
	            pstmt.executeUpdate();
	            System.out.println("Estación eliminada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }  
	    }
	 
	
	 


	
	
	
}

