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
public class PrincipalePageController implements Initializable {

    @FXML
    private BorderPane borderPane;

    public BorderPane getBorderPane() {
        return borderPane;
    }
    @FXML
    private Button GestionTranBtn;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Profil.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void TransportAjoutConsulterPage(ActionEvent event) {

            
        
     try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("CRUDMoyensTransport.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
             view.getStylesheets().add(getClass().getResource("style.css").toExternalForm());      
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void ReservationAjoutConsulterPage(ActionEvent event) {
    try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("ConsulterReservation.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
                 
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void PlanningAjoutConsulterPage(ActionEvent event) {
      try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("CrudPlanning.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EtatJournalierePAGE(ActionEvent event) {
     try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Etatjournaliere.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @FXML
    private void ProfilAdmin(ActionEvent event) {
         try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Profil.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GestionUser(ActionEvent event) {
         try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Btn_Abon(ActionEvent event) {
           try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("AjouterAbn.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void evenementBTN(ActionEvent event) {
 
     try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("DashbordAdminEvents.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void StationBtn(ActionEvent event) {
    try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("AjouterStation.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void CircuitBtn(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("AjouterCircuit.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    @FXML
    private void DemandeConsultBTN(ActionEvent event) {
     try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("AfficherDemande.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReclamationBtn(ActionEvent event) {
    
      try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("Home.fxml"));
            //System.out.print(view);
            borderPane.setCenter(view);
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalePageClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
