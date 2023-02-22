/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import taktak.entity.Abonnement;
import taktak.entity.TypeAbn;
import taktak.tools.MyConnection;

/**
 *
 * @author 21626
 */
public class TypeService implements NewInterface<TypeAbn> {
    
    Connection cnx;

    public TypeService() {
        cnx = MyConnection.getInstance().getCnx();
    }


        public boolean isUnique(TypeAbn t) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM abonnement WHERE idU = ? AND dureeA = ? AND prixA = ? dateExpA = ?";
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, t.getIdU());
            stmt.setString(2, t.getDureeA());
            stmt.setInt(3, t.getPrixA());
            stmt.setDate(4, (Date) t.getDateExpA());
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
    public void add(TypeAbn t) {
        try {
            String sql = "insert into typeabn(idA,idU,dureeA,prixA,dateExpA)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdA());
            ste.setInt(2, t.getIdU());

            ste.setString(3, t.getDureeA());
            ste.setInt(4, t.getPrixA());
            ste.setDate(5, (Date) t.getDateExpA());
            ste.executeUpdate();
            System.out.println("type abonnement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<TypeAbn> getAll() {
        List<TypeAbn> typea = new ArrayList<>();
        try {
            String sql = "select * from typeabn";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                TypeAbn t = new TypeAbn(s.getInt("idA"), s.getInt("idU"),
                        s.getString("dureeA"),s.getInt("prixA"), s.getDate("dateExpA"));
                typea.add(t);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return typea;
    }
   //complete this part
  //  @Override
    public TypeAbn findById(int idA, int idU) {
    TypeAbn t = null;
    try {
        String sql = "SELECT * FROM typean WHERE idA = " + idA + " AND idU = " + idU;
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        if (s.next()) {
            t = new TypeAbn(s.getInt("idA"), s.getInt("idU"),
                    s.getString("dureeA"), s.getInt("prixA"), s.getDate("dateExpA"));
        }
        s.close();
        ste.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return t;
}


    

//complex primary key?
    @Override
    public void delete(TypeAbn t) {
        String sql = "delete from typeabn where idA=? and idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getIdA()); 
            ste.setInt(2, t.getIdU());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

 //   @Override
    public void updateDuree(String d,TypeAbn t) {

        String sql = "update typeabn set dureeA=? where idA=? and idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, d);
            ste.setInt(2,t.getIdA());
            ste.setInt(3,t.getIdU());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
        public void updateDateExpA(String d,TypeAbn t) {

        String sql = "update typeabn set dateExpA=? where idA=? and idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, d);
            ste.setInt(2,t.getIdA());
            ste.setInt(3,t.getIdU());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
        
        public void updatePrixA(int p,TypeAbn t) {

        String sql = "update typeabn set prixA=? where idA=? and idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p);
            ste.setInt(2,t.getIdA());
            ste.setInt(3,t.getIdU());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
        
        
 


}
