package src;

import java.util.ArrayList;

import objetos.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Creación de objetos de ejemplo

        // Creación de estaciones
        Estacion estacionOrigen = new Estacion("ORI001", "Estación de origen", "Ciudad Origen", "País Origen");
        Estacion estacionDestino = new Estacion("DES001", "Estación de destino", "Ciudad Destino", "País Destino");

        // Creación de compañía
        Company company = new Company("COMP001", "Compañía de Ejemplo", "País de la Compañía");

        // Creación de viaje
        Viaje viaje = new Viaje("VIA001", System.currentTimeMillis(), estacionOrigen, estacionDestino, new VueloInter(50.0), 500.0, new ArrayList<>());

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
        viaje.getAsientos().add(asiento1);
        viaje.getAsientos().add(asiento2);
        viaje.getAsientos().add(asiento3);
        viaje.getAsientos().add(asiento4);
        viaje.getAsientos().add(asiento5);
        viaje.getAsientos().add(asiento6);
        viaje.getAsientos().add(asiento7);
        viaje.getAsientos().add(asiento8);
        viaje.getAsientos().add(asiento9);
        viaje.getAsientos().add(asiento10);
        viaje.getAsientos().add(asiento11);
        viaje.getAsientos().add(asiento12);
        viaje.getAsientos().add(asiento13);
        viaje.getAsientos().add(asiento14);

        // Agregar el viaje a la lista de viajes de la compañía
        company.getViajes().add(viaje);
        
        // Imprimir información de ejemplo
        System.out.println("Información del Viaje:");
        System.out.println("Código: " + viaje.getCodigo());
        System.out.println("Fecha: " + viaje.getFecha());
        System.out.println("Compañía: " + company.getNombre());
        System.out.println("Estación de Origen: " + viaje.getOrigen().getNombre());
        System.out.println("Estación de Destino: " + viaje.getDestino().getNombre());
        System.out.println("Precio Base: " + viaje.getPrecioBase());
        System.out.println("Impuesto de "+ viaje.getMedio().getClass().getSimpleName() + " : " + ((VueloInter) viaje.getMedio()).getImpuestoInter());
        System.out.println("Precio con Impuestos: "+ (viaje.getPrecioBase() + ((VueloInter) viaje.getMedio()).getImpuestoInter()) );
        System.out.println("Medio de Transporte: " + viaje.getMedio().getClass().getSimpleName());
        System.out.println("Cantidad Total de Asientos : " + viaje.getAsientos().size());
        System.out.println("Asientos Libres: " + viaje.asientosLibres());
        System.out.println("Asientos Ocupados: " + viaje.asientosOcupados());
	}

}
