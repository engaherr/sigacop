package javafxsigacop.modelo.pojo;

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
}
