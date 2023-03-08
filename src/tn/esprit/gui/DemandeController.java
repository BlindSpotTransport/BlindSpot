/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.entity.Demande;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import tn.esprit.services.MoyenstransportService;
import tn.esprit.services.PlanningService;
import tn.esprit.services.ServiceCircuit;
import tn.esprit.services.ServiceDemande;
//import tn.esprit.tests.MainFX;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import javafx.scene.layout.StackPane;
import tn.esprit.services.UsersSession;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DemandeController implements Initializable {

    @FXML
    private Button permis;
    @FXML
    private ImageView imgBrow;
    private TextField TextNomC;
    private TextField TextMoy;
    private TextField TextHD;
    private TextField TextHA;
    @FXML
    private Button ajouter;
    @FXML
    private TextField imgPath;
    
    FileChooser  filechooser ;
    File  file;
    @FXML
    private ComboBox<String> nomCombo;
    @FXML
    private ComboBox<String> moyenCombo;
    
    /**
     * Initializes the controller class.
     */
    
    ServiceCircuit sc = new ServiceCircuit();
    @FXML
    private AnchorPane IDimg;
    @FXML
    private ImageView chauffeur;
    
    MoyenstransportService mts = new MoyenstransportService();
    PlanningService ps = new PlanningService();
    @FXML
    private ComboBox<String> HDcombo;
    @FXML
    private ComboBox<String> HAcombo;
    @FXML
    private DatePicker dd;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> nom = sc.getNomC();
        ObservableList<String> ListNomC = FXCollections.observableArrayList(nom);
        nomCombo.setItems(ListNomC);

        
         List<String> typeM = mts.getType();
        ObservableList<String> ListM = FXCollections.observableArrayList(typeM);
        moyenCombo.setItems(ListM);
        
         List<String> HD = ps.getHeurD();
        ObservableList<String> ListHD = FXCollections.observableArrayList(HD);
        HDcombo.setItems(ListHD);
        
          List<String> HA = ps.getHeurA();
        ObservableList<String> ListHA = FXCollections.observableArrayList(HA);
        HAcombo.setItems(ListHA);
        
        
    }    
    ServiceDemande sd = new ServiceDemande();
    @FXML
    private void PermisButton(ActionEvent event) {
        
        try {
            filechooser = new FileChooser();
            filechooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*txt"),
                    new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"),
                    new ExtensionFilter("Audio Files","*wav","*.mp3","*.acc")
            );
            
            file = filechooser.showOpenDialog(new Stage());
            imgPath.setText(file.getAbsolutePath());
            
            
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage javafxImage = SwingFXUtils.toFXImage(bufferedImage,null);
            imgBrow.setImage(javafxImage);
          //  ImgView = new ImageView(javafxImage);
            
            
            //   img= new Image(new FileInputStream(file));
            
            

        
//          ImgView= ImageView(img);
//          file.toURI().toString()
        } catch (IOException ex) {
            Logger.getLogger(DemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
            @FXML
    private void AjouterAction(ActionEvent event) {
        // Image img = new Image("small.png");
         if (nomCombo.getValue().equals("") && moyenCombo.getValue().equals("") && TextHD.getText().equals("") 
                 && TextHA.getText().equals("")) {
            Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("fill all the case");
           alert.showAndWait( ); 
        }else{
        Demande d= new Demande();
        //c.setIdCircuit((ifid.getText()));
        d.setNomC(nomCombo.getValue());
        d.setMoyen(moyenCombo.getValue());
        d.setHD(HDcombo.getValue());
        d.setHA(HAcombo.getValue());
        d.setPermis(imgPath.getText());
        d.setEmailC(UsersSession.getEmail());
        sd.ajouterDemande(d);
        reset(); 
        
       
//      Notifications notifiactionBuiler =  Notifications.create().title("Notification")
//              .text("Request added successfully")
//              .graphic(null)
//              .hideAfter(Duration.minutes(1))
//              .position(Pos.TOP_RIGHT)
//              .onAction(new EventHandler<ActionEvent>(){
//                  
//               
//
//          @Override
//          public void handle(ActionEvent event) {
//              System.out.println("clicked on notifiaction");
//          }
//              });
//      // notifiactionBuiler.darkStyle();
//       notifiactionBuiler.show();
      
      
    }
    }


    private void reset() {
      
      nomCombo.setValue("");
      moyenCombo.setValue("");
      HDcombo.setValue("");
      HAcombo.setValue("");
      
    }

    @FXML
    private void VoirAction(ActionEvent event) {
       
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
         //  String url=searchURLtxt.getText();
        String myWeb = "https://www.google.com/maps/@34.819464,10.7460388,12z";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        
         BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
          //  Logger.getLogger(NewFXMainQR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        
         StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        
        
         Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
        
      
     }
    
}
