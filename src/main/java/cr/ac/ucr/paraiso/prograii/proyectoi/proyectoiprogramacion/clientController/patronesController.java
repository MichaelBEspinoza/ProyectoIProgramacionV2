package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.clientController;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class patronesController
{
    @javafx.fxml.FXML
    private Text estado;
    @javafx.fxml.FXML
    private BorderPane bp;

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void goBackOnAction(ActionEvent actionEvent) {loadPage("menu.fxml");
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void modifyOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void exitOnAction(ActionEvent actionEvent) {
    }
}