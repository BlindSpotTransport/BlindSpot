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
import java.util.ArrayList;
import tn.esprit.entity.Moyenstransport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author pc
 */
public class MoyenstransportService implements NewInterface<Moyenstransport>{
    Connection cnx;

    public MoyenstransportService() {
      cnx = MaConnection.getInstance().getCnx();
    }

    
    @Override
    public void ajouter(Moyenstransport m) {
    try{
        String sql = "INSERT INTO `moyenstransport`(`idMoy`,`type`, `matricule`, `capacite`, `numMoy`) VALUES (?,?,?,?,?) ";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, m.getIdMoy());
        ste.setString(2, m.getType());
        ste.setString(3, m.getMatricule());
        ste.setInt(4, m.getCapacite());
        ste.setString(5, m.getNumMoy());
        ste.executeUpdate();        
        System.out.println("Moyenne de transport ajoutée avec succées !");
    } catch (SQLException ex) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText("il y'a une erreur !");
        alert.showAndWait();
            
    }

    }
    
    
    @Override
    public List<Moyenstransport> getAll() {
        List<Moyenstransport> MoyensTran = new ArrayList<>();
        try{
            String sql="select * from moyenstransport ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Moyenstransport M = new Moyenstransport(s.getInt(1),s.getInt("capacite"),s.getString("type")
                        ,s.getString("matricule"),s.getString("numMoy"));
                MoyensTran.add(M);
            }
            
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
    }
        return MoyensTran;
        
    }

    @Override
    public void supprimer(Moyenstransport m) {
        String sql = "delete from moyenstransport where idMoy=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, m.getIdMoy());
            ste.executeUpdate();
            System.out.println("moyenne de transport a éte supprimer avec succées !!");

        } catch (SQLException ex) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("error");
           alert.setHeaderText(null);
           alert.setContentText(ex.getMessage());
           alert.showAndWait();
        }

    }

    
    public void modifierTransport(int capacite,String type, String matricule ,String numMoy,Moyenstransport m) {
        String sql = "update moyenstransport set capacite=? , type=? , matricule=? , numMoy=? where idMoy=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            if (capacite != m.getCapacite()){
            ste.setInt(1, capacite);
            }else{
                ste.setInt(1,m.getCapacite());
            }
            
            if (type != m.getType()){
            ste.setString(2,type );
            } else{
                ste.setString(2, m.getType());
            }
            
             if (matricule != m.getMatricule()){
            ste.setString(3,matricule );
            } else{
                ste.setString(3, m.getMatricule());
            }
             
             
              if (numMoy != m.getNumMoy()){
            ste.setString(4,numMoy);
            } else{
                ste.setString(4, m.getNumMoy());
            }              
            ste.setInt(5, m.getIdMoy());
            ste.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Moyenne de transport a ete modifié avec succès !");
            alert.showAndWait();
        
            System.out.println("moyenne de transport a éte Modifier avec succées !!");
        } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                
        }    
    }
    
    
    /* public void modifierTransport(String type ,Moyenstransport m) {
        String sql = "update moyenstransport set type=? where idMoy=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, type);
            ste.setInt   (2, m.getIdMoy());
            ste.executeUpdate();
            System.out.println("moyenne de transport a éte Modifier avec succées !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    */

    @Override
    public List<Moyenstransport> findById(int id) {
    List<Moyenstransport> MoyensTran = new ArrayList<>();
    try{
            String sql="select * from moyenstransport where idMoy ='"+id+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Moyenstransport M = new Moyenstransport(s.getInt(1),s.getInt("capacite"),s.getString("type")
                        ,s.getString("matricule"),s.getString("numMoy"));
                MoyensTran.add(M);
            }
            
            } catch (SQLException ex) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText(null);
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
    }
        return MoyensTran;
    }
   public ObservableList<String> GetByString(String ch){
            
            ObservableList<String> listeCH = FXCollections.observableArrayList();
        try {
           String SQLgetListS = "select  m."+ch+" as ch from moyenstransport as m";
           
           Statement ste = cnx.createStatement();
       
          ResultSet c= ste.executeQuery(SQLgetListS);
          while(c.next()){
              String Message = c.getString("ch");
                listeCH.add(Message);
          }
             } catch (SQLException ex) {
            Logger.getLogger(MoyenstransportService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return listeCH ;
   
   }  
    
   public boolean RechercherString(String CH, String Col){
       Boolean  result = false ;
       for (int i=0 ; i<GetByString(Col).size();i++){
           if (CH.equals(GetByString(Col).get(i).toString())){
               result = true;
               return result;
           }
       }
       return result;
  
   }
        public List<String> getType() {
        List<String> list = new ArrayList<>();
        try {
            String req = "SELECT distinct type FROM `moyenstransport`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String c = new String (rs.getString("type"));
                list.add(c);
               // System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
    return list;
    }
    

    
}
