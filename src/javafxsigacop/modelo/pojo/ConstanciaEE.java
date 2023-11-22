package javafxsigacop.modelo.pojo;

public class ConstanciaEE {
    private int idConstancia;
    private String fechaExpedicion;
    private String nombreDirector;
    private int numeroPersonal;
    private String bloque;
    private String seccion;
    private String horasSemanaMes;
    private String programaEducativo;
    private String experienciaEducativa;
    private String periodoEscolar;
    private String creditosExperiencia;

    public ConstanciaEE() {
    }

    public ConstanciaEE(int idConstancia, String fechaExpedicion, String nombreDirector, int numeroPersonal, String bloque, String seccion, String horasSemanaMes, String programaEducativo, String experienciaEducativa, String periodoEscolar, String creditosExperiencia) {
        this.idConstancia = idConstancia;
        this.fechaExpedicion = fechaExpedicion;
        this.nombreDirector = nombreDirector;
        this.numeroPersonal = numeroPersonal;
        this.bloque = bloque;
        this.seccion = seccion;
        this.horasSemanaMes = horasSemanaMes;
        this.programaEducativo = programaEducativo;
        this.experienciaEducativa = experienciaEducativa;
        this.periodoEscolar = periodoEscolar;
        this.creditosExperiencia = creditosExperiencia;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
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

    public String getHorasSemanaMes() {
        return horasSemanaMes;
    }

    public void setHorasSemanaMes(String horasSemanaMes) {
        this.horasSemanaMes = horasSemanaMes;
    }

    public String getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(String programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public String getCreditosExperiencia() {
        return creditosExperiencia;
    }

    public void setCreditosExperiencia(String creditosExperiencia) {
        this.creditosExperiencia = creditosExperiencia;
    }
}
