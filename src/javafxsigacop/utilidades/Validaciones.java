package javafxsigacop.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {
    public static boolean tieneFormatoNumeroPersonal(String cadenaPrueba) {
        String pattern = "\\d{5}";
        return Pattern.matches(pattern, cadenaPrueba);
    }
    
    public static boolean tieneFormatoTelefono(String telefonoIngresado){
        String pattern = "\\d{10}";
        return Pattern.matches(pattern, telefonoIngresado);
    }
    
    public static boolean correoValido(String correo) {
        if(correo != null && !correo.isEmpty()) {
            Pattern patronCorreo = 
                    Pattern.compile("^([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\."
                            + "([a-z]+))+$");
            Matcher matchPatron = patronCorreo.matcher(correo);
            return matchPatron.find();
        }
        
        return false;
    }
}
