/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import java.sql.Connection;
import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import taktak.tools.MyConnection;
import taktak.entity.Abonnement;

/**
 *
 * @author 21626
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import taktak.entity.Typeabn;

public class AbnService {



    private Connection connection;

    public AbnService() {
        connection=MyConnection.getInstance().getCnx();;
    }

    public void add(Abonnement t) {
        if (isUnique(t)){
            try {
                String sql = "INSERT INTO abonnement(moyTrA,dateExpA,idtypeA, etudiantA, redEtA, redEvA) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement ste = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                ste.setString(1, t.getMoyTrA());
                ste.setDate(2, java.sql.Date.valueOf(t.getDateExpA()));
              
               ste.setInt(3, t.getIdtypeA());
                ste.setBoolean(4, t.isEtudiantA());
                ste.setInt(5, t.getRedEtA());
                ste.setInt(6, t.getRedEvA());
                ste.executeUpdate();
                ResultSet rs = ste.getGeneratedKeys();
                if (rs.next()) {
                    t.setIdA(rs.getInt(1));  
        }
                System.out.println("Abonnement ajouté avec succès. ID généré: " + t.getIdA());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
        else 
        {System.out.println("Abonnement existe déjà");}
}

 /*   public Abonnement read(int idA) throws SQLException {
        String query = "SELECT * FROM abonnement WHERE idA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idA);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Abonnement abonnement = new Abonnement();
                    abonnement.setIdA(result.getInt("idA"));
                    abonnement.setIdU(result.getInt("idU"));
                    abonnement.setMoyTrA(result.getString("moyTrA"));
                    abonnement.setDateA(result.getDate("dateA"));
                    abonnement.setDateExpA(result.getDate("dateExpA"));
                    abonnement.setIdtypeA(result.getInt("idtypeA"));
                    abonnement.setEtudiantA(result.getBoolean("etudiantA"));
                    abonnement.setRedEtA(result.getInt("redEtA"));
                    abonnement.setRedEvA(result.getInt("redEvA"));

                    return abonnement;
                } else {
                    return null;
                }
            }
        }
    }*/

    public void update(Abonnement a)  {
        String query = "UPDATE abonnement SET  moyTrA = ?,dateExpA=?, idtypeA = ?, etudiantA = ?, redEtA = ?, redEvA = ? WHERE idA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, a.getMoyTrA());
            
            stmt.setDate(2, java.sql.Date.valueOf(a.getDateExpA()));
            stmt.setInt(3, a.getIdtypeA());

            
            stmt.setBoolean(4, a.isEtudiantA());
            stmt.setInt(5, a.getRedEtA());
            stmt.setInt(6, a.getRedEvA());
             stmt.setInt(7, a.getIdA());



            stmt.executeUpdate();
            System.out.println("Abonnement modifié avec succès. ID : " + a.getIdA());
        } catch (SQLException ex) {
            Logger.getLogger(AbnService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Abonnement t) {
        String sql = "delete from abonnement where idA=?";
        try {
            PreparedStatement ste = connection.prepareStatement(sql);
            ste.setInt(1, t.getIdA());
            ste.executeUpdate();
            ste.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public List<Abonnement> getAll() {
        List<Abonnement> abn = new ArrayList<>();
        try {
            String sql = "select * from abonnement";
            Statement ste = connection.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Abonnement a = new Abonnement(s.getInt("idA"), s.getInt("idU"),s.getString("moyTrA"), s.getDate("dateA"),
                        s.getDate("dateExpA").toLocalDate(),s.getInt("idtypeA"),s.getBoolean("etudiantA"), s.getInt("redEtA"),s.getInt("redEvA"));
                abn.add(a);

            }        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abn;
    }
   
 /*  public Date calculteDateExpA(Abonnement a) {
    //TypeService type = type.findById(abonnement.getIdtypeA());
    LocalDate dateA = a.getDateA().toLocalDate();
    LocalDate dateExpA = null;
    switch (a.getIdtypeA()) {
        case 1:
            dateExpA = dateA.plusMonths(1);
            break;
        case 2:
            dateExpA = dateA.plusMonths(6);
            break;
        case 3:
            dateExpA = dateA.plusYears(1);
            break;
      
    }
    return Date.valueOf(dateExpA);
}*/
   
   
       public boolean isUnique(Abonnement t) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM abonnement WHERE idU = ? AND moyTrA = ? AND dateA = ? AND idtypeA=? AND etudiantA = ? AND redEtA = ? AND redEvA = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, t.getIdU());
            stmt.setString(2, t.getMoyTrA());
            stmt.setDate(3, t.getDateA());
            stmt.setInt(4, t.getIdtypeA());
            stmt.setBoolean(5, t.isEtudiantA());
            stmt.setInt(6, t.getRedEtA());
            stmt.setInt(7, t.getRedEvA());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count == 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
    }

}
   
    public Abonnement findById(int idA) {
        Abonnement a = null;
        try {
            String sql = "SELECT * FROM abonnement WHERE idA = " + idA ;
            Statement ste = connection.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if (s.next()) {
                a = new Abonnement(s.getInt("idA"), s.getInt("idU"), s.getString("moyTrA"), s.getDate("dateA"),s.getDate("dateExpA").toLocalDate(), s.getInt("idtypeA"),s.getBoolean("etudiantA"),s.getInt("redEtA"),s.getInt("redEvA"));
        }
        s.close();
        ste.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return a;
}
    public void deleteExpiredAbonnements() {
    try {
        String sql = "DELETE FROM abonnement WHERE dateExpA < ?";
        PreparedStatement ste = connection.prepareStatement(sql);
        ste.setObject(1, LocalDate.now());
        int rowsAffected = ste.executeUpdate();
        System.out.println(rowsAffected + " expired subscriptions deleted.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

}