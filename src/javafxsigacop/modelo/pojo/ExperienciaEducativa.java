package javafxsigacop.modelo.pojo;

public class ExperienciaEducativa {
    private String nombre;
    private String idProgramaEducativo;
    private String bloque;
    private String seccion;
    private String idPeriodoEscolar;

    public ExperienciaEducativa() {
    }

    public ExperienciaEducativa(String nombre, String bloque, String seccion, String idProgramaEducativo, String idPeriodoEscolar) {
        this.nombre = nombre;
        this.idProgramaEducativo = idProgramaEducativo;
        this.bloque = bloque;
        this.seccion = seccion;
        this.idPeriodoEscolar = idPeriodoEscolar;
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

    public String getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(String idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
