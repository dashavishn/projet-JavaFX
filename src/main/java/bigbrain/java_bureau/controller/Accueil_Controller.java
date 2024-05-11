package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static bigbrain.java_bureau.Main.primaryStage;

public class Accueil_Controller implements Initializable{
    @FXML
    private Button stock;
    @FXML
    private Button chaine;
    @FXML
    private Button commandes;
    @FXML
    private Button historique;

    public void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
    }
    public void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    public void Page_Chaine() {
        ChargerPage("/bigbrain/java_bureau/chaine.fxml");
    }

    public void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/historique.fxml");
    }

    public void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //   stock.setOnAction(this::Page_Accueil);
        chaine.setOnAction(event -> Page_Chaine());
        commandes.setOnAction(event -> Page_Commandes());
        historique.setOnAction(event -> Page_Historique());
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

}