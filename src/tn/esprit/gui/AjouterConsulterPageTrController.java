/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.tests.TransportMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class AjouterConsulterPageTrController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //PrincipalePageController pr = new PrincipalePageController();

    //public AjouterConsulterPageTrController t1;
    @FXML
    private Label labelROOT;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void AjouterMoyenneTBtn(ActionEvent event) {
        try {
            //AnchorPane view = FXMLLoader.load(getClass().getResource("AjouterMoyens.fxml"));
            
              Parent loader =FXMLLoader.load(getClass().getResource("AjouterMoyens.fxml"));   
              //TransportMain m1 = new TransportMain();
              loader.prefHeight(100);
              loader.prefWidth(100);
              labelROOT.getScene().setRoot(loader);
              
            
        } catch (IOException ex) {
            Logger.getLogger(AjouterConsulterPageTrController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
        public void setSceneHeight(Stage stage, double height) {
        stage.setHeight(height);
    }

    @FXML
    private void ConsulterMoyenneTBtn(ActionEvent event) {
        try {   
            Parent loader =FXMLLoader.load(getClass().getResource("CRUDMoyensTransport.fxml"));
        labelROOT.getScene().setRoot(loader);
        
        } catch (IOException ex) {
            Logger.getLogger(AjouterConsulterPageTrController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
