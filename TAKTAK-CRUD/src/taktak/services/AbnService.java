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


    @Override
    public void add(Abonnement t) {
         try {
            String sql = "insert into abonnement(idA,idU,moyTrA,dateA,imageA,etudiantA,redEtA,redEvA)"
                    + "values (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdA());
            ste.setInt(2, t.getIdU());
            ste.setString(3,t.getMoyTrA());
            ste.setDate(4, (Date) t.getDateA());
            ste.setString(5,t.getImageA());
            ste.setBoolean(6, t.isEtudiantA());
            ste.setInt(7, t.getRedEtA());
            ste.setInt(8, t.getRedEvA());
            ste.executeUpdate();
            System.out.println("abonnement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

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
         String sql = "delete from abonnement where idA=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdA());
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