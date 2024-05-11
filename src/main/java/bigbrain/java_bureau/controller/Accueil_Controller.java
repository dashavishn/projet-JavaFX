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

public class Accueil_Controller implements Initializable{
    @FXML
    private Button stock;
    private Button chaine;
    private Button commandes;
    private Button historique;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

