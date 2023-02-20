/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Alerte;
import entites.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterAlerteController implements Initializable {
    
    
    String query = null;
    Connection cnx = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Alerte alerte = null ; 
    private boolean update;
    int alerteid;
    
    

    @FXML
    private TextField typeidalerte;
    @FXML
    private TextField titreldalerte;
    @FXML
    private TextField descldalerte;
    @FXML
    private DatePicker ddebldalerte;
    @FXML
    private DatePicker dfinldalerte;
    
    
     public AjouterAlerteController () {
        cnx = MaConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    

    @FXML
    private void save(MouseEvent event) {
        
        String typeAlerte= typeidalerte.getText();
        String titreEv =titreldalerte.getText();  
        String descEv =descldalerte.getText(); 
        String date_deb = String.valueOf(ddebldalerte.getValue());
        String date_fin = String.valueOf(dfinldalerte.getValue());
        
        // Controle de saisie
        
        if (typeAlerte.isEmpty()|| titreEv.isEmpty() ||descEv.isEmpty() || date_deb.isEmpty() || date_fin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir toutes les champs");
            alert.showAndWait();

        }else {
            getQuery();
            insert();
            clean();

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

        try {

            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, typeidalerte.getText());
            preparedStatement.setString(2, titreldalerte.getText());
            preparedStatement.setString(3, descldalerte.getText());
            preparedStatement.setString(4, String.valueOf(ddebldalerte.getValue()));
            preparedStatement.setString(5, String.valueOf(dfinldalerte.getValue()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjouterAlerteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
     @FXML
        private void clean() {
        typeidalerte.setText(null);
        titreldalerte.setText(null);
        descldalerte.setText(null);
       ddebldalerte.setValue(null);
        dfinldalerte.setValue(null);
        
    }
        
     

       @FXML
    void retour(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/GererAlerte.fxml"));
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
        typeidalerte.setText(type);
        titreldalerte.setText(titre);
        descldalerte.setText(description);
        ddebldalerte.setValue(fromLocalDate1);
         dfinldalerte.setValue(toLocalDate2);

    }

    void setUpdate(boolean b) {
        this.update = b;
    }
    
}
