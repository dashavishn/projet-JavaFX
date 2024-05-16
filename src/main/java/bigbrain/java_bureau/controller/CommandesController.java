package bigbrain.java_bureau.controller;

import bigbrain.java_bureau.classe_java.EcrireCSV;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.Stocks;
import bigbrain.java_bureau.classe_java.Historique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static bigbrain.java_bureau.Main.primaryStage;
import static bigbrain.java_bureau.classe_java.Element.trouverElement;
import static java.lang.String.valueOf;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Contrôleur pour la gestion des commandes dans l'application.
 * Fournit des méthodes pour l'achat d'éléments existants ou la création de nouveaux éléments,
 * ainsi que la navigation entre différentes pages.
 */
public class CommandesController {

    @FXML
    private TextField saisieCodeEx, saisieCodeNew, saisieNom, saisieUnite, saisiePrixAchat, saisieQteEX, saisieQteNew;

    /**
     * Initialisation du contrôleur. Cette méthode est automatiquement appelée après que tous les champs marqués @FXML aient été injectés.
     */
    @FXML
    private void initialize() { }
    /**
     * Affiche une alerte à l'utilisateur.
     * @param alertType Type de l'alerte.
     * @param title Titre de l'alerte.
     * @param content Contenu du message d'alerte.
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  // Pas de header text, peut être mis à null.
        alert.setContentText(content);
        alert.showAndWait();  // Affiche le popup et attend que l'utilisateur le ferme.
    }

    /**
     * Gère l'achat d'éléments existants basé sur les informations saisies.
     * @param event Événement qui a déclenché cette action.
     */
    @FXML
    public void achatEx(ActionEvent event) {
        String code = saisieCodeEx.getText();
        float qte = Float.parseFloat(saisieQteEX.getText());
        Element elem = Stocks.getElement(code);  // Utilisation directe de la méthode de Stocks
        if (elem != null) {
            elem.acheter(qte);
            EcrireCSV.clearCSVFile("/bigbrain/fichierscsv/elements.csv");
            EcrireCSV.writeElementsToCSV("/bigbrain/fichierscsv/elements.csv", new ArrayList<>(Stocks.getStockItems().values()));
            EcrireCSV.clearCSVFile("/bigbrain/fichierscsv/historique.csv");
            EcrireCSV.writeModificationsToCSV("/bigbrain/fichierscsv/historique.csv", Historique.getHistoriqueModifications());
            showAlert(AlertType.INFORMATION, "Achat réussi", "Commande existante réussie.");
        } else {
            showAlert(AlertType.ERROR, "Erreur", "Élément non trouvé.");
        }
    }
    /**
     * Gère l'achat de nouveaux éléments ou la mise à jour des éléments existants.
     * @param event Événement qui a déclenché cette action.
     */
    @FXML
    private void achatNew(ActionEvent event) {
        String code = saisieCodeNew.getText();
        String nom = saisieNom.getText();
        String unite = saisieUnite.getText();
        double prixAchat = Double.parseDouble(saisiePrixAchat.getText());
        double quantite = Double.parseDouble(saisieQteNew.getText());

        Element existingElement = Stocks.getElement(code);
        if (existingElement != null) {
            existingElement.acheter(quantite); // cette méthode met à jour la quantité en stock
            showAlert(AlertType.INFORMATION, "Commande mise à jour", "Quantité ajoutée à l'élément existant.");
        } else {
            // Créer un nouvel élément si aucun élément existant n'est trouvé avec ce code
            Element newElement = new Element(code, nom, quantite, unite, prixAchat, prixAchat * 1.1);
            Stocks.ajouterElem(newElement, quantite);
            showAlert(AlertType.INFORMATION, "Nouvelle commande", "Nouvelle commande ajoutée avec succès.");
        }
        // Mise à jour des fichiers CSV
        try {
            String elementsPath = "/bigbrain/fichierscsv/elements.csv";
            String historiquePath = "/bigbrain/fichierscsv/elements.csv";
            EcrireCSV.clearCSVFile(elementsPath);
            EcrireCSV.writeElementsToCSV(elementsPath, new ArrayList<>(Stocks.getStockItems().values()));
            EcrireCSV.clearCSVFile(historiquePath);
            EcrireCSV.writeModificationsToCSV(historiquePath, Historique.getHistoriqueModifications());
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Erreur CSV", "Impossible de mettre à jour les fichiers CSV: " + e.getMessage());
        }
    }

    /**
     * Navigation générique vers différentes pages.
     * @param page Chemin vers le fichier FXML de la page à charger.
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
    // Les méthodes suivantes chargent des pages spécifiques de l'application.
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Chaine() {
        ChargerPage("/bigbrain/java_bureau/chaine.fxml");
    }

    @FXML
    private void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/Historique.fxml");
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/Stock.fxml");
    }
}
