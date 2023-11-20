package javafxsigacop.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javafxsigacop.modelo.pojo.ExperienciaEducativa;
import javafxsigacop.modelo.pojo.PeriodoEscolar;
import javafxsigacop.modelo.pojo.ProgramaEducativo;

public class RecursosEstaticos {
    public static String obtenerNombreDirector() {
        return "Luis Gerardo Montané Jiménez";
    }
            
    public static ArrayList<ProgramaEducativo> obtenerProgramasEducativos() {
        return new ArrayList(Arrays.asList(
            new ProgramaEducativo("IS", "Ingeniería de Software"),
            new ProgramaEducativo("TC", "Tecnologías computacionales"),
            new ProgramaEducativo("RSC", "Redes y Servicios de Cómputo")
        ));
    }
    
    public static ArrayList<PeriodoEscolar> obtenerPeriodosEscolares() {
        return new ArrayList(Arrays.asList(
            new PeriodoEscolar("agofe2024", "Agosto 2023 - febrero 2024"),
            new PeriodoEscolar("feago2024", "Febrero 2024 - agosto 2024")
        ));
    }
    
    public static ArrayList<ExperienciaEducativa> obtenerExperienciasEducativas(String idPrograma, String idPeriodo) {
        ArrayList<ExperienciaEducativa> repositorio = new ArrayList(Arrays.asList(
            new ExperienciaEducativa("Diseño de software", "8", "Quinto", "1", "12", "IS", "agofe2024"),
            new ExperienciaEducativa("Tecnologías para la construcción de software", "6", "Quinto", "2", "8", "IS", "agofe2024"),
            new ExperienciaEducativa("Organización de computadoras", "6", "Tercero", "1", "6", "TC", "agofe2024"),
            new ExperienciaEducativa("Metodología de la investigación", "8", "Segundo", "2", "7", "TC", "agofe2024"),
            new ExperienciaEducativa("Redes inalámbricas", "8", "Sexto", "3", "6", "RSC", "agofe2024"),
            new ExperienciaEducativa("Programación segura", "4", "Octavo", "1", "12", "RSC", "agofe2024"),
            new ExperienciaEducativa("Principios de diseño de software", "6", "Cuarto", "1", "10", "IS", "feago2024"),
            new ExperienciaEducativa("Principios de construcción de software", "6", "Cuarto", "2", "12", "IS", "feago2024"),
            new ExperienciaEducativa("Ingeniería de software", "12", "Cuarto", "1", "6", "TC", "feago2024"),
            new ExperienciaEducativa("Metodologías de desarrollo", "8", "Quinto", "2", "6", "TC", "feago2024"),
            new ExperienciaEducativa("Arquitecturas en red", "8", "Cuarto", "3", "8", "RSC", "feago2024"),
            new ExperienciaEducativa("Desarrollo de sistemas web", "8", "Séptimo", "1", "8", "RSC", "feago2024")
        ));
        
        return new ArrayList(
            repositorio.stream()
                .filter(experiencia -> experiencia.getIdProgramaEducativo().equals(idPrograma) && experiencia.getIdPeriodoEscolar().equals(idPeriodo))
                .collect(Collectors.toList())
        );
    }
}
