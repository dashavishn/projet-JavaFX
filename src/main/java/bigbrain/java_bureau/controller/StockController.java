package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.Element;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class StockController {
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
    private TableView<Element> tableStock;
    @FXML
    private TextField textQuantite;

    @FXML
    private TextField textcode;

    @FXML
    private Button boutonValider;

    @FXML
    private Text textResultat;
    private ActionEvent actionEvent;

    public StockController(Button boutonValider, Text textResultat) {
        this.boutonValider = boutonValider;
        this.textResultat = textResultat;
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

    /* public void initialize(URL url, ResourceBundle resourceBundle) {

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colAchat.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        colVente.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        colQte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colUnite.setCellValueFactory(new PropertyValueFactory<>("uniteMesure"));

        ObservableList<Element> data = FXCollections.observableArrayList();
        data.addAll(Stocks.EStock);
        tableViewStock.setItems(data);
    }
*/
}