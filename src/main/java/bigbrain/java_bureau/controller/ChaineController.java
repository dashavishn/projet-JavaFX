package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.io.IOException;

import static bigbrain.java_bureau.Main.primaryStage;

import java.io.IOException;

import static bigbrain.java_bureau.Main.primaryStage;

//le bouton renvoie que la production est lancé peu importe le niveau d'activation,
// alors que tout est bien défini dans la méthode handleValidateProduction
//les tableviews se remplissent avec les données de fichier chaine,
// alors qu'ils doivent avoir les elements spécifiques qui sont dans TestChaineProduction et jsp quoi faire avec ça
//j'en peux plus
public class ChaineController {
    @FXML private TableView<Element> tableInputElements;
    @FXML private TableView<Element> tableOutputElements;
    @FXML private ChoiceBox<Integer> activationLevel;
    @FXML private TableColumn<Element, String> nomColumnInput;
    @FXML private TableColumn<Element, Double> quantityColumnInput;
    @FXML private TableColumn<Element, String> nomColumnOutput;
    @FXML private TableColumn<Element, Double> quantityColumnOutput;

    private ChaineProduction chaineProduction = new ChaineProduction("DefaultCode", "DefaultChaine");

    @FXML
    public void initialize() {
        setupTableColumns();
        setupChoiceBox();
        loadInitialData();
    }

    private void setupTableColumns() {
        nomColumnInput.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantityColumnInput.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        nomColumnOutput.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantityColumnOutput.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
    }

    private void setupChoiceBox() {
        activationLevel.getItems().addAll(0, 1);
        activationLevel.setValue(1);
        activationLevel.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                chaineProduction.setNiveauActivation(newVal);
                System.out.println("Niveau d'activation réglé sur : " + newVal);
            }
        });
    }



    private void loadInitialData() {
        ObservableList<Element> inputElements = FXCollections.observableArrayList(Stocks.getStockItems().values());
        ObservableList<Element> outputElements = FXCollections.observableArrayList(Stocks.getStockItems().values());
        tableInputElements.setItems(inputElements);
        tableOutputElements.setItems(outputElements);
    }

    @FXML
    private void handleValidateProduction() {
        if (activationLevel.getValue() > 0) {
            boolean result = false;
            try {
                result = chaineProduction.valider();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (result) {
                showAlert("Production Réalisée", "La production a été réalisée avec succès.", AlertType.INFORMATION);
            } else {
                showAlert("Erreur de Production", "Éléments en stock insuffisants pour réaliser la production.", AlertType.ERROR);
            }
        } else {
            showAlert("Production Inactive", "Le niveau d'activation est défini à 0, aucune production n'est réalisée.", AlertType.INFORMATION);
        }
        EcrireCSV a = new EcrireCSV();

        a.clearCSVFile("src/main/resources/bigbrain/fichierscsv/elements.csv");
        a.writeElementsToCSV("src/main/resources/bigbrain/fichierscsv/elements.csv", Stocks.EStock);
        a.clearCSVFile("src/main/resources/bigbrain/fichierscsv/historique.csv");
        a.writeModificationsToCSV("src/main/ressources/bigbrain/fichierscsv/historique.csv", Historique.historiqueModifications);
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
