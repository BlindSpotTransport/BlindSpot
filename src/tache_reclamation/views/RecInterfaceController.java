/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class RecInterfaceController implements Initializable {

    @FXML
    private TextArea prenomRec;
    @FXML
    private TextArea nomRec;
    @FXML
    private Button ajouterRec;
    @FXML
    private TableView<?> tableRec;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> userId;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private Button supprimerRec;
    @FXML
    private Button modifierRec;
    @FXML
    private DatePicker dateRec;
    @FXML
    private TextArea descRec;
    @FXML
    private TextArea idtxt;
    @FXML
    private TextArea recherche;
    @FXML
    private Button Retourbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addRec(MouseEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void deleteRec(MouseEvent event) {
    }

    @FXML
    private void updateRec(MouseEvent event) {
    }


    @FXML
    private void retour(MouseEvent event) {
    }

    @FXML
    private void addRec(ActionEvent event) {
    }

    @FXML
    private void deleteRec(ActionEvent event) {
    }

    @FXML
    private void updateRec(ActionEvent event) {
    }
    
}
