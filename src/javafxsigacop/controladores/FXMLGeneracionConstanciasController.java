package javafxsigacop.controladores;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafxsigacop.modelo.dao.ConstanciaDAO;
import javafxsigacop.modelo.pojo.ConstanciaEE;
import javafxsigacop.modelo.pojo.ExperienciaEducativa;
import javafxsigacop.modelo.pojo.PeriodoEscolar;
import javafxsigacop.modelo.pojo.ProgramaEducativo;
import javafxsigacop.utilidades.RecursosEstaticos;
import javafxsigacop.utilidades.Constantes;
import javafxsigacop.utils.Utilidades;
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
    @FXML
    private TextField tfHorasSemanaMes;

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
                    tfHorasSemanaMes.setText("");
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
                    tfHorasSemanaMes.setText(newValue.getHorasSemanaMes());
                    lbErrorExperienciaEducativa.setVisible(false);
                }
            }
        });
    }

    @FXML
    private void generarConstanciaClic(ActionEvent event) {
        ocultarErrores();
        
        if(validarCampos()) {
            generarConstancia();
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
    
    private void generarConstancia() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog((Stage) lbDirector.getScene().getWindow());

        if(selectedDirectory == null){
            Utilidades.mostrarDialogoSimple(
                "Seleccione una carpeta", 
                "Debe seleccionar la carpeta de destino en la que se "
                    + "guardará la constancia", 
                Alert.AlertType.WARNING
            );
        } else {
            try {
                boolean constanciaGuardada = guardarRegistroConstancia();
                if(constanciaGuardada) {
                    descargarDocumentoPDF(selectedDirectory.getAbsolutePath());
                    limpiarCampos();
                    mostrarMensajeGeneracionExitosa();
                }
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
    
    private void descargarDocumentoPDF(String nombreCarpetaAGuardar) throws IOException, DocumentException {
        //TO-DO: Recuperar informaciónd del singleton
        String nombreProfesor = "Erika Meneses Rico";
        String nombrePeriodo = cbPeriodoEscolar.getValue().getNombre();
        String nombrePrograma = cbProgramaEducativo.getValue().getNombre();
        String nombreExperiencia = cbExperienciaEducativa.getValue().getNombre();        
        String bloqueExperiencia = cbExperienciaEducativa.getValue().getBloque();
        String seccionExperiencia = cbExperienciaEducativa.getValue().getSeccion();
        String creditosExperiencia = cbExperienciaEducativa.getValue().getCreditos();        
        String horasSemanaMes = cbExperienciaEducativa.getValue().getHorasSemanaMes();
        
        Document document = new Document();
        document.setMargins(100f, 100f, 70f, 70f);

        String nombreAutogeneradoPDF = "constanciaEE-" + Utilidades.obtenerFechaHoraActual() + ".pdf";
        OutputStream outputStream = 
            new FileOutputStream(new File(nombreCarpetaAGuardar, nombreAutogeneradoPDF));

        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph parrafoAQuienCorresponda = new Paragraph(
            "A quien corresponda",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        );
        parrafoAQuienCorresponda.setSpacingAfter(20f);
        document.add(parrafoAQuienCorresponda);

        document.add(new Paragraph(
            "​​El que suscribe, Director de la Facultad de Estadística "
                + "e Informática, de la Universidad Veracruzana ",
            FontFactory.getFont(FontFactory.TIMES, 12)
        ));

        Paragraph parrafoHaceConstar = new Paragraph(
            "HACE CONSTAR",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        );
        parrafoHaceConstar.setAlignment(Element.ALIGN_CENTER);
        parrafoHaceConstar.setSpacingBefore(10f);            
        parrafoHaceConstar.setSpacingAfter(10f);
        document.add(parrafoHaceConstar);

        Paragraph parrafoCurso = new Paragraph(
            "​que el Mtro. ", 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        Chunk nombreProfesorBold = new Chunk(nombreProfesor, FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD));
        parrafoCurso.add(nombreProfesorBold);
        parrafoCurso.add(", impartió la siguiente experiencia educativa en el periodo " + nombrePeriodo);
        parrafoCurso.setSpacingAfter(15f);
        document.add(parrafoCurso);

        PdfPTable tablaEE = new PdfPTable(4);
        tablaEE.setWidthPercentage(100);
        tablaEE.addCell(new Paragraph(
            "Programa educativo",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        ));
        tablaEE.addCell(new Paragraph(
            "Experiencia educativa",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        ));
        tablaEE.addCell(new Paragraph(
            "​Bloque/Sección/Créditos",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        ));
        tablaEE.addCell(new Paragraph(
            "H/S/M",
            FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD)
        ));

        tablaEE.addCell(new Paragraph(nombrePrograma, FontFactory.getFont(FontFactory.TIMES, 12)));
        tablaEE.addCell(new Paragraph(nombreExperiencia, FontFactory.getFont(FontFactory.TIMES, 12)));
        tablaEE.addCell(new Paragraph(bloqueExperiencia + "/" + seccionExperiencia + "/" + creditosExperiencia, FontFactory.getFont(FontFactory.TIMES, 12)));
        tablaEE.addCell(new Paragraph(horasSemanaMes, FontFactory.getFont(FontFactory.TIMES, 12)));
        document.add(tablaEE);

        Paragraph parrafoFinal = new Paragraph(
            "A petición de la interesada y para los fines legales que a la "
                + "misma convenga, se extiende la presente en la ciudad de "
                + "Xalapa-Enríquez, Veracruz a fecha de " 
                + Utilidades.obtenerFechaActual() + ".", 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        parrafoFinal.setSpacingBefore(30f);            
        parrafoFinal.setSpacingAfter(30f);
        document.add(parrafoFinal);

        Paragraph parrafoAtentamente = new Paragraph(
            "A t e n t a m e n t e", 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        parrafoAtentamente.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoAtentamente);
        Paragraph parrafoLIS = new Paragraph(
            "​\"Lis de Veracruz: Arte, Ciencia, Luz\"", 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        parrafoLIS.setAlignment(Element.ALIGN_CENTER);
        parrafoLIS.setSpacingAfter(40f);
        document.add(parrafoLIS);

        Paragraph parrafoNombreDirector = new Paragraph(
            RecursosEstaticos.obtenerNombreDirector(), 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        parrafoNombreDirector.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoNombreDirector);
        Paragraph parrafoDirector = new Paragraph(
            "Director", 
            FontFactory.getFont(FontFactory.TIMES, 12)
        );
        parrafoDirector.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoDirector);

        document.close();
        outputStream.close();
    }
    
    private boolean guardarRegistroConstancia() {
        boolean constanciaguardadaEnBD = false;
        
        //TO-DO: Recuperar informaciónd del singleton
        int numeroPersonal = 1;
        String nombrePeriodo = cbPeriodoEscolar.getValue().getNombre();
        String nombrePrograma = cbProgramaEducativo.getValue().getNombre();
        String nombreExperiencia = cbExperienciaEducativa.getValue().getNombre();        
        String bloqueExperiencia = cbExperienciaEducativa.getValue().getBloque();
        String seccionExperiencia = cbExperienciaEducativa.getValue().getSeccion();
        String creditosExperiencia = cbExperienciaEducativa.getValue().getCreditos();        
        String horasSemanaMes = cbExperienciaEducativa.getValue().getHorasSemanaMes();
        
        ConstanciaEE constancia = new ConstanciaEE();
        constancia.setFechaExpedicion(Utilidades.obtenerFechaActualFormatoBD());
        constancia.setNombreDirector(RecursosEstaticos.obtenerNombreDirector());
        constancia.setNumeroPersonal(numeroPersonal);
        constancia.setBloque(bloqueExperiencia);
        constancia.setSeccion(seccionExperiencia);
        constancia.setHorasSemanaMes(horasSemanaMes);
        constancia.setProgramaEducativo(nombrePrograma);
        constancia.setExperienciaEducativa(nombreExperiencia);
        constancia.setPeriodoEscolar(nombrePeriodo);
        constancia.setCreditosExperiencia(creditosExperiencia);
        
        int codigoRespuesta = ConstanciaDAO.guardarConstanciaEE(constancia);
        switch (codigoRespuesta) {
            case Constantes.OPERACION_EXITOSA:
                constanciaguardadaEnBD = true;
                break;
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple(
                    "Error de conexión", 
                    "El sistema se encuentra enfrentando errores de conexión, "
                    + "por favor intente en otro momento", 
                    Alert.AlertType.ERROR
                );
            default:
                Utilidades.mostrarDialogoSimple(
                    "Error al guardar la constancia", 
                    "Ocurrió un error al guardar la constancia, por favor "
                    + "intente en otro momento", 
                    Alert.AlertType.ERROR
                );
        }
        
        return constanciaguardadaEnBD;
    }
    
    private void mostrarMensajeGeneracionExitosa() {
        Utilidades.mostrarDialogoSimple(
            "Constancia generada exitosamente", 
            "La constancia de experiencia docente se generó de forma exitosa", 
            Alert.AlertType.INFORMATION
        );
    }
    
    private void limpiarCampos() {
        tfBloque.setText("");
        tfSeccion.setText("");
        tfHorasSemanaMes.setText("");
        cbProgramaEducativo.setValue(null);
        cbExperienciaEducativa.setValue(null);
        cbPeriodoEscolar.setValue(null);
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
