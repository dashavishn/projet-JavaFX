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
/**
 * Contrôleur pour la gestion des stocks dans l'interface utilisateur de l'application.
 * Permet la visualisation et la manipulation des stocks d'éléments.
 */
public class StockController implements Initializable {
    /**
     *  TableView pour afficher les éléments en stock.
     */
    @FXML
    private TableView<Element> tableStock;
    /**
     * Colonnes pour le prix d'achat, le prix de vente et la quantité.
     */
    @FXML
    private TableColumn<Element, Double> colPrixAchat, colPrixVente, colQuantite;
    /**
     * Colonnes pour le code, le nom et l'unité de mesure des éléments.
     */
    @FXML
    private TableColumn<Element, String> colCode, colNom, colUnite;
    /**
     * Champ de texte pour saisir le code de l'élément.
     */
    @FXML
    private TextField inputCode;
    /**
     * Champ de texte pour saisir la quantité à vendre.
     */
    @FXML
    private TextField inputQuantity;
    /**
     * Initialise le contrôleur et configure la table des stocks.
     * @param location L'emplacement utilisé pour résoudre les chemins relatifs pour le chargement du root, ou null si inconnu.
     * @param resources Les ressources utilisées pour localiser le root object, ou null si le root object n'était pas localisé.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTable();
    }
    /**
     * Charge la page de gestion des stocks.
     */
    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");}
    /**
     * Charge la page d'accueil de l'application
     */
    @FXML
    private void Page_Accueil()  {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }
    /**
     * Charge la page de gestion des chaînes de production.
     */
    @FXML
    private void Page_Chaine() {
        ChargerPage("/bigbrain/java_bureau/chaine.fxml");
    }
    /**
     * Charge la page de gestion des commandes.
     */
    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }
    /**
     * Charge la page d'historique des actions.
     */
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
    /**
     * Affiche une alerte à l'utilisateur.
     * @param alertType Le type d'alerte.
     * @param title Le titre de l'alerte.
     * @param content Le contenu du message d'alerte.
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Configure la table pour afficher les éléments en stock.
     */
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
    /**
     * Méthode pour charger et afficher une page spécifiée.
     * Gère le chargement de la page, l'affichage dans la fenêtre principale, et les erreurs liées au chargement.
     *
     * @param page Le chemin vers le fichier FXML de la page à charger.
     */
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