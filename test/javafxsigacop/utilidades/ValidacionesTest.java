package javafxsigacop.utilidades;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidacionesTest {
    @Test
    public void testTieneFormatoNumeroPersonal() {
        System.out.println("tieneFormatoNumeroPersonal");
        String cadenaPrueba = "12345";
        boolean expResult = true;
        boolean result = Validaciones.tieneFormatoNumeroPersonal(cadenaPrueba);
        assertEquals(expResult, result);
    }

    @Test
    public void testTieneFormatoTelefono() {
        System.out.println("tieneFormatoTelefono");
        String telefonoIngresado = "2321345678";
        boolean expResult = true;
        boolean result = Validaciones.tieneFormatoTelefono(telefonoIngresado);
        assertEquals(expResult, result);
    }

    @Test
    public void testCorreoValido() {
        System.out.println("correoValido");
        String correo = "rodrigo@gmail.com";
        boolean expResult = true;
        boolean result = Validaciones.correoValido(correo);
        assertEquals(expResult, result);
    }
    
}
