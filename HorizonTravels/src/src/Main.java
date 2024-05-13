package src;

import database.GestorDB;
import objetos.Viaje;

public class Main {

	public static void main(String[] args) {

//		Menu.runClaseMenu();
//        
//        System.out.println("fin");

      // Inserta un viaje
      GestorDB.insertarViaje("COD002", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
//      // Modifica el viaje
      System.out.println(GestorDB.recuperarCodigoOrigenDeViaje("COD002"));
      System.out.println(GestorDB.recuperarCodigoDestinoDeViaje("COD002"));
      System.out.println(GestorDB.recuperarPrecioDeViaje("COD002"));
//      GestorDB.modificarViaje("COD002", System.currentTimeMillis(), "ORIGEN002", "DESTINO002", "COMP002", 200.0);

      System.out.println("inicio de modifi");
      
//      // Verifica que el viaje se haya modificado correctamente
//      System.out.println(GestorDB.recuperarCodigoOrigenDeViaje("COD002"));
//      System.out.println(GestorDB.recuperarCodigoDestinoDeViaje("COD002"));
//      System.out.println(GestorDB.recuperarPrecioDeViaje("COD002"));
        
		
//		Viaje viaje = Menu.introduceDatosModif();
//		Metodos.guardarViaje(viaje);
		
	      System.out.println(GestorDB.recuperarCodigoOrigenDeViaje("COD002"));
	      System.out.println(GestorDB.recuperarCodigoDestinoDeViaje("COD002"));
	      System.out.println(GestorDB.recuperarPrecioDeViaje("COD002"));
		
	}

}
