/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class PrincipalePageChauffeurController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button ReservationBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ButtonProfil(ActionEvent event) {
    try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Profil.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void DemandeBtn(ActionEvent event) {
    
    try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Demande.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void calenderBtn(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
