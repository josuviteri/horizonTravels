package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import database.GestorDB;
import objetos.Viaje;
import src.Menu;
import src.Metodos;

class MetodosTest {


	 @Test
	    public void testCrearViaje() {
	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        assertNotNull(nuevoViaje);
	        assertEquals("V001", nuevoViaje.getCodigo());
	    }
	 
	 @Test
	    public void testEliminarViaje() {
	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        Metodos.guardarViaje(nuevoViaje);
	        assertNotNull(nuevoViaje);
	        Metodos.eliminarViaje(nuevoViaje);
	        Metodos.recuperarViaje(nuevoViaje.getCodigo());
	        
	    }

	    @Test
	    public void testCalcularPrecioViaje() {
	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        Metodos.guardarViaje(nuevoViaje);
	        Double precioEsperado = nuevoViaje.getCompany().getMedio().calcularPrecio(nuevoViaje);
	        assertEquals(precioEsperado, Metodos.calcularPrecioViaje(nuevoViaje), 0.01);
	    }
	    
	    @Test
	    public void testCargarTodosViajes() {
	        GestorDB.borrarContenidoTablas();
	        Metodos.eliminarTodosCodigosViaje();
	        
	    	Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        Metodos.guardarViaje(nuevoViaje);

	        assertNotNull(nuevoViaje);
	        
	        Map<String, Viaje> mapaViajes = Metodos.cargarTodosViajes();
	        
	        assertNotNull(mapaViajes);
	        
	        assertFalse(mapaViajes.isEmpty());
	        
	        assertEquals(1, mapaViajes.size());
	        
	        assertTrue(mapaViajes.containsKey("V001"));
	    }
	    
	    @Test
	    public void testCargarCodigosViaje() {
	        GestorDB.borrarContenidoTablas();
	        Metodos.eliminarTodosCodigosViaje();
	        Menu.crearInsertarViajesPredeterminados();
	        
	        List<String> codigos = Metodos.cargarCodigosViaje();
	        
	        assertNotNull(codigos);
	        
	        assertFalse(codigos.isEmpty());
	        
	        assertTrue(codigos.contains("VIA001"));
	        assertTrue(codigos.contains("VIA002"));
	        assertTrue(codigos.contains("VIA003"));
	        
	        assertEquals(3, codigos.size());
	    }
	    
	    @Test
	    public void testEliminarTodosCodigosViaje() {
	    	
	        Metodos.guardarCodigoViaje("COD123");

	        Metodos.eliminarTodosCodigosViaje();

	        List<String> codigos = Metodos.cargarCodigosViaje();
	        
	        assertTrue(codigos.size() == 0);
	    }
	    
	    
	    @Test
	    public void testCodigoYaExiste() {
	        Metodos.eliminarTodosCodigosViaje();
	        Metodos.guardarCodigoViaje("COD123");

	        assertTrue(Metodos.codigoYaExiste("COD123"));
	    	
	    }
	    
	    @Test
	    public void testCalcularMediaPrecio() {
	        GestorDB.borrarContenidoTablas();
	        Metodos.eliminarTodosCodigosViaje();
	        Menu.crearInsertarViajesPredeterminados();
	        
	        Map<String, Viaje> mapaViajes = Metodos.cargarTodosViajes();

	        Double media = Metodos.calcularMediaPrecio(mapaViajes);
	        assertEquals(3886.6666666666665, media);
	    }
	    
	    @Test
	    public void testLongAFecha() {
	        long fechaEnMilisegundos = 1620900000000L; 
	        String fechaFormateada = Metodos.LongAFecha(fechaEnMilisegundos);
	        assertEquals("2021-05-13 12:00:00", fechaFormateada);
	    	
	    }


}
