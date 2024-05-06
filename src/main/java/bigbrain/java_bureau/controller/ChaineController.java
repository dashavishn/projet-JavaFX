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


public class ChaineController implements Initializable {
    @FXML
    private AnchorPane Chaine;

    @FXML
    private TextField textFieldCodeEntree;

    @FXML
    private TextField textFieldQuantiteEntree;

    @FXML
    private TextField textFieldCodeSortie;

    @FXML
    private TextField textFieldQuantiteSortie;

    @FXML
    private Text textResultat;

    @FXML
    private Button buttonValider;

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


    // Méthode pour valider les données saisies et effectuer des actions correspondantes
    @FXML
    private void valider() {
        String codeEntree = textFieldCodeEntree.getText();
        String quantiteEntreeText = textFieldQuantiteEntree.getText();
        String codeSortie = textFieldCodeSortie.getText();
        String quantiteSortieText = textFieldQuantiteSortie.getText();

        // je vais vérifier si les champs sont vides
        if (codeEntree.isEmpty() || quantiteEntreeText.isEmpty() || codeSortie.isEmpty() || quantiteSortieText.isEmpty()) {
            textResultat.setTextContent("Veuillez remplir tous les champs.");
            return;
        }

        // c'est pour convertir les quantités en entiers
        int quantiteEntree;
        int quantiteSortie;
        try {
            quantiteEntree = Integer.parseInt(quantiteEntreeText);
            quantiteSortie = Integer.parseInt(quantiteSortieText);
        } catch (NumberFormatException e) {
            textResultat.setTextContent("Veuillez saisir des nombres valides pour les quantités.");
            return;
        }

        textResultat.setTextContent("Données validées avec succès !");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        textFieldCodeEntree.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomE2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomE3.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomS1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomS2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomS3.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colQE1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));
        colQE2.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));
        colQE3.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));
        colQS1.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));
        colQS2.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));
        colQS3.setCellValueFactory(new PropertyValueFactory<>("quantiteEnProd"));

        ObservableList<Element> dataE1 = FXCollections.observableArrayList();
        ObservableList<Element> dataE2 = FXCollections.observableArrayList();
        ObservableList<Element> dataE3 = FXCollections.observableArrayList();
        ObservableList<Element> dataS1 = FXCollections.observableArrayList();
        ObservableList<Element> dataS2 = FXCollections.observableArrayList();
        ObservableList<Element> dataS3 = FXCollections.observableArrayList();

        for (ChaineProduction c : CSV.Chaines){
            if(c.getCode().equals("C001")){
                dataE1.addAll(c.getElementsEntreeKeys());
                dataS1.addAll(c.getElementsSortieKeys());
                TabChaineE1.setItems(dataE1);
                TabChaineS1.setItems(dataS1);
            }
            if(c.getCode().equals("C002")){
                dataE2.addAll(c.getElementsEntreeKeys());
                dataS2.addAll(c.getElementsSortieKeys());
                TabChaineE2.setItems(dataE2);
                TabChaineS2.setItems(dataS2);
            }
            if(c.getCode().equals("C003")){
                dataE3.addAll(c.getElementsEntreeKeys());
                dataS3.addAll(c.getElementsSortieKeys());
                TabChaineE3.setItems(dataE3);
                TabChaineS3.setItems(dataS3);
            }
        }
    }
*/
    }
}
//ON ACTION