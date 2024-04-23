package bigbrain.java_bureau.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CommandesController {

    @FXML
    private TextField textField;

    @FXML
    private Button actionButton;

    // Méthode appelée lorsque le bouton est cliqué
    @FXML
    private void handleAction() {
        String texte = textField.getText();
        System.out.println("Texte saisi : " + texte);
        // Ajoutez ici la logique de votre application en réponse à l'action de l'utilisateur
    }

    // Vous pouvez ajouter d'autres méthodes et attributs selon les besoins de votre application

}
