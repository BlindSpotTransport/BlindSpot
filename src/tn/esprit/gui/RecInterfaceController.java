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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.esprit.entities.Reclamation;
import tn.esprit.services.ServiceReclamation;

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
    private TableColumn<Reclamation, String>nom;
    @FXML 
    private TableColumn<Reclamation, String> prenom; 
    @FXML
    private TableColumn<Reclamation, String>date;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
 
    private TableView<Reclamation> tableRec;
      ObservableList<Reclamation>recList;    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private Button ajouterRec;
  @FXML 
  private DatePicker dateRec;
    @FXML
    private Button supprimerRec;
    @FXML
    private Button modifierRec;
    @FXML
    private TextArea descRec;
    @FXML
    private TextArea recherche;
    @FXML
    private Button Retourbtn;
    @FXML 
    private TextArea idtxt;
 



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  afficherReclamation();
    }  
    @FXML
    void afficherReclamation(){
            mc=tn.esprit.tools.Connexion.getInstance().getCnx();
        recList = FXCollections.observableArrayList();
       
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Reclamation r = new Reclamation();
                //tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setDater(rs.getDate("dater").toLocalDate());
                r.setDescrec(rs.getString("descrec"));
                recList.add(r); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDater().toString()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescrec()));
       
        tableRec.setItems(recList);
      //  search();   
        
    
        
    }

        

    @FXML
    private void addRec(MouseEvent event) throws SQLException {
      
        String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        LocalDate dater = dateRec.getValue();
        String description = descRec.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty()|| dater==null|| description.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             Reclamation r=new Reclamation(nom,prenom,dater,description);
             ServiceReclamation rc = new ServiceReclamation();
            
           
             ResultSet rs=ste.executeQuery();        
             rc.ajouter_reclamation(r);
             
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reclamation Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomRec.setText(null);
          prenomRec.setText(null);
          dateRec.setValue(null);
          descRec.setText(null);
         }
         refresh();
    
   
    }

  

    @FXML
    private void deleteRec(MouseEvent event) {
   
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
            
            String value1 = idtxt.getText();
            
             String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        LocalDate date = dateRec.getValue();
        String description = descRec.getText();

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
             Reclamation r= new Reclamation(Integer.parseInt(value1),nom,prenom,date,description);
             ServiceReclamation rc = new ServiceReclamation();
             rc.supprimer_reclamation(r);
            JOptionPane.showMessageDialog(null, "Reclamation supprimer" );
        
            refresh();
             }
        else{

          nomRec.setText(null);
          prenomRec.setText(null);
          dateRec.setValue(null);
          descRec.setText(null);

        }
    }

    @FXML
    private void updateRec(MouseEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
       
            String value1 = idtxt.getText();
             String value2 = nomRec.getText();
             String value3 = prenomRec.getText();
             LocalDate value4 =  dateRec.getValue();
             String value5 = descRec.getText();
            
             Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
            try{
        
             ServiceReclamation rc = new ServiceReclamation( );
             Reclamation r= new Reclamation(Integer.parseInt(value1),value2,value3,value4,value5);
             rc.modifier_reclamation(r);
            JOptionPane.showMessageDialog(null, "reclamation modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

              nomRec.setText(null);
              prenomRec.setText(null);
              dateRec.setValue(null);
              descRec.setText(null);
              
            ;

        }
        refresh();
    }
    //for optimization   
        public void refresh(){
        
         recList.clear();
          mc=tn.esprit.tools.Connexion.getInstance().getCnx();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reclamation e = new Reclamation();
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setDater(rs.getDate("dater").toLocalDate());
                e.setDescrec(rs.getString("desrec"));
                recList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRec.setItems(recList);   
    }
    
    @FXML
    //selecting data from tableveiw 
    private void getSelected(MouseEvent event) {
          Reclamation clicked = tableRec.getSelectionModel().getSelectedItem();
         nomRec.setText(String.valueOf(clicked.getNom()));
        prenomRec.setText(String.valueOf(clicked.getPrenom()));
       dateRec.setValue(clicked.getDater());                  
        descRec.setText(String.valueOf(clicked.getDescrec()));
       // idtxt.setText(String.valueOf(clicked.getId()));
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

    @FXML
    private void retour(MouseEvent event) {
    }

  

   
    
}
