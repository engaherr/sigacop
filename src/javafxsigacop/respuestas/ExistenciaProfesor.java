/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsigacop.respuestas;

import javafxsigacop.modelo.pojo.Cuenta;

/**
 *
 * @author dnava
 */
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
