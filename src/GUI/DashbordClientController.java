/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.NotificationService;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.net.URL;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;
//import java.time.Duration;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DashbordClientController implements Initializable {
      NotificationService notification = new NotificationService();
        
  
 

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
    //System.out.println(last);
        // TODO
      //calcul.setText(afficherPrix(old,pourcent).toString());
      
      
      //System.out.println(pourcentage);
      
      
      
    }    

    @FXML
    private void calcilerprix(ActionEvent event) {
        String last = old.getText();
      float oldprice = Float.parseFloat(last);
      
      String lastper = per.getText();
      int pourcentage = Integer.parseInt(lastper);
      
      
      calcul.setText(afficherPrix(oldprice,pourcentage).toString());
    }

  
    
    
    
}
