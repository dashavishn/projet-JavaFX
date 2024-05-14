package bigbrain.java_bureau.controller;
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

public class ChaineController {
    @FXML private TableView<Element> tableInputElements;
    @FXML private TableView<Element> tableOutputElements;
    @FXML private ChoiceBox<Integer> activationLevel;
    @FXML private TableColumn<Element, String> nomColumnInput;
    @FXML private TableColumn<Element, Float> quantityColumnInput;
    @FXML private TableColumn<Element, String> nomColumnOutput;
    @FXML private TableColumn<Element, Float> quantityColumnOutput;
    @FXML private Label error1;
    @FXML private Label error2;
    @FXML private Label error3;

    private ChaineProduction chaineProduction;

    @FXML
    public void initialize() {
        setupTable(tableInputElements, nomColumnInput, quantityColumnInput);
        setupTable(tableOutputElements, nomColumnOutput, quantityColumnOutput);
        setupChoiceBox();
        chaineProduction = new ChaineProduction("DefaultCode", "DefaultChaine");
    }

    private void setupTable(TableView<Element> tableView, TableColumn<Element, String> codeColumn, TableColumn<Element, Float> quantityColumn) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        tableView.setItems(getExampleElements());
    }

    private ObservableList<Element> getExampleElements() {
        ObservableList<Element> elements = FXCollections.observableArrayList();
        elements.add(new Element("E001", "Element 1", 100, "kg", 10, 20));
        elements.add(new Element("E002", "Element 2", 150, "kg", 15, 25));
        return elements;
    }

    private void setupChoiceBox() {
        activationLevel.getItems().addAll(0, 1, 2, 3);
        activationLevel.setValue(0); // Default value
        activationLevel.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            chaineProduction.setNiveauActivation(newVal);
        });
    }

    @FXML
    private void validateProduction() {
        try {
            double result = chaineProduction.simulerProduction();
            Alert alert = new Alert(AlertType.INFORMATION);
            if (result == -1.0) {
                alert.setTitle("Production Impossible");
                alert.setHeaderText(null);
                alert.setContentText("La simulation a été arrêtée en raison d'un manque de stock.");
                alert.showAndWait();
            } else {
                alert.setTitle("Production Réussie");
                alert.setHeaderText(null);
                alert.setContentText("La production a été simulée avec succès. Coût total : " + result);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de la simulation de la production");
            alert.setContentText("Une erreur est survenue : " + e.getMessage());
            alert.showAndWait();
            System.err.println("Error during production simulation: " + e.getMessage());
        }
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

    public void Valider1() throws Exception {
        int i = Integer.parseInt(activationLevel.getValue().toString());
        if (0 <= i && i <= 9){
            for (ChaineProduction c : CSV.liste) {
                if(c.getCode().equals("C001")) {
                    c.setNiveauActivation(i);

                    if (!c.valider()){
                        error1.setText("Stock insuffisant");
                    }
                    else{
                        error1.setText("Commande validée");


                    }
                }

            }
            EcrireCSV a = new EcrireCSV();

            a.clearCSVFile("src/main/ressources/fichierscsv/elements.csv");
            a.writeElementsToCSV("src/main/ressources/fichierscsv/elements.csv", Stocks.EStock);
            a.clearCSVFile("src/main/ressources/fichierscsv/historique.csv");
            a.writeModificationsToCSV("src/main/ressources/fichierscsv/historique.csv", Historique.historiqueModifications);
        }
        else {
            error1.setText("Niveau d'activation incorrect");
        }


    }
}
