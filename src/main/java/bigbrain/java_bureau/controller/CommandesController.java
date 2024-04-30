package bigbrain.java_bureau.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.*;
import static bigbrain.java_bureau.Main.getPrimaryStage;
import static bigbrain.java_bureau.Main.primaryStage;

import java.io.IOException;
import java.util.Objects;


public class CommandesController {

    //Champ de texte pour saisir le code de produit
    @FXML
    private TextField code_text;
//champ de texte pour saisir le nom de produit
    @FXML
    private TextField nom_text;
//champ de texte pour saisir l'unité
    @FXML
    private TextField unite_text;

    //champ de texte pour saisir la quantité de produit
    @FXML
    private TextField qt_text;

    @FXML
    private Button commander_Button;

     // Ancre pour contenir les composants de la page de gestion des commandes.
    @FXML
    private AnchorPane ap_commande;

     // Bouton pour accéder au stock.
    @FXML
    private Button button_stock;


     //Bouton pour accéder à la chaine de production.
    @FXML
    private Button button_chaine;

 // Bouton pour accéder à l'historique.
    @FXML
    private Button button_Historique;



     // Méthode permettant de charger la page de l'historique
    @FXML
    private void PageHistorique() {
        ChargerPage("historique.fxml");
    }

     // Méthode permettant de charger la page de la chaîne de production
    @FXML
    private void PageChaine() {
        ChargerPage("chaine.fxml");
    }
    //méthode permettant de charger la page du stock
    @FXML
    private void PageStock() {
        ChargerPage("stock.fxml");
    }

    public void ChargerPage(String page) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    // Méthode appelée lorsque le bouton est cliqué
    @FXML
    private void handleAction() {
        String texte = nom_text.getText();
        System.out.println("Texte saisi : " + texte);
        // Ajoutez ici la logique de votre application en réponse à l'action de l'utilisateur
    }

    // Vous pouvez ajouter d'autres méthodes et attributs selon les besoins de votre application

}
