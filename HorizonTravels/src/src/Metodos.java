package src;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import database.GestorDB;
import objetos.*;

public class Metodos {

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
		        GestorDB.insertarMedio(vueloInter.getCodigoMedio(), impuestoNacional, vueloInter.getViaje().getCodigo());
		        	
		    } else if (medio instanceof VueloNacional) {
		    	VueloNacional vueloNacional = (VueloNacional) medio;
		        int impuestoNacional = (int) Math.round(vueloNacional.getImpuestoNacional());
		        GestorDB.insertarMedio(vueloNacional.getCodigoMedio(), impuestoNacional, vueloNacional.getViaje().getCodigo());
		        	
		    } else if (medio instanceof TrenInter) {
		    	TrenInter trenInter = (TrenInter) medio;
		    	int impuestoNacional = (int) Math.round(trenInter.getImpuestoNacional());
		    	GestorDB.insertarMedio(trenInter.getCodigoMedio(), impuestoNacional, trenInter.getViaje().getCodigo());
		        	
		    } else if (medio instanceof TrenNacional) {
		    	TrenNacional trenNacional = (TrenNacional) medio;
		    	int impuestoNacional = (int) Math.round(trenNacional.getImpuestoNacional());
		    	GestorDB.insertarMedio(trenNacional.getCodigoMedio(), impuestoNacional, trenNacional.getViaje().getCodigo());
		        	
		    } else if (medio instanceof BarcoInter) {
		    	BarcoInter barcoInter = (BarcoInter) medio;
		    	int impuestoNacional = (int) Math.round(barcoInter.getImpuestoNacional());
		     	GestorDB.insertarMedio(barcoInter.getCodigoMedio(), impuestoNacional, barcoInter.getViaje().getCodigo());
		        	
		    } else if (medio instanceof BarcoNacional) {
		    	BarcoNacional barcoNacional = (BarcoNacional) medio;
		    	int impuestoNacional = (int) Math.round(barcoNacional.getImpuestoNacional());
		     	GestorDB.insertarMedio(barcoNacional.getCodigoMedio(), impuestoNacional, barcoNacional.getViaje().getCodigo());   	
		    	}
		    }else {

		        }
		    GestorDB.insertarViaje(viaje.getCodigo(), viaje.getFecha(), viaje.getOrigen().getCodigo(), viaje.getDestino().getCodigo(), viaje.getCompany().getCodigo(), viaje.getPrecioBase());

		    // Insertar los asientos
		    List<Asiento> asientos = viaje.getAsientos();
		    for (Asiento asiento : asientos) {
		    GestorDB.insertarAsiento(asiento.getId(), asiento.getPasajero(), asiento.getViaje().getCodigo());
		        }
	}
	

	
	public static void eliminarViaje(Viaje viaje) {
		

		        
		GestorDB.eliminarEstacion(viaje.getOrigen().getCodigo());
		GestorDB.eliminarEstacion(viaje.getDestino().getCodigo());

		Company company = viaje.getCompany();
		GestorDB.eliminarCompany(viaje.getCompany().getCodigo());
		
		       
		// Insertar el medio de transporte (si existe)
		Medio medio = company.getMedio();
		GestorDB.eliminarMedio(company.getMedio().getCodigoMedio());

		GestorDB.eliminarViaje(viaje.getCodigo());

		    // Insertar los asientos
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
	
	public static void cargarViajes() {
		
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
}
