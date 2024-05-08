package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import database.ConexionDB;
import objetos.*;
import src.Metodos;

class MetodosTest {
	private Viaje viaje; 

	
	 @Before
	    public void setUp() throws Exception {

		 	Estacion estacionOrigen = new Estacion("BI480", "Derio", "Bilbo", "España");
	        Estacion estacionDestino = new Estacion("NY001", "New York", "New York", "EEUU");
	        Medio vueloInternacional = new VueloInter(50, "AvionI002");
	        Company company= new Company("COC324", "Cocompany");

	        company.setMedio(vueloInternacional);
	        viaje = new Viaje("VIA002", System.currentTimeMillis(), estacionOrigen, estacionDestino, company, 800.0, new ArrayList<>());
	        vueloInternacional.setViaje(viaje);

	        List<Asiento> listaAsientos = new ArrayList<>();
	        listaAsientos.add(new Asiento(viaje, "Alice"));
	        listaAsientos.add(new Asiento(viaje, "Bob"));
	        listaAsientos.add(new Asiento(viaje, ""));
	        listaAsientos.add(new Asiento(viaje, "Dave"));
	        listaAsientos.add(new Asiento(viaje, "Eva"));
	        listaAsientos.add(new Asiento(viaje, ""));
	        listaAsientos.add(new Asiento(viaje, "Hannah"));
	        listaAsientos.add(new Asiento(viaje, ""));
	        listaAsientos.add(new Asiento(viaje, "Mike"));
	        listaAsientos.add(new Asiento(viaje, "Sophia"));

	        viaje.setAsientos(listaAsientos);

//	      Metodos.guardarViaje(viaje);
	 }
	 

	 @Test
	    public void testCrearViaje() {
	        // Verificar que se crea un viaje correctamente
	        Viaje nuevoViaje = Metodos.crearViaje("EO1", "Origen", "Ciudad Origen", "Pais Origen", "ED1", "Destino", "Ciudad Destino", "Pais Destino", 1, "M001", 100, "C001", "Compañía", "V001", 500.0, false);
	        assertNotNull(nuevoViaje);
	        assertEquals(viaje.getCodigo(), nuevoViaje.getCodigo());
	    }

	    @Test
	    public void testEliminarViaje() {
	        // Verificar que se elimina un viaje correctamente
	        Metodos.eliminarViaje(viaje);
	        assertNull(Metodos.recuperarViaje(viaje.getCodigo()));
	    }

	    @Test
	    public void testModificarViaje() {
	        // Modificar un viaje y verificar que se actualiza correctamente
	        viaje.getPrecioBase(); // Actualizar precio base para prueba
	        viaje.setPrecioBase(600.0);
	        Metodos.modificarViaje(viaje);
	        assertEquals(600.0, Metodos.recuperarViaje(viaje.getCodigo()).getPrecioBase(), 0.01);
	    }

	    @Test
	    public void testCalcularPrecioViaje() {
	        // Verificar que se calcula el precio del viaje correctamente
	        Double precioEsperado = viaje.getCompany().getMedio().calcularPrecio(viaje);
	        assertEquals(precioEsperado, Metodos.calcularPrecioViaje(viaje), 0.01);
	    }


	
}
