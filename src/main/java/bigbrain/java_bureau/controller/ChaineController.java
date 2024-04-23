package bigbrain.java_bureau.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    @FXML
    private Button buttonValider;

    // Méthode pour valider les données saisies et effectuer des actions correspondantes
    @FXML
    private void valider() {
        String codeEntree = textFieldCodeEntree.getText();
        String quantiteEntreeText = textFieldQuantiteEntree.getText();
        String codeSortie = textFieldCodeSortie.getText();
        String quantiteSortieText = textFieldQuantiteSortie.getText();

        // Vérifier si les champs sont vides
        if (codeEntree.isEmpty() || quantiteEntreeText.isEmpty() || codeSortie.isEmpty() || quantiteSortieText.isEmpty()) {
            textResultat.setText("Veuillez remplir tous les champs.");
            return;
        }

        // Convertir les quantités en entiers
        int quantiteEntree;
        int quantiteSortie;
        try {
            quantiteEntree = Integer.parseInt(quantiteEntreeText);
            quantiteSortie = Integer.parseInt(quantiteSortieText);
        } catch (NumberFormatException e) {
            textResultat.setText("Veuillez saisir des nombres valides pour les quantités.");
            return;
        }

        // Effectuer des actions avec les données saisies
        // Par exemple, vous pouvez appeler des méthodes de vos classes de modèle pour manipuler les données

        // Afficher un message de succès
        textResultat.setText("Données validées avec succès !");
    }
}
