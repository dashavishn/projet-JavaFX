package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

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
    @FXML private TableColumn<ChaineProduction, String> nomColumnOutput;
    @FXML private TableColumn<ChaineProduction, Double> quantityColumnOutput;

    private ChaineProduction selectedChaine;

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
        activationLevel.getItems().addAll(0, 1, 2, 3, 4, 5);
        activationLevel.setValue(0);
        activationLevel.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (selectedChaine != null && newVal != null) {
                selectedChaine.setNiveauActivation(newVal);
                updateTableData();
            }
        });
    }

    private void updateTableData() {
        ObservableList<Element> inputElements = FXCollections.observableArrayList();
        selectedChaine.getElementEntree().forEach((element, qty) -> {
            element.setQuantiteStock(qty * selectedChaine.getNiveauActivation());
            inputElements.add(element);
        });

        ObservableList<Element> outputElements = FXCollections.observableArrayList();
        selectedChaine.getElementSortie().forEach((element, qty) -> {
            element.setQuantiteStock(qty * selectedChaine.getNiveauActivation());
            outputElements.add(element);
        });

        tableInputElements.setItems(inputElements);
        tableOutputElements.setItems(outputElements);
    }

    private void loadInitialData() {
        selectedChaine = new ChaineProduction("C001", "Chaine de Test");  // Exemple de création d'une chaîne
        // Supposé ajout d'éléments en entrée et sortie
        Element inputElement = new Element("E001", "Plastique", 100, "kg", 5.0, 6.0);
        Element outputElement = new Element("P001", "Produit Fini", 0, "unité", 15.0, 20.0);
        selectedChaine.ajouterElementEntree(inputElement, 10);
        selectedChaine.ajouterElementSortie(outputElement, 1);
        updateTableData();
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
