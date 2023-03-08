/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.services.NotificationService;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;
//import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import tn.esprit.entity.Reservation;
import tn.esprit.gui.AjouterOffreController;
import tn.esprit.services.PlanningService;
import tn.esprit.services.ReservationService;
import tn.esprit.services.SendMail;
import tn.esprit.services.SendSms;
import tn.esprit.services.UsersSession;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordClientController implements Initializable {
      NotificationService notification = new NotificationService();
        
  
 
    Connection cnx;
    @FXML
    private Button notif;
    @FXML
    private Button evenement;
    @FXML
    private TextField calcul;
    @FXML
    private TextField per;

    @FXML
    private TextField old;
    @FXML
    private Button btncalc;
      @FXML
    private ImageView events_image;

    @FXML
    private ImageView alerte_img;
    @FXML
    private Text NameClient;
    private ImageView ImageUser;
    @FXML
    private Circle profilepicture;
    @FXML
    private Button buttonReser;
     
    
    //private float last=Float.parseFloat(old.getText());
    
    //float old=Float.parseFloat(poldprice.getText());
    
      
    //Integer pourcent=Integer.parseInt(pourcentage.getText());
    
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    void notifier(ActionEvent event) throws InterruptedException {
        notification.NotifyondashbordAlert(); 
    }
       @FXML
    void notifierevent(ActionEvent event) {
        notification.NotifyondashbordEvent();
    }
    
    public Float afficherPrix(Float prix,int percent) {
        return prix -((prix*percent)/100) ; 
    }
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NameClient.setText(UsersSession.getName()+" "+UsersSession.getLastname());
//        ImageUser.setImage(UsersSession.getProfilepicture());
    String Photo=UsersSession.getProfilepicture();
    javafx.scene.image.Image im = new javafx.scene.image.Image(Photo);
    ImagePattern pattern = new ImagePattern(im);
    profilepicture.setFill(pattern);
    profilepicture.setStroke(Color.SEAGREEN);
    profilepicture.setEffect(new DropShadow(10, Color.BLACK));
    //System.out.println(last);
        // TODO
      //calcul.setText(afficherPrix(old,pourcent).toString());
      
      
      //System.out.println(pourcentage);
      
      
      
    }    
    public DashbordClientController(){
        cnx = MaConnection.getInstance().getCnx();
    }

    @FXML
    private void calcilerprix(ActionEvent event) {
        String Desc = null;
        String last = old.getText();
      float oldprice = Float.parseFloat(last);
      
      String lastper = per.getText();
            String SqlGetDesc = "select desc_eve from evenement where id_eve = (select max(id_eve) from evenement)";
            Statement ste;
          try {
              ste = cnx.createStatement();
        
            ResultSet c = ste.executeQuery(SqlGetDesc);
            while (c.next()) {
                Desc = c.getString("desc_eve");
            }
            //id de circuit
            
      
        } catch (SQLException ex) {
              Logger.getLogger(DashbordClientController.class.getName()).log(Level.SEVERE, null, ex);
          }
      
//      int pourcentage = Integer.parseInt(lastper);
      
      if(lastper.equals(Desc)){
      calcul.setText(afficherPrix(oldprice,50).toString());
      }
      else{
          System.out.println("offre invalide !");
      }
      
      
      
      
    }

    @FXML
    private void btn_fb(ActionEvent event) {
        String url = "https://www.facebook.com/profile.php?id=100090856030610"; // Replace with the URL of the website you want to link to
    try {
        java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
    } catch (Exception ex) {
        System.out.println("Error opening website: " + ex.getMessage());
    }
    }

    @FXML
    private void ReserverBtn(ActionEvent event) {
    }
}

  
    
    
    


//    @FXML
//    private void ReserverBtn(ActionEvent event) {
//         try {
//                    PlanningService pl = new PlanningService();
//            ReservationService rs = new ReservationService();
//
//   int idMoyenne = ReservationPage1Controller.idmoyy ; 
//   int idCircuitt= ReservationPage1Controller.idCirr;
//   Time timee1= ReservationPage1Controller.t1;
//   int NembrePlace=pl.GetNombreDePlace(idCircuitt, idMoyenne, timee1);
//    //Recuperer Time Now in variable TimeNow 
//            Date date = new Date(System.currentTimeMillis());
//            SimpleDateFormat formatteur = new SimpleDateFormat("HH:mm:ss");
//            String Timenow = formatteur.format(date);
//            Date parsedDate = formatteur.parse(Timenow);
//            long dt = parsedDate.getTime();
//            TimeNow = new Time(dt);
//
//   Time timee2 = ReservationPage1Controller.t2 ;
//   int Prixx= (int)Integer.parseInt(calcul.getText().toString());
//   String typee=ReservationPage1Controller.Type;
//   String Numeroo=ReservationPage1Controller.NUM;
//              Reservation R = new Reservation(UsersSession.getCin(), Prixx, TimeNow, timee1, timee2, typee, Numeroo);
//            rs.ajouter(R);
//            
//                        pl.SuppNbPlace(idCircuitt, idMoyenne, timee1);
//            //recuperer ID de ticket ajouter
//            int Ticket = rs.GetEndTicket();
//
//            SendMail sm = new SendMail();
//            sm.sendEmail(UsersSession.getEmail(), Ticket);
//
//            //envoyer sms 
//            SendSms sms = new SendSms();
//            //sms.sendSms(Ticket,"+216"+u1.getTelephoneU());
//            
//    
//         }catch(Exception ex){
//             System.out.println(ex.getMessage());
//         }
//
//    }