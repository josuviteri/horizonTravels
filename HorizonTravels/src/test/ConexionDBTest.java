package test;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import database.ConexionDB;

public class ConexionDBTest {

    private static Connection conexion;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Establecer la conexión antes de todas las pruebas
        conexion = ConexionDB.obtenerConexion();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Cerrar la conexión después de todas las pruebas
        ConexionDB.cerrarConexion(conexion);
    }

    @Test
    public void testObtenerConexion() {
        // Verificar que la conexión no sea nula
        assertNotNull(conexion);
        try {
            // Verificar que la conexión esté abierta
            assertFalse(conexion.isClosed());
        } catch (SQLException e) {
            fail("Error al verificar el estado de la conexión: " + e.getMessage());
        }
    }

    
    @Test
    public void testCerrarConexion() {
        ConexionDB.cerrarConexion(conexion);
        try {
            assertTrue(conexion.isClosed());
        } catch (SQLException e) {
            fail("Error al verificar el estado de la conexión: " + e.getMessage());
        }
    }

}

