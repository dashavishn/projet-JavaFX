package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.EcrireCSV;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Historique;
import bigbrain.java_bureau.classe_java.Stocks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static bigbrain.java_bureau.Main.primaryStage;
import static java.lang.String.valueOf;

public class StockController implements Initializable {
    @FXML
    private TableView<Element> tableStock;
    @FXML
    private TableColumn<Element, Double> colPrixAchat, colPrixVente, colQuantite;
    @FXML
    private TableColumn<Element, String> colCode, colNom, colUnite;

    @FXML
    private TextField inputCode;
    @FXML
    private TextField inputQuantity;
    @FXML
    private Label errorLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
    }
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Chaine() {
        ChargerPage("/bigbrain/java_bureau/chaine.fxml");
    }

    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/historique.fxml");
    }



    /**
     * Vend l'élément spécifié par l'utilisateur et met à jour le stock.
     * @param event L'événement qui déclenche cette méthode.
     */
    @FXML
    private void vendreElement(ActionEvent event) {
        try {
            String code = inputCode.getText().trim(); // Assurez-vous que c'est le bon TextField selon votre FXML
            String quantiteText = inputQuantity.getText().trim(); // Récupération du texte
            System.out.println("Quantité saisie: '" + quantiteText + "'"); // Log pour débogage

            float quantite = Float.parseFloat(quantiteText);
            Element element = Stocks.getElement(code);

            if (element == null) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Élément non trouvé avec ce code.");
                return;
            }

            if (quantite > element.getQuantiteStock()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Stock insuffisant.");
            } else {
                Stocks.retirerStock(code, quantite);
                showAlert(Alert.AlertType.INFORMATION, "Vente réalisée", "Vente réalisée avec succès.");
                // tableStock.refresh(); // Mettre à jour l'affichage du tableau, si nécessaire
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", "Veuillez saisir un nombre valide pour la quantité.");
            e.printStackTrace(); // Cela permettra d'imprimer l'erreur exacte dans la console
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur lors de la vente", e.getMessage());
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void setupTable() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colPrixAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        colPrixVente.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        colUnite.setCellValueFactory(new PropertyValueFactory<>("uniteMesure"));

        ObservableList<Element> elementsObservable = FXCollections.observableArrayList(Stocks.getInstance().getStockItems().values());
        tableStock.setItems(elementsObservable);

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