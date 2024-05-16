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
/**
 * Contrôleur pour la gestion de l'historique des stocks dans l'interface utilisateur.
 * Permet d'effectuer des opérations sur les stocks comme la validation des commandes, et affiche les résultats.
 */
public class HistoriqueController implements Initializable {
    /**
     * Tableau pour afficher les éléments en stock.
     */
    @FXML
    private TableView<Element> tableStock;
    /**
     * Zone de texte pour afficher les messages de résultat.
     */
    @FXML
    private Text textResultat;
    /**
     * Colonnes pour le prix d'achat, la quantité, et le prix de vente.
     */
    @FXML
    private TableColumn<Element, Float> colPrixAchat, colQuantite, colPrixVente;
    /**
     * Colonnes pour le code, le nom, et l'unité de mesure.
     */
    @FXML
    private TableColumn<Element, String> colCode, colNom, colUnite;

    /**
     * Initialisation du contrôleur.
     * Cette méthode est automatiquement appelée après le chargement du fichier FXML et configure la table des stocks.
     *
     * @param url L'URL utilisée pour résoudre les chemins relatifs pour le root, ou null si l'URL n'est pas connue.
     * @param resourceBundle Le bundle qui localise le root object, ou null si le root object n'était pas localisé.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTable();
    }
    /**
     * Configure les colonnes de la table des stocks et y charge les données.
     */
    private void setupTable() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colPrixAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        colPrixVente.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantiteStock"));
        colUnite.setCellValueFactory(new PropertyValueFactory<>("uniteMesure"));
        // Charge les éléments du stock dans le tableau.
        ObservableList<Element> elementsObservable = FXCollections.observableArrayList(Stocks.getInstance().getStockItems().values());
        tableStock.setItems(elementsObservable); }
    /**
     * Charge la page d'accueil de l'application
     */
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }
    /**
     * Charge la page de gestion des stocks.
     */
    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
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
     * Méthode pour charger et afficher une page spécifiée.
     * Gère le chargement de la page, l'affichage dans la fenêtre principale, et les erreurs liées au chargement.
     *
     * @param page Le chemin vers le fichier FXML de la page à charger.
     */
    private void ChargerPage(String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
            Parent root = loader.load();
            Stage stage = (Stage) tableStock.getScene().getWindow();
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