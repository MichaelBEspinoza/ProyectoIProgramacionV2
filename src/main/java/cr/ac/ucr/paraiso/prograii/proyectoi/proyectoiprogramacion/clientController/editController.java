package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.clientController;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class editController {
    @javafx.fxml.FXML
    private TextField solutionArea;
    @javafx.fxml.FXML
    private TextField nameArea;
    @javafx.fxml.FXML
    private TextField clasificationArea;
    @javafx.fxml.FXML
    private TextField problemArea;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextField contextArea;
    @javafx.fxml.FXML
    private Text state;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField exampleArea;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void goBackOnAction(ActionEvent actionEvent) {loadPage("menu.fxml");
    }

    @javafx.fxml.FXML
    public void insertOnAction(ActionEvent actionEvent) {
    }
}
