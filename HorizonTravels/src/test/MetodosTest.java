package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import objetos.Viaje;
import src.Metodos;

class MetodosTest {


	 @Test
	    public void testCrearViaje() {
	        // Verificar que se crea un viaje correctamente
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

//	    @Test
//	    public void testModificarViaje() {
//	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
//	        Metodos.guardarViaje(nuevoViaje);
//	        nuevoViaje.setPrecioBase(600.0);
//	        Metodos.modificarViaje(nuevoViaje);
//	        assertEquals(600.0, Metodos.recuperarViaje("V001").getPrecioBase());
//	    }

	    @Test
	    public void testCalcularPrecioViaje() {
	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        Metodos.guardarViaje(nuevoViaje);
	        Double precioEsperado = nuevoViaje.getCompany().getMedio().calcularPrecio(nuevoViaje);
	        assertEquals(precioEsperado, Metodos.calcularPrecioViaje(nuevoViaje), 0.01);
	    }


}
