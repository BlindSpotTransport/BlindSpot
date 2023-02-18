/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnection;

/**
 *
 * @author hp
 */
public class PersonneService implements NewInterface<Personne>{
    Connection cnx;
    String sql="";
    Statement ste;

    public PersonneService() {
       cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Personne t) {
//        try {
//            sql="insert into personne(nom,prenom,age) values ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()+"')";
//            ste = cnx.createStatement();
//            ste.executeUpdate(sql);
//        } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
//        }
          //tsaba9 requette baad valeur 
        try {
            String sql = "insert into personne(nom,prenom,age)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setInt(3, t.getAge());
            ste.executeUpdate();
            System.out.println(ste.executeUpdate()+"Personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    }
    

    @Override
    public void supprimer(Personne t) {
         sql = "delete from personne where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    @Override
    public List<Personne> afficher() {
       
        List<Personne> personnes = new ArrayList<>();
        try {
            String sql = "select * from personne";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Personne p = new Personne(s.getInt("id"),s.getInt("age"),
                        s.getString("nom"), s.getString("prenom"));
                personnes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    
}

    @Override
    public void modifier(Personne t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
