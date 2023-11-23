package javafxsigacop.respuestas;

import javafxsigacop.modelo.pojo.ConstanciaEE;

public class ConstanciaEERespuesta {
    private ConstanciaEE constanciaEE;
    private int codigoRespuesta;

    public ConstanciaEERespuesta() {
    }

    public ConstanciaEERespuesta(ConstanciaEE constanciaEE, int codigoRespuesta) {
        this.constanciaEE = constanciaEE;
        this.codigoRespuesta = codigoRespuesta;
    }

    public ConstanciaEE getConstanciaEE() {
        return constanciaEE;
    }

    public void setConstanciaEE(ConstanciaEE constanciaEE) {
        this.constanciaEE = constanciaEE;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
}
