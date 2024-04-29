package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class ChaineController implements Initializable{
    @FXML
    private AnchorPane Chaine;

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

        // je vais vérifier si les champs sont vides
        if (codeEntree.isEmpty() || quantiteEntreeText.isEmpty() || codeSortie.isEmpty() || quantiteSortieText.isEmpty()) {
            textResultat.setTextContent("Veuillez remplir tous les champs.");
            return;
        }

        // c'est pour convertir les quantités en entiers
        int quantiteEntree;
        int quantiteSortie;
        try {
            quantiteEntree = Integer.parseInt(quantiteEntreeText);
            quantiteSortie = Integer.parseInt(quantiteSortieText);
        } catch (NumberFormatException e) {
            textResultat.setTextContent("Veuillez saisir des nombres valides pour les quantités.");
            return;
        }

        textResultat.setTextContent("Données validées avec succès !");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
