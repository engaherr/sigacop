/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsigacop.respuestas;

/**
 *
 * @author dnava
 */
public class ExistenciaPropiedad {
    private int codigoRespuesta;
    private boolean existe;

    public ExistenciaPropiedad() {
    }

    public ExistenciaPropiedad(int codigoRespuesta, boolean existe) {
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
