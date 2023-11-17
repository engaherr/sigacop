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
import java.util.ArrayList;
import javafxsigacop.modelo.ConexionBD;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.respuestas.ExistenciaProfesor;
import javafxsigacop.respuestas.ListaUsuariosRespuesta;
import javafxsigacop.utils.Constantes;

/**
 *
 * @author dnava
 */
public class CrudProfesoresDAO {
    
    public static int actualizarProfesor (Cuenta profesor){
        
        return 0;
        
    }
    public static ExistenciaProfesor obtenerProfesorPorNumeroPersonal(
        int numeroPersonal
    ) {
        ExistenciaProfesor respuesta = new ExistenciaProfesor();
        respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null) {
            try {
                String consulta = "SELECT numero_personal, nombre, apellido_paterno, "
                        + "apellido_materno, telefono, correo_institucional\n" +
                        "FROM sigacop.usuarios\n" +
                        "WHERE numero_personal = ? ";
                
                PreparedStatement sentenciaPreparada 
                    = conexionDB.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, numeroPersonal);
                
                ResultSet resultado = sentenciaPreparada.executeQuery();
                if(resultado.next()) {
                    Cuenta profesor = new Cuenta();
                    
                    profesor.setNombre(resultado.getString("nombre"));
                    profesor.setApellidoPaterno(resultado.getString("apellido_paterno"));
                    profesor.setApellidoMaterno(resultado.getString("apellido_materno"));
                    profesor.setNumeroPersonal(
                        resultado.getInt("numero_personal")
                    );
                    profesor.setCorreoInstitucional(
                        resultado.getString("correo_institucional")
                    );
                    profesor.setTelefono(resultado.getString("telefono"));
                    
                    respuesta.setProfesor(profesor);
                }
                conexionDB.close();
            } catch(SQLException e) {
                respuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            respuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        
        return respuesta;
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
    public static ListaUsuariosRespuesta recuperarListaUsuarios() {
        ListaUsuariosRespuesta respuesta = new ListaUsuariosRespuesta();
        respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null) {
            try {
                String consulta = "SELECT numero_personal, " +
                    " CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) AS nombre, " +
                    " telefono, correo_institucional FROM sigacop.usuarios;";
                
                PreparedStatement sentenciaPreparada = 
                    conexionDB.prepareStatement(consulta);
                
                ResultSet resultado = sentenciaPreparada.executeQuery();
                
                ArrayList<Cuenta> usuarios = new ArrayList();
                while(resultado.next()) {
                    Cuenta usuario = new Cuenta();

                    usuario.setNombre(resultado.getString("nombre"));
                    usuario.setTelefono(resultado.getString("telefono"));
                    usuario.setCorreoInstitucional(resultado.getString("correo_institucional"));
                    usuario.setNumeroPersonal(resultado.getInt("numero_personal"));

                    usuarios.add(usuario);
                }
                respuesta.setUsuarios(usuarios);
                
                conexionDB.close();
            } catch (SQLException e) {
                respuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        } else {
            respuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        
        return respuesta;
    }
    public static int eliminarUsuario(int numero_personal) {
        int codigoRespuesta = Constantes.OPERACION_EXITOSA;
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null) {
            try {
                String consulta = "DELETE FROM usuarios WHERE numero_personal = ? ";
                
                PreparedStatement sentenciaPreparada = 
                    conexionDB.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, numero_personal);
                
                int registrosAfectados = sentenciaPreparada.executeUpdate();
                if(registrosAfectados != 1) {
                    codigoRespuesta = Constantes.ERROR_CONSULTA;
                }
                
                conexionDB.close();
            } catch (SQLException e) {
                codigoRespuesta = Constantes.ERROR_CONSULTA;
            }
        } else {
            codigoRespuesta = Constantes.ERROR_CONEXION;
        }
        
        return codigoRespuesta;
    }
}
