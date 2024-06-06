package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.clientController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.Socket;

public class ConexionController {

    @FXML
    private Text label;
    private Socket socket;

    @FXML
    public void disconnectOnAction(ActionEvent actionEvent) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                label.setText("Disconnected from server");
            } catch (IOException e) {
                label.setText("Error disconnecting: " + e.getMessage());
            }
        } else {
            label.setText("Not connected to any server");
        }
    }

    @FXML
    public void connectOnAction(ActionEvent actionEvent) {
        try {
            socket = new Socket("localhost", 12345);  // Asegúrate de que la dirección y el puerto sean correctos
            label.setText("Connected to server");
        } catch (IOException e) {
            label.setText("Error connecting: " + e.getMessage());
        }
    }

    @FXML
    public void exitOnAction(ActionEvent actionEvent) {
        System.exit(1);
    }
}
