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
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;

import static bigbrain.java_bureau.Main.primaryStage;
import static bigbrain.java_bureau.classe_java.Entrepot.getChaine;


//le bouton renvoie que la production est lancé peu importe le niveau d'activation,
// alors que tout est bien défini dans la méthode handleValidateProduction
//les tableviews se remplissent avec les données de fichier chaine,
// alors qu'ils doivent avoir les elements spécifiques qui sont dans TestChaineProduction et jsp quoi faire avec ça
//j'en peux plus


public class ChaineController {
    public TableColumn<ChaineProduction, String> chaineNom;
    public TableColumn<ChaineProduction, String> chaineCode;
    public TableColumn<ChaineProduction, String> chaineActivation;
    public TableColumn<ChaineProduction, String> chaineUnite;
    public TableColumn<ChaineProduction, String> chaineEntree;
    public TableColumn<ChaineProduction, String> chaineSortie;

    @FXML private TableView<ChaineProduction> tableChaine;


    private ChaineProduction selectedChaine;

    @FXML
    public void initialize() {
        chaineNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        chaineCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        chaineEntree.setCellValueFactory(new PropertyValueFactory<>("strEntree"));
        chaineSortie.setCellValueFactory(new PropertyValueFactory<>("strSortie"));

        ObservableList<String> activationOptions = FXCollections.observableArrayList("Activée", "Désactivée");
        chaineActivation.setCellValueFactory(new PropertyValueFactory<>("activation"));


        tableChaine.setItems(getChaine());
    }



    @FXML
    private void handleValidateProduction() {
        try {
            if (selectedChaine != null && selectedChaine.getNiveauActivation() > 0 && selectedChaine.valider()) {
                showAlert("Production Réalisée", "La production a été réalisée avec succès.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Erreur de Production", "Problème de validation de la production ou niveau d'activation à 0.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("Erreur Système", "Erreur lors de la validation de la production: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
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
