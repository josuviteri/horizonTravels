package src;

import java.util.ArrayList;
import java.util.List;

import objetos.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Creación de objetos de ejemplo

        // Creación de estaciones
        Estacion estacionOrigen = new Estacion("ORI001", "Estación de origen", "Ciudad Origen", "País Origen");
        Estacion estacionDestino = new Estacion("DES001", "Estación de destino", "Ciudad Destino", "País Destino");

        // Creación de compañía
        
        
        Company company = new Company("COMP001", "Compañía de Ejemplo");

        // Creación de viaje
        Viaje viaje = new Viaje("VIA001", System.currentTimeMillis(), estacionOrigen, estacionDestino, company, 500.0, new ArrayList<>());
       
        Medio vuelo = new VueloInter(30);
        vuelo.setViaje(viaje);
        company.setMedio(vuelo);
        

        
        
        List<Asiento> listaAsientos = new ArrayList<Asiento>();
        // Creación de asientos llenos
        Asiento asiento1 = new Asiento(viaje, "Josu");
        Asiento asiento2 = new Asiento(viaje, "Gotzon");
        Asiento asiento3 = new Asiento(viaje, "Giovanni");
        Asiento asiento4 = new Asiento(viaje, "Asier");
        Asiento asiento5 = new Asiento(viaje, "Aitor");
        Asiento asiento6 = new Asiento(viaje, "Clara");
        Asiento asiento7 = new Asiento(viaje, "Seta");
        Asiento asiento8 = new Asiento(viaje, "Guadi");
        //asientos vacios
        Asiento asiento9 = new Asiento(viaje);
        Asiento asiento10 = new Asiento(viaje);
        Asiento asiento11 = new Asiento(viaje);
        Asiento asiento12 = new Asiento(viaje);
        Asiento asiento13 = new Asiento(viaje);
        Asiento asiento14 = new Asiento(viaje);
        

        
        // Agregar asientos al viaje
        listaAsientos.add(asiento1);
        listaAsientos.add(asiento2);
        listaAsientos.add(asiento3);
        listaAsientos.add(asiento4);
        listaAsientos.add(asiento5);
        listaAsientos.add(asiento6);
        listaAsientos.add(asiento7);
        listaAsientos.add(asiento8);
        listaAsientos.add(asiento9);
        listaAsientos.add(asiento10);
        listaAsientos.add(asiento11);
        listaAsientos.add(asiento12);
        listaAsientos.add(asiento13);
        listaAsientos.add(asiento14);

        // Agregar el viaje a la lista de viajes de la compañía
        viaje.setAsientos(listaAsientos);
        
        System.out.println(viaje.getCompany().getMedio().calcularPrecio());
        
        
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
