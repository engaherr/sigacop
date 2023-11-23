package javafxsigacop.respuestas;

import javafxsigacop.modelo.pojo.Cuenta;

public class ExistenciaProfesor {
    private Cuenta profesor;
    private int codigoRespuesta;
    public ExistenciaProfesor() {
    }
    public ExistenciaProfesor(Cuenta profesor, int codigoRespuesta) {
        this.profesor = profesor;
        this.codigoRespuesta = codigoRespuesta;
    }

    public Cuenta getProfesor() {
        return profesor;
    }

    public void setProfesor(Cuenta profesor) {
        this.profesor = profesor;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
}
