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
import tn.esprit.entity.Moyenstransport;
import tn.esprit.entity.Planning;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author pc
 */
public class PlanningService implements NewInterface<Planning>{
        Connection cnx;

    public PlanningService() {
         cnx = MaConnection.getInstance().getCnx();

    }

    @Override
    public void ajouter(Planning p) {
       try{
        String sql = "INSERT INTO `planning`(`idMoy`,`idCir`, `dateD`, `DateA`, `NbPlace`) VALUES (?,?,?,?,?) ";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, p.getIdMoy());
        ste.setInt(2, p.getIdCir());
        ste.setTime(3, p.getDateD());
        ste.setTime(4, p.getDateA());
        ste.setInt(5, p.getNbPlace());
        System.out.println("planning ajoutée avec succées !");
        ste.executeUpdate();
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<Planning> getAll() {
        List<Planning> Plan = new ArrayList<>();
        try{
            String sql="select * from planning ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) 
            {
                Planning p = new Planning(s.getInt(1),s.getInt(2),s.getInt(5)
                        ,s.getTime(3),s.getTime(4));
                Plan.add(p);
            }
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        return Plan;
    }

    @Override
    public List<Planning> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Planning p) {
    String sql = "delete from planning where idMoy=? and idCir=? and dateD=? ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getIdMoy());
            ste.setInt(2, p.getIdCir());
            ste.setTime(3, p.getDateD());
            ste.executeUpdate();
            System.out.println("Planning a éte supprimer avec succées !!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // fiha khedma okhra khater modification khater fiha 3 cle etrangere 
     public void modifierPlannig(Time DateA, int NbPlace,Planning p) {
        String sql = "update planning set  DateA=? , NbPlace=? where idMoy=? and idCir=? and DateD=?" ;
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);     
              if (DateA != p.getDateA()){
            ste.setTime(1,DateA);
            } else{
                ste.setTime(1, p.getDateA());
            }
              if (NbPlace != p.getNbPlace()){
            ste.setInt(2,NbPlace);
            } else{
                ste.setInt(2, p.getNbPlace());
            }
           
            ste.setInt(3, p.getIdMoy());
            ste.setInt(4, p.getIdCir());
            ste.setTime(5, p.getDateD());
            ste.executeUpdate();
            System.out.println("moyenne de transport a éte Modifier avec succées !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
}
