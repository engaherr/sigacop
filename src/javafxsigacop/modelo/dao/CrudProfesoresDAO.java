/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsigacop.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafxsigacop.modelo.ConexionBD;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utils.Constantes;

/**
 *
 * @author dnava
 */
public class CrudProfesoresDAO {
    public static int actualizarProfesor (Cuenta profesor){
        
        return 0;
        
    }
    public static int obtenerProfesorPorNumeroEmpleado(int numeroEmpleado){
        return 0;
    }
    public static int registrarProfesor(Cuenta profesor) {
        int codigoRespuesta = Constantes.OPERACION_EXITOSA;
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null) {
            try {
                String registroUsuario = "INSERT INTO usuarios "
                    + "(numero_personal, nombre, apellido_paterno, apellido_materno, telefono, correo_institucional)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
                
                PreparedStatement sentenciaPreparada = conexionDB.prepareStatement(
                        registroUsuario,
                        Statement.RETURN_GENERATED_KEYS
                );
                sentenciaPreparada.setInt(1, profesor.getNumeroPersonal());
                sentenciaPreparada.setString(2, profesor.getNombre());
                sentenciaPreparada.setString(3, profesor.getApellidoPaterno());
                sentenciaPreparada.setString(4, profesor.getApellidoMaterno());
                sentenciaPreparada.setString(5, profesor.getTelefono());
                sentenciaPreparada.setString(6, profesor.getCorreoInstitucional());
                
                int filasAfectadas = sentenciaPreparada.executeUpdate();
                if(filasAfectadas != 1) {
                    codigoRespuesta = Constantes.ERROR_CONSULTA;
                }
                conexionDB.close();
            } catch(SQLException e) {
                codigoRespuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            codigoRespuesta = Constantes.ERROR_CONEXION;
        }
        return codigoRespuesta;
    } 
    
}
