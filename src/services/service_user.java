/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author user
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.User;
import entities.adresse;
import javax.swing.JOptionPane;
import tools.MaConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public abstract class service_user implements InterfaceService<User> {
    Connection cnx;
    Statement ste;

    public service_user() {
        cnx = MaConnection.getInstance().getCnx();
    } //assure la connectivité
      public static String encryptMdp(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


     public boolean validerEmail(String email) {
    String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    return email.matches(mail);
}
      public static boolean verifierEmail(String email)  {
        try {
            String requete = "SELECT idU from  utilisateur where emailU=?";
            PreparedStatement pst = MaConnection.getInstance().getCnx().prepareStatement(requete);
            int Login =0;
            ResultSet rs ;
            pst.setString(1, email);
            rs=pst.executeQuery();
            while(rs.next()){
                Login = rs.getInt("idU");
            }
            rs.close();
            pst.close();
            if (Login>0){
                System.out.println("Error user same email found !");
                
                JOptionPane.showMessageDialog(null, "email not valid");

                return false;
            }else {
                System.out.println("Email is valid");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
            return false;
            
        }
        
     }
    @Override
    public void ajouter(User u) {
        
    String encryptedPassword = encryptMdp(u.getMdpU());
     try {
            String sql = "INSERT INTO `utilisateur`(`idU`, `nomU`, `prenomU`, `idAdresseU`, `telephoneU`, `emailU`, `roleU`, `createdAtU`, `cinU`, `imagePU`, `permisU`, `abonnéU`, `mdpU`) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getIdU());
            ste.setString(2, u.getNomU());
            ste.setString(3, u.getPrenomU());
            ste.setInt(4, u.getAdresseU().getIdAdresse());
            ste.setInt(5, u.getTelephoneU());
            if (validerEmail(u.getEmailU())){
               //System.out.println("email valide ");
                 if(verifierEmail(u.getEmailU())){
                  ste.setString(6, u.getEmailU());
                 }
                }
            else {
                System.out.println("email non valide");
            }
            ste.setString(7, u.getRoleU());
            ste.setString(8, u.getCreatedAtU());
            ste.setInt(9, u.getCinU());
            ste.setString(10, u.getImagePU());
            ste.setString(11, u.getPermisU());
            ste.setBoolean(12, u.isAbonnéU());
            ste.setString(13, encryptedPassword);
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null, "Utilisateur ajoutée");
            //System.out.println("Utilisateur ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    @Override
    public void supprimer(User u) {
        String sql = "delete from utilisateur where IdU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getIdU());
            ste.executeUpdate();
            System.out.println("utilisateur supprimé avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
   public void modifier(String nom,String prenom,String image,String mdp,String Email,adresse  Adresse,int telephone,User u) {
    int idAdresse;
    idAdresse=Adresse.getIdAdresse();
    
    String encryptedPassword = encryptMdp(mdp);
       
       String sql = "UPDATE utilisateur SET nomU=?, prenomU=?, imagePU=?, mdpU=?, emailU=?, idAdresseU=?, telephoneU=? WHERE idU=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
             ste.setString(2, prenom);
              ste.setString(3, image);
               ste.setString(4,encryptedPassword);
                ste.setString(5, Email);
                 ste.setInt(6,idAdresse);
                  ste.setInt(7, telephone);
            ste.setInt(8,u.getIdU());
            ste.executeUpdate();
            System.out.println("utilisateur a éte modifier avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
   
   @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u = new User(s.getInt(1), 
                        s.getString("nomU"), s.getString("prenomU"),s.getString("imagePU"),s.getString("emailU"),s.getInt("idAdresseU"),s.getInt("telephoneU"));
                users.add(u);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }
   public List<User> findById(int id ) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where idU='"+id+"'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                User u = new User(s.getInt(1), 
                        s.getString("nomU"), s.getString("prenomU"),s.getString("imagePU"),s.getString("emailU"),s.getInt("idAdresseU"),s.getInt("telephoneU"));
                users.add(u);
 

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
        
     
    }
    
}