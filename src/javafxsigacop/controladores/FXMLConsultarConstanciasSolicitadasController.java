package javafxsigacop.controladores;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafxsigacop.modelo.dao.ConstanciaDAO;
import javafxsigacop.modelo.pojo.Constancia;
import javafxsigacop.modelo.pojo.ConstanciaEE;
import javafxsigacop.modelo.pojo.Cuenta;
import javafxsigacop.respuestas.ConstanciaEERespuesta;
import javafxsigacop.respuestas.ListaConstanciasRespuesta;
import javafxsigacop.utilidades.Constantes;
import javafxsigacop.utilidades.Utilidades;

public class FXMLConsultarConstanciasSolicitadasController implements Initializable {

    @FXML
    private TableColumn tcTipoConstancia;
    @FXML
    private TableColumn tcFechaExpedicion;
    @FXML
    private TableColumn tcProfesor;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private ComboBox<String> cbTiposConstancia;
    @FXML
    private ComboBox<String> cbProfesores;
    @FXML
    private Label lblFiltroProfesor;
    @FXML
    private TableView<Constancia> tvConstancias;
    
    private ObservableList<Constancia> constancias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacionTabla();
        cargarFiltros();
        cargarInformacionTiposConstancias();
        if(!Cuenta.getInstanciaSingleton().isEsAdministrativo()){
            cbProfesores.setVisible(false);
            tcProfesor.setVisible(false);
            lblFiltroProfesor.setVisible(false);
            tcTipoConstancia.setPrefWidth(360);
            tcFechaExpedicion.setPrefWidth(303);
        }else{
            cargarInformacionProfesores();
        }
    }

    private void configurarTabla() {
        tcTipoConstancia.setCellValueFactory(new PropertyValueFactory("tipoConstancia"));
        tcFechaExpedicion.setCellValueFactory(new PropertyValueFactory("fechaExpedicionConFormato"));
        if(Cuenta.getInstanciaSingleton().isEsAdministrativo()){
            tcProfesor.setCellValueFactory(new PropertyValueFactory("nombreProfesor"));
        }
    }
    
    private void cargarInformacionTabla() {
        constancias = FXCollections.observableArrayList();
        ListaConstanciasRespuesta respuestaBD = new ListaConstanciasRespuesta();
        
        if(Cuenta.getInstanciaSingleton().isEsAdministrativo()){
            respuestaBD = ConstanciaDAO.obtenerListaCompletaConstancias();
        }else{
            respuestaBD = ConstanciaDAO.obtenerListaConstanciasPropias();
        }
        
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
                constancias.addAll(respuestaBD.getConstancias());
                tvConstancias.setItems(constancias);
                break;    
        }
    }
    
    private void cargarInformacionProfesores(){
        ObservableList<String> profesores = FXCollections.observableArrayList();
        for(int i = 0; i < constancias.size(); i++){
            if(!profesores.contains(constancias.get(i).getNombreProfesor())){
                profesores.add(constancias.get(i).getNombreProfesor());
            }
        }
        cbProfesores.setItems(profesores);
    }
    
    private void cargarInformacionTiposConstancias(){
        ObservableList<String> tiposConstancias = FXCollections.observableArrayList();
        tiposConstancias.add(Constantes.CONSTANCIA_EE);
        tiposConstancias.add(Constantes.CONSTANCIA_JURADO);
        tiposConstancias.add(Constantes.CONSTANCIA_PLADEA);
        tiposConstancias.add(Constantes.CONSTANCIA_PROYECTO);
        
        cbTiposConstancia.setItems(tiposConstancias);
    }

    @FXML
    private void clicRegresarPantallaPrincipal(MouseEvent event) {
        Stage escenarioPrincipal = (Stage) dpFechaFin
            .getScene()
            .getWindow();
         escenarioPrincipal.close();
    }

    @FXML
    private void btnDescargarConstancia(ActionEvent event) {
        Constancia constanciaSeleccionada = 
                tvConstancias.getSelectionModel().getSelectedItem();
        
        if(constanciaSeleccionada == null){
            Utilidades.mostrarDialogoSimple(
                    "Error al querer descargar", 
                    "Debe seleccionar la constancia que desea descargar", 
                    Alert.AlertType.WARNING
                );
        }else{
            validarInformacionConstancia(
                    constanciaSeleccionada.getTipoConstancia(),
                    constanciaSeleccionada.getIdConstancia()
                    );
        }
    }
    
    private void validarInformacionConstancia(
            String tipoConstancia, 
            int idConstancia
    ){
        if(tipoConstancia.equals(Constantes.CONSTANCIA_EE)){
            obtenerInformacionConstanciaEE(idConstancia);
        }
    }
    
    private void obtenerInformacionConstanciaEE(int idConstancia){
        ConstanciaEERespuesta constanciaEERespuesta = 
                ConstanciaDAO.obtenerConstaciaEE(idConstancia);
        
        switch(constanciaEERespuesta.getCodigoRespuesta()) {
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
                generarConstancia(constanciaEERespuesta.getConstanciaEE());
                break;    
        }
    }
    
    private void generarConstancia(ConstanciaEE constanciaEE) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog((Stage) lblFiltroProfesor.getScene().getWindow());
        
        if(selectedDirectory == null){
            Utilidades.mostrarDialogoSimple(
                "Seleccione una carpeta", 
                "Debe seleccionar la carpeta de destino en la que se "
                    + "guardará la constancia", 
                Alert.AlertType.WARNING
            );
        } else {
            try {
                Utilidades.descargarDocumentoPDF(
                        selectedDirectory.getAbsolutePath(), 
                        constanciaEE
                );
                Utilidades.mostrarDialogoSimple(
                    "Constancia generada exitosamente", 
                    "La constancia de experiencia docente se generó de forma exitosa", 
                    Alert.AlertType.INFORMATION
                );
            } catch (IOException e) {
                Utilidades.mostrarDialogoSimple(
                    "Error de descarga", 
                    "Ocurrió un error al descargar el archivo, por favor "
                        + "intente de nuevo", 
                    Alert.AlertType.WARNING
                );
            } catch (DocumentException e) {
                Utilidades.mostrarDialogoSimple(
                    "Error en formato de documento", 
                    "Ocurrió un error al descargar el documento pdf por una "
                        + "corrupción en su formato, por favor intente de nuevo", 
                    Alert.AlertType.WARNING
                );
            }
        }
    }

    @FXML
    private void btnLimpiarFiltros(ActionEvent event) {
        cbTiposConstancia.setValue("");
        cbProfesores.setValue("");
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
    }
    
    public void cargarFiltros(){
        FilteredList<Constancia> filteredList = 
                new FilteredList<>(constancias, p -> true);

        tvConstancias.setItems(filteredList);
        
        cbTiposConstancia.setOnAction(event -> aplicarFiltros(
                filteredList, 
                cbTiposConstancia.getValue(), 
                cbProfesores.getValue(), 
                dpFechaInicio.getValue(), 
                dpFechaFin.getValue())
        );

        cbProfesores.setOnAction(event -> aplicarFiltros(
                filteredList, 
                cbTiposConstancia.getValue(), 
                cbProfesores.getValue(), 
                dpFechaInicio.getValue(), 
                dpFechaFin.getValue())
        );
        
        dpFechaInicio.setOnAction(event -> aplicarFiltros(
                filteredList, 
                cbTiposConstancia.getValue(), 
                cbProfesores.getValue(), 
                dpFechaInicio.getValue(), 
                dpFechaFin.getValue())
        );
        
        dpFechaFin.setOnAction(event -> aplicarFiltros(
                filteredList, 
                cbTiposConstancia.getValue(), 
                cbProfesores.getValue(), 
                dpFechaInicio.getValue(), 
                dpFechaFin.getValue())
        );
    }
    
    public void aplicarFiltros(
            FilteredList<Constancia> filteredList, 
            String tipoConstancia, 
            String profesor, 
            LocalDate fechaInicio, 
            LocalDate fechaFin
    ){
        filteredList.setPredicate(constancia -> {
            boolean nombreCoincide = 
                    tipoConstancia == null || tipoConstancia.isEmpty() 
                    ||constancia.getTipoConstancia().toLowerCase().
                            contains(tipoConstancia.toLowerCase());
            boolean profesorCoincide = 
                    profesor == null || profesor.isEmpty() 
                    ||constancia.getNombreProfesor().toLowerCase().
                            contains(profesor.toLowerCase());
            boolean fechaCoincide = 
                    (
                    fechaInicio == null 
                    || constancia.getFechaExpedicion().isAfter(fechaInicio) 
                    || constancia.getFechaExpedicion().isEqual(fechaInicio)
                    )
                    && 
                    (
                    fechaFin == null 
                    || constancia.getFechaExpedicion().isBefore(fechaFin) 
                    || constancia.getFechaExpedicion().isEqual(fechaFin)
                    );

            return nombreCoincide && profesorCoincide && fechaCoincide;
        });
    }
}
