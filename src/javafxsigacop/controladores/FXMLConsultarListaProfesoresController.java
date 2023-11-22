/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsigacop.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsigacop.interfaces.INotificacionOperacion;
import javafxsigacop.interfaces.INotificacionRegreso;
import javafxsigacop.modelo.dao.CrudProfesoresDAO;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.respuestas.ListaUsuariosRespuesta;
import javafxsigacop.utilidades.Constantes;
import javafxsigacop.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author dnava
 */
public class FXMLConsultarListaProfesoresController 
        implements Initializable, INotificacionOperacion{

    @FXML
    private Label lblTituloTabla;
    @FXML
    private TableView<Cuenta> tvProfesores;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcTelefono;
    @FXML
    private TableColumn tcCorreoInstitucional;
    @FXML
    private TableColumn tcNoPersonal;

    private ObservableList<Cuenta> usuarios;
    private INotificacionRegreso notificacionRegreso;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionTabla();
    }  
    /*public void inicializarPantalla(
        INotificacionRegreso notificacionRegreso
    ) {
        this.notificacionRegreso = notificacionRegreso;
    }*/
    
    private void configurarTabla() {
        tcNoPersonal.setCellValueFactory(new PropertyValueFactory("numeroPersonal"));
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        tcCorreoInstitucional.setCellValueFactory(
                new PropertyValueFactory("correoInstitucional")
        );
        
    }
    
    private void cargarInformacionTabla() {
        usuarios = FXCollections.observableArrayList();
        ListaUsuariosRespuesta respuestaBD = CrudProfesoresDAO.recuperarListaUsuarios();
        
        switch(respuestaBD.getCodigoRespuesta()) {
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple(
                    "Sin conexión", 
                    "Por el momento no hay conexión", 
                    Alert.AlertType.ERROR
                );
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple(
                    "Error al cargar los datos", 
                    "Hubo un error al cargar la información", 
                    Alert.AlertType.WARNING
                );
                break;
            case Constantes.OPERACION_EXITOSA:
                usuarios.addAll(respuestaBD.getUsuarios());
                tvProfesores.setItems(usuarios);
                break;    
        }
    }
    
    private void redirigirAFormularioUsuario(Cuenta usuarioSeleccionado) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(
                javafxsigacop
                    .JavaFXSIGACOP
                    .class
                    .getResource("vistas/FXMLCrudProfesores.fxml")
            );
            Parent vista = accesoControlador.load();
            
            FXMLCrudProfesoresController controladorFormulario = 
                accesoControlador.getController();
            controladorFormulario.incializarFormulario(
                usuarioSeleccionado,
                this);
        
            Stage escenarioBase = new Stage();
            escenarioBase.setScene(new Scene(vista));
            escenarioBase.setTitle(
                usuarioSeleccionado == null
                ? "Registro de usuario"
                : "Edición de usuario"
            );
            escenarioBase.initModality(Modality.APPLICATION_MODAL);
            escenarioBase.showAndWait();
        } catch(IOException ex) {
            Utilidades.mostrarDialogoSimple(
                "Error de redirección", 
                "Por el momento no se puede acceder a la pantalla, "
                + "intente más tarde", 
                Alert.AlertType.ERROR
            );
        }
    }
    
    private void mostrarMensajeUsuarioNoSeleccionado() {
        Utilidades.mostrarDialogoSimple(
            "Seleccione un usuario", 
            "Debe seleccionar un usuario para realizar esta operación",
            Alert.AlertType.WARNING
        );
    }


    @FXML
    private void btnEliminar(ActionEvent event) {
        Cuenta usuarioSeleccionado = tvProfesores
            .getSelectionModel()
            .getSelectedItem();
        int idAdministrador = Cuenta.getInstanciaSingleton().getNumeroPersonal();
        
        if(usuarioSeleccionado == null) {
            mostrarMensajeUsuarioNoSeleccionado();
            return;
        }
        
        if(idAdministrador == usuarioSeleccionado.getNumeroPersonal()) {
            Utilidades.mostrarDialogoSimple(
                "Acción no disponible", 
                "No puede eliminar su cuenta mientras se encuentra loggeado, "
                + "para realizar esta acción inicie sesión con otra cuentea", 
                Alert.AlertType.WARNING
            );
            return;
        }
        
        boolean confirmacionEliminar = Utilidades.mostrarDialogoConfirmacion(
            "Confirmar eliminación", 
            "¿Estás seguro de eliminar el registro del usuario " + 
            usuarioSeleccionado+ "?"
        );

        if(confirmacionEliminar) {
            int codigoRespuesta = CrudProfesoresDAO.eliminarUsuario(
                usuarioSeleccionado.getNumeroPersonal()
            );

            switch(codigoRespuesta){
                case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple(
                        "Sin conexión", 
                        "Por el momento no hay conexión, intente más "
                        + "tarde", 
                        Alert.AlertType.ERROR
                    );
                    break;
                case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple(
                        "Error al eliminar", 
                        "Hubo un error al eliminar el usuario, "
                        + "intente más tarde", 
                        Alert.AlertType.WARNING
                    );
                    break;
                case Constantes.OPERACION_EXITOSA:
                    Utilidades.mostrarDialogoSimple(
                        "Eliminación exitosa", 
                        "El usuario se eliminó de forma exitosa", 
                        Alert.AlertType.INFORMATION
                    );
                    cargarInformacionTabla();
                    break;    
            }
        }
    }

    @FXML
    private void btnModificar(ActionEvent event) {
        Cuenta usuarioSeleccionado = tvProfesores
            .getSelectionModel()
            .getSelectedItem();
        
        if(usuarioSeleccionado != null) {
            redirigirAFormularioUsuario(usuarioSeleccionado);
        } else {
            mostrarMensajeUsuarioNoSeleccionado();
        }
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        redirigirAFormularioUsuario(null);
    }

    @Override
    public void notificarOperacionGuardar() {
        cargarInformacionTabla();
    }

    @Override
    public void notificarOperacionActualizar() {
        cargarInformacionTabla();
    }

    @FXML
    private void clicRegresarPantallaPrincipal(MouseEvent event) {
         Stage escenarioPrincipal = (Stage) lblTituloTabla
            .getScene()
            .getWindow();
         escenarioPrincipal.close();
         notificacionRegreso.regresarAPantalla();
    }
}
