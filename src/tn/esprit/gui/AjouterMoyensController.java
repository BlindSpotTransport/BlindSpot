/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import tn.esprit.entity.Moyenstransport;
import tn.esprit.services.MoyenstransportService;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjouterMoyensController implements Initializable {

    @FXML
    private ComboBox<String> TypeCombo;
    @FXML
    private TextField MatriculeField;
    @FXML
    private TextField capaciteField;
    @FXML
    private TextField NumMoyField;
    private String s ;
    MoyenstransportService Ms = new MoyenstransportService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> List = FXCollections.observableArrayList("Bus","Metro","Train");
        TypeCombo.setItems(List);
        TypeCombo.getSelectionModel().select(0);
        //String x="Bus" ;
        //TypeCombo.setValue(x);
    }    

    @FXML
    private void AjouterBtn(ActionEvent event) {
        s=TypeCombo.getSelectionModel().getSelectedItem().toString();
        Moyenstransport m = new Moyenstransport();
        m.setType(s);
        m.setMatricule(MatriculeField.getText());
        int cap = Integer.parseInt(capaciteField.getText());
        m.setCapacite(cap);
        m.setNumMoy(NumMoyField.getText());
        Ms.ajouter(m);
        //System.out.println(s);
        reset();
    }
    
        private void reset(){
        TypeCombo.getSelectionModel().select(0);
        MatriculeField.setText("");
        capaciteField.setText("");
        NumMoyField.setText("");   
        }

}
