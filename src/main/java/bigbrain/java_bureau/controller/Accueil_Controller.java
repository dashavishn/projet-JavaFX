package bigbrain.java_bureau.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Accueil_Controller implements Initializable {

    @FXML
    private Button stock;
    @FXML
    private Button chaine;
    @FXML
    private Button commandes;
    @FXML
    private Button historique;
    /*je meurs*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation spécifique, si nécessaire
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
    }

    @FXML
    private void Page_Chaine() {
        ChargerPage("/bigbrain/java_bureau/chaine.fxml");
    }

    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/historique.fxml");
    }

    private void ChargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) stock.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
