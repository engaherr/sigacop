package javafxsigacop.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafxsigacop.modelo.ConexionBD;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utils.Constantes;

public class SesionDAO {
    public static Cuenta verificarSesion(int numeroPersonal, String contrasenha){
        Cuenta cuentaVerificada = new Cuenta();
        Connection conexion = ConexionBD.abrirConexionBD();
        if(conexion != null){
            try{
                String consulta = "SELECT * FROM cuentas WHERE numero_personal = ? and contrasenha = ?;";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, numeroPersonal);
                System.out.println("Setteo de numeroPersonal correcto");
                prepararSentencia.setString(2, contrasenha);
                System.out.println("Setteo de contra correcto");
                ResultSet resultado = prepararSentencia.executeQuery();
                cuentaVerificada.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if(resultado.next()){
                    cuentaVerificada.setContrasenha(resultado.getString("contrasenha"));
                    System.out.println("Setteo de contra en POJO correcto");
                    cuentaVerificada.setIdCuenta(resultado.getInt("id_cuenta"));
                    System.out.println("Setteo de id en POJO correcto");
                    cuentaVerificada.setNumeroPersonal(resultado.getInt("numero_personal"));
                    System.out.println("Setteo de numeroPersonal en POJO correcto");
                    cuentaVerificada.setEsAdministrativo(resultado.getBoolean("es_administrativo"));
                    System.out.println("Setteo de esAdmin en POJO correcto");
                }
                conexion.close();
                System.out.println("cierre de Bd correcto");
            } catch (SQLException ex){
                cuentaVerificada.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        }else{
            cuentaVerificada.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return cuentaVerificada;
    }
}
