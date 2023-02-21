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
public class AbnService implements NewInterface<Abonnement> {
    
    Connection cnx;
    
    public AbnService() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public boolean isUnique(Abonnement t) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM abonnement WHERE idU = ? AND moyTrA = ? AND dateA = ?  AND etudiantA = ? AND redEtA = ? AND redEvA = ?";
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, t.getIdU());
            stmt.setString(2, t.getMoyTrA());
            stmt.setDate(3, t.getDateA());
            //stmt.setString(4, t.getImageA());
            stmt.setBoolean(4, t.isEtudiantA());
            stmt.setInt(5, t.getRedEtA());
            stmt.setInt(6, t.getRedEvA());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count == 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
    }
}


    @Override
    public void add(Abonnement t) {
        if (isUnique(t)){
            try {
                String sql = "INSERT INTO abonnement(idU, moyTrA, etudiantA, redEtA, redEvA) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ste = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ste.setInt(1, t.getIdU());
                ste.setString(2, t.getMoyTrA());
                //ste.setDate(3, t.getDateA());
                //ste.setString(3, t.getImageA());
                ste.setBoolean(3, t.isEtudiantA());
                ste.setInt(4, t.getRedEtA());
                ste.setInt(5, t.getRedEvA());
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

    public void add2(Abonnement t) {
        if (isUnique(t)){
            try {
                String sql = "INSERT INTO abonnement(idU, moyTrA,etudiantA, redEtA, redEvA) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ste = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ste.setInt(1, t.getIdU());
                ste.setString(2, t.getMoyTrA());
                //ste.setDate(3, t.getDateA());
                //ste.setString(3, t.getImageA());
                ste.setBoolean(3, t.isEtudiantA());
                ste.setInt(4, t.getRedEtA());
                ste.setInt(5, t.getRedEvA());
                ste.executeUpdate();
                ResultSet rs = ste.getGeneratedKeys();
                if (rs.next()) {
                    t.setIdA(rs.getInt(1));  
        }
                System.out.println("Abonnement ajouté avec succès. ID généré: " + t.getIdA());
            } catch (SQLException ex) {System.out.println(ex.getMessage());}
        }
        else 
        {System.out.println("Abonnement existe déjà");}
}

    @Override
    public List<Abonnement> getAll() {
        List<Abonnement> abn = new ArrayList<>();
        try {
            String sql = "select * from abonnement";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Abonnement a = new Abonnement(s.getInt("idA"), s.getInt("idU"),s.getString("moyTrA"), s.getDate("dateA"),
                        s.getString("imageA"),s.getBoolean("etudiantA"), s.getInt("redEtA"),s.getInt("redEvA"));
                abn.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return abn;
    }
//complete this part
 //   @Override
     public Abonnement findById(int idA) {
    Abonnement a = null;
    try {
        String sql = "SELECT * FROM abonnement WHERE idA = " + idA ;
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        if (s.next()) {
            a = new Abonnement(s.getInt("idA"), s.getInt("idU"),
                    s.getString("moyTrA"), s.getDate("dateA"), s.getString("imageA"),s.getBoolean("etudiantA"),s.getInt("redEtA"),s.getInt("redEvA"));
        }
        s.close();
        ste.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return a;
}

    @Override
    public void delete(Abonnement t) {
        String sql = "delete from abonnement where idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdU());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void delete(int id) {
        String sql = "delete from abonnement where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 //   @Override
    public void updateMoy(String moy,Abonnement t) {
        String sql = "update abonnement set moyTrA=? where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, moy);
            ste.setInt(2,t.getIdA());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void updateImg(String img,Abonnement t) {
        String sql = "update abonnement set imageA=? where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, img);
            ste.setInt(2,t.getIdA());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void updateEtud(boolean e,Abonnement t) {
        String sql = "update abonnement set etudiantA=? where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setBoolean(1,e);
            ste.setInt(2,t.getIdA());
            ste.executeUpdate();
            updateRedEt(e,t);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void updateRedEt(boolean e,Abonnement t) {
        String sql = "update abonnement set redEtA=? where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            if (e){ste.setInt(1, 20);}
            else{ste.setInt(1, 0);}
            ste.setInt(2,t.getIdA());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        public void updateRedEv(int rd,Abonnement t) {
        String sql = "update abonnement set redEvA=? where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, rd);
            ste.setInt(2,t.getIdA());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  /*  @Override
    public void update(Abonnement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}