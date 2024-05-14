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
import java.util.List;

public class CommandesController {

    @FXML
    private TextField saisieCodeEx, saisieCodeNew, saisieNom, saisieUnite, saisiePrixAchat, saisieQteEX, saisieQteNew;


    @FXML
    private void initialize() {
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  // Pas de header text, peut être mis à null.
        alert.setContentText(content);
        alert.showAndWait();  // Affiche le popup et attend que l'utilisateur le ferme.
    }


    @FXML
    public void achatEx(ActionEvent event) {
        Element elem = trouverElement(valueOf(saisieCodeEx.getText()));
        float qte = Float.parseFloat(saisieQteEX.getText());
       if(elem != null){
        elem.acheter(elem, qte);
        EcrireCSV a = new EcrireCSV();
        a.clearCSVFile("/bigbrain/fichierscsv/elements.csv");
        a.writeElementsToCSV("/bigbrain/fichierscsv/elements.csv", (List<Element>) Stocks.stockItems);
        a.clearCSVFile("/bigbrain/fichierscsv/historique.csv");
        a.writeModificationsToCSV("/bigbrain/fichierscsv/historique.csv", Historique.historiqueModifications );
        showAlert(AlertType.INFORMATION, "Achat réussi", "Commande existante réussie.");
       }
       else {
           showAlert(AlertType.ERROR, "Erreur", "Élément non trouvé.");
       }
    }


    @FXML
    private void achatNew(ActionEvent event) {
        String code = saisieCodeNew.getText();
        String nom = saisieNom.getText();
        String unite = saisieUnite.getText();
        double prixAchat = Double.parseDouble(saisiePrixAchat.getText());
        double quantite = Double.parseDouble(saisieQteNew.getText());

        Element nouveauElement = new Element(code, nom, quantite, unite, prixAchat, prixAchat * 1.1);  // Assume a 10% markup for selling price
        Stocks.ajouterElem(nouveauElement, quantite);
        showAlert(AlertType.INFORMATION, "Nouvelle commande", "Nouvelle commande ajoutée avec succès.");

    }

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
