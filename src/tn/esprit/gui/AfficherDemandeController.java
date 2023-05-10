/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import API.Mail;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Demande;
import tn.esprit.entity.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tn.esprit.services.ServiceDemande;
import tn.esprit.services.UsersSession;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherDemandeController implements Initializable {

    @FXML
    private TableView<Demande> table;
    @FXML
    private TableColumn<Demande, String> nomCol;
    @FXML
    private TableColumn<Demande, String> moyCol;
    @FXML
    private TableColumn<Demande, String> hdCol;
    @FXML
    private TableColumn<Demande, String> haCol;
    @FXML
    private TableColumn<Demande, String> permisCol;
    @FXML
    private TableColumn<Demande, Integer> idCol;
    
      User u = new User();
    
    ServiceDemande sd = new ServiceDemande();
    @FXML
    private ImageView img;
    @FXML
    private Button acceptBtn;
    @FXML
    private Button refuseBtn;
     int Rec ;
    @FXML
    private TableColumn<Demande, String> emailCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Demande> demande = sd.getAll();
        ObservableList<Demande> listDem = FXCollections.observableArrayList(demande);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
        moyCol.setCellValueFactory(new PropertyValueFactory<>("moyen"));
        hdCol.setCellValueFactory(new PropertyValueFactory<>("HD"));
        haCol.setCellValueFactory(new PropertyValueFactory<>("HA"));
        permisCol.setCellValueFactory(new PropertyValueFactory<>("permis"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("EmailC"));
        table.setItems(listDem);
    }    

    @FXML
    private void acceptAction(ActionEvent event) {
       
        String resultat="Votre demande est acceptée";
        sd.sms(resultat);
         Mail mail = new Mail();
                   
            int index=table.getSelectionModel().getSelectedIndex();
           mail.envoyer("Demande", "montabwi@gmail.com", resultat);
            
    }
    
  

    @FXML
    private void refuseAction(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem()== null){
        } else {
            System.out.println(Rec);
            
            String resultat="Desolé, Votre demande est refusée";
            sd.sms(resultat);
            
             Mail mail = new Mail();
            int index=table.getSelectionModel().getSelectedIndex();
           mail.envoyer("Demande", emailCol.getCellData(index).toString(), resultat);
            
            Demande d = new Demande();
            
            d.setId(Rec);
            System.out.println(Rec);
            sd.supprimerDemande(d);
            refresh();
        }
    }
    

//    @FXML
//    private void refuseAction(ActionEvent event) {
//        String resultat="Desolé, Votre demande est refusée";
//        sd.sms(resultat);
//        
//    }

    @FXML
    private void getSelected(MouseEvent event) {
        int index = -1;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        
        acceptBtn.setDisable(false);
        refuseBtn.setDisable(false);
       acceptBtn.setVisible(true);
       refuseBtn.setVisible(true);
        Rec= Integer.parseInt(idCol.getCellData(index).toString());
        
    }
     private void refresh(){
        List<Demande> demande = sd.getAll();
        ObservableList<Demande> listDem = FXCollections.observableArrayList(demande);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
        moyCol.setCellValueFactory(new PropertyValueFactory<>("moyen"));
        hdCol.setCellValueFactory(new PropertyValueFactory<>("HD"));
        haCol.setCellValueFactory(new PropertyValueFactory<>("HA"));
        permisCol.setCellValueFactory(new PropertyValueFactory<>("permis"));
        table.setItems(listDem);
        
     }
    
}
