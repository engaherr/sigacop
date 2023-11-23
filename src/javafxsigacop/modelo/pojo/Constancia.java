package javafxsigacop.modelo.pojo;

import java.time.LocalDate;

public class Constancia {
    private String tipoConstancia;
    private int idConstancia;
    private LocalDate fechaExpedicion;
    private String fechaExpedicionConFormato;
    private String nombreProfesor;

    public Constancia() {
    }

    public Constancia(
            String tipoConstancia, 
            int idConstancia, 
            LocalDate fechaExpedicion,
            String fechaExpedicionConFormato,
            String nombreProfesor) {
        this.tipoConstancia = tipoConstancia;
        this.idConstancia = idConstancia;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaExpedicionConFormato = fechaExpedicionConFormato;
        this.nombreProfesor = nombreProfesor;
    }

    public String getTipoConstancia() {
        return tipoConstancia;
    }

    public void setTipoConstancia(String tipoConstancia) {
        this.tipoConstancia = tipoConstancia;
    }

    public int getIdConstancia() {
        return idConstancia;
    }

    public void setIdConstancia(int idConstancia) {
        this.idConstancia = idConstancia;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }
    
    public String getFechaExpedicionConFormato() {
        return fechaExpedicionConFormato;
    }

    public void setFechaExpedicionConFormato(String fechaExpedicionConFormato) {
        this.fechaExpedicionConFormato = fechaExpedicionConFormato;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }
}
