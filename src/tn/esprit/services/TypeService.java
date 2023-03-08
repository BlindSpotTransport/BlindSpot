/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import tn.esprit.entity.Typeabn;
 import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author 21626
 */
public class TypeService {




    private Connection connection;

    public TypeService() {
        connection=MaConnection.getInstance().getCnx();;
    }
  

    // Create
 /*   public void add(Typeabn typeabn) throws SQLException {
        String sql = "INSERT INTO typeabn (idtypeA, dureeA, prixA) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, typeabn.getIdtypeA());
        statement.setString(2, typeabn.getDureeA());
        statement.setInt(3, typeabn.getPrixA());
        statement.executeUpdate();
    }
*/
    // Read
    public Typeabn findById(int id) throws SQLException {
        String sql = "SELECT * FROM typeabn WHERE idtypeA=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Typeabn typeabn = new Typeabn();
            typeabn.setIdtypeA(resultSet.getInt("idtypeA"));
            typeabn.setDureeA(resultSet.getString("dureeA"));
            typeabn.setPrixA(resultSet.getInt("prixA"));
            return typeabn;
        }
        return null;
    }

    public List<Typeabn> getAll(){
        List<Typeabn> typea = new ArrayList<>();
        try {
            String sql = "select * from typeabn";
            Statement ste = connection.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Typeabn t = new Typeabn(s.getInt("idtypeA"),
                        s.getString("dureeA"),s.getInt("prixA"));
                typea.add(t);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return typea;
    }

    // Update
    public void update(Typeabn typeabn) throws SQLException {
        String sql = "UPDATE typeabn SET dureeA=?, prixA=? WHERE idtypeA=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, typeabn.getDureeA());
        statement.setInt(2, typeabn.getPrixA());
        statement.setInt(3, typeabn.getIdtypeA());
        statement.executeUpdate();
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM typeabn WHERE idtypeA=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
            public boolean isUnique(Typeabn t) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM abonnement WHERE idtypeA = ? AND dureeA = ? AND prixA = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, t.getIdtypeA());
            stmt.setString(2, t.getDureeA());
            stmt.setInt(3, t.getPrixA());
           
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count == 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
    }
}
}
   

