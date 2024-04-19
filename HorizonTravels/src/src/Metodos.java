package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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

		if(company.getMedio().getCodigoMedio() == null) {
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
			guardarCodigoViaje(viaje.getCodigo());
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
	
//	public static void mostrarDatosViaje(Viaje viaje) {
//		GestorDB.mostrarDatosViaje(viaje.getCodigo());
//	}
	
	
	public static Viaje recuperarViaje(String codigo_viaje) {
//		GestorDB.mostrarDatosViaje(codigo_viaje);
		//sacar Estacion de origen y destino 
		String codigo_origen = GestorDB.recuperarCodigoOrigenDeViaje(codigo_viaje);
		String codigo_destino = GestorDB.recuperarCodigoDestinoDeViaje(codigo_viaje);
		Estacion origen = GestorDB.recuperarEstacion(codigo_origen);
		Estacion destino = GestorDB.recuperarEstacion(codigo_destino);
		//sacar company
		//recuperar codigoasiento de codigoviaje
		String codigo_comp = GestorDB.recuperarCodigoCompDeViaje(codigo_viaje);
		Company company = GestorDB.recuperarCompany(codigo_comp);
		
		String codigo_medio = GestorDB.recuperarCodigoMedioDeCompany(codigo_comp);
		System.out.println("hola");
		System.out.println(codigo_medio);
		Medio medio = GestorDB.recuperarMedio(codigo_medio);
		company.setMedio(medio);
		
		Long fecha = GestorDB.recuperarFechaDeViaje(codigo_viaje);
		Integer precioBase = GestorDB.recuperarPrecioDeViaje(codigo_viaje);
		//sacar objeto medio del company
		//crear objeto viaje
		Viaje viaje = new Viaje(codigo_viaje, fecha, origen, destino, company, precioBase, new ArrayList<>());
		//sacar asientos
		//no recupera el medios
//		medio.setViaje(viaje);
		
        List<Asiento> listaAsientos = new ArrayList<Asiento>();
        
        //recuperar todos los 20 asientos llenos y vacios del vuelo
        for(Integer i = 0; i < 20; i++) {
        	listaAsientos.add(GestorDB.recuperarAsiento(i, codigo_viaje, viaje));
        }
        viaje.setAsientos(listaAsientos);
        
		return viaje;
	}
	
	
	public static void mostrarDetallesViaje(Viaje viaje) {
        System.out.println("Codigo del viaje: " + viaje.getCodigo() + 
                           "\nFecha: " + Metodos.LongAFecha(viaje.getFecha()) + 
                           "\nCodigo estacion origen: " + viaje.getOrigen().getCodigo() + 
                           "\nCodigo estacion destino: " + viaje.getDestino().getCodigo() + 
                           "\nCodigo de la compañía: " + viaje.getCompany().getCodigo() + 
                           "\nPrecio del viaje: " + viaje.getPrecioBase());
	Medio medio = viaje.getCompany().getMedio();
	if (medio instanceof VueloInter) {
		System.out.println("Tipo de Medio: Vuelo Internacional");
    } else if (medio instanceof VueloNacional) {
		System.out.println("Tipo de Medio: Vuelo Nacional");
    } else if (medio instanceof TrenInter) {
		System.out.println("Tipo de Medio: Tren Internacional");
    } else if (medio instanceof TrenNacional) {
		System.out.println("Tipo de Medio: Tren Nacional");
    } else if (medio instanceof BarcoInter) {
		System.out.println("Tipo de Medio: Barco Internacional");
    } else if (medio instanceof BarcoNacional) {
		System.out.println("Tipo de Medio: Barco Nacional");
    }
	}
	
	
	//todos
	public static void cargarTodosViajes() {
		//iterar con cargarViaje() todas las lineas del .txt
		
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
    	if(!codigoYaExiste(codigoViaje)) {
    		try (FileWriter fw = new FileWriter(nombre_fichero, true); 
    	             BufferedWriter bw = new BufferedWriter(fw)) {
    	            bw.write(codigoViaje); 
    	            bw.newLine(); 
    	            System.out.println("Codigo de viaje: "+ codigoViaje + ", guardado correctamente.");
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	}else {
    		System.out.println("El codigo ya existe en el fichero de texto. No se puede insertar.");
    	}
    }
    
    private static boolean codigoYaExiste(String codigoViaje) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombre_fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(codigoViaje)) {
                    return true; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
}
