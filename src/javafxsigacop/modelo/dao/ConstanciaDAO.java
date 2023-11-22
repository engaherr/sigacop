package javafxsigacop.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafxsigacop.modelo.ConexionBD;
import javafxsigacop.modelo.pojo.ConstanciaEE;
import javafxsigacop.utils.Constantes;

public class ConstanciaDAO {
    public static int guardarConstanciaEE(ConstanciaEE constancia) {
        int respuesta = Constantes.ERROR_CONEXION;
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null) {
            try {
                respuesta = Constantes.ERROR_CONSULTA;
                String consultaRegistroConstancia = "INSERT INTO constancias "
                    + "(fecha_expedicion, nombre_director, numero_personal) "
                    + "VALUES (?, ?, ?)";
                
                PreparedStatement sentenciaConstancia = conexionDB.prepareStatement(
                    consultaRegistroConstancia,
                    Statement.RETURN_GENERATED_KEYS
                );
                sentenciaConstancia.setString(1, constancia.getFechaExpedicion());
                sentenciaConstancia.setString(2, constancia.getNombreDirector());
                sentenciaConstancia.setInt(3, constancia.getNumeroPersonal());
                
                int constanciasAfectadas = sentenciaConstancia.executeUpdate();
                if (constanciasAfectadas == 1) {
                    ResultSet llavesGeneradas = sentenciaConstancia.getGeneratedKeys();
                    if (llavesGeneradas.next()) {
                        int idConstancia = llavesGeneradas.getInt(1);
                        String consultaRegistroConstanciaEE = 
                            "INSERT INTO constancias_imparticion_ee "
                            + "(id_constancia, bloque, seccion, "
                            + "horas_semana_mes, programa_educativo, "
                            + "experiencia_educativa, periodo_escolar, "
                            + "creditos_experiencia) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement sentenciaConstanciaEE = conexionDB.prepareStatement(
                            consultaRegistroConstanciaEE
                        );
                        sentenciaConstanciaEE.setInt(1, idConstancia);
                        sentenciaConstanciaEE.setString(2, constancia.getBloque());
                        sentenciaConstanciaEE.setString(3, constancia.getSeccion());
                        sentenciaConstanciaEE.setString(4, constancia.getHorasSemanaMes());
                        sentenciaConstanciaEE.setString(5, constancia.getProgramaEducativo());
                        sentenciaConstanciaEE.setString(6, constancia.getExperienciaEducativa());
                        sentenciaConstanciaEE.setString(7, constancia.getPeriodoEscolar());
                        sentenciaConstanciaEE.setString(8, constancia.getCreditosExperiencia());

                        int filasAfectadasConstanciaEE = sentenciaConstanciaEE.executeUpdate();
                        if (filasAfectadasConstanciaEE == 1) {
                            respuesta = Constantes.OPERACION_EXITOSA;
                        }
                    }
                }
                conexionDB.close();
                } catch (SQLException e) {
                    respuesta = Constantes.ERROR_CONSULTA;
                }
        }
        
        return respuesta;
    }
}
