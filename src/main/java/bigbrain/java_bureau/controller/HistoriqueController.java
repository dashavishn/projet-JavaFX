package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.Element;
import bigbrain.java_bureau.classe_java.ModificationStockElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HistoriqueController {
    @FXML
    TableView<Element> tab;
    private final ObservableList<Element> elts = FXCollections.observableArrayList();

    @FXML
    private AnchorPane ap_Historique;

    @FXML
    private Button buttonChaine;

    @FXML
    private Button buttonStock;

    @FXML
    private Button buttonCommande;

    @FXML
    private Button buttonPageAccueil;

    @FXML
    private TableColumn<ModificationStockElement, String> nom_col;
    @FXML
    private TableColumn<ModificationStockElement, String> code_col;
    @FXML
    private TableColumn<ModificationStockElement, Float> quantite_col;

    @FXML
    private TableColumn<ModificationStockElement, String> unite_col;

    @FXML
    private TableColumn<ModificationStockElement, Double> prixA_col;

    @FXML
    private TableColumn<ModificationStockElement, Float> prixV_col;

}


