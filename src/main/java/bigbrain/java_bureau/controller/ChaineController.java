package bigbrain.java_bureau.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import bigbrain.java_bureau.classe_java.ChaineProduction;
import bigbrain.java_bureau.classe_java.*;


import static bigbrain.java_bureau.Main.primaryStage;

public class ChaineController {
    @FXML
    private TextField textFieldCodeEntree;
    @FXML
    private TextField textFieldQuantiteEntree;
    @FXML
    private TextField textFieldCodeSortie;
    @FXML
    private TextField textFieldQuantiteSortie;
    @FXML
    private Text textResultat;

    // Méthodes pour la navigation
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
    }

    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/historique.fxml");
    }

    @FXML
   /* public void updateNiveauActivation(int nouveauNiveau) {
        ChaineProduction.setNiveauActivation(nouveauNiveau);
        simulerEtAfficherResultats();  // Méthode qui simule la production et affiche les résultats
    }
*/
    private void ChargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print the error to help with debugging.
        }
    }

    // Méthode pour valider les données saisies
    @FXML
    private void valider() {
        String codeEntree = textFieldCodeEntree.getText();
        String quantiteEntreeText = textFieldQuantiteEntree.getText();
        String codeSortie = textFieldCodeSortie.getText();
        String quantiteSortieText = textFieldQuantiteSortie.getText();

        if (codeEntree.isEmpty() || quantiteEntreeText.isEmpty() || codeSortie.isEmpty() || quantiteSortieText.isEmpty()) {
            textResultat.setText("Veuillez remplir tous les champs.");
            return;
        }

        try {
            int quantiteEntree = Integer.parseInt(quantiteEntreeText);
            int quantiteSortie = Integer.parseInt(quantiteSortieText);
            // Assume some logic here to process the quantities...
            textResultat.setText("Données validées avec succès !");
        } catch (NumberFormatException e) {
            textResultat.setText("Veuillez saisir des nombres valides pour les quantités.");
        }
    }



}
