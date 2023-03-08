/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tn.esprit.tools.MaConnection;
import tn.esprit.entity.Reservation;
/**
 *
 * @author pc
 */
public class ReservationService implements NewInterface<Reservation>{
    Connection cnx;

    public ReservationService() {
          cnx = MaConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Reservation r) {
 try{
        String sql = "INSERT INTO `reservation`(`dateR`, `heureDep`, `heureArr`, `type`, `cin`, `prix`, `NumeroT`) VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setTime(1, r.getDateR());
        ste.setTime(2, r.getHeureDep());
        ste.setTime(3, r.getHeureArr());
        ste.setString(4, r.getType());
        ste.setInt(5, r.getCin());
        ste.setInt(6, r.getPrix());
        ste.setString(7, r.getNUM());
        ste.executeUpdate();
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Reservation a éte ajoutée avec succées !");
            alert.showAndWait();
            
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> Reserv = new ArrayList<>();
try{
            String sql="select * from reservation ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Reservation R = new Reservation(s.getInt("idNum"), s.getInt("cin"), s.getInt("prix"), s.getTime("dateR"),
                        s.getTime("heureDep"), s.getTime("heureArr"),s.getString("type") );
                Reserv.add(R);
            }
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        return Reserv;
        
    }

    @Override
    public void supprimer(Reservation r) {
    String sql = "delete from reservation where idNum=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, r.getIdNum());
            ste.executeUpdate();
            System.out.println("Reservation a éte supprimer avec succées !!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
     public void modifierReservation(String type ,Reservation R) {
        String sql = "update reservation set type=? where idNum=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, type);
            ste.setInt   (2, R.getIdNum());
            ste.executeUpdate();
            System.out.println("Reservation a éte Modifier avec succées !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
     }

    
    /*** fonction pour changer heure to 12 if hour==00***/
    public void changerHourTo12(Time t){
        if (t.getHours()== 00){
            t.setHours(12);
    }
}

    @Override
    public List<Reservation> findById(int cin) {
          List<Reservation> Reserv = new ArrayList<>();
try{
            String sql="select * from reservation where cin='"+cin+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Reservation R = new Reservation(s.getInt("idNum"), s.getInt("cin"), s.getInt("prix"), s.getTime("dateR"),
                        s.getTime("heureDep"), s.getTime("heureArr"),s.getString("type") );
                Reserv.add(R);
            }
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        return Reserv;
    }
    
    public int GetEndTicket(){
       int ticket = 0;
        try{
        String sqlGetDernier = "select MAX(idNum) as ticket from reservation";
                    Statement ste = cnx.createStatement();
                                ResultSet s = ste.executeQuery(sqlGetDernier);

        while (s.next()) {
        ticket = s.getInt("ticket");
            }
        
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     
        
            return ticket;
    }

}
