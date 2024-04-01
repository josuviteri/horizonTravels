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
	


    
	 
	
	 


	
	
	
}

