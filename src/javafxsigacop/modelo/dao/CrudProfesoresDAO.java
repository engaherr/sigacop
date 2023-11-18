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
        int codigoRespuesta = Constantes.OPERACION_EXITOSA;
        Connection conexionDB = ConexionBD.abrirConexionBD();
            if (conexionDB != null) {
                try {
                    String actualizacionQuery = "UPDATE usuarios " 
                            + "JOIN cuentas ON usuarios.numero_personal = cuentas.numero_personal " 
                            + "SET nombre = ?, " 
                            + "apellido_paterno = ?, " 
                            + "apellido_materno = ?, " 
                            + "correo_institucional = ?, " 
                            + "telefono = ?, "
                            + "contrasenha = ? " 
                            + "WHERE usuarios.numero_personal = ?";

                    PreparedStatement sentenciaPreparada = conexionDB.prepareStatement(actualizacionQuery);
                    sentenciaPreparada.setString(1, profesor.getNombre());
                    sentenciaPreparada.setString(2, profesor.getApellidoPaterno());
                    sentenciaPreparada.setString(3, profesor.getApellidoMaterno());
                    sentenciaPreparada.setString(4, profesor.getCorreoInstitucional());
                    sentenciaPreparada.setString(5, profesor.getTelefono());
                    sentenciaPreparada.setString(6, profesor.getContrasenha());
                    sentenciaPreparada.setInt(7, profesor.getNumeroPersonal());

                    int filasAfectadas = sentenciaPreparada.executeUpdate();
                    if (filasAfectadas < 1 || filasAfectadas > 2) {
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
    
    public static ExistenciaProfesor obtenerProfesorPorNumeroPersonal(int numeroPersonal) {
    ExistenciaProfesor respuesta = new ExistenciaProfesor();
    respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);

    Connection conexionDB = ConexionBD.abrirConexionBD();
    if (conexionDB != null) {
        try {
            String consulta = "SELECT p.numero_personal, p.nombre, " +
                "p.apellido_paterno, p.apellido_materno, p.telefono, " +
                "p.correo_institucional, c.contrasenha " +
                "FROM usuarios p " +
                "INNER JOIN cuentas c ON p.numero_personal = c.numero_personal " +
                "WHERE p.numero_personal = ?";

            PreparedStatement sentenciaPreparada = conexionDB.prepareStatement(consulta);
            sentenciaPreparada.setInt(1, numeroPersonal);

            ResultSet resultado = sentenciaPreparada.executeQuery();
            if (resultado.next()) {
                Cuenta profesor = new Cuenta();

                profesor.setNombre(resultado.getString("nombre"));
                profesor.setApellidoPaterno(resultado.getString("apellido_paterno"));
                profesor.setApellidoMaterno(resultado.getString("apellido_materno"));
                profesor.setNumeroPersonal(resultado.getInt("numero_personal"));
                profesor.setCorreoInstitucional(resultado.getString("correo_institucional"));
                profesor.setContrasenha(resultado.getString("contrasenha"));
                profesor.setTelefono(resultado.getString("telefono"));

                respuesta.setProfesor(profesor);
            }
            conexionDB.close();
        } catch (SQLException e) {
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
    if (conexionDB != null) {
        try {
            String registroUsuario = "INSERT INTO usuarios "
                    + "(numero_personal, nombre, apellido_paterno, "
                    + "apellido_materno, telefono, correo_institucional)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            String registroCuenta = "INSERT INTO cuentas "
                    + "(contrasenha, es_administrativo, numero_personal)"
                    + "VALUES (?, ?, ?)";

            PreparedStatement sentenciaUsuario = conexionDB.prepareStatement(
                    registroUsuario,
                    Statement.RETURN_GENERATED_KEYS
            );
            sentenciaUsuario.setInt(1, profesor.getNumeroPersonal());
            sentenciaUsuario.setString(2, profesor.getNombre());
            sentenciaUsuario.setString(3, profesor.getApellidoPaterno());
            sentenciaUsuario.setString(4, profesor.getApellidoMaterno());
            sentenciaUsuario.setString(5, profesor.getTelefono());
            sentenciaUsuario.setString(6, profesor.getCorreoInstitucional());

            int filasAfectadasUsuario = sentenciaUsuario.executeUpdate();

            if (filasAfectadasUsuario != 1) {
                codigoRespuesta = Constantes.ERROR_CONSULTA;
            } else {
                ResultSet generatedKeys = sentenciaUsuario.getGeneratedKeys();
                int idUsuarioGenerado = -1;
                if (generatedKeys.next()) {
                    idUsuarioGenerado = generatedKeys.getInt(1);
                }

                PreparedStatement sentenciaCuenta = conexionDB.prepareStatement(
                        registroCuenta
                );
                sentenciaCuenta.setString(1, profesor.getContrasenha());
                sentenciaCuenta.setInt(2, 1);
                sentenciaCuenta.setInt(3, profesor.getNumeroPersonal());

                int filasAfectadasCuenta = sentenciaCuenta.executeUpdate();

                if (filasAfectadasCuenta != 1) {
                    codigoRespuesta = Constantes.ERROR_CONSULTA;
                }
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
