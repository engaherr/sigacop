package javafxsigacop.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxsigacop.modelo.dao.SesionDAO;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utils.Constantes;
import javafxsigacop.utils.Utilidades;

public class FXMLInicioSesionController implements Initializable {
    
    @FXML
    private PasswordField tfContraseña;
    @FXML
    private Label lbErrorContraseña;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private Label lbErrorNumeroPersonal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIniciarSesion(ActionEvent event) {
        lbErrorContraseña.setText("");
        lbErrorNumeroPersonal.setText("");
        validarCampos();
    }

    private void validarCampos() {
        String numeroPersonal = tfNumeroPersonal.getText();
        String contrasenha = tfContraseña.getText();
        boolean sonValidos = true;
        if(numeroPersonal.trim().isEmpty()){
            sonValidos = false;
            lbErrorNumeroPersonal.setText("El usuario es requerido");
        }
        if(contrasenha.trim().isEmpty()){
            sonValidos = false;
            lbErrorContraseña.setText("La contraseña es requerida");
        }
        if(sonValidos){
            validarCredencialesUsuario(numeroPersonal,contrasenha);
        }
    }

    private void validarCredencialesUsuario(String numeroPersonal, String contrasenha) {
        Cuenta cuentaRespuesta = null;
        boolean sonValidos = true;
        int respuesta = Constantes.ERROR_CONSULTA;
        int noPersonal = 0;
        
        try{
            noPersonal = Integer.parseInt(numeroPersonal);
        }catch(NumberFormatException ex){
            sonValidos = false;
            lbErrorNumeroPersonal.setText("El número de personal debe contener sólo números");
        }
        
        if(sonValidos){
            cuentaRespuesta = SesionDAO.verificarSesion(noPersonal, contrasenha);
            respuesta = cuentaRespuesta.getCodigoRespuesta();
            switch(respuesta){
                case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Error de Conexion", 
                            "Por el momento no hay conexión, por favor intentélo más tarde",
                            Alert.AlertType.ERROR);
                    break;
                case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error en la solicitud", 
                            "Por el momento no se puede procesar la solicitud de verificación", 
                            Alert.AlertType.ERROR);
                    break;
                case Constantes.OPERACION_EXITOSA:
                    if(cuentaRespuesta != null && cuentaRespuesta.getIdCuenta()> 0 && cuentaRespuesta.isEsAdministrativo()){
                        Utilidades.mostrarDialogoSimple("Administrativo verificado",
                                "Bienvenid@ " + " al sistema...", 
                                Alert.AlertType.INFORMATION);
                        Cuenta.setInstanciaSingleton(cuentaRespuesta);
                        irPantallaPrincipal();
                    }else if(cuentaRespuesta != null && cuentaRespuesta.getIdCuenta()> 0 && !cuentaRespuesta.isEsAdministrativo()){
                        Utilidades.mostrarDialogoSimple("Profesor verificado",
                                "Bienvenid@ " + " al sistema...", 
                                Alert.AlertType.INFORMATION);
                        Cuenta.setInstanciaSingleton(cuentaRespuesta);
                        irPantallaPrincipal();
                    }else{
                        Utilidades.mostrarDialogoSimple("Credenciales incorrectas", 
                                "El usuario y/o contraseñas son incorrectas, por favor verifique "
                                        + "la información", Alert.AlertType.WARNING);
                    }
                    break;
                default:
                    Utilidades.mostrarDialogoSimple("Error de petición", 
                                "El sistema no está disponible por el momento", Alert.AlertType.ERROR);
            }
        }
    }

    private void irPantallaPrincipal() {
        Stage escenarioBase = (Stage) tfContraseña.getScene().getWindow();
        escenarioBase.setScene(Utilidades.inicializaEscena(
                "vistas/FXMLPrincipal.fxml"));
        escenarioBase.setTitle("Menú Principal");
        escenarioBase.show();
    }
}
