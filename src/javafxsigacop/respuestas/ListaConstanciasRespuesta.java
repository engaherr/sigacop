package javafxsigacop.respuestas;

import java.util.ArrayList;
import javafxsigacop.modelo.pojo.Constancia;

public class ListaConstanciasRespuesta {
    private ArrayList<Constancia> constancias;
    private int codigoRespuesta;

    public ListaConstanciasRespuesta() {
    }

    public ListaConstanciasRespuesta(ArrayList<Constancia> constancias, int codigoRespuesta) {
        this.constancias = constancias;
        this.codigoRespuesta = codigoRespuesta;
    }

    public ArrayList<Constancia> getConstancias() {
        return constancias;
    }

    public void setConstancias(ArrayList<Constancia> constancias) {
        this.constancias = constancias;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
}
