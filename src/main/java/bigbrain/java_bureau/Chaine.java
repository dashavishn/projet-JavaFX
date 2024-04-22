package bigbrain.java_bureau;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
public class Chaine implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation du contrôleur
    }

    @FXML
    private void validerBoutton(ActionEvent event) {
        System.out.println("Le bouton VALIDER a été cliqué !");
    }
}
