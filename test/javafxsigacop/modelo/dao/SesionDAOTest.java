/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsigacop.modelo.dao;

import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utilidades.Constantes;
import org.junit.Test;
import static org.junit.Assert.*;

public class SesionDAOTest {
    
    @Test
    public void testVerificarSesion(){
        System.out.println("verificarSesion");
        int numeroPersonal = 00000;
        String password = "test";
        Cuenta usuarioEsperado = new Cuenta();
        usuarioEsperado.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Cuenta usuarioRespuesta = SesionDAO.verificarSesion(numeroPersonal, password);
        assertEquals(usuarioEsperado.getCodigoRespuesta(), usuarioRespuesta.getCodigoRespuesta());
    }
}
