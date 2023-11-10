package javafxsigacop.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FXMLGeneracionConstanciasController implements Initializable {

    @FXML
    private ComboBox<?> cbProgramaEducativo;
    @FXML
    private Label lbErrorProgramaEducativo;
    @FXML
    private ComboBox<?> cbBloque;
    @FXML
    private Label lbErrorBloque;
    @FXML
    private ComboBox<?> cbSeccion;
    @FXML
    private Label lbErrorSeccion;
    @FXML
    private Label lbErrorSeccion1;
    @FXML
    private ComboBox<?> cbSeccion1;
    @FXML
    private ComboBox<?> cbSeccion11;
    @FXML
    private Label lbErrorSeccion11;
    @FXML
    private Label lbFechaExpedicion;
    @FXML
    private Label lbDocente;
    @FXML
    private Label lbDirector;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
