/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Evenement;
import entites.Offres;
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
public class AjouterOffreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection cnx = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Evenement offre = null ; 
    private boolean update;
    int Offreid;
    
    
    @FXML
    private TextField titreld;
    @FXML
    private TextField descld;
    @FXML
    private DatePicker ddebld;
    @FXML
    private DatePicker dfinld;

    public AjouterOffreController() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    
    @FXML
    private void save(MouseEvent event) {
        
        String titre = titreld.getText();
        String description =descld.getText();                                                
        String date_deb = String.valueOf(ddebld.getValue());
        String date_fin = String.valueOf(dfinld.getValue());
        
        // Controle de saisie
        if (titre.isEmpty() || description.isEmpty() || date_deb.isEmpty() || date_fin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Alerte");
            alert.setContentText("Veuillez remplir toutes les champs");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }
    
        private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `evenement`(`titre_eve`, `desc_eve`, `date_deb_eve`, `date_fin_eve`) VALUES (?,?,?,?)";

        }else{
            query = "UPDATE `evenement` SET "
                    + "`titre_eve`=?,"
                    + "`desc_eve`=?,"
                    + "`date_deb_eve`=?,"
                    + "`date_fin_eve`= ? WHERE id_eve = '"+Offreid+"'";
        }

    }
        
        private void insert() {

        try {

            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, titreld.getText());
            preparedStatement.setString(2, descld.getText());
            preparedStatement.setString(3, String.valueOf(ddebld.getValue()));
            preparedStatement.setString(4, String.valueOf(dfinld.getValue()));
            preparedStatement.execute();

        } catch (SQLException ex) {
            //Logger.getLogger(AjouterOffreController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" Offre déja existe !! ");
            alert.showAndWait();
        }

    }
        private void clean() {
        titreld.setText(null);
        descld.setText(null);
        ddebld.setValue(null);
        dfinld.setValue(null);
        
    }
         @FXML
    void retour(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/GererOffre.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GererOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            
        
        
    void setTextField(int id, String titre,String description, LocalDate fromLocalDate, LocalDate toLocalDate) {

        Offreid = id;
        titreld.setText(titre);
        descld.setText(description);
        ddebld.setValue(fromLocalDate);
        dfinld.setValue(toLocalDate);

    }

    void setUpdate(boolean b) {
        this.update = b;
    }


    
}
