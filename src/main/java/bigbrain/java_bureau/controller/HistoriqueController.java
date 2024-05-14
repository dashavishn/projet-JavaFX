package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class HistoriqueController implements Initializable {
    @FXML
    private TableView<Element> tableStock;
    @FXML
    private TextField textQuantite, textcode;
    @FXML
    private Text textResultat;
    @FXML
    private TableColumn<Element, Float> colPrixAchat, colQuantite, colPrixVente;
    @FXML
    private TableColumn<Element, String> colCode, colNom, colUnite;

    @FXML
    private void valider() {
        String quantiteS = textQuantite.getText();
        String codeProduit = textcode.getText();
        if (quantiteS.isEmpty() || codeProduit.isEmpty()) {
            textResultat.setText("Veuillez remplir tous les champs.");
            return;
        }
        try {
            double quantite = Double.parseDouble(quantiteS);
            if (quantite < 0) {
                textResultat.setText("Quantité doit être supérieur à 0");
                return;
            }

            // Utilisation du code produit pour vérifier la disponibilité et pour l'opération de stock
            if (Stocks.getInstance().verifierDisponibilite(codeProduit, quantite)) {
                Stocks.getInstance().enleverElem(codeProduit, quantite);
                textResultat.setText("Commande effectuée avec succès !");
                textQuantite.clear();
                textcode.clear();
            } else {
                textResultat.setText("Stock insuffisant pour cet élément.");
            }
        } catch (NumberFormatException e) {
            textResultat.setText("Veuillez saisir des nombres valides pour les quantités.");
        } catch (Exception e) {
            textResultat.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
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


    // Navigation methods
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
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

    private void ChargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
            Parent root = loader.load();
            Stage stage = (Stage) tableStock.getScene().getWindow(); // Assume that 'tableStock' is always in the current scene.
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            textResultat.setText("Erreur lors du chargement de la page: " + page);
        } catch (NullPointerException e) {
            textResultat.setText("Le fichier FXML n'a pas été trouvé: " + page);
            e.printStackTrace();
        }
    }
}
