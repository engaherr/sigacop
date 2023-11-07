package javafxsigacop.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String nombreBase = "sigacop";
    private static String hostname = "localhost";
    private static String puerto = "3306";
    
    private static String usuario = "sigacop_owner";
    private static String password = "adminps";

    private static String urlConexion = "jdbc:mysql://" + hostname + ":" + puerto 
            + "/" + nombreBase +"?allowPublicKeyRetrieval=true&useSSL=false";
    
    public static Connection abrirConexionBD(){
        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlConexion,usuario,password);
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Error de conexion con BD: " + ex.getMessage());
            ex.printStackTrace();
        }
        return conexion;
    }
}
