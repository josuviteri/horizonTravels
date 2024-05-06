package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import database.GestorDB;
import objetos.*;

public class Metodos {
    private static final String nombre_fichero = "codigos_viaje.txt";

    public static Viaje crearViaje(String codEO,String nomEO,String ciuEO,String anyEO, String codED,String nomED,String ciuED,String anyED, Integer tipMed, String codMed, Integer impMed,String codCo,String nomCo,String codVia, Double precVia, Boolean elim) {
    	if(!elim) {
    		Estacion estacionOrigen = new Estacion(codEO, nomEO, ciuEO, anyEO);
    		Estacion estacionDestino = new Estacion(codED, nomED, ciuED, anyED);
    		Medio medio = null;
    		
    			switch (tipMed) {
    		    case 1:
    		        medio = new VueloInter(impMed, codMed);
    		        break;
    		    case 2:
    		        medio = new VueloNacional(impMed, codMed);
    		        break;
    		    case 3:
    		        medio = new BarcoInter(impMed, codMed);
    		        break;
    		    case 4:
    		        medio = new BarcoNacional(impMed, codMed);
    		        break;
    		    case 5:
    		        medio = new TrenInter(impMed, codMed);
    		        break;
    		    case 6:
    		        medio = new TrenNacional(impMed, codMed);
    		        break;
    		    default:
    		        System.out.println("Valor de tipMed no valido");
    		        break;
    			
    		}
    		
    		Company company = new Company(codCo, nomCo);
    		company.setMedio(medio);
    		
    		Viaje viaje = new Viaje(codVia, System.currentTimeMillis(), estacionOrigen, estacionDestino, company, precVia, new ArrayList<>());
    		medio.setViaje(viaje);
    		
    		return viaje;
    	}else if(elim) {
    		Estacion estacionOrigen = new Estacion(codEO, nomEO, ciuEO, anyEO);
    		Estacion estacionDestino = new Estacion(codED, nomED, ciuED, anyED);
    		Medio medio = null;

    			switch (tipMed) {
    		    case 1:
    		        medio = new VueloInter(0, codMed);
    		        break;
    		    case 2:
    		        medio = new VueloNacional(0, codMed);
    		        break;
    		    case 3:
    		        medio = new BarcoInter(0, codMed);
    		        break;
    		    case 4:
    		        medio = new BarcoNacional(0, codMed);
    		        break;
    		    case 5:
    		        medio = new TrenInter(0, codMed);
    		        break;
    		    case 6:
    		        medio = new TrenNacional(0, codMed);
    		        break;
    		    default:
    		        System.out.println("Valor de tipMed no valido");
    		        break;
    			

    		}
    		
    		Company company = new Company(codCo, nomCo);
    		company.setMedio(medio);
    		Viaje viaje = new Viaje(codCo, null, estacionOrigen, estacionDestino, company, 0, null);
    		medio.setViaje(viaje);
    		
    		return viaje;
    	}
		return null;
    }
    
    
    
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
		        int impuestoInter = (int) Math.round(vueloInter.getImpuestoInter());
		        GestorDB.insertarMedio(vueloInter.getCodigoMedio(), impuestoInter, vueloInter.getViaje().getCodigo(), 1);
		        	
		    } else if (medio instanceof VueloNacional) {
		    	VueloNacional vueloNacional = (VueloNacional) medio;
		        int impuestoNacional = (int) Math.round(vueloNacional.getImpuestoNacional());
		        GestorDB.insertarMedio(vueloNacional.getCodigoMedio(), impuestoNacional, vueloNacional.getViaje().getCodigo(), 2);
		        	
		    } else if (medio instanceof TrenInter) {
		    	TrenInter trenInter = (TrenInter) medio;
		    	int impuestoInter = (int) Math.round(trenInter.getImpuestoInter());
		    	GestorDB.insertarMedio(trenInter.getCodigoMedio(), impuestoInter, trenInter.getViaje().getCodigo(), 3);
		        	
		    } else if (medio instanceof TrenNacional) {
		    	TrenNacional trenNacional = (TrenNacional) medio;
		    	int impuestoNacional = (int) Math.round(trenNacional.getImpuestoNacional());
		    	GestorDB.insertarMedio(trenNacional.getCodigoMedio(), impuestoNacional, trenNacional.getViaje().getCodigo(), 4);
		        	
		    } else if (medio instanceof BarcoInter) {
		    	BarcoInter barcoInter = (BarcoInter) medio;
		    	int impuestoInter = (int) Math.round(barcoInter.getImpuestoNacional());
		     	GestorDB.insertarMedio(barcoInter.getCodigoMedio(), impuestoInter, barcoInter.getViaje().getCodigo(), 5);
		        	
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
		
		borrarCodigoViaje(viaje.getCodigo());
		
	}
	
	public static void eliminarViajeCodigo(String codMed, String codVia) {
		Viaje viaje = recuperarViaje(codVia);

		List<Asiento> asientos = viaje.getAsientos();
		for (Asiento asiento : asientos) {
			GestorDB.eliminarAsiento(asiento.getId(), asiento.getViaje().getCodigo());
		}

		GestorDB.eliminarMedio(codMed);

		if(GestorDB.existeViaje(codVia)) {
			borrarCodigoViaje(codVia);
		}
		
		GestorDB.eliminarViaje(codVia);
		
	}
	
	
	public static void modificarViaje(Viaje viaje) {
		// modificar estaciones de origen y destino
				Estacion origen = viaje.getOrigen();
				Estacion destino = viaje.getDestino();
				GestorDB.modificarEstacion(origen.getCodigo(), origen.getNombre(), origen.getCiudad(), origen.getPais());
				GestorDB.modificarEstacion(destino.getCodigo(), destino.getNombre(), destino.getCiudad(), destino.getPais());

				Company company = viaje.getCompany();

				if(company.getMedio().getCodigoMedio() == null) {
					GestorDB.modificarCompany(company.getCodigo(), company.getNombre(), "");

				}else {
					GestorDB.modificarCompany(company.getCodigo(), company.getNombre(), company.getMedio().getCodigoMedio());

					}
				       
				// modificar el medio de transporte (si existe)
				Medio medio = company.getMedio();
					if (medio != null) {
					if (medio instanceof VueloInter) {
						VueloInter vueloInter = (VueloInter) medio;
				        int impuestoNacional = (int) Math.round(vueloInter.getImpuestoNacional());
				        GestorDB.modificarMedio(vueloInter.getCodigoMedio(), impuestoNacional, vueloInter.getViaje().getCodigo(), 1);
				        	
				    } else if (medio instanceof VueloNacional) {
				    	VueloNacional vueloNacional = (VueloNacional) medio;
				        int impuestoNacional = (int) Math.round(vueloNacional.getImpuestoNacional());
				        GestorDB.modificarMedio(vueloNacional.getCodigoMedio(), impuestoNacional, vueloNacional.getViaje().getCodigo(), 2);
				        	
				    } else if (medio instanceof TrenInter) {
				    	TrenInter trenInter = (TrenInter) medio;
				    	int impuestoNacional = (int) Math.round(trenInter.getImpuestoNacional());
				    	GestorDB.modificarMedio(trenInter.getCodigoMedio(), impuestoNacional, trenInter.getViaje().getCodigo(), 3);
				        	
				    } else if (medio instanceof TrenNacional) {
				    	TrenNacional trenNacional = (TrenNacional) medio;
				    	int impuestoNacional = (int) Math.round(trenNacional.getImpuestoNacional());
				    	GestorDB.modificarMedio(trenNacional.getCodigoMedio(), impuestoNacional, trenNacional.getViaje().getCodigo(), 4);
				        	
				    } else if (medio instanceof BarcoInter) {
				    	BarcoInter barcoInter = (BarcoInter) medio;
				    	int impuestoNacional = (int) Math.round(barcoInter.getImpuestoNacional());
				     	GestorDB.modificarMedio(barcoInter.getCodigoMedio(), impuestoNacional, barcoInter.getViaje().getCodigo(), 5);
				        	
				    } else if (medio instanceof BarcoNacional) {
				    	BarcoNacional barcoNacional = (BarcoNacional) medio;
				    	int impuestoNacional = (int) Math.round(barcoNacional.getImpuestoNacional());
				     	GestorDB.modificarMedio(barcoNacional.getCodigoMedio(), impuestoNacional, barcoNacional.getViaje().getCodigo(), 6);   	
				    	}
				    }
					
				    GestorDB.modificarViaje(viaje.getCodigo(), viaje.getFecha(), viaje.getOrigen().getCodigo(), viaje.getDestino().getCodigo(), viaje.getCompany().getCodigo(), viaje.getPrecioBase());

				    // modificar los asientos
//				    List<Asiento> asientos = viaje.getAsientos();
//				    for (Asiento asiento : asientos) {
//				    GestorDB.modificarAsiento(asiento.getId(), asiento.getPasajero(), asiento.getViaje().getCodigo());
//				        }
				    

	}
	
	public static void recuperarListaAsientos(String codigo_viaje) {
        List<Asiento> listaAsientos = new ArrayList<Asiento>();
        Viaje viaje = recuperarViaje(codigo_viaje);
      for(Integer i = 0; i < 20; i++) {
    	Asiento asiento = GestorDB.recuperarAsiento(i, codigo_viaje, viaje);
    	if(asiento != null) {
    		System.out.println((i+1)+ ": "+asiento.getPasajero()+ " (ocupado)");
    	}else {
    		System.out.println((i+1)+". libre");
    	}
    }
    if(listaAsientos != null) {
    	viaje.setAsientos(listaAsientos);
    }
	}

	
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
		Medio medio = GestorDB.recuperarMedio(codigo_medio);
		company.setMedio(medio);
		
		Long fecha = GestorDB.recuperarFechaDeViaje(codigo_viaje);
		Integer precioBase = GestorDB.recuperarPrecioDeViaje(codigo_viaje);
		//sacar objeto medio del company
		//crear objeto viaje
		Viaje viaje = new Viaje(codigo_viaje, fecha, origen, destino, company, precioBase, new ArrayList<>());
		//sacar asientos
		//no recupera el medios
		if(medio != null) {
			medio.setViaje(viaje);
		}
		
        List<Asiento> listaAsientos = new ArrayList<Asiento>();
        
        //recuperar todos los 20 asientos llenos y vacios del vuelo
        //FIXME!
//        for(Integer i = 0; i < 20; i++) {
//        	Asiento asiento = GestorDB.recuperarAsiento(i, codigo_viaje, viaje);
//        	if(asiento != null) {
//        		listaAsientos.add(asiento);
//        	}
//        }
        if(listaAsientos != null) {
        	viaje.setAsientos(listaAsientos);
        }
        
		return viaje;
	}
	
	
	public static void mostrarDetallesViaje(Viaje viaje) {
        System.out.println("\n\nCodigo del viaje: " + viaje.getCodigo() + 
                           "\nFecha: " + Metodos.LongAFecha(viaje.getFecha()) + 
                           "\nCodigo estacion origen: " + viaje.getOrigen().getCodigo() + 
                           "\nNombre pais origen: " + viaje.getOrigen().getPais() + 
                           "\nCodigo estacion destino: " + viaje.getDestino().getCodigo() + 
                           "\nNombre pais destino: " + viaje.getDestino().getPais() + 
                           "\nCodigo de la compañía: " + viaje.getCompany().getCodigo() + 
                           "\nNombre de la compañía: " + viaje.getCompany().getNombre() + 
                           "\nPrecio base del viaje: " + viaje.getPrecioBase());
        if(viaje.getCompany().getMedio() != null) {
            System.out.println("Precio completo del viaje: " + viaje.getCompany().getMedio().calcularPrecio(viaje));
        }


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
	
	
	public static Map<String, Viaje> cargarTodosViajes() {
		Map<String, Viaje> mapaViajes = new HashMap<>();
		List<String> codigos = cargarCodigosViaje();
		if (codigos != null) {
		for (String codigoViaje : codigos) {
			Viaje viaje = recuperarViaje(codigoViaje);
//			mostrarDetallesViaje(viaje);
			mapaViajes.put(codigoViaje, viaje);
			}
		}else {
            System.out.println("La lista de codigos esta vacia.");
        }
		
		return mapaViajes;
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
    
    public static void borrarCodigoViaje(String codigoViaje) {
        File inputFile = new File(nombre_fichero);
        File tempFile = new File("tempFile.txt");

        try (Scanner scanner = new Scanner(inputFile);
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (!currentLine.equals(codigoViaje)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete()) {
            System.out.println("No se pudo borrar el archivo original.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("No se pudo renombrar el archivo temporal.");
        }
        System.out.println("Código de viaje borrado del fichero.");
    }
    
    public static List<String> cargarCodigosViaje() {
        List<String> codigos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombre_fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                codigos.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return codigos;
    }
    
    public static void eliminarCodigosViaje() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre_fichero))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
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
    
    
    public static Double calcularPrecioViaje(Viaje viaje) {
    	Double precio = viaje.getCompany().getMedio().calcularPrecio(viaje);
		return precio;
    }
    
    public static Double calcularMediaPrecio(Map<String, Viaje> mapaViajes){
    	
    	 if (mapaViajes == null || mapaViajes.isEmpty()) {
             System.out.println("El mapa de viajes está vacío o es nulo.");
             return 0.0;
         }
    	
    	Double total = 0.0;
    	for (String viaje : mapaViajes.keySet()) {
    		Viaje v = mapaViajes.get(viaje);
			total += calcularPrecioViaje(v);
		}
    	return total / mapaViajes.size();
    	
    }
    
}
