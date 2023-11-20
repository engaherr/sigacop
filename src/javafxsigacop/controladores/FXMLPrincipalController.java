/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsigacop.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafxsigacop.JavaFXSIGACOP;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author kikga
 */
public class FXMLPrincipalController implements Initializable {

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
    }

    @FXML
    private void clicIrAdministradorProfesores(MouseEvent event) {
        Stage escenarioBase = (Stage) imgMenu.getScene().getWindow();
        escenarioBase.setScene(Utilidades.inicializaEscena(
                "vistas/FXMLConsultarListaProfesores.fxml"));
        escenarioBase.setTitle("Profesores registrados");
        escenarioBase.show();
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
