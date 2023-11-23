package javafxsigacop.respuestas;

import java.util.ArrayList;
import javafxsigacop.modelo.pojo.Cuenta;

public class ListaUsuariosRespuesta {
    private ArrayList<Cuenta> usuarios;
    private int codigoRespuesta;

    public ListaUsuariosRespuesta() {
    }

    public ListaUsuariosRespuesta(
        ArrayList<Cuenta> usuarios, 
        int codigoRespuesta
    ) {
        this.usuarios = usuarios;
        this.codigoRespuesta = codigoRespuesta;
    }

    public ArrayList<Cuenta> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Cuenta> usuarios) {
        this.usuarios = usuarios;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
}
