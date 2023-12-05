package javafxsigacop.modelo.pojo;

import java.util.Objects;

public class ProgramaEducativo {
    String identificador;
    String nombre;

    public ProgramaEducativo() {
    }

    public ProgramaEducativo(String identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ProgramaEducativo other = (ProgramaEducativo) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}
