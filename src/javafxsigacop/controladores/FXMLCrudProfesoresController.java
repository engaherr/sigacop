package javafxsigacop.controladores;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxsigacop.interfaces.INotificacionOperacion;
import javafxsigacop.modelo.dao.CrudProfesoresDAO;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.respuestas.ExistenciaProfesor;
import javafxsigacop.utilidades.Validaciones;
import javafxsigacop.utilidades.Constantes;
import javafxsigacop.utilidades.Utilidades;

public class FXMLCrudProfesoresController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfNumeroPersonal;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfTelefono;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Label lblTituloPantallaCrud;
    @FXML
    private TextField tfContrasenhia;
    
    private boolean esEdicion;
    private INotificacionOperacion interfazNotificacion;
    private int idProfesorEdicion;
    @FXML
    private Label lbContrasenha;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicBtnCancelar(ActionEvent event) {
        cerrarVentana();
    }

    @FXML
    private void clicBtnRegistrar(ActionEvent event) {
        limpiarCampos();
        boolean camposValidos = validarCampos();
        if (!camposValidos) {
            Utilidades.mostrarDialogoSimple(
                    "Campos inválidos",
                    "Por favor corrija los campos marcados para poder continuar",
                    Alert.AlertType.WARNING
            );
            return;
        }
        try {
            guardarInformacionProfesor();
            cerrarVentana();
        } catch (NoSuchAlgorithmException ex) {
            Utilidades.mostrarDialogoSimple("Error",
                    "Ha ocurrido un error al obtener la contraseña ingresada. "
                            + "Por favor, contacte al administrador del sistema",
                    Alert.AlertType.ERROR
            );
        }
    }
    
    public void incializarFormulario(
        Cuenta usuarioSeleccionado,
        INotificacionOperacion interfazNotificacion
    ) {
        this.interfazNotificacion = interfazNotificacion;
        esEdicion = usuarioSeleccionado != null;
        
        if(esEdicion) {
            lblTituloPantallaCrud.setText("Editar información de usuario");
            btnRegistrar.setText("Actualizar");
            idProfesorEdicion = usuarioSeleccionado.getNumeroPersonal();
            System.out.println(usuarioSeleccionado.getNumeroPersonal());
            cargarInformacionUsuario(usuarioSeleccionado.getNumeroPersonal());
        } else {
            lblTituloPantallaCrud.setText("Registrar profesor");
            btnRegistrar.setText("Registrar");
        }
    } 
    private void guardarInformacionProfesor() throws NoSuchAlgorithmException {
        Cuenta profesor = new Cuenta();
        profesor.setNumeroPersonal(Integer.parseInt(tfNumeroPersonal.getText()));
        profesor.setNombre(tfNombre.getText());
        profesor.setApellidoPaterno(tfApellidoPaterno.getText());
        profesor.setApellidoMaterno(tfApellidoMaterno.getText());
        profesor.setTelefono(tfTelefono.getText());
        profesor.setCorreoInstitucional(tfCorreoInstitucional.getText());
        
        int codigoRespuesta;
        if(esEdicion && tfContrasenhia.getText().isEmpty()) {
            profesor.setNumeroPersonal(idProfesorEdicion);
            codigoRespuesta = CrudProfesoresDAO.actualizarProfesorSinContraseña(profesor);
        } else if(esEdicion && !tfContrasenhia.getText().isEmpty()){
            profesor.setNumeroPersonal(idProfesorEdicion);
            profesor.setContrasenha(Utilidades.cifrarContrasenha(tfContrasenhia.getText()));
            codigoRespuesta = CrudProfesoresDAO.actualizarProfesorConContraseña(profesor);
        }else{
            profesor.setContrasenha(Utilidades.cifrarContrasenha(tfContrasenhia.getText()));
            codigoRespuesta = CrudProfesoresDAO.registrarProfesor(profesor);
        }
        
        notificarModificacionesBase(codigoRespuesta);
    }
    private void notificarModificacionesBase(int codigoRespuesta) {
        switch(codigoRespuesta) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple(
                    "Sin conexión", 
                    "No se pudo guardar la información del usuario, intente "
                    + "de nuevo", 
                    Alert.AlertType.ERROR
                );
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple(
                    "Error al guardar los datos", 
                    "Hubo un error al guardar la información, intente de nuevo", 
                    Alert.AlertType.WARNING
                );
                break;
            case Constantes.OPERACION_EXITOSA:
                Utilidades.mostrarDialogoSimple(
                    "Guardado exitoso", 
                    "La información del usuario se guardó correctamente", 
                    Alert.AlertType.INFORMATION
                );
                interfazNotificacion.notificarOperacionGuardar();
                cerrarVentana();
                break;    
        }
    }
    
    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) lblTituloPantallaCrud.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    private boolean validarCampos() {
        boolean camposValidos = true;
        String nombre = tfNombre.getText();
        String primerApellido = tfApellidoPaterno.getText();
        String segundoApellido = tfApellidoMaterno.getText();
        String correo = tfCorreoInstitucional.getText();
        String telefono = tfTelefono.getText();
        String numeroPersonal = tfNumeroPersonal.getText();
        String contrasenhia = tfContrasenhia.getText();
        
        
        if(nombre.isEmpty()) {
            camposValidos = false;
            tfNombre.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        
        if(primerApellido.isEmpty()) {
            camposValidos = false;
            tfApellidoPaterno.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        
        if(segundoApellido.isEmpty()) {
            camposValidos = false;
            tfApellidoMaterno.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        if(contrasenhia.isEmpty() && !esEdicion){
            camposValidos = false;
            tfContrasenhia.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        
        if(!Validaciones.correoValido(correo)) {
            camposValidos = false;
            tfCorreoInstitucional.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        
        if(!Validaciones.tieneFormatoTelefono(telefono)){
            camposValidos = false;
            tfTelefono.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        
        if(!Validaciones.tieneFormatoNumeroPersonal(numeroPersonal)){
            camposValidos=false;
            tfNumeroPersonal.setStyle(Constantes.CAMPO_ESTILOS_ERROR);
        }
        return camposValidos;
    }
    
    private void limpiarCampos() {
        tfNumeroPersonal.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfNombre.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfApellidoPaterno.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfApellidoMaterno.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfCorreoInstitucional.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfTelefono.setStyle(Constantes.CAMPO_ESTILOS_BASE);
        tfContrasenhia.setStyle(Constantes.CAMPO_ESTILOS_BASE);
    }

    private void cargarInformacionUsuario(int numeroPersonal) {
        ExistenciaProfesor respuesta = CrudProfesoresDAO
            .obtenerProfesorPorNumeroPersonal(numeroPersonal);
        
        switch(respuesta.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple(
                    "Sin conexión", 
                    "No se puede recuperar la información por el momento", 
                    Alert.AlertType.ERROR
                );
                cerrarVentana();
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple(
                    "Error al cargar los datos", 
                    "Hubo un error al cargar la información", 
                    Alert.AlertType.WARNING
                );
                cerrarVentana();
                break;
            case Constantes.OPERACION_EXITOSA:
                llenarCamposProfesor(respuesta.getProfesor());
                break;    
        }
    }
    private void llenarCamposProfesor(Cuenta profesor) {
        tfNombre.setText(profesor.getNombre());
        tfApellidoPaterno.setText(profesor.getApellidoPaterno());
        tfApellidoMaterno.setText(profesor.getApellidoMaterno());
        tfNumeroPersonal.setText(String.valueOf(profesor.getNumeroPersonal()));
        tfCorreoInstitucional.setText(profesor.getCorreoInstitucional());
        tfTelefono.setText(profesor.getTelefono());

        tfNumeroPersonal.setEditable(false);
        lbContrasenha.setText("Nueva contraseña");
    }
}
