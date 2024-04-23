package bigbrain.java_bureau;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void  nHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}