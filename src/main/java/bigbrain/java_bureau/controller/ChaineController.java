package bigbrain.java_bureau.controller;
import bigbrain.java_bureau.classe_java.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.util.Map;

import static bigbrain.java_bureau.Main.primaryStage;

/**
 * Contrôleur pour la gestion des chaînes de production.
 * Permet l'affichage, la modification et la validation des chaînes de production.
 */
public class ChaineController {
    /**
     * TableView pour afficher les chaînes de production.
     */
    @FXML
    private TableView<ChaineProduction> tableChaine;
    /**
     *  Colonne pour afficher les noms des chaînes.
     */
    @FXML
    private TableColumn<ChaineProduction, String> chaineNom;
    /**
     * Colonne pour afficher les codes des chaînes.
     */
    @FXML
    private TableColumn<ChaineProduction, String> chaineCode;
    /**
     * Colonne pour éditer le niveau d'activation des chaînes.
     */
    @FXML
    private TableColumn<ChaineProduction, Integer> chaineActivation;
    /**
     * Colonne pour afficher les éléments entrants des chaînes.
     */
    @FXML
    private TableColumn<ChaineProduction, String> chaineEntree;
    /**
     * Colonne pour afficher les éléments sortants des chaînes.
     */
    @FXML
    private TableColumn<ChaineProduction, String> chaineSortie;
    /**
     * Bouton pour exporter les données historiques des chaînes de production.
     */
    @FXML
    private Button exportButton;

    /**
     * Initialise le contrôleur et configure les cellules de la table et les gestionnaires d'événements.
     */
    @FXML
    public void initialize() {
        tableChaine.setEditable(true);

        chaineNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        chaineCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        chaineActivation.setCellValueFactory(new PropertyValueFactory<>("niveauActivation"));
        chaineEntree.setCellValueFactory(new PropertyValueFactory<>("stringElementEntree"));
        chaineSortie.setCellValueFactory(new PropertyValueFactory<>("stringElementSortie"));

        // Configurer la colonne d'activation pour utiliser TextFieldTableCell
        chaineActivation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        chaineActivation.setOnEditCommit(event -> {
            ChaineProduction chaine = event.getRowValue();
            int newLevel = event.getNewValue();
            chaine.setNiveauActivation(newLevel);
            try {
                if (!chaine.valider()) {
                    showAlert("Validation échouée", "Niveau d'activation non supporté par les stocks actuels pour la chaîne: " + chaine.getNom());
                } else {
                    showAlert("Validation réussie", "La chaîne de production " + chaine.getNom() + " a été activée avec succès au niveau " + newLevel);
                }
            } catch (Exception e) {
                showAlert("Erreur lors de la validation", "Erreur lors de la validation de la chaîne: " + chaine.getNom() + " Error: " + e.getMessage());
            }
        });

        loadChaineData();  // Charger les données à l'initialisation
    }
    /**
     * Charge les données des chaînes de production dans la TableView.
     */
    private void loadChaineData() {
        CSV csvUtil = new CSV();
        csvUtil.lireChaines();  // Cette méthode devrait déjà mettre à jour la liste des chaînes dans la classe Entrepot.
        tableChaine.setItems(Entrepot.getChaine());  // Définir les éléments de TableView avec les données chargées
    }
    /**
     * Gère l'exportation des données historiques vers un fichier texte.
     * Le chemin du fichier est défini statiquement dans la méthode.
     */
    @FXML
    private void handleExport() {
        String filePath = "historique_actions.txt";
        EcrireFichier.ecrireHistorique(filePath);
        showAlert("Exportation réussie", "L'historique des actions a été exporté vers " + filePath);
    }
    /**
     * Affiche une alerte de type information avec un titre et un message.
     * @param title Le titre de l'alerte.
     * @param message Le message de l'alerte.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Les méthodes ci-dessous sont des gestionnaires pour les actions de navigation.
    @FXML
    private void Page_Accueil() {
        ChargerPage("/bigbrain/java_bureau/page_accueil.fxml");
    }

    @FXML
    private void Page_Stock() {
        ChargerPage("/bigbrain/java_bureau/stock.fxml");
    }

    @FXML
    private void Page_Commandes() {
        ChargerPage("/bigbrain/java_bureau/commandes.fxml");
    }

    @FXML
    private void Page_Historique() {
        ChargerPage("/bigbrain/java_bureau/historique.fxml");
    }


   /** Navigue vers une autre page de l'application
            * @param page Chemin du fichier FXML de la page à charger.
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
    @FXML
    private void handleValidateProduction() {
        ObservableList<ChaineProduction> chaines = tableChaine.getItems();
        boolean allValid = true;
        for (ChaineProduction chaine : chaines) {
            try {
                System.out.println("Vérification des stocks avant validation pour la chaîne: " + chaine.getNom());
                for (Map.Entry<Element, Float> entry : chaine.getElementEntree().entrySet()) {
                    Element element = entry.getKey();
                    float quantiteNecessaire = entry.getValue() * chaine.getNiveauActivation();
                    System.out.println("Stock de " + element.getCode() + ": " + element.getQuantiteStock() + ", Nécessaire: " + quantiteNecessaire);
                }
                if (!chaine.valider()) {  // Supposons que valider() vérifie si la chaîne peut être activée avec les stocks actuels
                    allValid = false;
                    showAlert("Validation échouée", "Validation échouée pour la chaîne: " + chaine.getNom());
                    break;
                }
            } catch (Exception e) {
                allValid = false;
                showAlert("Erreur lors de la validation", "Erreur lors de la validation de la chaîne: " + chaine.getNom() + " Error: " + e.getMessage());
                break;
            }
        }
        if (allValid) {
            showAlert("Validation réussie", "Toutes les chaînes sont validées avec succès.");
        } else {
            showAlert("Validation échouée", "Certaines chaînes ne peuvent pas être activées selon les niveaux d'activation et les stocks.");
        }
    }
}


