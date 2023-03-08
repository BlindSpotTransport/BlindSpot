/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class UserFXMain extends Application {
    
    @Override
    
       
        
       public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/InterfaceLogin.fxml"));
           //Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherUser.fxml"));
             Scene scene = new Scene(root, 800,600);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
public static BufferedImage iconToImage(Icon icon) { 
   if (icon instanceof ImageIcon) {
      return (BufferedImage)((ImageIcon)icon).getImage();
   } 
   else {
      int w = icon.getIconWidth();
      int h = icon.getIconHeight();
       GraphicsEnvironment ge = 
        GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice gd = ge.getDefaultScreenDevice();
       GraphicsConfiguration gc = gd.getDefaultConfiguration();
       BufferedImage image = gc.createCompatibleImage(w, h);
       Graphics2D g = image.createGraphics();
      icon.paintIcon(null, g, 0, 0);
      g.dispose();
      return image;
   }
 }  
//    public static void setScene(String sceneName)
//    {
//        try {
//            Parent root = FXMLLoader.load(_self.getClass().getResource(sceneName + ".fxml"));
//            //Parent root = FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
//
//            Scene scene = new Scene(root);
//            _stage.setScene(scene);
//            _stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }   
    public static void main(String[] args) {
        launch(args);
    }
}


    

