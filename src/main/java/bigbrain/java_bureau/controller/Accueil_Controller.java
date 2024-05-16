package bigbrain.java_bureau.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bigbrain.java_bureau.Main.primaryStage;
/**
 * Contrôleur pour la page d'accueil de l'application.
 * Gère les interactions de l'utilisateur avec les boutons de navigation principaux pour accéder aux différentes sections de l'application.
 */
public class Accueil_Controller implements Initializable {
    /**
     * Bouton pour naviguer vers la page de gestion des stocks.
     */
    @FXML
    private Button stock;
    /**
     * Bouton pour naviguer vers la page de gestion des chaînes de production.
     */
    @FXML
    private Button chaine;
    /**
     * Bouton pour naviguer vers la page de gestion des commandes.
     */
    @FXML
    private Button commandes;
    /**
     * Bouton pour naviguer vers la page de l'historique
     */
    @FXML
    private Button historique;
    /**
     * Initialise le contrôleur.
     * Cette méthode est automatiquement appelée après le chargement du fichier FXML.
     *
     * @param url L'URL utilisée pour résoudre les chemins relatifs pour le root, ou null si l'URL n'est pas connue.
     * @param resourceBundle Le bundle qui localise le root object, ou null si le root object n'était pas localisé.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
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
