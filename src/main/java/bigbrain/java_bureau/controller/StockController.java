package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static bigbrain.java_bureau.Main.primaryStage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static java.lang.String.valueOf;
import bigbrain.java_bureau.classe_java.Stocks;



public class StockController implements Initializable {

    @FXML
    private TableView<Element> tableStock;
    /* ===========================================
     *Ces boutons permettent d'accèder à la barre de navigation
     * =========================================== */
    @FXML
    private Button boutonChaine;
    @FXML
    private Button boutonHistorique;

    @FXML
    private Button boutonCommande;

    @FXML
    private TextField textQuantite;

    @FXML
    private TextField textcode;

    @FXML
    private Button boutonValider;

    @FXML
    private Text textResultat;
    private ActionEvent actionEvent;

    /**
     * les table colonne .
     */
    @FXML
    private TableColumn<Element, Float> colPrixAchat;
    @FXML
    private TableColumn<Element, String> colCode;

    @FXML
    private TableColumn<Element, String> colNom;

    @FXML
    private TableColumn<Element, Float> colQuantite;

    @FXML
    private TableColumn<Element, String> colUnite;

    @FXML
    private TableColumn<Element, Float> colPrixVente;


    /**
     * Champ de texte permettant de saisir le code de l'élément à vendre.
     */
    @FXML
    private TextField textecode;

    public void PageStock() {
        ChargerPage("stock.fxml");
    }


    //Validation les données saisies
    @FXML
    private void valider() {
        String quantiteS = textQuantite.getText();
        String codeProduit = textcode.getText();
        // Vérifier si les champs sont vides
        if (quantiteS.isEmpty() || codeProduit.isEmpty()) {
            textResultat.setText("Veuillez remplir tous les champs.");
            return;
        } else {
            textResultat.setText("Commande effectuée avec succès !");
            // Réinitialisation les champs après la validation
            textQuantite.clear();
            textcode.clear();
        }
        // Convertir les quantités en entiers
        int quantite;
        try {
            quantite = Integer.parseInt(quantiteS);
        } catch (NumberFormatException e) {
            textResultat.setText("Veuillez saisir des nombres valides pour les quantités.");
            return;
        }
        //Vérifier que la quantité est >0
        if (Integer.parseInt(quantiteS) < 0) {
            textResultat.setText("Quantité doit être superieur à 0");
            return;
        }
    }

    public void ChargerPage(String page) {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //une méthode permettant de cliquer le bouton
    public void start(Stage primaryStage) {
        Button btn = new Button("Click me");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajoutez des instructions d'impression pour vérifier les données chargées
        System.out.println("Nombre d'éléments dans la liste ElemStocks : " + Stocks.ElemStocks.size());
        for (Element element : Stocks.ElemStocks) {
            System.out.println("Nom : " + element.getNom() + ", Code : " + element.getCode() + ", Quantité : " + element.getQuantiteStock());

            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colPrixAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
            colPrixVente.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
            colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            colUnite.setCellValueFactory(new PropertyValueFactory<>("uniteMesure"));

            ObservableList<Element> data = FXCollections.observableArrayList();
            data.addAll(Stocks.ElemStocks);
            tableStock.setItems(data);
        }


    }
}