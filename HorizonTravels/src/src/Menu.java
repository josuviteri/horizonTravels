package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import database.GestorDB;
import objetos.Asiento;
import objetos.Company;
import objetos.Estacion;
import objetos.Medio;
import objetos.Viaje;
import objetos.VueloInter;
import objetos.VueloNacional;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
	
	public static void menuInicio() {
        String opcion;

        do {
        	System.out.println("\nBienvenidx a Horizon Travels\n\nSelecciona una opcion:\n1. Mostrar viajes\n2. Gestionar Viajes\n3. Gestionar la BD\n\nPulsa 'q' para salir ");
        	opcion = scanner.nextLine();
        	
        	switch (opcion) {
            case "1":
                System.out.println("Abriendo menu mostrar viajes...");
                // Lógica para mostrar todos los viajes
                menuMostrarViajes();
                break;
            case "2":
                System.out.println("Abriendo menu de gestion de viajes...");
                // Lógica para abrir menú de gestión
                menuGestionViajes();
                break;
            case "3":
                System.out.println("Abriendo menu de gestion de la BD...");
                // Lógica para abrir menú de gestión
                menuGestionBD();
                break;
            case "q":
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida. Por favor, selecciona una opcion valida\n");
        }
        	
        } while (!opcion.equals("q"));

    }
	
	public static void menuGestionViajes() {
        String opcion;

        do {
        	System.out.println("\n\nMenu de gestion de viajes\n-----------\nSelecciona una opcion\n1. Insertar nuevo viaje\n2. Cargar viaje\n3. Modificar viaje\n4. Eliminar viaje\n\nPulsa 'z' para volver\n");
        	opcion = scanner.nextLine();
        	
        	switch (opcion) {
            case "1":
                System.out.println("Menu insertar viaje");
                // Lógica para insertar viaje
                insertarViaje();
                break;
            case "2":
                System.out.println("Menu cargar viaje");
                // Lógica para recuperar viaje de la base de datos y insertarla en el mapa de viajes
                
                break;
            case "3":
                System.out.println("Menu modificar viaje");
                // Lógica para modificar viaje de la base de datos
               modificarViaje();
                break;
            case "4":
                System.out.println("Menu eliminar viaje");
                // Lógica para eliminar viaje de la base de datos
                eliminarViaje();
                break;

            
            case "z":
                System.out.println("Volviendo al menu inicial...");
                break;
            default:
                System.out.println("Opcion no valida. Por favor, selecciona una opcion valida\n");
        }
        	
        } while (!opcion.equals("z"));

	}
	
	public static Viaje introduceDatos() {
		System.out.println("Introduce el código de la estación de origen:");
        String codEO = scanner.nextLine();

        System.out.println("Introduce el nombre de la estación de origen:");
        String nomEO = scanner.nextLine();

        System.out.println("Introduce la ciudad de la estación de origen:");
        String ciuEO = scanner.nextLine();

        System.out.println("Introduce el pais de la estación de origen:");
        String anyEO = scanner.nextLine();

        System.out.println("Introduce el código de la estación de destino:");
        String codED = scanner.nextLine();

        System.out.println("Introduce el nombre de la estación de destino:");
        String nomED = scanner.nextLine();

        System.out.println("Introduce la ciudad de la estación de destino:");
        String ciuED = scanner.nextLine();

        System.out.println("Introduce el año de la estación de destino:");
        String anyED = scanner.nextLine();

        System.out.println("Selecciona el tipo de medio de transporte (1-VueloInter, 2-VueloNacional, 3-BarcoInter, 4-BarcoNacional, 5-TrenInter, 6-TrenNacional):");
        Integer tipMed = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código del medio de transporte:");
        String codMed = scanner.nextLine();

        System.out.println("Introduce el importe del medio de transporte:");
        Integer impMed = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código de la compañía:");
        String codCo = scanner.nextLine();

        System.out.println("Introduce el nombre de la compañía:");
        String nomCo = scanner.nextLine();

        System.out.println("Introduce el código del viaje:");
        String codVia = scanner.nextLine();

        System.out.println("Introduce el precio del viaje:");
        Double precVia = Double.parseDouble(scanner.nextLine());
        
        Viaje viaje = Metodos.crearViaje(codEO, nomEO, ciuEO, anyEO, codED, nomED, ciuED, anyED, tipMed, codMed, impMed, codCo, nomCo, codVia, precVia, false);
        return viaje;
	}
	
	public static void introduceDatosElim() {


        System.out.println("Introduce el código del viaje:");
        String codVia = scanner.nextLine();
		
        System.out.println("Selecciona el tipo de medio de transporte (1-VueloInter, 2-VueloNacional, 3-BarcoInter, 4-BarcoNacional, 5-TrenInter, 6-TrenNacional):");
        Integer tipMed = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código del medio de transporte:");
        String codMed = scanner.nextLine();

        System.out.println("Introduce el importe del medio de transporte:");
        Integer impMed = Integer.parseInt(scanner.nextLine());

 
        Metodos.eliminarViajeCodigo(codMed,codVia);
        
	}
	
	public static Viaje introduceDatosModif() {
		
		
		System.out.println("Introduce el código de la estación de origen:");
        String codEO = scanner.nextLine();

        System.out.println("Introduce el nombre de la estación de origen:");
        String nomEO = scanner.nextLine();

        System.out.println("Introduce la ciudad de la estación de origen:");
        String ciuEO = scanner.nextLine();

        System.out.println("Introduce el pais de la estación de origen:");
        String anyEO = scanner.nextLine();

        System.out.println("Introduce el código de la estación de destino:");
        String codED = scanner.nextLine();

        System.out.println("Introduce el nombre de la estación de destino:");
        String nomED = scanner.nextLine();

        System.out.println("Introduce la ciudad de la estación de destino:");
        String ciuED = scanner.nextLine();

        System.out.println("Introduce el año de la estación de destino:");
        String anyED = scanner.nextLine();

        System.out.println("Selecciona el tipo de medio de transporte (1-VueloInter, 2-VueloNacional, 3-BarcoInter, 4-BarcoNacional, 5-TrenInter, 6-TrenNacional):");
        Integer tipMed = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código del medio de transporte:");
        String codMed = scanner.nextLine();

        System.out.println("Introduce el importe del medio de transporte:");
        Integer impMed = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduce el código de la compañía:");
        String codCo = scanner.nextLine();

        System.out.println("Introduce el nombre de la compañía:");
        String nomCo = scanner.nextLine();

        System.out.println("Introduce el código del viaje:");
        String codVia = scanner.nextLine();

        System.out.println("Introduce el precio del viaje:");
        Double precVia = Double.parseDouble(scanner.nextLine());
        
        Metodos.eliminarViajeCodigo(codMed,codVia);
        
        Viaje viaje = Metodos.crearViaje(codEO, nomEO, ciuEO, anyEO, codED, nomED, ciuED, anyED, tipMed, codMed, impMed, codCo, nomCo, codVia, precVia, false);
 
        return viaje;
        
	}
	
	
	public static void insertarViaje() {
		Viaje viaje = introduceDatos();
		Metodos.guardarViaje(viaje);
	}
	
	public static void modificarViaje() {
		Viaje viaje = introduceDatosModif();
		Metodos.guardarViaje(viaje);
	}
	
	public static void eliminarViaje() {
		introduceDatosElim();
	}
	
	public static void menuGestionBD() {
		String opcion;

        do {
            System.out.println("\n\nMenu de gestion de la BD\n-----------\nSelecciona una opcion\n1. Crear e insertar viajes predeterminados\n3. Mostrar BD completa\n9. Eliminar todos los viajes\n\nPulsa 'z' para volver\n");
        	opcion = scanner.nextLine();
        	
        	switch (opcion) {
        	case "1":
                System.out.println("Crear e insertar viajes predeterminados");
                // Lógica para cargar viajes
                crearInsertarViajesPredeterminados();
                break;
                
        	case "3":
                System.out.println("Mostrar todo el contenido de la BD sin crear objetos");
                // Lógica para mostrar todo por sentencias de sql, sin crear objetos
              GestorDB.mostrarTodosViajes();
                break;
            case "9":
                System.out.println("Eliminar todos los viajes");
                // Lógica para eliminar todo
                GestorDB.borrarContenidoTablas();
                Metodos.eliminarTodosCodigosViaje();
                break;
            
            case "z":
                System.out.println("Volviendo al menu inicial...");
                break;
            default:
                System.out.println("Opcion no valida. Por favor, selecciona una opcion valida\n");
        }
        	
        } while (!opcion.equals("z"));
	}
	
	public static void menuMostrarViajes() {
        String opcion;

        do {
        	System.out.println("\n\nMenu mostrar viajes\n-----------\nSelecciona una opcion\n1. Mostrar todos los viajes\n2. Filtrar viajes\n3. Mostrar asientos de un viaje\n\nPulsa 'z' para volver\n");
        	opcion = scanner.nextLine();
        	
        	switch (opcion) {
            case "1":
                System.out.println("Mostrar todos los viajes");
                // Lógica para Mostrar todos los viajes
                menuMostrarTodos();
                
                break;
            case "2":
                System.out.println("Filtrar viajes");
                // Lógica para Filtrar viajes
                menuFiltarViajes();
                break;
            case "3":
                System.out.println("Mostrar asientos");
                // Lógica para mostrar asientos
                menuMostrarAsientos();
                break;
            case "z":
                System.out.println("Volviendo al menu inicial...");
                break;
            default:
                System.out.println("Opcion no valida. Por favor, selecciona una opcion valida\n");
        }
        	
        } while (!opcion.equals("z"));
	}
	
	public static void menuMostrarTodos() {
		Map<String, Viaje> mapaViajes  = Metodos.cargarTodosViajes();
        for (String codViaje : mapaViajes.keySet()) {
        	Viaje viaje = mapaViajes.get(codViaje);
        	Metodos.mostrarDetallesViaje(viaje);
        }
	}
	
	public static void menuFiltarViajes() {
		System.out.println("Introduce el nombre del pais de origen:");
        String nomOri = scanner.nextLine();
        System.out.println("Introduce el nombre del pais destino:");
        String nomDes = scanner.nextLine();
        System.out.println("Introduce el precio completo maximo:");
        Integer precioMax = Integer.parseInt(scanner.nextLine());
        
        Map<String, Viaje> mapaViajes  = Metodos.cargarTodosViajes();
        for (String codViaje : mapaViajes.keySet()) {
        	Viaje viaje = mapaViajes.get(codViaje);
        	
        	String origen = viaje.getOrigen().getPais();
        	String destino = viaje.getDestino().getPais();
        	Double precio = viaje.getCompany().getMedio().calcularPrecio(viaje);
        	
        	if(nomOri.equals(origen) && nomDes.equals(destino) && precio < precioMax) {
            	Metodos.mostrarDetallesViaje(viaje);
        	}
        }
	}
	
	public static void menuMostrarAsientos() {
		System.out.println("Introduce el codigo del viaje:");
        String codVia = scanner.nextLine();
        Metodos.recuperarListaAsientos(codVia);
	}
	
	public static void crearInsertarViajesPredeterminados() {
		
		Estacion estacionOrigen = new Estacion("BI480", "Derio", "Bilbo", "España");
        Estacion estacionDestino1 = new Estacion("MA003", "Madrid", "Madrid", "España");
        Estacion estacionDestino2 = new Estacion("NY001", "New York", "New York", "EEUU");
        Estacion estacionDestino3 = new Estacion("TK420", "Tokyo", "Tokyo", "Japón");

        // Creación de compañía
        Medio vueloNacional = new VueloNacional(20, "AvionN001");
        Medio vueloInternacional1 = new VueloInter(50, "AvionI002");
        Medio vueloInternacional2 = new VueloInter(90, "AvionI003");
        
        Company company1 = new Company("COM001", "Rayanair");
        Company company2 = new Company("COC324", "Cocompany");
        Company company3 = new Company("LA024", "LA420");
        company1.setMedio(vueloNacional);

        // Creación de viaje nacional
        Viaje viajeNacional = new Viaje("VIA001", System.currentTimeMillis(), estacionOrigen, estacionDestino1, company1, 200.0, new ArrayList<>());
        vueloNacional.setViaje(viajeNacional);

        // Creación de pasajeros
        List<Asiento> listaAsientosNacional = new ArrayList<>();
        listaAsientosNacional.add(new Asiento(viajeNacional, "Josu"));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Gotzon"));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Giovanni"));
        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Aitor"));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Clara"));
        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Fabi"));
        listaAsientosNacional.add(new Asiento(viajeNacional, ""));
        listaAsientosNacional.add(new Asiento(viajeNacional, "Llerai"));

        viajeNacional.setAsientos(listaAsientosNacional);

        // Creación de viaje internacional
        company2.setMedio(vueloInternacional1);
        Viaje viajeInternacional1 = new Viaje("VIA002", System.currentTimeMillis(), estacionOrigen, estacionDestino2, company2, 800.0, new ArrayList<>());
        vueloInternacional1.setViaje(viajeInternacional1);

        // Creación de pasajeros
        List<Asiento> listaAsientosInternacional1 = new ArrayList<>();
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Alice"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Bob"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Dave"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Eva"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Hannah"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, ""));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Mike"));
        listaAsientosInternacional1.add(new Asiento(viajeInternacional1, "Sophia"));

        viajeInternacional1.setAsientos(listaAsientosInternacional1);

        // Creación de otro viaje internacional
        company3.setMedio(vueloInternacional2);
        Viaje viajeInternacional2 = new Viaje("VIA003", System.currentTimeMillis(), estacionOrigen, estacionDestino3, company3, 1200.0, new ArrayList<>());
        vueloInternacional2.setViaje(viajeInternacional2);

        // Creación de pasajeros
        List<Asiento> listaAsientosInternacional2 = new ArrayList<>();
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Nobu"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Kaito"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Akira"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Yuki"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Yui"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Goku"));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, ""));
        listaAsientosInternacional2.add(new Asiento(viajeInternacional2, "Haruka"));

        viajeInternacional2.setAsientos(listaAsientosInternacional2);
		
        
      Metodos.guardarViaje(viajeNacional);
      Metodos.guardarViaje(viajeInternacional1);
      Metodos.guardarViaje(viajeInternacional2);
		
	}
	
	public static void runClaseMenu() {
		menuInicio();
        scanner.close();
	}

}
