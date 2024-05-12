package bigbrain.java_bureau.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static bigbrain.java_bureau.Main.primaryStage;

public class CommandesController {

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textFieldCode;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldUnite;
    @FXML
    private TextField textFieldQuantite;

    @FXML
    private Text textStatus;

    @FXML
    private Button buttonCommander;

    // Navigation methods
    @FXML
    private void Page_Accueil() {
        loadPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Stock() {
        loadPage("/bigbrain/java_bureau/stock.fxml");
    }

    @FXML
    private void Page_Chaine() {
        loadPage("/bigbrain/java_bureau/chaine.fxml");
    }

    @FXML
    private void Page_Commandes() {
        loadPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Historique() {
        loadPage("/bigbrain/java_bureau/historique.fxml");
    }

    // Method to load pages
    private void loadPage(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            textStatus.setText("Error loading the page: " + e.getMessage());
        }
    }

    // Method to handle the 'Commander' action
    @FXML
    private void handleCommand() {
        String code = textFieldCode.getText();
        String nom = textFieldNom.getText();
        String unite = textFieldUnite.getText();
        String quantite = textFieldQuantite.getText();

        if (code.isEmpty() || nom.isEmpty() || unite.isEmpty() || quantite.isEmpty()) {
            textStatus.setText("Please fill in all fields.");
            return;
        }

        try {
            int qty = Integer.parseInt(quantite);
            if (qty <= 0) {
                textStatus.setText("Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            textStatus.setText("Invalid quantity format.");
            return;
        }

        // Assume a successful command submission
        textStatus.setText("Command submitted successfully.");
        // Clear fields after submission
        textFieldCode.clear();
        textFieldNom.clear();
        textFieldUnite.clear();
        textFieldQuantite.clear();
    }
}
