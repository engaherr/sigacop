package javafxsigacop.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javafxsigacop.modelo.pojo.ExperienciaEducativa;
import javafxsigacop.modelo.pojo.PeriodoEscolar;
import javafxsigacop.modelo.pojo.ProgramaEducativo;

public class RecursosEstaticos {
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
            new ExperienciaEducativa("Diseño de software", "Quinto", "1", "IS", "agofe2024"),
            new ExperienciaEducativa("Tecnologías para la construcción de software", "Quinto", "2", "IS", "agofe2024"),
            new ExperienciaEducativa("Organización de computadoras", "Tercero", "1", "TC", "agofe2024"),
            new ExperienciaEducativa("Metodología de la investigación", "Segundo", "2", "TC", "agofe2024"),
            new ExperienciaEducativa("Redes inalámbricas", "Sexto", "3", "RSC", "agofe2024"),
            new ExperienciaEducativa("Programación segura", "Octavo", "1", "RSC", "agofe2024"),
            new ExperienciaEducativa("Principios de diseño de software", "Cuarto", "1", "IS", "feago2024"),
            new ExperienciaEducativa("Principios de construcción de software", "Cuarto", "2", "IS", "feago2024"),
            new ExperienciaEducativa("Ingeniería de software", "Cuarto", "1", "TC", "feago2024"),
            new ExperienciaEducativa("Metodologías de desarrollo", "Quinto", "2", "TC", "feago2024"),
            new ExperienciaEducativa("Arquitecturas en red", "Cuarto", "3", "RSC", "feago2024"),
            new ExperienciaEducativa("Desarrollo de sistemas web", "Séptimo", "1", "RSC", "feago2024")
        ));
        
        return new ArrayList(
            repositorio.stream()
                .filter(experiencia -> experiencia.getIdProgramaEducativo().equals(idPrograma) && experiencia.getIdPeriodoEscolar().equals(idPeriodo))
                .collect(Collectors.toList())
        );
    }
}
