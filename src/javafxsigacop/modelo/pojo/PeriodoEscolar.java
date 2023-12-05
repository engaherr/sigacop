package javafxsigacop.modelo.pojo;

import java.util.Objects;

public class PeriodoEscolar {
    private String identificador;
    private String nombre;

    public PeriodoEscolar() {
    }

    public PeriodoEscolar(String identificador, String nombre) {
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
        hash = 53 * hash + Objects.hashCode(this.identificador);
        hash = 53 * hash + Objects.hashCode(this.nombre);
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
        final PeriodoEscolar other = (PeriodoEscolar) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}
