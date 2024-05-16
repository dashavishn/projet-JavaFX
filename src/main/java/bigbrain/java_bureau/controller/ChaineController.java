package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bigbrain.java_bureau.classe_java.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

import static bigbrain.java_bureau.Main.primaryStage;
import static bigbrain.java_bureau.classe_java.Entrepot.getChaine;

public class ChaineController {

    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnCommandes;
    @FXML
    private Button commandes;
    @FXML
    private Button historique;


    @FXML
    private TableView<ChaineProduction> tableChaine;

    @FXML
    private TableColumn<ChaineProduction, String> chaineNom;
    @FXML
    private TableColumn<ChaineProduction, String> chaineCode;
    @FXML
    private TableColumn<ChaineProduction, Integer> chaineActivation;
    @FXML
    private TableColumn<ChaineProduction, String> chaineEntree;
    @FXML
    private TableColumn<ChaineProduction, String> chaineSortie;

    @FXML
    public void initialize() {
        tableChaine.setEditable(true);

        chaineNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        chaineCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        chaineActivation.setCellValueFactory(new PropertyValueFactory<>("niveauActivation"));
        chaineEntree.setCellValueFactory(new PropertyValueFactory<>("stringElementEntree"));
        chaineSortie.setCellValueFactory(new PropertyValueFactory<>("stringElementSortie"));

        // Configurer la colonne d'activation pour utiliser TextFieldTableCell
        chaineActivation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        chaineActivation.setOnEditCommit(event -> {
            ChaineProduction chaine = event.getRowValue();
            int newLevel = event.getNewValue();
            chaine.setNiveauActivation(newLevel);
            try {
                if (!chaine.valider()) {
                    showAlert("Validation échouée", "Niveau d'activation non supporté par les stocks actuels pour la chaîne: " + chaine.getNom());
                    // Réinitialiser la valeur si nécessaire ou notifier l'utilisateur
                } else {
                    // Si la validation passe, mettre à jour le niveau d'activation
                    chaine.setNiveauActivation(newLevel);
                }
            } catch (Exception e) {
                showAlert("Erreur lors de la validation", "Erreur lors de la validation de la chaîne: " + chaine.getNom() + " Error: " + e.getMessage());
            }
        });

        loadChaineData();  // Charger les données à l'initialisation
    }

    private void loadChaineData() {
        CSV csvUtil = new CSV();
        csvUtil.lireChaines();  // Cette méthode devrait déjà mettre à jour la liste des chaînes dans la classe Entrepot.
        tableChaine.setItems(Entrepot.getChaine());  // Définir les éléments de TableView avec les données chargées
    }

    @FXML
    private void handleValidateProduction() {
        ObservableList<ChaineProduction> chaines = tableChaine.getItems();
        boolean allValid = true;
        for (ChaineProduction chaine : chaines) {
            try {
                if (!chaine.valider()) {  // Supposons que valider() vérifie si la chaîne peut être activée avec les stocks actuels
                    allValid = false;
                    showAlert("Validation échouée", "Validation échouée pour la chaîne: " + chaine.getNom());
                    break;
                }
            } catch (Exception e) {
                allValid = false;
                showAlert("Erreur lors de la validation", "Erreur lors de la validation de la chaîne: " + chaine.getNom() + " Error: " + e.getMessage());
                break;
            }
        }
        if (allValid) {
            showAlert("Validation réussie", "Toutes les chaînes sont validées avec succès.");
        } else {
            showAlert("Validation échouée", "Certaines chaînes ne peuvent pas être activées selon les niveaux d'activation et les stocks.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



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



    public void ChargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la page: " + page);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Fichier FXML non trouvé: " + page);
            e.printStackTrace();
        }
    }
}
