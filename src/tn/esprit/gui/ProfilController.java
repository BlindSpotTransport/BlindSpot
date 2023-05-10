/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.tools.MaConnection;
import tn.esprit.entity.Adresse;
import tn.esprit.entity.User;
import java.io.File;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tn.esprit.services.service_adresse;
import tn.esprit.services.service_user;
import tn.esprit.services.UsersSession;
import tn.esprit.tests.UserFXMain;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProfilController implements Initializable {
int idU;
    @FXML
    private TextField Prenom_field;
    @FXML
    private TextField Role_field;
    @FXML
    private TextField region_field;
    @FXML
    private TextField CPostal_field;
    @FXML
    private TextField rue_field;
    @FXML
    private TextField cité_field;
    @FXML
    private TextField Tel_field;
    @FXML
    private Button bttn_del;
    @FXML
    private Button bttn_modify;
    @FXML
    private Button bttn_back;
    @FXML
    private TextField Nom_field;
    @FXML
    private Button bttn_logout;
    @FXML
    private TextField Email_field;

     private String text;
     ResultSet rs=null ;
Connection cnx;
PreparedStatement pst;
private int FINAL_ID_A ;
String ID="";

//    ProfilController(String text) {
//       this.text = text;
//    }
    @FXML
    private Circle profilepicture;
    @FXML
    private Button changepicbtn;
    @FXML
    private Label namellastnameuser;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        idU=UsersSession.getIdU();
        //        System.out.println(idU+"montassser");
        //if(idU==0){return;}
        cnx = MaConnection.getInstance().getCnx();
        Statement ste;
        ste = cnx.createStatement();
        ResultSet m,A,Us;
//       String SQLgetUser = "SELECT * from utilisateur where   idU='" +idU+"' ";
//        Us = ste.executeQuery(SQLgetUser);
//         while (Us.next()) {
//String nom = Us.getString("nomU");
String nom=UsersSession.getName();
String prenom=UsersSession.getLastname();
String Role=UsersSession.getRole();
String Photo=UsersSession.getProfilepicture();
String email=UsersSession.getEmail();
int phone=UsersSession.getTelU();
 ID= String.valueOf(idU);
//int phone = UsersSession.();
System.out.println(nom);
//                String prenom= Us.getString("prenomU");
//                String Role = Us.getString("roleU");
//                int phone = Us.getInt("telephoneU");
//                ID= String.valueOf(Us.getInt("IdU"));
//                String Photo=Us.getString("imagePU");
//                String email=Us.getString("emailU");
Nom_field.setText(nom);
Prenom_field.setText(prenom);
Role_field.setText(Role);
                Tel_field.setText( String.valueOf(phone));
Email_field.setText(email);
Image im = new Image(Photo);
ImagePattern pattern = new ImagePattern(im);
profilepicture.setFill(pattern);
profilepicture.setStroke(Color.SEAGREEN);
profilepicture.setEffect(new DropShadow(10, Color.BLACK));
namellastnameuser.setText("Welcome, "+ " "+Role.toUpperCase()+ " "+nom.toUpperCase()+" "+prenom.toUpperCase());
//}
String SQLgetIDAdress = "SELECT idAdresse from utilisateur where id='" +idU+"' ";
m = ste.executeQuery(SQLgetIDAdress);
ObservableList<Integer> IDAdresse = FXCollections.observableArrayList();
while (m.next()) {
    int IDD_A = m.getInt("idAdresse");
    IDAdresse.add(IDD_A);
    FINAL_ID_A = IDAdresse.get(0);
}
String SQLget = "SELECT * from adresse where   id='" +FINAL_ID_A +"' ";
A = ste.executeQuery(SQLget);
while (A.next()) {
    String region = A.getString("region");
    String cité = A.getString("cite");
    String rue = A.getString("rue");
    int poste = A.getInt("numPoste");
    region_field.setText(region);
    cité_field.setText(cité);
    rue_field.setText(rue);
    CPostal_field.setText( String.valueOf(poste));
}
    } catch (SQLException ex) {
        Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }    

    @FXML
    private void DeleteAccount(ActionEvent event) {
        
        
        try {
            service_user su=new service_user(){};
            User s=new User();
            int x=Integer.parseInt(ID);
//            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
//            if (choice == JOptionPane.YES_OPTION) {
//                s.setIdU(x);
//                System.out.println(x);
//                su.supprimer(s);
//            }

Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Are you sure you want to proceed?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // user clicked OK button
    s.setIdU(x);
    System.out.println(x);
    su.supprimer(s);
    
}


            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLogin.fxml"));
            Parent root;
            root = loader.load();
            //ProfilController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) Nom_field.getScene().getWindow(); // get the current stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Modify(ActionEvent event) {
       
     Connection cnx;
     service_user su=new service_user(){};
     service_adresse sa=new service_adresse() {
};
        User b=new User();
         Adresse a=new Adresse();
      int x=Integer.parseInt(ID);
        b.setIdU(x);
        b.setNomU(Nom_field.getText());
        b.setPrenomU(Prenom_field.getText());
        b.setEmailU(Email_field.getText());
        b.setRoleU(Role_field.getText());
         b.setTelephoneU(Integer.parseInt(Tel_field.getText()));
         String nom=b.getNomU();
         String prenom=b.getPrenomU();
         String role=b.getRoleU();
         String email=b.getEmailU();
         int phone=b.getTelephoneU();
       a.setIdAdresse(FINAL_ID_A);
       a.setRegion(region_field.getText());
       a.setCité(cité_field.getText());
       a.setRue(rue_field.getText());
       a.setNumPoste(Integer.parseInt(CPostal_field.getText()));
        String region=a.getRegion();
         String cité=a.getCité();
         String rue=a.getRue();
         int Cpostal=a.getNumPoste();
       sa.modifierA(Cpostal,region, cité, rue, a);
        su.modifier1(nom,prenom,role,email,phone,b);
    
         
    
   
    
    
    }

    @FXML
    private void Back(ActionEvent event) {
        //UserFXMain.setScene("InterfaceLogin.fxml");
    }

    @FXML
    private void Logout(ActionEvent event) {
                          
    
try {
FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceLogin.fxml"));
Parent root;
root = loader.load();

Scene scene = new Scene(root);
Stage stage = (Stage) Nom_field.getScene().getWindow(); // get the current stage
stage.setScene(scene);
stage.show();
        //Parent loader = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));
        //id_nomU.getScene().setRoot(loader);
    } catch (IOException ex) {
        Logger.getLogger(InterfaceLoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void uploadsiguppic(ActionEvent event) {
        int x=Integer.parseInt(ID);
        service_user su=new service_user(){};
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            Image image = new Image(TempprofilePicture);
            ImagePattern pattern = new ImagePattern(image);
            UsersSession.setProfilepicture(TempprofilePicture);
            profilepicture.setFill(pattern);
            profilepicture.setStroke(Color.SEAGREEN);
            profilepicture.setEffect(new DropShadow(20, Color.BLACK));
                    su.modifierImage(TempprofilePicture,x);
        }
    }

   
}
