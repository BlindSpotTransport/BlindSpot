/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Reclamation;
import tn.esprit.entity.RepReclamation;
import tn.esprit.services.ServiceReclamation;
import tn.esprit.services.ServiceRepReclam;
import tn.esprit.tools.Variables;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class RepReclamationDisplayController implements Initializable {

    @FXML
    private TableColumn<RepReclamation, String> nom;
    @FXML
    private TableColumn<RepReclamation, String> date;
    @FXML
    private TableColumn<RepReclamation, Integer> idr;
    @FXML
    private TextArea idtxt;
    @FXML
    private TextField recherche;
     private static int id_tst = 0;
      ServiceRepReclam rc = new ServiceRepReclam();
int ID;
    @FXML
    private TableView<RepReclamation> tablerep;
         ObservableList<RepReclamation>repList;    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TableColumn<Reclamation, String> Reclamation;
    @FXML
    private TableColumn<RepReclamation, String> Reponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void afficherReclamation() {
        mc=tn.esprit.tools.MaConnection.getInstance().getCnx();
        repList = FXCollections.observableArrayList();
       
        
//        String sql="select rp.idrep,rp.NomAg,rp.reponse,rp.daterep,r.descrec from repreclamation rp, reclamation r where rp.idr=r.idr";
        String sql="select rp.idrep,rp.NomAg,rp.reponse,rp.daterep,rp.reclamation from repreclamation rp";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                RepReclamation rp = new RepReclamation();
                Reclamation r = new Reclamation();
                //tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                rp.setIdrep(rs.getInt("idrep"));
                rp.setNomAg(rs.getString("NomAg"));
                rp.setDaterep(rs.getDate("daterep").toLocalDate());
                
                rp.setReponse(rs.getString("reponse"));
               
                repList.add(rp); 
            }
            
         // nom.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdr());
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomAg()));
        date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDaterep().toString()));
         Reclamation.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescrec()));
        Reponse.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getReponse()));
       
        tablerep.setItems(repList);
      //  search();   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Variables.setRepClicked(tablerep.getSelectionModel().getSelectedItem());
    }


    @FXML
    private void retourRec(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void Recherche(KeyEvent event) {
    }

    @FXML
    private void deleteRep(ActionEvent event) {
    }

    @FXML
    private void repRep(ActionEvent event) {
    }
    
}
