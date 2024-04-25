package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Commandes_ValidationController {

        @FXML
        private TextField codeTextField;

        @FXML
        private TextField nomTextField;

        @FXML
        private TextField uniteTextField;

        @FXML
        private TextField quantiteTextField;

        @FXML
        private Button commanderButton;

        @FXML
        private TextArea messageTextArea;

        @FXML
        private void initialize() {
            // Code d'initialisation (si nécessaire)
        }

        @FXML
        private void handleCommander() {
            String code = codeTextField.getText();
            String nom = nomTextField.getText();
            String unite = uniteTextField.getText();
            String quantite = quantiteTextField.getText();

            if (code.isEmpty() || nom.isEmpty() || unite.isEmpty() || quantite.isEmpty()) {
                messageTextArea.setText("Veuillez remplir tous les champs.");
            } else {

                messageTextArea.setText("Commande effectuée avec succès !");

                codeTextField.clear();
                nomTextField.clear();
                uniteTextField.clear();
                quantiteTextField.clear();
            }
        }

        @FXML
        private void handleNouvelleCommande() {
            // Ajoutez ici la logique pour une nouvelle commande
        }

        @FXML
        private void handleRetour() {
            // Ajoutez ici la logique pour retourner à la vue précédente
        }
    }

