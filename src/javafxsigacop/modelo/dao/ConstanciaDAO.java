package javafxsigacop.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafxsigacop.modelo.ConexionBD;
import javafxsigacop.modelo.pojo.Constancia;
import javafxsigacop.modelo.pojo.ConstanciaEE;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.respuestas.ConstanciaEERespuesta;
import javafxsigacop.respuestas.ListaConstanciasRespuesta;
import javafxsigacop.utilidades.Constantes;

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
    
    public static ConstanciaEERespuesta obtenerConstaciaEE(int idConstancia){
        ConstanciaEERespuesta constanciaEERespuesta = new ConstanciaEERespuesta();
        constanciaEERespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        
        if(conexionDB != null){
            try{
                String consulta = "SELECT c.id_constancia, c.fecha_expedicion, "
                        + "c.nombre_director, c.numero_personal, cee.bloque, "
                        + "cee.creditos_experiencia, cee.experiencia_educativa, "
                        + "cee.horas_semana_mes, cee.periodo_escolar, "
                        + "cee.programa_educativo, cee.seccion "
                        + "FROM constancias c "
                        + "INNER JOIN constancias_imparticion_ee cee "
                        + "ON c.id_constancia = cee.id_constancia "
                        + "WHERE c.id_constancia = ?;";

                PreparedStatement sentenciaPreparada = 
                        conexionDB.prepareStatement(consulta);
                sentenciaPreparada.setInt(1, idConstancia);
                
                ResultSet resultado = sentenciaPreparada.executeQuery();
                
                if(resultado.next()){
                    ConstanciaEE constanciaEE = new ConstanciaEE();
                    
                    constanciaEE.setIdConstancia(
                            resultado.getInt("id_constancia")
                    );
                    constanciaEE.setFechaExpedicion(
                            resultado.getString("fecha_expedicion")
                    );
                    constanciaEE.setNombreDirector(
                            resultado.getString("nombre_director")
                    );
                    constanciaEE.setNumeroPersonal(
                            resultado.getInt("numero_personal")
                    );
                    constanciaEE.setBloque(resultado.getString("bloque"));
                    constanciaEE.setCreditosExperiencia(
                            resultado.getString("creditos_experiencia")
                    );
                    constanciaEE.setExperienciaEducativa(
                            resultado.getString("experiencia_educativa")
                    );
                    constanciaEE.setHorasSemanaMes(
                            resultado.getString("horas_semana_mes")
                    );
                    constanciaEE.setPeriodoEscolar(
                            resultado.getString("periodo_escolar")
                    );
                    constanciaEE.setProgramaEducativo(
                            resultado.getString("programa_educativo")
                    );
                    constanciaEE.setSeccion(resultado.getString("seccion"));
                    
                    constanciaEERespuesta.setConstanciaEE(constanciaEE);
                }
            }catch (SQLException e){
                constanciaEERespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        }else{
            constanciaEERespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        
        return constanciaEERespuesta;
    }
    
    public static ListaConstanciasRespuesta obtenerListaCompletaConstancias(){
        ListaConstanciasRespuesta ListaConstanciasRespuesta = 
                new ListaConstanciasRespuesta();
        ListaConstanciasRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        
        if(conexionDB != null){
            try {
                String consulta = "SELECT c.id_constancia, c.fecha_expedicion, "
                        + "CONCAT(u.nombre, ' ', u.apellido_paterno, ' ', u.apellido_materno) "
                        + "AS nombre, ciee.experiencia_educativa, "
                        + "ccp.eje_estrategico, cpj.rol, cpp.proyecto "
                        + "FROM constancias c "
                        + "LEFT JOIN constancias_imparticion_ee ciee "
                        + "ON c.id_constancia = ciee.id_constancia "
                        + "LEFT JOIN constancias_contribucion_pladea ccp "
                        + "ON c.id_constancia = ccp.id_constancia "
                        + "LEFT JOIN constancias_participacion_jurado cpj "
                        + "ON c.id_constancia = cpj.id_constancia "
                        + "LEFT JOIN constancias_participacion_proyecto cpp "
                        + "ON c.id_constancia = cpp.id_constancia "
                        + "LEFT JOIN usuarios u "
                        + "ON c.numero_personal = u.numero_personal;";

                PreparedStatement sentenciaPreparada = 
                        conexionDB.prepareStatement(consulta);
                ResultSet resultado = sentenciaPreparada.executeQuery();
                
                ArrayList<Constancia> constancias = new ArrayList();
                while(resultado.next()) {
                    Constancia constancia = new Constancia();
                    
                    constancia.setIdConstancia(
                            resultado.getInt("id_constancia")
                    );
                    constancia.setFechaExpedicion(
                            resultado.getDate("fecha_expedicion").toLocalDate()
                    );
                    
                    DateTimeFormatter formato = 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    constancia.setFechaExpedicionConFormato(
                            resultado.getDate("fecha_expedicion").
                                    toLocalDate().format(formato)
                    );
                    constancia.setNombreProfesor(
                            resultado.getString("nombre")
                    );
                    
                    if(resultado.getString("experiencia_educativa") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_EE);
                    }else if(resultado.getString("eje_estrategico") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_PLADEA);
                    }else if(resultado.getString("rol") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_JURADO);
                    }else if(resultado.getString("proyecto") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_PROYECTO);
                    }
                    
                    constancias.add(constancia);          
                }
                ListaConstanciasRespuesta.setConstancias(constancias);
                
                conexionDB.close();
            } catch (SQLException e) {
                ListaConstanciasRespuesta
                        .setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        }else{
            ListaConstanciasRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        
        return ListaConstanciasRespuesta;
    }
    
    public static ListaConstanciasRespuesta obtenerListaConstanciasPropias(){
        ListaConstanciasRespuesta ListaConstanciasRespuesta = 
                new ListaConstanciasRespuesta();
        ListaConstanciasRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        
        Connection conexionDB = ConexionBD.abrirConexionBD();
        
        if(conexionDB != null){
            try {
                String consulta = "SELECT c.id_constancia, c.fecha_expedicion, "
                        + "CONCAT(u.nombre, u.apellido_paterno, u.apellido_materno) "
                        + "AS nombre, ciee.experiencia_educativa, "
                        + "ccp.eje_estrategico, cpj.rol, cpp.proyecto "
                        + "FROM constancias c "
                        + "LEFT JOIN constancias_imparticion_ee ciee "
                        + "ON c.id_constancia = ciee.id_constancia "
                        + "LEFT JOIN constancias_contribucion_pladea ccp "
                        + "ON c.id_constancia = ccp.id_constancia "
                        + "LEFT JOIN constancias_participacion_jurado cpj "
                        + "ON c.id_constancia = cpj.id_constancia "
                        + "LEFT JOIN constancias_participacion_proyecto cpp "
                        + "ON c.id_constancia = cpp.id_constancia "
                        + "LEFT JOIN usuarios u "
                        + "ON c.numero_personal = u.numero_personal "
                        + "WHERE c.numero_personal = ?;";

                PreparedStatement sentenciaPreparada = 
                        conexionDB.prepareStatement(consulta);
                sentenciaPreparada.setInt(
                        1, 
                        Cuenta.getInstanciaSingleton().getNumeroPersonal()
                );
                
                ResultSet resultado = sentenciaPreparada.executeQuery();
                
                ArrayList<Constancia> constancias = new ArrayList();
                while(resultado.next()) {
                    Constancia constancia = new Constancia();
                    
                    constancia.setIdConstancia(
                            resultado.getInt("id_constancia")
                    );
                    constancia.setFechaExpedicion(
                            resultado.getDate("fecha_expedicion").toLocalDate()
                    );
                    
                    DateTimeFormatter formato = 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    constancia.setFechaExpedicionConFormato(
                            resultado.getDate("fecha_expedicion").
                                    toLocalDate().format(formato)
                    );
                    constancia.setNombreProfesor(
                            resultado.getString("nombre")
                    );
                    
                    if(resultado.getString("experiencia_educativa") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_EE);
                    }else if(resultado.getString("eje_estrategico") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_PLADEA);
                    }else if(resultado.getString("rol") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_JURADO);
                    }else if(resultado.getString("proyecto") != null){
                        constancia.setTipoConstancia(Constantes.CONSTANCIA_PROYECTO);
                    }
                    
                    constancias.add(constancia);          
                }
                ListaConstanciasRespuesta.setConstancias(constancias);
                
                conexionDB.close();
            } catch (SQLException e) {
                ListaConstanciasRespuesta
                        .setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            }
        }else{
            ListaConstanciasRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        
        return ListaConstanciasRespuesta;
    }
}
