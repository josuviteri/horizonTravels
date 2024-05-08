package test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.GestorDB;
import database.ConexionDB;

public class GestorDBTest {
    private static Connection conexion;

    @Before
    public void setUp() throws Exception {
        conexion = ConexionDB.obtenerConexion();
        GestorDB.crearTablaViaje(); 
        GestorDB.crearTablaAsiento();
        GestorDB.crearTablaCompany();
        GestorDB.crearTablaMedio(); 
    }

    @After
    public void tearDown() throws Exception {
        ConexionDB.cerrarConexion(conexion); // Cierra la conexión con la base de datos después de cada prueba
    }

    @Test
    public void testInsertarYEliminarViaje() {
        // Inserta un viaje
        GestorDB.insertarViaje("COD001", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        // Verifica que el viaje se haya insertado correctamente
        assertTrue(GestorDB.existeViaje("COD001"));

        // Elimina el viaje
        GestorDB.eliminarViaje("COD001");
        // Verifica que el viaje se haya eliminado correctamente
        assertFalse(GestorDB.existeViaje("COD001"));
    }

    @Test
    public void testModificarViaje() {
        // Inserta un viaje
        GestorDB.insertarViaje("COD002", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        // Modifica el viaje
        GestorDB.modificarViaje("COD002", System.currentTimeMillis(), "ORIGEN002", "DESTINO002", "COMP002", 200.0);

        // Verifica que el viaje se haya modificado correctamente
        assertEquals("ORIGEN002", GestorDB.recuperarCodigoOrigenDeViaje("COD002"));
        assertEquals("DESTINO002", GestorDB.recuperarCodigoDestinoDeViaje("COD002"));
        assertEquals(200.0, GestorDB.recuperarPrecioDeViaje("COD002"), 0.0);
    }

    @Test
    public void testMostrarTodosViajes() {
        // Inserta algunos viajes
        GestorDB.insertarViaje("COD003", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        GestorDB.insertarViaje("COD004", System.currentTimeMillis(), "ORIGEN002", "DESTINO002", "COMP002", 200.0);
        GestorDB.insertarViaje("COD005", System.currentTimeMillis(), "ORIGEN003", "DESTINO003", "COMP003", 300.0);

        // Muestra todos los viajes
        GestorDB.mostrarTodosViajes(); // Simplemente verifica que no se produzca una excepción
    }

    @Test
    public void testMostrarDatosViaje() {
        // Inserta un viaje
        GestorDB.insertarViaje("COD006", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);

        // Muestra los datos del viaje
        GestorDB.mostrarDatosViaje("COD006"); // Simplemente verifica que no se produzca una excepción
    }

    @Test
    public void testRecuperarDatosDeViaje() {
        // Inserta un viaje
        GestorDB.insertarViaje("COD007", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);

        // Recupera datos del viaje
        assertEquals("ORIGEN001", GestorDB.recuperarCodigoOrigenDeViaje("COD007"));
        assertEquals("DESTINO001", GestorDB.recuperarCodigoDestinoDeViaje("COD007"));
        assertNotNull(GestorDB.recuperarFechaDeViaje("COD007"));
        assertNotNull(GestorDB.recuperarPrecioDeViaje("COD007"));
    }
    
    @Test
    public void testInsertarYEliminarAsiento() {
        // Inserta un asiento
        GestorDB.insertarAsiento(1, "Pasajero1", "COD001");
        // Verifica que el asiento se haya insertado correctamente
        assertTrue(GestorDB.existeAsiento(1, "COD001"));

        // Elimina el asiento
        GestorDB.eliminarAsiento(1, "COD001");
        // Verifica que el asiento se haya eliminado correctamente
        assertFalse(GestorDB.existeAsiento(1, "COD001"));
    }

    @Test
    public void testModificarAsiento() {
        // Inserta un asiento
        GestorDB.insertarAsiento(1, "Pasajero1", "COD002");
        // Modifica el asiento
        GestorDB.modificarAsiento(1, "Pasajero2", "COD002");

        // Verifica que el asiento se haya modificado correctamente
        assertEquals("Pasajero2", GestorDB.recuperarAsiento(1, "COD002", null).getPasajero());
    }

    @Test
    public void testMostrarAsientos() {
        // Inserta algunos asientos
        GestorDB.insertarAsiento(1, "Pasajero1", "COD003");
        GestorDB.insertarAsiento(2, "Pasajero2", "COD003");
        GestorDB.insertarAsiento(3, "Pasajero3", "COD003");

        // Muestra todos los asientos
        GestorDB.mostrarAsientos(); // Simplemente verifica que no se produzca una excepción
    }

    @Test
    public void testRecuperarAsiento() {
        // Inserta un asiento
        GestorDB.insertarAsiento(1, "Pasajero1", "COD004");

        // Recupera el asiento
        assertNotNull(GestorDB.recuperarAsiento(1, "COD004", null));
    }
    
    @Test
    public void testInsertarYEliminarCompany() {
        // Inserta una compañía
        GestorDB.insertarCompany("COMP001", "Company1", "MED001");
        // Verifica que la compañía se haya insertado correctamente
        assertNotNull(GestorDB.recuperarCompany("COMP001"));

        // Elimina la compañía
        GestorDB.eliminarCompany("COMP001");
        // Verifica que la compañía se haya eliminado correctamente
        assertNull(GestorDB.recuperarCompany("COMP001"));
    }

    @Test
    public void testModificarCompany() {
        // Inserta una compañía
        GestorDB.insertarCompany("COMP002", "Company2", "MED001");
        // Modifica la compañía
        GestorDB.modificarCompany("COMP002", "Company3", "MED002");

        // Verifica que la compañía se haya modificado correctamente
        assertEquals("Company3", GestorDB.recuperarCompany("COMP002").getNombre());
    }

    @Test
    public void testMostrarCompanies() {
        // Inserta algunas compañías
        GestorDB.insertarCompany("COMP003", "Company3", "MED001");
        GestorDB.insertarCompany("COMP004", "Company4", "MED002");
        GestorDB.insertarCompany("COMP005", "Company5", "MED003");

        // Muestra todas las compañías
        GestorDB.mostrarCompanies(); // Simplemente verifica que no se produzca una excepción
    }

    @Test
    public void testRecuperarCompany() {
        // Inserta una compañía
        GestorDB.insertarCompany("COMP006", "Company6", "MED001");

        // Recupera la compañía
        assertNotNull(GestorDB.recuperarCompany("COMP006"));
    }
    
    @Test
    public void testRecuperarCodigoCompDeViaje() {
        // Inserta un viaje
        GestorDB.insertarViaje("COD001", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        // Recupera el código de compañía del viaje
        String codigoComp = GestorDB.recuperarCodigoCompDeViaje("COD001");
        // Verifica que el código de compañía recuperado sea el esperado
        assertEquals("COMP001", codigoComp);
    }
    
    public void testRecuperarCodigoMedioDeCompany() {
        // Inserta una compañía
        GestorDB.insertarCompany("COMP007", "Company7", "MED007");
        // Inserta un medio
        GestorDB.insertarMedio("MED007", 10, "VIAJE007", 1);
        // Recupera el código de medio de la compañía
        String codigoMedio = GestorDB.recuperarCodigoMedioDeCompany("COMP007");
        // Verifica que el código de medio recuperado sea el esperado
        assertEquals("MED007", codigoMedio);
    }
    
    @Test
    public void testInsertarModificarEliminarEstacion() {
        // Insertar estación
        GestorDB.insertarEstacion("EST001", "Estación 1", "Ciudad 1", "Pais 1");
        // Verificar si la estación fue insertada correctamente
        assertTrue(GestorDB.existeEstacion("EST001"));

        // Modificar estación
        GestorDB.modificarEstacion("EST001", "Estación Modificada", "Nueva Ciudad", "Nuevo País");
        // Verificar si la estación fue modificada correctamente
        assertEquals("Estación Modificada", GestorDB.recuperarEstacion("EST001").getNombre());

        // Eliminar estación
        GestorDB.eliminarEstacion("EST001");
        // Verificar si la estación fue eliminada correctamente
        assertFalse(GestorDB.existeEstacion("EST001"));
    }
    
    
    @Test
    public void testBorrarContenidoTablas() {
        // Insertar algunos datos en las tablas para probar la eliminación del contenido
        GestorDB.insertarEstacion("EST001", "Estación 1", "Ciudad 1", "Pais 1");
        GestorDB.insertarCompany("COMP001", "Company 1", "MED001");
        GestorDB.insertarMedio("MED001", 10, "VIA001", 1);
        GestorDB.insertarAsiento(1, "Pasajero 1", "VIA001");

        // Borrar el contenido de las tablas
        GestorDB.borrarContenidoTablas();

        // Verificar que todas las tablas estén vacías después de borrar su contenido
        assertFalse(GestorDB.existeEstacion("EST001"));
        assertFalse(GestorDB.existeCompany("COMP001"));
        assertFalse(GestorDB.existeMedio("MED001"));
        assertFalse(GestorDB.existeAsiento(1, "VIA001"));
    }

}
