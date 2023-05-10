/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.api.EnvoyerEmail;
import tn.esprit.services.NotificationService;
import tn.esprit.services.ServiceCircuit;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import tn.esprit.entity.Alerte;
import tn.esprit.entity.Circuit;
import tn.esprit.entity.Evenement;
import tn.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterAlerteController implements Initializable {
    
    List<User> clients = new ArrayList<>();
    String query = null;
    Connection cnx = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Alerte alerte = null ; 
    private boolean update;
    int alerteid;
    
    
    LocalDate today = LocalDate.now();
    EnvoyerEmail msg_envoyer = new EnvoyerEmail();
    NotificationService notification = new NotificationService();

    private TextField typeidalerte;
    private TextField titreldalerte;
    @FXML
    private TextField descldalerte;
    @FXML
    private DatePicker ddebldalerte;
    @FXML
    private DatePicker dfinldalerte;
    ObservableList<String> liste_circuits=FXCollections.observableArrayList();
   
    @FXML
    private ComboBox<String> combo_alertetype=new ComboBox<String>();
    @FXML
    private ComboBox<String> titre_comboalerte=new ComboBox<String>();
    
    ObservableList<String> list=FXCollections.observableArrayList("greve","panne","climat","accident","traffic");
    ObservableList<String> list_greve=FXCollections.observableArrayList("Bus","Metro","Train");
    ObservableList<String> list_autres=FXCollections.observableArrayList();
    String typeAlerte ,titreEv,date_deb;
    
    
     public AjouterAlerteController () {
        cnx = MaConnection.getInstance().getCnx();
       
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       this.combo_alertetype.setItems(list);
       this.titre_comboalerte.setEditable(true);
     //typeAlerte
      
       this.combo_alertetype.setOnAction((event) -> {
            int selectedIndex = combo_alertetype.getSelectionModel().getSelectedIndex();
            String selectedItem = (String) combo_alertetype.getValue();
            String selectedItem1 = (String) titre_comboalerte.getValue();
            this.typeAlerte=selectedItem ;
             if(selectedItem.equals("greve")){
                this.titre_comboalerte.setItems(list_greve);
             }else if(selectedItem.equals("traffic")){
                  this.liste_circuits=FXCollections.observableArrayList();
                  this.getAllCircuit();
             }else{
                  this.titre_comboalerte.setItems(this.list_autres);
             }
        });
  
        this.titre_comboalerte.setOnKeyPressed( event -> { 

                if( event.getCode() == KeyCode.ENTER ) {
                   String selectedValue = this.titre_comboalerte.getValue();
                   this.list_autres.add(selectedValue);

                }
                
              } );
         this.titre_comboalerte.setOnAction((event) -> {
            String selectedItem = (String)this.titre_comboalerte.getValue();
                 this.titreEv=selectedItem;
        });
               /*
         this.titre_comboalerte.setOnAction((event) -> {
           
         String selectedItem = (String)this.titre_comboalerte.getValue();
         this.titreEv=selectedItem;
            } );*/
       
    
    }
    
    
    // metier Calandrier ki yabda date lyoum 
        public void sendcREALtIME() {
        try {
            String sql = "select * from utilisateur WHERE  roleu ='Client' ";   //	enum('admin', 'chauffeur', 'client', 'partenaire')	
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                User u = new User(s.getInt("id"),s.getString("nomu"),s.getString("prenomu"),s.getString("email"));
                this.clients.add(u);    
            }
                        //if(ddebldalerte.getValue().compareTo(today)==0){
                            for (User u : clients) {   
                        msg_envoyer.envoyer(this.typeAlerte,u.getEmailU(),descldalerte.getText());
                        }
                        
                          
                        
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    
}

// ajouter sur 3 partie 
    @FXML
    private void save(MouseEvent event) {

        String typeAlerte= (String)this.combo_alertetype.getValue();
        String titreEv =(String)this.titre_comboalerte.getValue();
        String descEv =descldalerte.getText(); 
        String date_deb = String.valueOf(ddebldalerte.getValue());
        String date_fin = String.valueOf(dfinldalerte.getValue());
        
          //System.out.println("this.typeAlerte : "+this.typeAlerte+"this.titreEv :  "+this.titreEv);
        // Controle de saisie
        
        if (typeAlerte.isEmpty()|| titreEv.isEmpty() ||descEv.isEmpty() || date_deb.isEmpty() || date_fin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Champs Vide !");
            alert.setContentText("Veuillez remplir toutes les champs");
            alert.showAndWait();
        }
        else {
            getQuery();
            insert();
            //clean();
           sendcREALtIME();
           // notification.sms("Type:"+this.typeAlerte+"\n"+"Description:"+this.descldalerte.getText());
            
            
                    

            
        }
    }

    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `alerte`(`type_alerte_eve`,`titre_eve`, `desc_eve`, `date_deb_eve`, `date_fin_eve`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `alerte` SET "
                    + "`type_alerte_eve`=?,"
                    + "`titre_eve`=?,"
                    + "`desc_eve`=?,"
                    + "`date_deb_eve`=?,"
                    + "`date_fin_eve`= ? WHERE id_alerte_eve = '"+alerteid+"'";
        }

    }
    
    private void insert() {
           // System.out.println("this.typeAlerte"+this.typeAlerte+"this.titreEv :  "+this.titreEv);
        try {

            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1,this.typeAlerte);
            preparedStatement.setString(2,this.titreEv);
            preparedStatement.setString(3, descldalerte.getText());
            preparedStatement.setString(4, String.valueOf(ddebldalerte.getValue()));
            preparedStatement.setString(5, String.valueOf(dfinldalerte.getValue()));
            preparedStatement.execute();
            
            if (dfinldalerte.getValue().isBefore(ddebldalerte.getValue())) {
                    JOptionPane.showMessageDialog(null, "La date de début doit être antérieure à la date de fin", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "L'alerte a été enregistrée avec succès", "Ajout d'alerte  réussi", JOptionPane.INFORMATION_MESSAGE);
                }
            
            

        } catch (SQLException ex) {
         //   Logger.getLogger(AjouterAlerteController.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("excep -----------" +ex);
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" Alerte déja existe !! ");
            alert.showAndWait();
        }

    }
    
    
    
//        private void clean() {
//        combo_alertetype.getItems().clear();
//        titre_comboalerte.getItems().clear();
//        descldalerte.setText(null);
//        ddebldalerte.setValue(null);
//        dfinldalerte.setValue(null);
//        
//    }
        
     

       @FXML
    void retour(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("GererAlerte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        
    void setTextField(int id, String type,String titre,String description, LocalDate fromLocalDate1, LocalDate toLocalDate2) {

        alerteid = id;
        combo_alertetype.setValue(type);
        titre_comboalerte.setValue(titre);
        descldalerte.setText(description);
        ddebldalerte.setValue(fromLocalDate1);
         dfinldalerte.setValue(toLocalDate2);

    }
// tab3a variable boolean update 
    void setUpdate(boolean b) {
        this.update = b;
    }
    
    //getALLCircuit
    public void getAllCircuit(){
        
        List<Circuit> circuits = new ArrayList<>();
        ServiceCircuit service_circuits=new ServiceCircuit();
        circuits=service_circuits.getAll();
        
       for (Circuit c : circuits) {
             this.liste_circuits.add(c.getDepartC()+" ===> "+c.getArriveeC()); 
       } 
       this.titre_comboalerte.setItems(this.liste_circuits);
     
        
    }
    
    
    
}
