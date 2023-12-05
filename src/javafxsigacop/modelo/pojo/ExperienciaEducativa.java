package javafxsigacop.modelo.pojo;

import java.util.Objects;

public class ExperienciaEducativa {
    private String nombre;
    private String idProgramaEducativo;
    private String bloque;
    private String seccion;
    private String idPeriodoEscolar;
    private String creditos;
    private String horasSemanaMes;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(String nombre, String horasSemanaMes, String bloque, String seccion, String creditos, String idProgramaEducativo, String idPeriodoEscolar) {
        this.nombre = nombre;
        this.idProgramaEducativo = idProgramaEducativo;
        this.bloque = bloque;
        this.seccion = seccion;
        this.creditos = creditos;
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.horasSemanaMes = horasSemanaMes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdProgramaEducativo() {
        return idProgramaEducativo;
    }

    public void setIdProgramaEducativo(String idProgramaEducativo) {
        this.idProgramaEducativo = idProgramaEducativo;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(String idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public String getHorasSemanaMes() {
        return horasSemanaMes;
    }

    public void setHorasSemanaMes(String horasSemanaMes) {
        this.horasSemanaMes = horasSemanaMes;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.idProgramaEducativo);
        hash = 89 * hash + Objects.hashCode(this.bloque);
        hash = 89 * hash + Objects.hashCode(this.seccion);
        hash = 89 * hash + Objects.hashCode(this.idPeriodoEscolar);
        hash = 89 * hash + Objects.hashCode(this.creditos);
        hash = 89 * hash + Objects.hashCode(this.horasSemanaMes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExperienciaEducativa other = (ExperienciaEducativa) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.idProgramaEducativo, other.idProgramaEducativo)) {
            return false;
        }
        if (!Objects.equals(this.bloque, other.bloque)) {
            return false;
        }
        if (!Objects.equals(this.seccion, other.seccion)) {
            return false;
        }
        if (!Objects.equals(this.idPeriodoEscolar, other.idPeriodoEscolar)) {
            return false;
        }
        if (!Objects.equals(this.creditos, other.creditos)) {
            return false;
        }
        return Objects.equals(this.horasSemanaMes, other.horasSemanaMes);
    }
    
    
}
