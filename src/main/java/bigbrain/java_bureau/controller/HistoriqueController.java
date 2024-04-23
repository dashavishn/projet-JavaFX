package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.Element;
import bigbrain.java_bureau.Stocks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.util.*;

public class HistoriqueController {
    @FXML
    TableView<Element> tab;
    @FXML
    TextField nom;
    @FXML
    TextField code;
    @FXML
    TextField quantite;
    @FXML
    TextField unite;
    @FXML
    TextField prixAchat;
    @FXML
    TextField prixVente;
    @FXML
    TableColumn<Element, String> noms;
    @FXML
    TableColumn<Element, String> prenoms;

    private final ObservableList<Element> elts = FXCollections.observableArrayList();

    //Récupération des elements et sa affichage dans le tableau
   /* public void initialize(URL location, ResourceBundle resources) {
        elts.addAll(Stocks.tournoiActuel.getJoueurs());
        noms.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenoms.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        tableview.setItems(joueurs);
    }
    public void retourTournoi(){
        if(AccueilApplication.tournoiActuel.getNbJoueurs()==8){
            AccueilApplication.setFXMLForStage("affichageTournoi8.fxml");
        }else if(AccueilApplication.tournoiActuel.getNbJoueurs()==16){
            AccueilApplication.setFXMLForStage("affichageTournoi16.fxml");
        }else if(AccueilApplication.tournoiActuel.getNbJoueurs()==32){
            AccueilApplication.setFXMLForStage("affichageTournoi32.fxml");
        }
    } */


}
