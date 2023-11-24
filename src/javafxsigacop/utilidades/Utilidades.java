package javafxsigacop.utilidades;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafxsigacop.JavaFXSIGACOP;
import javafxsigacop.modelo.pojo.ConstanciaEE;
import javafxsigacop.modelo.pojo.Cuenta;

public class Utilidades {
    public static void mostrarDialogoSimple(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alertaSimple = new Alert(tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.showAndWait();
    }
    
    public static Scene inicializaEscena(String ruta){
        Scene escena = null;
        try {
            Parent vista = FXMLLoader.load(JavaFXSIGACOP.class.getResource(ruta));
            escena = new Scene(vista);
        } catch (IOException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return escena;
    }
    
    public static boolean mostrarDialogoConfirmacion(String titulo, String mensaje){
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle(titulo);
        alertaConfirmacion.setHeaderText(null);
        alertaConfirmacion.setContentText(mensaje);
        Optional<ButtonType> botonClic = alertaConfirmacion.showAndWait();
        return (botonClic.get() == ButtonType.OK);
    }

    public static String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

        String fechaFormateada = fechaActual.format(formateador);
        return fechaFormateada;
    }
    
    public static String obtenerFechaActualFormatoBD() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("es", "ES"));

        String fechaFormateada = fechaActual.format(formateador);
        return fechaFormateada;
    }
    
    public static String obtenerFechaHoraActual() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String fechaHoraFormateada = fechaHoraActual.format(formato);

        return fechaHoraFormateada;
    }
    
    public static void descargarDocumentoPDF(
            String nombreCarpetaAGuardar, 
            ConstanciaEE constanciaEE
    ) throws IOException, DocumentException {
        String nombreProfesor = Cuenta.getInstanciaSingleton().toString();
        String nombrePeriodo = constanciaEE.getPeriodoEscolar();
        String nombrePrograma = constanciaEE.getProgramaEducativo();
        String nombreExperiencia = constanciaEE.getExperienciaEducativa();        
        String bloqueExperiencia = constanciaEE.getBloque();
        String seccionExperiencia = constanciaEE.getSeccion();
        String creditosExperiencia = constanciaEE.getCreditosExperiencia();        
        String horasSemanaMes = constanciaEE.getHorasSemanaMes();
        
        Document document = new Document();
        document.setMargins(100f, 100f, 70f, 70f);

        String nombreAutogeneradoPDF = 
                "constanciaEE-" + Utilidades.obtenerFechaHoraActual() + ".pdf";
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
    
    public static String cifrarContrasenha(String contrasenha) throws NoSuchAlgorithmException{
        final String FORMATO_BYTE_DOS_CARACTERES_HEXADECIMAL = "%02x";
        MessageDigest procesadorMensaje = MessageDigest.getInstance("SHA-256");
        byte[] cifradoBytes = procesadorMensaje.digest(contrasenha.getBytes());
            
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : cifradoBytes){
            stringBuilder.append(String.format(FORMATO_BYTE_DOS_CARACTERES_HEXADECIMAL,b));
        }

        String contrasenhaCifrada = stringBuilder.toString();
        
        return contrasenhaCifrada;
    }
}
