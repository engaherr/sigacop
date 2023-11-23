package javafxsigacop.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxsigacop.JavaFXSIGACOP;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utilidades.Utilidades;

public class FXMLPrincipalController implements Initializable{

    @FXML
    private ImageView imgMenu;
    @FXML
    private Pane paneMenu;
    @FXML
    private Pane paneGenerarConstancia;
    @FXML
    private Pane paneAdminProfesores;
    private boolean menuAbierto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuAbierto = false;
        
        if (Cuenta.getInstanciaSingleton().isEsAdministrativo()) {
            paneAdminProfesores.setVisible(true);
            paneGenerarConstancia.setVisible(false);
        }
    }    

    @FXML
    private void clicAbrirMenu(MouseEvent event) {
        if(menuAbierto)
            actualizaEstadoMenu(-274, false, "recursos/menu-icono.png");
        else
            actualizaEstadoMenu(274, true, "recursos/cerrar-icono.png");
    }

    @FXML
    private void clicGenerarConstancia(MouseEvent event) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(
                javafxsigacop
                    .JavaFXSIGACOP
                    .class
                    .getResource("vistas/FXMLGeneracionConstancias.fxml")
            );
            Parent vista = accesoControlador.load();
        
            Stage escenarioConstancias = new Stage();
            escenarioConstancias.setScene(new Scene(vista));
            escenarioConstancias.setTitle("Generar constancia");
            escenarioConstancias.initModality(Modality.APPLICATION_MODAL);
            escenarioConstancias.showAndWait();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple(
                "Error de redirección", 
                "Por el momento no se puede acceder a la pantalla, "
                + "intente más tarde", 
                Alert.AlertType.ERROR
            );
        }
    }

    @FXML
    private void clicCerrarSesion(MouseEvent event) {
        Stage escenarioPrincipal = (Stage)imgMenu.getScene().getWindow();
        escenarioPrincipal.setScene(Utilidades.inicializaEscena("vistas/FXMLInicioSesion.fxml"));
        escenarioPrincipal.setTitle("Inicio de sesión");
        Cuenta.setInstanciaSingleton(null);
        escenarioPrincipal.show();
    }

    @FXML
    private void clicIrConstanciasSolicitadas(MouseEvent event) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(
                javafxsigacop
                    .JavaFXSIGACOP
                    .class
                    .getResource("vistas/FXMLConsultarConstanciasSolicitadas.fxml")
            );
            Parent vista = accesoControlador.load();
        
            Stage escenarioConstanciasSolicitadas = new Stage();
            escenarioConstanciasSolicitadas.setScene(new Scene(vista));
            escenarioConstanciasSolicitadas.setTitle("Constancias solicitadas");
            escenarioConstanciasSolicitadas.initModality(Modality.APPLICATION_MODAL);
            escenarioConstanciasSolicitadas.showAndWait();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple(
                "Error de redirección", 
                "Por el momento no se puede acceder a la pantalla, "
                + "intente más tarde", 
                Alert.AlertType.ERROR
            );
            ex.printStackTrace();
        }
    }

    @FXML
    private void clicIrAdministradorProfesores(MouseEvent event) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(
                javafxsigacop
                    .JavaFXSIGACOP
                    .class
                    .getResource("vistas/FXMLConsultarListaProfesores.fxml")
            );
            Parent vista = accesoControlador.load();
            
            Stage escenarioProfesores = new Stage();
            escenarioProfesores.setScene(new Scene(vista));
            escenarioProfesores.setTitle("Profesores registrados");
            escenarioProfesores.initModality(Modality.APPLICATION_MODAL);
            escenarioProfesores.showAndWait();
        } catch (IOException ex) {
            Utilidades.mostrarDialogoSimple(
                "Error de redirección", 
                "Por el momento no se puede acceder a la pantalla, "
                + "intente más tarde", 
                Alert.AlertType.ERROR
            );
        }
    }
    
    private void actualizaEstadoMenu(int posicion, boolean abierto, String icono){
        animacionMenu(posicion);
        menuAbierto = abierto;
        imgMenu.setImage(new Image(JavaFXSIGACOP.class.getResource(icono).toString()));
    }
    
    private void animacionMenu(int posicion){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(paneMenu);
        translate.setDuration(Duration.millis(300));
        translate.setByX(posicion);
        translate.setAutoReverse(true);
        translate.play();
    }
}
