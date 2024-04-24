package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.Element;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StockController {

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

    public void start(Stage primaryStage) {
        Button btn = new Button("Click me");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}