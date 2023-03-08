/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
import tn.esprit.entity.Demande;
import java.io.File;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tn.esprit.tools.MaConnection;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author ASUS
 */
public class ServiceDemande implements InterfaceDemande{
     Connection cnx;

    public ServiceDemande() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterDemande(Demande d) {
 

                try {

                    String req = "insert into demande(nomC, moyen,hd,ha, permis, EmailC) VALUES (?,?,?,?,?,?)";
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setString(1, d.getNomC());
                    ps.setString(2, d.getMoyen());
                    ps.setString(3, d.getHD());
                    ps.setString(4, d.getHA());
                    //ps.setString(5, d.getHA());
                    ps.setString(6, d.getEmailC());
                    
                   

//            File imageFile = new File("C:/Users/Nour Benkairia/Documents/NetBeansProjects/ArtistyProject/src/edu/artisty/images.png");
                   File file = new File( d.getPermis().toString());

                         Path path = file.toPath();
                         String img = path.toString();
                         ps.setString(5, img);
                          ps.executeUpdate();
                          
                            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("request added successfully");
           alert.showAndWait();

                    } catch (SQLException ex) {
                        Alert alert = new Alert (Alert.AlertType.ERROR);  
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Circuit already exist");
           alert.showAndWait();
                    }

//    try {
//         fis=new FileInputStream(file);
//          ps.setBinaryStream(4, fis, file.length());
//     } catch (FileNotFoundException ex) {
//        System.err.println(ex.getMessage());
//     }
 

    }

    @Override
    public List getAll() {
        List<Demande> demandes = new ArrayList<>();
        try {
            String sql = "select * from demande";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Demande d = new Demande(rs.getInt(1),
                        rs.getString("nomC"), rs.getString("moyen"), rs.getString("HD"),
                rs.getString("HA"), rs.getString("permis"), rs.getString("EmailC"));
                demandes.add(d);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return demandes;
    }
    
    public  void  sms (String resultat) {
    
        Twilio.init("AC59fc270cf600cf72e19f55209c147e0e","bb1421e71da5ced6fc834953a6d043f2");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21629117005"),
                new com.twilio.type.PhoneNumber("+15676676188"),
                 resultat)
            .create();

        System.out.println(message.getSid());
    }
    
     @Override
    public void supprimerDemande(Demande d) {
          String sql = "delete from demande where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, d.getId());
            ste.executeUpdate();
            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);  
            alert.setTitle("Succes");
            alert.setHeaderText(null);
           alert.setContentText("Demande deleted");
           alert.showAndWait();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
