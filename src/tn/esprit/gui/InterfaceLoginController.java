/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entity.User;
import tn.esprit.entity.Adresse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javax.swing.JOptionPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javax.mail.MessagingException;
import tn.esprit.services.service_user;
import tn.esprit.services.service_adresse;
import services.EnvoyerEmail;
import tn.esprit.tools.MaConnection;
import javax.mail.internet.AddressException;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JLabel;
import co.yogesh.Captcha;
import java.io.File;
import java.net.URL;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFrame;
import javafx.stage.FileChooser;
import tn.esprit.services.UsersSession;
//import static services.service_user.cUserRow;
 import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import tn.esprit.tests.UserFXMain;


/**
 * FXML Controller class
 *
 * @author user
 */
public class InterfaceLoginController implements Initializable {
   //cnx = MaConnection.getInstance().getCnx();
    service_user  ps = new service_user() {} ;
    @FXML
    private TextField CinU;
    @FXML
    private TextField txt_email_up;
    @FXML
    private PasswordField txt_password_up;
    @FXML
    private ComboBox<String> type_up;
    @FXML
    private Button bttn_login;
    @FXML
    private AnchorPane pane_login;
   
    @FXML
    private AnchorPane pane_signup;
    @FXML
    private TextField id_nomU;
    @FXML
    private TextField id_prenomU;
    @FXML
    private TextField id_telU;
    @FXML
    private TextField id_emailU;
    @FXML
    private TextField id_pwdU;
    @FXML
    private RadioButton id_abonU;
    
    @FXML
    private Button bttn_signup;
    private static String profilePicture="";
 private JLabel tempLabel;
ResultSet rs=null ;
Connection cnx;
PreparedStatement pst;
 public static ResultSet cUserRow;
    @FXML
    private ComboBox<String> region_Id;
    @FXML
    private ComboBox<String> cité_id;
    @FXML
    private ComboBox<String> rue_id;
    @FXML
    private ComboBox<Integer>  poste_id;
    @FXML
    private CheckBox cbox;
    @FXML
    private Button MdpBttn;
    @FXML
    private TextField captchainput;
    @FXML
    private ImageView captchagenerate;
    @FXML
    private TextField cImageUrl;
    @FXML
    private ImageView sigupimage;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void LoginpaneShow (){
    pane_login.setVisible (true);
    pane_signup.setVisible (false);
    }
    @FXML
 public void SignuppaneShow () {
    pane_login.setVisible (false);
    pane_signup.setVisible (true);
            }
 Captcha cap = new Captcha();
@FXML
private void Login (ActionEvent event) throws Exception{

 cnx = MaConnection.getInstance().getCnx();
 service_user ps=new service_user() {
};
  try {
  if(cap.Validate(tempLabel, captchainput.getText()))
       {
           System.out.println("captcha valid");
           String email=txt_email_up.getText();
           String password=txt_password_up.getText();
           boolean result = ps.LoginUser(email, password);
            if(result)
        {
           
        if(UsersSession.getRole().equals("admin")){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePage.fxml"));
            
    Parent root = loader.load();
             
//    ProfilController controller = loader.getController();
//    controller.setUserEmail(txt_email_up.getText());
    Scene scene = new Scene(root);
    Stage stage = (Stage) id_nomU.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();
        }
        else if(UsersSession.getRole().equals("client")){
                          FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePageClient.fxml"));
            
    Parent root = loader.load();
             
//    ProfilController controller = loader.getController();
//    controller.setUserEmail(txt_email_up.getText());
    Scene scene = new Scene(root);
    Stage stage = (Stage) id_nomU.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();
            }
        else{
               FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePageChauffeur.fxml"));
            
    Parent root = loader.load();
             
//    ProfilController controller = loader.getController();
//    controller.setUserEmail(txt_email_up.getText());
    Scene scene = new Scene(root);
    Stage stage = (Stage) id_nomU.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();
        }
        }

}

  } catch (IOException ex) {
        Logger.getLogger(service_user.class.getName()).log(Level.SEVERE, null, ex);
}
}
    @FXML
  private void SignUp(ActionEvent event) throws SQLException {
      try {
      cnx = MaConnection.getInstance().getCnx(); 
      service_user ps =new service_user() {
};
        service_adresse as =new service_adresse() {
};
        if( region_Id.getSelectionModel().getSelectedItem() == null
                || cité_id.getSelectionModel().getSelectedItem() == null
                || rue_id.getSelectionModel().getSelectedItem() == null
                || poste_id.getSelectionModel().getSelectedItem() == null
                || type_up.getSelectionModel().getSelectedItem() == null
                || id_nomU.getText().equals("")
                || id_prenomU.getText().equals("")
                ||id_telU.getText().toString().equals("")||CinU.getText().toString().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs sont obligatoire !");
            alert.showAndWait();
            return;
        }
        
         if (id_telU.getText().toString().length() <8) {
               
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Le telephone est composé de 8 champs !");
                    alert.showAndWait();
                    return;}
        if (CinU.getText().toString().length() <8) {
               
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Le cin  est composé de 8 champs !");
                    alert.showAndWait();
                    return;
        }
                
            
        if (ps.validerEmail(id_emailU.getText())==false) {
               
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("le format d'email est incorrect  !");
                    alert.showAndWait();
                    return;
                
            } 
        
        
         Date thisDate = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String myString = sdf.format(thisDate);
        Adresse a=new Adresse();
        User p= new User();
        a.setRegion(region_Id.getValue());
        a.setCité(cité_id.getValue());
        a.setRue(rue_id.getValue());
        a.setNumPoste(poste_id.getValue());
        
         String SQLgetIDAdress = "SELECT idAdresseU from adresse where region='" + region_Id.getValue() + "' AND cité='" +cité_id.getValue() + "' AND rue='" +rue_id.getValue() + "' ";
         Statement ste= cnx.createStatement();   
         ResultSet m,s;
        
            m = ste.executeQuery(SQLgetIDAdress);
        
            ObservableList<Integer> IDAdresse = FXCollections.observableArrayList();
            while (m.next()) {
                int IDD_A = m.getInt("idAdresseU");
                IDAdresse.add(IDD_A);
            } 
            if (IDAdresse.isEmpty()){
             as.ajouter(a);
             s =ste.executeQuery(SQLgetIDAdress);
              ObservableList<Integer> ID_A = FXCollections.observableArrayList();
            while (s.next()) {
                int IDD = s.getInt("idAdresseU");
                ID_A.add(IDD);
                 int FINAL_ID = ID_A.get(0);
                 a.setIdAdresse(FINAL_ID);
              
            
            }}
            else{
                int FINAL_ID_A = IDAdresse.get(0);
            a.setIdAdresse(FINAL_ID_A);
            System.out.println(FINAL_ID_A);
            }
            String nom=id_nomU.getText();
            String prenom=id_prenomU.getText();
            String Tel=id_telU.getText();
            String Cin=CinU.getText();
            
  if (ps.validerCin(Tel)&&ps.validerCin(Cin)&&ps.validateString(nom) && ps.validateString(prenom)){
        p.setNomU(id_nomU.getText());
        p.setPrenomU(id_prenomU.getText());
        p.setImagePU(cImageUrl.getText());
        p.setMdpU(id_pwdU.getText());
        p.setEmailU(id_emailU.getText());
        p.setRoleU((String)type_up.getValue());
        //p.setPermisU(id_permis.getText());
        String Permis=null;
        p.setPermisU(Permis);
        p.setTelephoneU(Integer.parseInt(id_telU.getText()));
        p.setCinU(Integer.parseInt(CinU.getText()));
        p.setAdresseU(a);
        p.setCreatedAtU(myString);
        p.setAbonnéU(id_abonU.isSelected());
        ps.ajouter(p);

         ResultSet rs=null ;
         Connection cnx; 
         String email=id_emailU.getText();
         cnx = MaConnection.getInstance().getCnx();
         String sql = "Select* from utilisateur where emailU=? ";
        PreparedStatement pst = cnx.prepareStatement(sql);
         pst.setString(1,email);
        rs = pst.executeQuery();
        if(rs.next()) {
                   //JOptionPane.showMessageDialog(null, "Email And Password is Correct");
                cUserRow=rs;
                
//                cUserId = rs.getInt("idU");
//                cUserRow = rs;
//                UsersSession.addUserLogin(cUserRow);
     
               }
                UsersSession.addUserLogin(cUserRow);
           //System.out.println(UsersSession.getIdU());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrincipalePageClient.fxml"));
    Parent root;
          try {
              root = loader.load();
              Scene scene = new Scene(root);
    Stage stage = (Stage) id_nomU.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();    
          } catch (IOException ex) {
              Logger.getLogger(InterfaceLoginController.class.getName()).log(Level.SEVERE, null, ex);
          }
//    ProfilController controller = loader.getController();
//    controller.setUserEmail(txt_email_up.getText());
    
    
  }
  
//  else if(id_nomU.getText().equals("")||id_prenomU.getText().equals("")||id_pwdU.getText().equals("")||id_emailU.getText().equals("")) {
// }  
     else if(ps.validateString(nom)==false){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("");
    alert.setHeaderText("Format nom");
    alert.setContentText("nom doit etre une chaine de caractére");
    alert.showAndWait();}

   
          
      }catch (SQLException ex) {
            Logger.getLogger(InterfaceLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tempLabel = new JLabel();
       
        cap.setImageCaptcha(tempLabel);
        captchagenerate.setImage(SwingFXUtils.toFXImage(UserFXMain.iconToImage(tempLabel.getIcon()),null));
         JFrame frame = new JFrame("JOptionPane showMessageDialog example");
         frame.setAlwaysOnTop(true);    
        cnx = MaConnection.getInstance().getCnx(); 
    type_up.getItems().addAll("client","chauffeur");
    region_Id.getItems().addAll("Tunis", "Manouba","Ariana","Ben arous");
    rue_id.getItems().addAll("Avenue de Carthage", "Avenue de France","Avenue de Paris","Avenue de la Liberté","Avenue des États-Unis","Avenue Farhat-Hached","Avenue Habib-Bourguiba","Avenue Habib-Thameur","Avenue Hédi-Chaker","Avenue Mohammed-V");
    poste_id.getItems().addAll(1111, 2080,2037,2027,2091,2050);}



    @FXML
    private void SelectAvenue(ActionEvent event) {
        String Region=region_Id.getSelectionModel().getSelectedItem();
    if (Region.equals("Tunis")){
        cité_id.getItems().clear();
      cité_id.getItems().addAll("Bab El Bhar","Marsa","Sidi bousaid","Kram","Bab Souika", "Cité El Khadra", "Djebel Jelloud", "El Kabaria", "El Menzah", "El Omrane", "El Omrane supérieur", "El Ouardia","Ettahrir", "Ezzouhour", "Hraïria", "Médina", "Séjoumi","Sidi El Béchir");
       }else if(Region.equals("Manouba")){
           cité_id.getItems().clear();
     cité_id.getItems().addAll("Borj El Amri", "Douar Hicher","El Battan", "Jedaida", "Mannouba"," Mornaguia","Oued Ellil" ,"Tebourba");
 }else if(Region.equals("Ariana")) {
    cité_id.getItems().clear();
     cité_id.getItems().addAll("Ariana Aéroport" ,"Sidi Thabet" , "Kalaat Landalous","Choutrana - N'khilet","M'nihla","El Bokri","Ariana soghra");
 }else if(Region.equals("Ben arous")){
     cité_id.getItems().clear();
     cité_id.getItems().addAll("Hammam Lif","Mohamedia" ,"Medina Jedida" ,"Mégrine");
 };
    }

     @FXML
    private void checkbox(ActionEvent event) {
        if (cbox.isSelected()){
            txt_password_up.setPromptText(txt_password_up.getText());
            txt_password_up.setText("");
            txt_password_up.setDisable(true);
        }else {
            txt_password_up.setText(txt_password_up.getPromptText());
            txt_password_up.setPromptText("");
            txt_password_up.setDisable(false);
        }
    }

//    private void EmailOnAction(ActionEvent event) {
//       cnx = MaConnection.getInstance().getCnx();
// service_user ps=new service_user() {
//};
//        if(ps.verifierEmail(txt_email_up.getText()))
//        {
//            try {
//      String sql = "SELECT * FROM utilisateur WHERE emailU=?";
//      PreparedStatement stmt = null;
//      ResultSet rst = null;
//    stmt = cnx.prepareStatement(sql);
//    stmt.setString(6, txt_email_up.getText());
//      rst = stmt.executeQuery();
//            if (rst.next()) {
//       String s = rst.getString("nomU");
////EnvoyerEmail e = new EnvoyerEmail(){};
//    String str=rst.getString("mdpU");
//    String firstFiveChars = str.substring(0,5);
//    ps.sendEmail(txt_email_up.getText(),firstFiveChars,s);
//}
//   
//} catch (SQLException ex) {
//   System.out.println(ex.getMessage());
//}}
//        
//
//    }

    @FXML
    private void SMSOnAction(ActionEvent event) {

 FXMLLoader loader = new FXMLLoader(getClass().getResource("RecupererMdp.fxml"));
    Parent root;
          try {
              root = loader.load();
              Scene scene = new Scene(root);
    Stage stage = (Stage) id_nomU.getScene().getWindow(); // get the current stage
    stage.setScene(scene);
    stage.show();    
          } catch (IOException ex) {
              Logger.getLogger(InterfaceLoginController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    private void uploadsiguppic(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\pc\\Downloads\\Final\\TakTak_Final\\src\\tn\\esprit\\gui\\images"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String TempprofilePicture = file.toURI().toString();
            System.out.println(TempprofilePicture);
            profilePicture = file.getName();
            System.out.println(profilePicture);
            Image image = new Image(TempprofilePicture);
            sigupimage.setImage(image);
            cImageUrl.setText(TempprofilePicture);
            
        }
    }
      
    
    }


   
