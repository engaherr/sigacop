/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsigacop.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import javafxsigacop.modelo.pojo.ExperienciaEducativa;
import javafxsigacop.modelo.pojo.PeriodoEscolar;
import javafxsigacop.modelo.pojo.ProgramaEducativo;
import static javafxsigacop.utilidades.RecursosEstaticos.obtenerExperienciasEducativas;
import static javafxsigacop.utilidades.RecursosEstaticos.obtenerNombreDirector;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kikga
 */
public class RecursosEstaticosTest {
    
    @Test
    public void testObtenerNombreDirector(){
        System.out.println("obtenerNombreDirector");
        String expResult = "Luis Gerardo Montané Jiménez";
        String result = obtenerNombreDirector();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObtenerProgramasEducativos(){
        System.out.println("obtenerProgramasEducativos");
        ArrayList expResult = new ArrayList(Arrays.asList(
            new ProgramaEducativo("IS", "Ingeniería de Software"),
            new ProgramaEducativo("TC", "Tecnologías computacionales"),
            new ProgramaEducativo("RSC", "Redes y Servicios de Cómputo")
        ));
        ArrayList result = RecursosEstaticos.obtenerProgramasEducativos();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
    @Test
    public void testObtenerPeriodosEscolares(){
        System.out.println("obtenerPeriodosEscolares");
        ArrayList expResult = new ArrayList(Arrays.asList(
            new PeriodoEscolar("agofe2024", "Agosto 2023 - febrero 2024"),
            new PeriodoEscolar("feago2024", "Febrero 2024 - agosto 2024")
        ));
        ArrayList result = RecursosEstaticos.obtenerPeriodosEscolares();
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    
    @Test
    public void testObtenerExperienciasEducativas() {
        System.out.println("testObtenerExperienciasEducativas");

        String idPrograma = "IS";
        String idPeriodo = "agofe2024";
        
         ArrayList expResult = new ArrayList(Arrays.asList(
            new ExperienciaEducativa("Diseño de software", "8", "Quinto", "1", "12", "IS", "agofe2024"),
            new ExperienciaEducativa("Tecnologías para la construcción de software", "6", "Quinto", "2", "8", "IS", "agofe2024")
        ));
            
        ArrayList result = obtenerExperienciasEducativas(idPrograma, idPeriodo);
        Assert.assertArrayEquals(expResult.toArray(), result.toArray());
    }
}
