package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.clientController;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.HelloApplication;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client.ClienteXMLData;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client.InsertarXML;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.GeneratePatternCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.jdom2.JDOMException;

import java.io.IOException;

public class InsertController {
    @javafx.fxml.FXML
    private TextField nameArea;
    @javafx.fxml.FXML
    private TextField clasificationArea;
    @javafx.fxml.FXML
    private TextField contextArea;
    @javafx.fxml.FXML
    private TextField exampleArea;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField problemArea;
    @javafx.fxml.FXML
    private TextField solutionArea;
    @javafx.fxml.FXML
    private DatePicker datePicker;

    InsertarXML insertar = new InsertarXML();
    GeneratePatternCode generatePatternCode = new GeneratePatternCode();

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void ConnectionOnAction(ActionEvent actionEvent) {
        loadPage("conexion.fxml");
    }

    @javafx.fxml.FXML
    public void goBackOnAction(ActionEvent actionEvent) {
        loadPage("menu.fxml");
    }

    @javafx.fxml.FXML
    public void insertOnAction(ActionEvent actionEvent) {
        try {

            DesignPattern designPattern = new DesignPattern();

            DesignPattern designPatternUsed = new DesignPattern(generatePatternCode.generatePatternCode(),nameArea.getText(),contextArea.getText(),clasificationArea.getText(),exampleArea.getText(),contextArea.getText(),problemArea.getText(),solutionArea.getText(),datePicker.getValue());

            ClienteXMLData client = new ClienteXMLData("C:\\Users\\micha\\IdeaProjects\\Progra II\\Intellij\\PruebaProyectoIProgramacion\\src\\main\\java\\cr\\ac\\ucr\\paraiso\\prograii\\pruebaproyectoi\\pruebaproyectoiprogramacion\\Proyecto.xml");

            insertar.insertar(designPatternUsed, client.getDocumento(), client.getRaiz(),client.getRutaDocumento());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JDOMException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
