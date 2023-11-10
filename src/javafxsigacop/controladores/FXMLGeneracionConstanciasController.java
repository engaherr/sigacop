package javafxsigacop.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafxsigacop.modelo.pojo.ExperienciaEducativa;
import javafxsigacop.modelo.pojo.PeriodoEscolar;
import javafxsigacop.modelo.pojo.ProgramaEducativo;
import javafxsigacop.utilidades.RecursosEstaticos;
import javafxsigacop.utils.Utilidades;

public class FXMLGeneracionConstanciasController implements Initializable {
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramaEducativo;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciaEducativa;
    @FXML
    private ComboBox<PeriodoEscolar> cbPeriodoEscolar;
    @FXML
    private Label lbErrorProgramaEducativo;
    @FXML
    private Label lbFechaExpedicion;
    @FXML
    private Label lbDocente;
    @FXML
    private Label lbDirector;
    @FXML
    private TextField tfBloque;
    @FXML
    private TextField tfSeccion;
    @FXML
    private Label lbErrorExperienciaEducativa;
    @FXML
    private Label lbErrorPeriodoEscolar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarFechaActual();
        mostrarNombreDirector();
        
        cargarProgramasEducativos();
        cargarPeriodosEscolares();
        
        manejarCambioProgramaYPeriodo();
        manejarCambioExperienciaEducativa();
    }
    
    private void mostrarFechaActual() {
        lbFechaExpedicion.setText(Utilidades.obtenerFechaActual());
    }
    
    private void mostrarNombreDirector() {
        lbDirector.setText(RecursosEstaticos.obtenerNombreDirector());
    }
    
    private void cargarProgramasEducativos() {
        ObservableList<ProgramaEducativo> programasEducativos = FXCollections.observableArrayList();
        programasEducativos.addAll(RecursosEstaticos.obtenerProgramasEducativos());
        
        cbProgramaEducativo.setItems(programasEducativos);
    }
    
    private void cargarPeriodosEscolares() {
        ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
        periodosEscolares.addAll(RecursosEstaticos.obtenerPeriodosEscolares());
        
        cbPeriodoEscolar.setItems(periodosEscolares);
    }
    
    private void manejarCambioProgramaYPeriodo() {
        ChangeListener<Object> cambioProgramaPeriodoListener = new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                ocultarErroresDeProgramaYPeriodo();

                if (cbProgramaEducativo.getValue() != null && cbPeriodoEscolar.getValue() != null) {
                    cargarExperienciasEducativas();
                    cbExperienciaEducativa.setDisable(false);
                    tfBloque.setText("");
                    tfSeccion.setText("");
                }
            }
        };

        cbProgramaEducativo.valueProperty().addListener(cambioProgramaPeriodoListener);
        cbPeriodoEscolar.valueProperty().addListener(cambioProgramaPeriodoListener);
    }
    
    private void ocultarErroresDeProgramaYPeriodo() {
        if(cbProgramaEducativo.getValue() != null) {
            lbErrorProgramaEducativo.setVisible(false);
        }
        
        if(cbPeriodoEscolar.getValue() != null) {
            lbErrorPeriodoEscolar.setVisible(false);
        }
    }
    
    private void cargarExperienciasEducativas() {
        String idPrograma = cbProgramaEducativo.getValue().getIdentificador();        
        String idPeriodo = cbPeriodoEscolar.getValue().getIdentificador();
        
        ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
        experienciasEducativas.addAll(RecursosEstaticos.obtenerExperienciasEducativas(idPrograma, idPeriodo));
        
        cbExperienciaEducativa.setItems(experienciasEducativas);
    }
    
    private void manejarCambioExperienciaEducativa() {
        cbExperienciaEducativa.valueProperty().addListener(new ChangeListener<ExperienciaEducativa>() {
            @Override
            public void changed(ObservableValue<? extends ExperienciaEducativa> observable, ExperienciaEducativa oldValue, ExperienciaEducativa newValue) {
                if (newValue != null) {
                    tfBloque.setText(newValue.getBloque());
                    tfSeccion.setText(newValue.getSeccion());
                    lbErrorExperienciaEducativa.setVisible(false);
                }
            }
        });
    }

    @FXML
    private void generarConstanciaClic(ActionEvent event) {
        ocultarErrores();
        
        if(validarCampos()) {
            
        } else {
            Utilidades.mostrarDialogoSimple(
                "Campos inválidos", 
                "Para poder generar su constancia, corrija la información indicada", 
                Alert.AlertType.WARNING
            );
        }
    }
    
    private void ocultarErrores() {
        lbErrorExperienciaEducativa.setVisible(false);
        lbErrorPeriodoEscolar.setVisible(false);
        lbErrorProgramaEducativo.setVisible(false);
    }
    
    private boolean validarCampos() {
        boolean camposValidos = true;
        
        if(cbExperienciaEducativa.getValue() == null) {
            lbErrorExperienciaEducativa.setVisible(true);
            camposValidos = false;
        }
        
        if(cbPeriodoEscolar.getValue() == null) {
            lbErrorPeriodoEscolar.setVisible(true);
            camposValidos = false;
        }
        
        if(cbProgramaEducativo.getValue() == null) {
            lbErrorProgramaEducativo.setVisible(true);
            camposValidos = false;
        }
        
        return camposValidos;
    }

    @FXML
    private void cancelarGeneracionClic(ActionEvent event) {
    }
}
