package javafxsigacop.respuestas;

public class ExistenciaRegistro {
    private int codigoRespuesta;
    private boolean existe;

    public ExistenciaRegistro() {
    }

    public ExistenciaRegistro(int codigoRespuesta, boolean existe) {
        this.codigoRespuesta = codigoRespuesta;
        this.existe = existe;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
