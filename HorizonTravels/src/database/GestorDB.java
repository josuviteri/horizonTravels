package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objetos.*;
import src.Metodos;

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
	    if (existeViaje(codigo_viaje)) {
	        String sql = "DELETE FROM Viaje WHERE codigo_viaje = ? ";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, codigo_viaje); // Establecer el valor del parámetro
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
		        String sql = "UPDATE Viaje SET fecha = ?, codigo_origen = ?, codigo_destino = ?, codigo_comp = ?, precioBase = ? WHERE codigo_viaje = ?";
		        
		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setLong(1, fecha);
		            System.out.println(fecha);
		            pstmt.setString(2, codigo_origen);
		            System.out.println(codigo_origen);
		            pstmt.setString(3, codigo_destino);
		            System.out.println(codigo_destino);
		            pstmt.setString(4, codigo_comp);
		            System.out.println(codigo_comp);
		            pstmt.setDouble(5, precioBase);
		            System.out.println(precioBase);
		            pstmt.executeUpdate();
		            System.out.println("fin");
		            System.out.println("Viaje modificado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        System.out.println("No se puede modificar un viaje inexistente.");
		    }
	 }  

	 
	 
	 public static void mostrarTodosViajes() {
	        String sql = "SELECT * FROM Viaje";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("\n\nCodigo del viaje: " + rs.getString("codigo_viaje") + "\nFecha: " + Metodos.LongAFecha(rs.getLong("fecha")) + "\nCodigo estacion origen: " + rs.getString("codigo_origen") + "\nCodigo estacion destino: " + rs.getString("codigo_destino") + "\nCodigo de la company: " + rs.getString("codigo_comp") + "\nPrecio del viaje sin impuestos: " + rs.getString("precioBase"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void mostrarDatosViaje(String codigo_viaje) {
		    if(existeViaje(codigo_viaje)) {
		    	String sql = "SELECT * FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        ResultSet rs = pstmt.executeQuery();
			        
			        if (rs.next()) {
			            System.out.println("Datos del viaje con código: " + codigo_viaje);
			            System.out.println("Código de viaje: " + rs.getString("codigo_viaje"));
			            System.out.println("Fecha: " + Metodos.LongAFecha(rs.getLong("fecha")));
			            System.out.println("Código de origen: " + rs.getString("codigo_origen"));
			            System.out.println("Código de destino: " + rs.getString("codigo_destino"));
			            System.out.println("Código de compañía: " + rs.getString("codigo_comp"));
			            System.out.println("Precio base: " + rs.getDouble("precioBase"));
			        } else {
			            System.out.println("No se encontraron datos para el viaje con codigo: " + codigo_viaje);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
		    }else {
		    	System.out.println("El viaje no existe. No se puede mostrar");
		    }
		}

	    
	 
	 
	 public static boolean existeViaje(String codigo_viaje) {
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
	 
	 public static String recuperarCodigoOrigenDeViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "SELECT codigo_origen FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getString("codigo_origen");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_viaje);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }
	 
	 public static String recuperarCodigoDestinoDeViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "SELECT codigo_destino FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getString("codigo_destino");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_viaje);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }
	 
	 
	 public static Long recuperarFechaDeViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "SELECT fecha FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getLong("fecha");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_viaje);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }
	 
	 
	 public static Integer recuperarPrecioDeViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "SELECT precioBase FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getInt("precioBase");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_viaje);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }
	 
	 
	    public static void crearTablaAsiento() {
	        String sql = "CREATE TABLE IF NOT EXISTS Asiento (\n"
	                + "        	    asiento_id INTEGER,\n"
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
	        if(!existeAsiento(asiento_id, codigo_viaje)) {
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
	        }else {
		        System.out.println("El asiento ya existe. No se puede insertar.");
		    }
	    }

	    public static void modificarAsiento(int asiento_id, String nombre_pasajero, String codigo_viaje) {
	        if(existeAsiento(asiento_id, codigo_viaje)) {
	        	String sql = "UPDATE Asiento SET nombre_pasajero = ?, codigo_viaje = ? WHERE asiento_id = ?  AND codigo_viaje = ?";

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
	        }else {
		        System.out.println("El asiento no existe. No se puede modificar.");
	        }
	    }

	    public static void eliminarAsiento(int asiento_id, String codigo_viaje) {
	        if(existeAsiento(asiento_id, codigo_viaje)) {
	        	String sql = "DELETE FROM Asiento WHERE asiento_id = ? AND codigo_viaje = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setInt(1, asiento_id);
		            pstmt.setString(2, codigo_viaje);
		            pstmt.executeUpdate();
		            System.out.println("Asiento eliminado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	        }else {
		        System.out.println("El asiento no existe. No se puede eliminar.");
	        }
	    }
	    
		 public static void mostrarAsientos() {
		        String sql = "SELECT * FROM Asiento";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql);
		             ResultSet rs = pstmt.executeQuery()) {
		            while (rs.next()) {
		                System.out.println("Codigo del asiento: " + rs.getString("id_asiento"));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		 
		 
		 public static boolean existeAsiento(Integer asiento_id, String codigo_viaje) {
			    String sql = "SELECT * FROM Asiento WHERE asiento_id = ? AND codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setInt(1, asiento_id);
			        pstmt.setString(2, codigo_viaje);
			        ResultSet rs = pstmt.executeQuery();
			        return rs.next();
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}
	    
		 public static Asiento recuperarAsiento(Integer asiento_id, String codigo_viaje, Viaje viaje) {
			    if (existeAsiento(asiento_id, codigo_viaje)) {
			        String sql = "SELECT * FROM Asiento WHERE asiento_id = ? AND codigo_viaje = ?";
			        try (Connection conn = ConexionDB.obtenerConexion();
			             PreparedStatement pstmt = conn.prepareStatement(sql)) {
			            pstmt.setInt(1, asiento_id);
			            pstmt.setString(2, codigo_viaje);
			            try (ResultSet rs = pstmt.executeQuery()) {
			                if (rs.next()) {
			                    String nombre_pasajero = rs.getString("nombre_pasajero");
			                    if (nombre_pasajero != null && !nombre_pasajero.isEmpty()) {
			                        return new Asiento(viaje, nombre_pasajero);
			                    } else {
			                        return null;
			                    }
			                } else {
			                    System.out.println("No se encontró ningún asiento con el id: " + asiento_id + ", del viaje: " + codigo_viaje);
			                    return null;
			                }
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			            return null;
			        }
			    }
			    return null;
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
	        if(!existeCompany(codigo_comp)) {
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
	        }else {
		        System.out.println("La company ya existe. No se puede insertar.");
	        }
	    }

	    public static void modificarCompany(String codigo_comp, String nombre_comp, String codigo_medio) {
	        if(existeCompany(codigo_comp)) {
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
	        }else {
		        System.out.println("La company no existe. No se puede modificar.");
	        }
	    }

	    public static void eliminarCompany(String codigo_comp) {
	       if(existeCompany(codigo_comp)) {
	    	   String sql = "DELETE FROM Company WHERE codigo_comp = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codigo_comp);
		            pstmt.executeUpdate();
		            System.out.println("Company eliminada correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	       }else {
		        System.out.println("La company no existe. No se puede eliminar.");
	       }
	    }
	    
	    
	    
	    public static void mostrarCompanies() {
	        String sql = "SELECT * FROM Company";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("codigo de la company: " + rs.getString("codigo_comp") + "\tNombre: " + rs.getString("nombre_comp") + "\tCodigo del medio: " + rs.getString("codigo_medio"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 
	 
	 public static boolean existeCompany(String codigo_comp) {
		    String sql = "SELECT * FROM Company WHERE codigo_comp = ?";

		    try (Connection conn = ConexionDB.obtenerConexion();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, codigo_comp);
		        ResultSet rs = pstmt.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	 
	 public static Company recuperarCompany(String codigo_comp) {
		 if(existeCompany(codigo_comp)) {
			 String sql = "SELECT * FROM Company WHERE codigo_comp = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_comp);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                String nombre_comp = rs.getString("nombre_comp");
			                return new Company(codigo_comp, nombre_comp);
			            } else {
			                System.out.println("No se encontró ninguna compañía con el código: " + codigo_comp);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		return null;
		 
	 }
	    
	 public static String recuperarCodigoCompDeViaje(String codigo_viaje) {
		 if(existeViaje(codigo_viaje)) {
			 String sql = "SELECT codigo_comp FROM Viaje WHERE codigo_viaje = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_viaje);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getString("codigo_comp");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_viaje);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }
	    

	    public static void crearTablaMedio() {
	        String sql = "CREATE TABLE IF NOT EXISTS Medio (\n"
	                + "        	    codigo_medio TEXT PRIMARY KEY,\n"
	                + "        	    impuesto INTEGER,\n"
	                + "        	    codigo_viaje TEXT,\n"
	                + "        	    tipo_medio INTEGER,\n"
	                + "        	    FOREIGN KEY (codigo_viaje) REFERENCES Viaje(codigo_viaje)\n"
	                + "        	)";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void insertarMedio(String codigo_medio, Integer impuesto, String codigo_viaje, Integer tipo_medio) {
	       if(!existeMedio(codigo_medio)) {
	    	   String sql = "INSERT INTO Medio(codigo_medio, impuesto, codigo_viaje, tipo_medio) VALUES(?,?,?,?)";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codigo_medio);
		            pstmt.setInt(2, impuesto);
		            pstmt.setString(3, codigo_viaje);
		            pstmt.setInt(4, tipo_medio);
		            pstmt.executeUpdate();
		            System.out.println("Medio insertado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	       }else {
		        System.out.println("El medio ya existe. No se puede insertar.");
	       }
	    }

	    public static void modificarMedio(String codigo_medio, int impuesto, String codigo_viaje, Integer tipo_medio) {
	        if(existeMedio(codigo_medio)) {
	        	String sql = "UPDATE Medio SET impuesto = ?, codigo_viaje = ? WHERE codigo_medio = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setInt(1, impuesto);
		            pstmt.setString(2, codigo_viaje);
		            pstmt.setString(3, codigo_medio);
		            pstmt.setInt(4, tipo_medio);
		            pstmt.executeUpdate();
		            System.out.println("Medio modificado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	        }else {
		        System.out.println("El medio no existe. No se puede modificar.");
	        }
	    }

	    public static void eliminarMedio(String codigo_medio) {
	       if(existeMedio(codigo_medio)) {
	    	   String sql = "DELETE FROM Medio WHERE codigo_medio = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codigo_medio);
		            pstmt.executeUpdate();
		            System.out.println("Medio eliminado correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
	       }else {
	    	   System.out.println("El medio no existe. No se puede eliminar.");
	       }
	    }		       

	    
	    public static void mostrarMedios() {
	        String sql = "SELECT * FROM Medio";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Codigo del medio: " + rs.getString("codigo_medio") + "\tImpuesto: " + rs.getInt("impuesto") + "\tTipo: " + rs.getInt("tipo_medio"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 
	 
	 public static boolean existeMedio(String codigo_medio) {
		    String sql = "SELECT * FROM Medio WHERE codigo_medio = ?";

		    try (Connection conn = ConexionDB.obtenerConexion();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, codigo_medio);
		        ResultSet rs = pstmt.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	 public static Medio recuperarMedio(String codigo_medio) {
		 if(existeMedio(codigo_medio)) {
			 String sql = "SELECT * FROM Medio WHERE codigo_medio = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_medio);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                Integer impuesto = rs.getInt("impuesto");
			                Integer tipo= rs.getInt("tipo_medio");
			                if(tipo == 1) {
			                	return new VueloInter(impuesto, codigo_medio);
			                }else if(tipo == 2) {
			                	return new VueloNacional(impuesto, codigo_medio);
			                }else if(tipo == 3) {
			                	return new TrenInter(impuesto, codigo_medio);
			                }else if(tipo == 4) {
			                	return new TrenNacional(impuesto, codigo_medio);
			                }else if(tipo == 5) {
			                	return new BarcoInter(impuesto, codigo_medio);
			                }else if(tipo == 6) {
			                	return new BarcoNacional(impuesto, codigo_medio);
			                }
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }else {
			 System.out.println("El medio con el código " + codigo_medio + " no existe.");
			 return null;
		 }
		return null;
	 }
	 
	 
	 public static String recuperarCodigoMedioDeCompany(String codigo_comp) {
		 if(existeCompany(codigo_comp)) {
			 String sql = "SELECT codigo_medio FROM Company WHERE codigo_comp = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_comp);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                return rs.getString("codigo_medio");
			            } else {
			                System.out.println("No se encontró ningún viaje con el código: " + codigo_comp);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		 return null;
	 }


	    public static void insertarEstacion(String codigo_estacion, String nombre_estacion, String ciudad_estacion, String pais_estacion) {
	        if(!existeEstacion(codigo_estacion)) {
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
	        }else {
		        System.out.println("La estacion ya existe. No se puede insertar.");
	        }
	    }

	    public static void modificarEstacion(String codigo_estacion, String nombre_estacion, String ciudad_estacion, String pais_estacion) {
	        if(existeEstacion(codigo_estacion)) {
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
	        }else {
		        System.out.println("La estacion no existe. No se puede modificar.");
	        }
	    }

	    public static void eliminarEstacion(String codigo_estacion) {
	        if(existeEstacion(codigo_estacion)) {
	        	String sql = "DELETE FROM Estacion WHERE codigo_estacion = ?";

		        try (Connection conn = ConexionDB.obtenerConexion();
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		            pstmt.setString(1, codigo_estacion);
		            pstmt.executeUpdate();
		            System.out.println("Estación eliminada correctamente.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }  
	        }else {
		        System.out.println("La estacion no existe. No se puede eliminar.");
	        }
	    }
	 
	    public static void mostrarEstaciones() {
	        String sql = "SELECT * FROM Estacion";

	        try (Connection conn = ConexionDB.obtenerConexion();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                System.out.println("Codigo de la estacion: " + rs.getString("codigo_estacion") + "\tNombre: " + rs.getInt("nombre_estacion") + "\tCiudad: " + rs.getInt("ciudad_estacion") + "\tPais: " + rs.getInt("pais_estacion"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	 public static Estacion recuperarEstacion(String codigo_estacion) {
		 if(existeEstacion(codigo_estacion)) {
			 String sql = "SELECT * FROM Estacion WHERE codigo_estacion = ?";

			    try (Connection conn = ConexionDB.obtenerConexion();
			         PreparedStatement pstmt = conn.prepareStatement(sql)) {
			        pstmt.setString(1, codigo_estacion);
			        try (ResultSet rs = pstmt.executeQuery()) {
			            if (rs.next()) {
			                String nombre = rs.getString("nombre_estacion");
			                String ciudad = rs.getString("ciudad_estacion");
			                String pais = rs.getString("pais_estacion");
			                return new Estacion(codigo_estacion, nombre, ciudad, pais);
			            } else {
			                System.out.println("No se encontró ninguna estación con el código: " + codigo_estacion);
			                return null;
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return null;
			    }
		 }
		return null;
		 
	 }
	 
	 public static boolean existeEstacion(String codigo_estacion) {
		    String sql = "SELECT * FROM Estacion WHERE codigo_estacion = ?";

		    try (Connection conn = ConexionDB.obtenerConexion();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, codigo_estacion);
		        ResultSet rs = pstmt.executeQuery();
		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	 
	 
	 //solo para el proceso del desarrollo!!!
	 public static void borrarContenidoTablas() {
	        // Lista de nombres de tablas en tu base de datos
	        String[] tablas = {"Viaje", "Asiento", "Company", "Medio", "Estacion"};
	        
	        // Recorremos todas las tablas y eliminamos su contenido
	        for (String tabla : tablas) {
	            String sql = "DELETE FROM " + tabla;
	            
	            try (Connection conn = ConexionDB.obtenerConexion();
	                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
	                pstmt.executeUpdate();
	                System.out.println("Contenido de la tabla " + tabla + " borrado correctamente.");
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 


	
	
	
}

