/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsigacop.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rodri
 */
public class FXMLConsultarConstanciasSolicitadasController implements Initializable {

    @FXML
    private TableView<?> tvProfesores;
    @FXML
    private TableColumn<?, ?> tcTipoConstancia;
    @FXML
    private TableColumn<?, ?> tcFechaExpedicion;
    @FXML
    private TableColumn<?, ?> tcProfesor;
    @FXML
    private ComboBox<?> cbTipoConstancia;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private ComboBox<?> cbProfesor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clicRegresarPantallaPrincipal(MouseEvent event) {
    }

    @FXML
    private void btnDescargarConstancia(ActionEvent event) {
    }
    
}
