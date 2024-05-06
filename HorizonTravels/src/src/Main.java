package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import database.GestorDB;
import objetos.*;

public class Main {

	public static void main(String[] args) {
//		 Estacion estacionOrigen = new Estacion("BI480", "Derio", "Bilbo", "España");
//	        Estacion estacionDestino1 = new Estacion("MA003", "Madrid", "Madrid", "España");
//	        Estacion estacionDestino2 = new Estacion("NY001", "New York", "New York", "EEUU");
//	        Estacion estacionDestino3 = new Estacion("TK420", "Tokyo", "Tokyo", "Japón");
//
//	        // Creación de compañía
//	        Medio vueloNacional = new VueloNacional(20, "MedioNacional");
//	        Medio vueloInternacional1 = new VueloInter(50, "MedioInternacional");
//	        Medio vueloInternacional2 = new VueloInter(90, "MedioInternacional");
//	        
//	        Company company1 = new Company("COM001", "Rayanair");
//	        Company company2 = new Company("COC324", "Cocompany");
//	        Company company3 = new Company("LA024", "LA420");
//	        company1.setMedio(vueloNacional);
//
//	        // Creación de viaje nacional
//	        Viaje viajeNacional = new Viaje("VIA001", System.currentTimeMillis(), estacionOrigen, estacionDestino1, company1, 200.0, new ArrayList<>());
//	        vueloNacional.setViaje(viajeNacional);
//
//	        // Creación de pasajeros
//	        List<Asiento> listaAsientosNacional = new ArrayList<>();
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Josu"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Gotzon"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Giovanni"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Aitor"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Clara"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Fabi"));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
//	        listaAsientosNacional.add(new Asiento(viajeNacional, "Llerai"));
//
//	        viajeNacional.setAsientos(listaAsientosNacional);
//
//	        // Creación de viaje internacional
//	        company2.setMedio(vueloInternacional1);
//	        Viaje viajeInternacional1 = new Viaje("VIA002", System.currentTimeMillis(), estacionOrigen, estacionDestino2, company2, 800.0, new ArrayList<>());
//	        vueloInternacional1.setViaje(viajeInternacional1);
//
//	        // Creación de pasajeros
//	        List<Asiento> listaAsientosInternacional1 = new ArrayList<>();
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Alice"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Bob"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Dave"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Eva"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Hannah"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Mike"));
//	        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Sophia"));
//
//	        viajeInternacional1.setAsientos(listaAsientosInternacional1);
//
//	        // Creación de otro viaje internacional
//	        company3.setMedio(vueloInternacional2);
//	        Viaje viajeInternacional2 = new Viaje("VIA003", System.currentTimeMillis(), estacionOrigen, estacionDestino3, company3, 1200.0, new ArrayList<>());
//	        vueloInternacional2.setViaje(viajeInternacional2);
//
//	        // Creación de pasajeros
//	        List<Asiento> listaAsientosInternacional2 = new ArrayList<>();
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Nobu"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Kaito"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Akira"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Yuki"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Yui"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Goku"));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
//	        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Haruka"));
//
//	        viajeInternacional2.setAsientos(listaAsientosInternacional2);
        
        
//        Metodos.mostrarDetallesViaje(viaje);
//        GestorDB.borrarContenidoTablas();
        
//        GestorDB.mostrarTodosViajes();
//        Metodos.guardarViaje(viajeNacional);
//        Metodos.guardarViaje(viajeInternacional1);
//        Metodos.guardarViaje(viajeInternacional2);
//        
//        Metodos.eliminarViaje(viaje);
//        String codigo_viaje = viaje.getCodigo();
//        Viaje nuevoViaje = Metodos.recuperarViaje(codigo_viaje);
//        Metodos.mostrarDetallesViaje(nuevoViaje);
		
		
//		Metodos.recuperarListaAsientos("VIA001");
		
		
		Menu.runClaseMenu();
        
        System.out.println("fin");
//        Scanner scanner = new Scanner(System.in);
//        
////        System.out.println(viaje.getCompany().getMedio().calcularPrecio());
//        
//        
//        boolean correcto1 = false;
//        boolean correcto3 = false;
//        
//        while(correcto1 == false) {
//        	System.out.println("Selecciona una opcion: \n1. Cargar viajes\n2. Mostrar viajes\n3. Reservar viajes\n ");
//            Integer seleccion1 = scanner.nextInt();
//            //cargar viajes
//        	if(seleccion1 == 1) {
//            	correcto1 = true;
//            	System.out.println("okay1");
//
//            //mostrar viajes
//            }else if(seleccion1 == 2) {
//            	correcto1 = true;
//            	System.out.println("okay2");
//
//            //Reservar viajes	
//            }else if (seleccion1 == 3) {
//            	correcto1 = true;
//            	
//            	while(correcto3 == false) {
//            		System.out.println("Selecciona el tipo de accion de reserva: \n1. Reservar viaje/s\n2. Eliminar reserva/s\n");
//                	Integer seleccion3 = scanner.nextInt();
//                	
//            		if(seleccion3 == 1) {
//            			correcto3 = true;
//                		System.out.println("okay 3.1");
//                		
//                	}else if (seleccion3 == 2) {
//                		correcto3 = true;
//                		System.out.println("okay 3.2");
//
//                	}else {
//                    	System.out.println("Error, introduce un valor valido.");
//                	}
//            	}
//            	
//            	
//            }else {
//            	System.out.println("Error, introduce un valor valido.");
//            	
//            }
//        }
//        
//        
//        System.out.println("fin");
        // Imprimir información de ejemplo
//        System.out.println("Información del Viaje:");
//        System.out.println("Código: " + viaje.getCodigo());
//        System.out.println("Fecha: " + viaje.getFecha());
//        System.out.println("Compañía: " + company.getNombre());
//        System.out.println("Estación de Origen: " + viaje.getOrigen().getNombre());
//        System.out.println("Estación de Destino: " + viaje.getDestino().getNombre());
//        System.out.println("Precio Base: " + viaje.getPrecioBase());
//        System.out.println("Precio ");
//        
        
//        System.out.println("Impuesto de "+ viaje.getMedio().getClass().getSimpleName() + " : " + ((VueloInter) viaje.getMedio()).getImpuestoInter());
//        System.out.println("1 Precio con impuesto de  "+ viaje.getMedio().getClass().getSimpleName() + " : " + ((VueloInter) viaje.getMedio()).calcularPrecio());
//        System.out.println("2 Precio con Impuestos: "+ (viaje.getPrecioBase() + ((VueloInter) viaje.getMedio()).getImpuestoInter()) );
//        System.out.println("Medio de Transporte: " + viaje.getMedio().getClass().getSimpleName());
//        System.out.println("Cantidad Total de Asientos : " + viaje.getAsientos().size());
//        System.out.println("Asientos Libres: " + viaje.asientosLibres());
//        System.out.println("Asientos Ocupados: " + viaje.asientosOcupados());
	}

}
