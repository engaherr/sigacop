package javafxsigacop.utilidades;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilidadesTest {
    @Test
    public void testObtenerFechaActual() {
        System.out.println("obtenerFechaActual");
        String expResult = "1 de diciembre de 2023";
        String result = Utilidades.obtenerFechaActual();
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerFechaActualFormatoBD() {
        System.out.println("obtenerFechaActualFormatoBD");
        String expResult = "2023-12-01";
        String result = Utilidades.obtenerFechaActualFormatoBD();
        assertEquals(expResult, result);
    }

    @Test
    public void testObtenerFechaHoraActual() {
        System.out.println("obtenerFechaHoraActual");
        String expResult = "01-12-2023_00-36";
        String result = Utilidades.obtenerFechaHoraActual();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCifrarContrasenha() throws Exception {
        System.out.println("cifrarContrasenha");
        String contrasenha = "Rodrigo1911_";
        String expResult = "6078c539395232af895a9784550ab29b98ca14f7f06d429fd13da23071b3e6a1";
        String result = Utilidades.cifrarContrasenha(contrasenha);
        assertEquals(expResult, result);
    }
}