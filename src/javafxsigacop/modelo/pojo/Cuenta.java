package javafxsigacop.modelo.pojo;

public class Cuenta {
    private int idCuenta;
    private String contrasenha;
    private boolean esAdministrativo;
    private int numeroPersonal;
    private int codigoRespuesta;

    private static Cuenta instanciaSingleton = null;

    public Cuenta() {
    }

    public Cuenta(int idCuenta, String contrasenha, boolean esAdministrativo, int numeroPersonal, int codigoRespuesta) {
        this.idCuenta = idCuenta;
        this.contrasenha = contrasenha;
        this.esAdministrativo = esAdministrativo;
        this.numeroPersonal = numeroPersonal;
        this.codigoRespuesta = codigoRespuesta;
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
}
