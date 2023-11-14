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
                String consulta = "SELECT c.numero_personal, c.id_cuenta, c.contrasenha, c.es_administrativo, \n" +
                    "u.nombre, u.apellido_paterno, u.apellido_materno, u.telefono, u.correo_institucional \n" +
                    "FROM cuentas c INNER JOIN usuarios u \n" +
                    "ON c.numero_personal = u.numero_personal\n" +
                    "WHERE c.numero_personal = ? AND contrasenha = ?";
                PreparedStatement prepararSentencia = conexion.prepareStatement(consulta);
                prepararSentencia.setInt(1, numeroPersonal);
                prepararSentencia.setString(2, contrasenha);
                ResultSet resultado = prepararSentencia.executeQuery();
                cuentaVerificada.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if(resultado.next()){
                    cuentaVerificada.setContrasenha(resultado.getString("contrasenha"));
                    cuentaVerificada.setIdCuenta(resultado.getInt("id_cuenta"));
                    cuentaVerificada.setNumeroPersonal(resultado.getInt("numero_personal")); //TODO
                    cuentaVerificada.setEsAdministrativo(resultado.getBoolean("es_administrativo"));
                    cuentaVerificada.setNombre(resultado.getString("nombre"));
                    cuentaVerificada.setApellidoMaterno(resultado.getString("apellido_materno"));
                    cuentaVerificada.setApellidoPaterno(resultado.getString("apellido_paterno"));
                    cuentaVerificada.setCorreoInstitucional(resultado.getString("correo_institucional"));
                    cuentaVerificada.setTelefono(resultado.getString("telefono"));
                }
                conexion.close();
            } catch (SQLException ex){
                cuentaVerificada.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        }else{
            cuentaVerificada.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return cuentaVerificada;
    }    
}
