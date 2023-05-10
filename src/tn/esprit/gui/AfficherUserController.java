/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import tn.esprit.entity.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.services.service_user;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherUserController implements Initializable {
 
    @FXML
    private TableColumn<User, String> nomColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> RoleColumn;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<User, String> preColumn;
    //private TableColumn<User, Integer> idColumn;
    @FXML
    private Button bttn_supp;
   
    @FXML
    private TextField nomfield;
    @FXML
    private TextField prenomflied;
    
    @FXML
    private TextField emailfield;
    @FXML
    private Button modif_bttn;
    @FXML
    private TextField roleflied;
    String ID="";
    Connection cnx;
     service_user su=new service_user(){};
    @FXML
     private void supprimerAction(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a row from the table");
           alert.showAndWait();
        }else{
      User s=new User();

       int x=Integer.parseInt(ID);
       
        s.setIdU(x);
        System.out.println(x);

        su.supprimer(s);
        String Message="Suppression qvec succés";
        su.sms(Message,"+21653821895");
        Refresh();
    }
    }
  
     
   @Override
    public void initialize(URL url, ResourceBundle rb) {
               cnx = MaConnection.getInstance().getCnx(); 
     List<User> stat = su.getAll();
        ObservableList<User> listStat = FXCollections.observableArrayList(stat);
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("idU"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomU"));
        preColumn.setCellValueFactory(new PropertyValueFactory<>("prenomU"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailU"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("roleU"));
        table.setItems(listStat);
    }    

    
    

    @FXML
    private void onSelected(MouseEvent event) {
    int index = -1;
    ResultSet m;
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        nomfield.setText(nomColumn.getCellData(index).toString());
        prenomflied.setText(preColumn.getCellData(index).toString());
        emailfield.setText(emailColumn.getCellData(index).toString());
        roleflied.setText(RoleColumn.getCellData(index).toString());
        String text=emailfield.getText();
        cnx = MaConnection.getInstance().getCnx();
        Statement ste;
        try {
            ste = cnx.createStatement();
            String SQLgetIDAdress = "SELECT id from utilisateur where   email='" +text+"' ";
        m = ste.executeQuery(SQLgetIDAdress);
          ObservableList<Integer> IDU = FXCollections.observableArrayList();
            while (m.next()) {
            int IDD_A = m.getInt("id");
                IDU.add(IDD_A);
              ID = String.valueOf(IDU.get(0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        //ID = idColumn.getCellData(index).toString();
}
    private void Refresh(){
        cnx = MaConnection.getInstance().getCnx(); 
        List<User> stat = su.getAll();
        ObservableList<User> listStat = FXCollections.observableArrayList(stat);
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("idU"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nomU"));
        preColumn.setCellValueFactory(new PropertyValueFactory<>("prenomU"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailU"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("roleU"));
        table.setItems(listStat);
    }

    @FXML
    private void ModiferAction(ActionEvent event) {
        String rolesJson = "['ROLE_USER']";
    if (table.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a row from the table");
           alert.showAndWait();
        }
         User b=new User();
      int x=Integer.parseInt(ID);
        b.setIdU(x);
        b.setNomU(nomfield.getText());
        b.setPrenomU(prenomflied.getText());
        b.setEmailU(emailfield.getText());
        b.setRoleU(roleflied.getText());
 

         // Assuming you have the role stored in this variable
         // Assuming you have the ID stored in this variable

        // Create the JSON array as a string
        try {
        if(b.getRoleU().equals("Client")||b.getRoleU().equals("Chauffeur")){
        rolesJson = "['ROLE_USER']";
        }else{
         rolesJson = "['ROLE_ADMIN']";
        }
        // Construct the SQL query
        String sql = "UPDATE utilisateur SET roles = ? WHERE id = ?";

            // Connect to the database
            Connection cnxx = MaConnection.getInstance().getCnx();
            // Create a prepared statement with parameter placeholders
            PreparedStatement statement = cnxx.prepareStatement(sql);

            // Set the JSON array as a parameter
            statement.setString(1, rolesJson);

            // Set the ID as a parameter
            statement.setInt(2, x);

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            System.out.println(rowsAffected + " row(s) updated.");

            // Close the statement and connection

         //b.setRoles(ROLESFIELD.getText());
         String nom=b.getNomU();
         String prenom=b.getPrenomU();
         String role=b.getRoleU();
         System.out.println(role);
         String email=b.getEmailU();
         int phone;
        su.modifier2(nom,prenom,role,email,b);
       
        
        Refresh();
         } catch (SQLException e) {
            e.printStackTrace();
        } 
       
}
    
    }


