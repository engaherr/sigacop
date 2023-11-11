package javafxsigacop.modelo.pojo;

public class Cuenta {
    private int idCuenta;
    private String contrasenha;
    private boolean esAdministrativo;
    private int numeroPersonal;
    private int codigoRespuesta;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correoInstitucional;

    private static Cuenta instanciaSingleton = null;

    public Cuenta() {
    }

    public Cuenta(int idCuenta, String contrasenha, boolean esAdministrativo, int numeroPersonal, int codigoRespuesta, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String correoInstitucional) {
        this.idCuenta = idCuenta;
        this.contrasenha = contrasenha;
        this.esAdministrativo = esAdministrativo;
        this.numeroPersonal = numeroPersonal;
        this.codigoRespuesta = codigoRespuesta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoInstitucional = correoInstitucional;
    }
    
    public static Cuenta getInstanciaSingleton() {
        return instanciaSingleton;
    }   
    
    public static void setInstanciaSingleton(Cuenta instanciaSingleton){
        Cuenta.instanciaSingleton = instanciaSingleton;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public boolean isEsAdministrativo() {
        return esAdministrativo;
    }

    public void setEsAdministrativo(boolean esAdministrativo) {
        this.esAdministrativo = esAdministrativo;
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
    
    
}
