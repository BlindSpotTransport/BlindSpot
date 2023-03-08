/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import tn.esprit.services.ServiceNotification;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NotificationController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ServiceNotification sn = new ServiceNotification();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void notifier(ActionEvent event) throws InterruptedException{
          sn.NotifyondashbordDemande(); 
    }

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }
    
}
