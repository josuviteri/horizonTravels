package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import database.GestorDB;
import objetos.*;

public class Metodos {
    private static final String nombre_fichero = "codigos_viaje.txt";

	public static void guardarViaje(Viaje viaje) {

		// Insertar estaciones de origen y destino
		Estacion origen = viaje.getOrigen();
		Estacion destino = viaje.getDestino();
		GestorDB.insertarEstacion(origen.getCodigo(), origen.getNombre(), origen.getCiudad(), origen.getPais());
		GestorDB.insertarEstacion(destino.getCodigo(), destino.getNombre(), destino.getCiudad(), destino.getPais());

		Company company = viaje.getCompany();
		        
		if(company.getMedio().getCodigoMedio() != null) {
			GestorDB.insertarCompany(company.getCodigo(), company.getNombre(), "");

		}else {
			GestorDB.insertarCompany(company.getCodigo(), company.getNombre(), company.getMedio().getCodigoMedio());

			}
		       
		// Insertar el medio de transporte (si existe)
		Medio medio = company.getMedio();
			if (medio != null) {
			if (medio instanceof VueloInter) {
				VueloInter vueloInter = (VueloInter) medio;
		        int impuestoNacional = (int) Math.round(vueloInter.getImpuestoNacional());
		        GestorDB.insertarMedio(vueloInter.getCodigoMedio(), impuestoNacional, vueloInter.getViaje().getCodigo(), 1);
		        	
		    } else if (medio instanceof VueloNacional) {
		    	VueloNacional vueloNacional = (VueloNacional) medio;
		        int impuestoNacional = (int) Math.round(vueloNacional.getImpuestoNacional());
		        GestorDB.insertarMedio(vueloNacional.getCodigoMedio(), impuestoNacional, vueloNacional.getViaje().getCodigo(), 2);
		        	
		    } else if (medio instanceof TrenInter) {
		    	TrenInter trenInter = (TrenInter) medio;
		    	int impuestoNacional = (int) Math.round(trenInter.getImpuestoNacional());
		    	GestorDB.insertarMedio(trenInter.getCodigoMedio(), impuestoNacional, trenInter.getViaje().getCodigo(), 3);
		        	
		    } else if (medio instanceof TrenNacional) {
		    	TrenNacional trenNacional = (TrenNacional) medio;
		    	int impuestoNacional = (int) Math.round(trenNacional.getImpuestoNacional());
		    	GestorDB.insertarMedio(trenNacional.getCodigoMedio(), impuestoNacional, trenNacional.getViaje().getCodigo(), 4);
		        	
		    } else if (medio instanceof BarcoInter) {
		    	BarcoInter barcoInter = (BarcoInter) medio;
		    	int impuestoNacional = (int) Math.round(barcoInter.getImpuestoNacional());
		     	GestorDB.insertarMedio(barcoInter.getCodigoMedio(), impuestoNacional, barcoInter.getViaje().getCodigo(), 5);
		        	
		    } else if (medio instanceof BarcoNacional) {
		    	BarcoNacional barcoNacional = (BarcoNacional) medio;
		    	int impuestoNacional = (int) Math.round(barcoNacional.getImpuestoNacional());
		     	GestorDB.insertarMedio(barcoNacional.getCodigoMedio(), impuestoNacional, barcoNacional.getViaje().getCodigo(), 6);   	
		    	}
		    }
			
		    GestorDB.insertarViaje(viaje.getCodigo(), viaje.getFecha(), viaje.getOrigen().getCodigo(), viaje.getDestino().getCodigo(), viaje.getCompany().getCodigo(), viaje.getPrecioBase());

		    // Insertar los asientos
		    List<Asiento> asientos = viaje.getAsientos();
		    for (Asiento asiento : asientos) {
		    GestorDB.insertarAsiento(asiento.getId(), asiento.getPasajero(), asiento.getViaje().getCodigo());
		        }
		    
			//al guardar un viaje se tiene que guardar el codigo del viaje en un .txt
			String codigoViaje = viaje.getCodigo();
	}
	

	
	public static void eliminarViaje(Viaje viaje) {
		

		        
		GestorDB.eliminarEstacion(viaje.getOrigen().getCodigo());
		GestorDB.eliminarEstacion(viaje.getDestino().getCodigo());

		Company company = viaje.getCompany();
		GestorDB.eliminarCompany(viaje.getCompany().getCodigo());
		
		       

		GestorDB.eliminarMedio(company.getMedio().getCodigoMedio());

		GestorDB.eliminarViaje(viaje.getCodigo());

		List<Asiento> asientos = viaje.getAsientos();
		for (Asiento asiento : asientos) {
			GestorDB.eliminarAsiento(asiento.getId(), asiento.getViaje().getCodigo());
		}
		
	}
	
	
	public static void modificarViaje() {
		//kperezalol
	}
	
	public static void mostrarDatosViaje(Viaje viaje) {
		GestorDB.mostrarDatosViaje(viaje.getCodigo());
	}
	
	
	public static void cargarViaje(String codigo_viaje) {
		//sacar Estacion de origen y destino 
		//sacar company
		//sacar objeto medio del company
		//crear objeto viaje
		//sacar asientos
		//setear asientos del viaje
	}
	
	
	//todos
	public static void cargarTodosViajes() {
		
	}
	
    public static String LongAFecha(long fechaEnMilisegundos) {
        // Crear un objeto Instant desde el valor en milisegundos
        Instant instant = Instant.ofEpochMilli(fechaEnMilisegundos);
        
        // Crear un objeto LocalDateTime desde el Instant
        LocalDateTime fecha = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        
        // Formatear la fecha en una cadena legible
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fecha.format(formatter);
        
        return fechaFormateada;
    }
    
    public static void guardarCodigoViaje(String codigoViaje) {

        try (FileWriter fw = new FileWriter(nombre_fichero, true); 
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(codigoViaje); 
            bw.newLine(); 
            System.out.println("Codigo de viaje: "+ codigoViaje + ", guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
