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
    public void testInsertarEliminarViaje() {
        GestorDB.insertarViaje("COD001", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        assertTrue(GestorDB.existeViaje("COD001"));

        GestorDB.eliminarViaje("COD001");
        assertFalse(GestorDB.existeViaje("COD001"));
    }


    
//    @Test
//    public void testModificarViaje() {
//        // Inserta un viaje
//        GestorDB.insertarViaje("COD002", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
//        // Modifica el viaje
//        GestorDB.modificarViaje("COD002", System.currentTimeMillis(), "ORIGEN002", "DESTINO002", "COMP002", 200.0);
//
//        // Verifica que el viaje se haya modificado correctamente
//        assertEquals("ORIGEN002", GestorDB.recuperarCodigoOrigenDeViaje("COD002"));
//        assertEquals("DESTINO002", GestorDB.recuperarCodigoDestinoDeViaje("COD002"));
//        assertEquals(200.0, GestorDB.recuperarPrecioDeViaje("COD002"), 0.0);
//    }
//



//
    @Test
    public void testRecuperarDatosDeViaje() {
        GestorDB.insertarViaje("COD007", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);

        assertEquals("ORIGEN001", GestorDB.recuperarCodigoOrigenDeViaje("COD007"));
        assertEquals("DESTINO001", GestorDB.recuperarCodigoDestinoDeViaje("COD007"));
        assertNotNull(GestorDB.recuperarFechaDeViaje("COD007"));
        assertNotNull(GestorDB.recuperarPrecioDeViaje("COD007"));
    }
  
    @Test
    public void testInsertarYEliminarAsiento() {
        GestorDB.insertarAsiento(1, "Pasajero1", "COD001");
        assertTrue(GestorDB.existeAsiento(1, "COD001"));

        GestorDB.eliminarAsiento(1, "COD001");
        assertFalse(GestorDB.existeAsiento(1, "COD001"));
    }

//    @Test
//    public void testModificarAsiento() {
//        // Inserta un asiento
//        GestorDB.insertarAsiento(1, "Pasajero1", "COD002");
//        // Modifica el asiento
//        GestorDB.modificarAsiento(1, "Pasajero2", "COD002");
//
//        // Verifica que el asiento se haya modificado correctamente
//        assertEquals("Pasajero2", GestorDB.recuperarAsiento(1, "COD002", null).getPasajero());
//    }



    @Test
    public void testRecuperarAsiento() {
        GestorDB.insertarAsiento(1, "Pasajero1", "COD004");

        assertNotNull(GestorDB.recuperarAsiento(1, "COD004", null));
    }
    
    @Test
    public void testInsertarYEliminarCompany() {
        GestorDB.insertarCompany("COMP001", "Company1", "MED001");
        assertNotNull(GestorDB.recuperarCompany("COMP001"));

        GestorDB.eliminarCompany("COMP001");
        assertNull(GestorDB.recuperarCompany("COMP001"));
    }

    @Test
    public void testModificarCompany() {
        GestorDB.insertarCompany("COMP002", "Company2", "MED001");
        GestorDB.modificarCompany("COMP002", "Company3", "MED002");

        assertEquals("Company3", GestorDB.recuperarCompany("COMP002").getNombre());
    }



    @Test
    public void testRecuperarCompany() {
        GestorDB.insertarCompany("COMP006", "Company6", "MED001");

        assertNotNull(GestorDB.recuperarCompany("COMP006"));
    }
    
    @Test
    public void testRecuperarCodigoCompDeViaje() {
        GestorDB.insertarViaje("COD001", System.currentTimeMillis(), "ORIGEN001", "DESTINO001", "COMP001", 100.0);
        String codigoComp = GestorDB.recuperarCodigoCompDeViaje("COD001");
        assertEquals("COMP001", codigoComp);
    }
    
    public void testRecuperarCodigoMedioDeCompany() {
        GestorDB.insertarCompany("COMP007", "Company7", "MED007");
        GestorDB.insertarMedio("MED007", 10, "VIAJE007", 1);
        String codigoMedio = GestorDB.recuperarCodigoMedioDeCompany("COMP007");
        assertEquals("MED007", codigoMedio);
    }
    
    @Test
    public void testInsertarModificarEliminarEstacion() {
        GestorDB.insertarEstacion("EST001", "Estación 1", "Ciudad 1", "Pais 1");
        assertTrue(GestorDB.existeEstacion("EST001"));

        GestorDB.modificarEstacion("EST001", "Estación Modificada", "Nueva Ciudad", "Nuevo País");
        assertEquals("Estación Modificada", GestorDB.recuperarEstacion("EST001").getNombre());

        GestorDB.eliminarEstacion("EST001");
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
