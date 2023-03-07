/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import services.service_user;
import services.service_adresse;
import entities.User;
import entities.Adresse;
import tools.MaConnection;

/**
 *
 * @author user
 */
public class UsersSession {
     public static UsersSession getInstance() {
        return instance;
    }

    private static int idU;
    public static UsersSession instance;
    private static String email;
    private static String role,permis;
    private static String nom;
    private static String prenom;
    private static String password;
    private static String profilepicture;
     //private static Adresse AdresseU;
     //private static int IdAdresseU;
    private static int telephoneU,cinU;
    private static boolean  abonnéU;
     private static String createdAtU;
    //private static  Adresse idA;

    public static int getIdU() {
        return idU;
    }

    public static void setIdU(int idU) {
        UsersSession.idU = idU;
    }

    public static String getEmailU() {
        return email;
    }

    public static void setEmailU(String email) {
        UsersSession.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UsersSession.role = role;
    }

   public static String getNomU() {
        return nom;
    }

    public static void setNomU(String nom) {
        UsersSession.nom= nom;
    }

     public static String getPrenomU() {
        return prenom;
    }

   public static void setPrenomU(String prenom) {
        UsersSession.prenom = prenom;
    }

    public static String getMdpU() {
        return password;
    }

   public static void setMdpU(String password) {
        UsersSession.password = password;
    }

public static String getImagePU() {
        return profilepicture;
    }

    public static void setImagePU(String profilepicture) {
        UsersSession.profilepicture = profilepicture;
    }
     public static String getPermisU() {
        return permis;
    }

     public static void setPermisU(String permis) {
        UsersSession.permis = permis;
    }

//    public static Adresse getAdresseU() {
//        return AdresseU;
//    }
//
//     public static void setAdresseU(Adresse AdresseU) {
//       UsersSession.AdresseU = AdresseU;
//    }

//   public static int getIdAdresseU(){
//  return getAdresseU().getIdAdresse();}

    public static int getTelephoneU() {
        return telephoneU;
    }

     public static void setTelephoneU(int telephoneU) {
        UsersSession.telephoneU = telephoneU;
    }

   public static int getCinU() {
        return cinU;
    }

    public static void setCinU(int cinU) {
        UsersSession.cinU = cinU;
    }

     public static String getCreatedAtU() {
        return createdAtU;
    }

     public static void setCreatedAtU(String createdAtU) {
        UsersSession.createdAtU = createdAtU;
    }

     public static boolean isAbonnéU() {
        return abonnéU;
    }

    public static void setAbonnéU(boolean abonnéU) {
        UsersSession.abonnéU = abonnéU;
    }

    /**
     *
     * @return
     */
    
//
//    public static Adresse getIdA() {
//        return idA;
//    }
//
//    public static void setIdA(Adresse idA) {
//        UsersSession.idA = idA;
//    }
//    

   public UsersSession(int idU, String nom, String prenom, String profilepicture, String password, String email, String role, String permis,  int telephoneU, int cinU, String createdAtU, boolean abonnéU) {
        this.idU = idU;
        this.nom = nom;
        this.prenom = prenom;
        this.profilepicture = profilepicture;
        this.password = password;
        this.email = email;
        this.role = role;
        this.permis = permis;
        //this.AdresseU = AdresseU;
        this.telephoneU = telephoneU;
        this.cinU = cinU;
        this.createdAtU = createdAtU;
        this.abonnéU = abonnéU;
    }
 public static void setInstance(UsersSession instance) {
        UsersSession.instance = instance;
    }

    public static UsersSession getInstance(int id, String email, String role, String nom, String prenom, String profilepicture) {
        if (instance == null) {
            instance = new UsersSession( idU,  nom,  prenom,  profilepicture,  password,  email, role,  permis  ,telephoneU,cinU,createdAtU,  abonnéU);
        }


        return instance;

    }
      public static void cleanUserSession() {
        idU = 0;
        email = null;
        role = null;
        nom = null;
        prenom = null;
        profilepicture = null;
        permis=null;
        cinU=0;
        telephoneU=0;
        //AdresseU=null;
        createdAtU=null;
        password=null;
        abonnéU=false;
        instance = null;
    }
    
       public static void  addUserLogin(ResultSet userRow) {
        try {
          //String requete = "INSERT into userlogins(id_user) values(?) ";
           //String requete2 ="Select* from utilisateur where idU=?";
            //PreparedStatement pst = MaConnection.getInstance().getCnx().prepareStatement(requete);
            //PreparedStatement pst2 = MaConnection.getInstance().getCnx().prepareStatement(requete2);
//            pst.setInt(1, userRow.getInt("idU"));
//            pst.executeQuery();
            //pst.close();
            System.out.println("Added user session!");
            
            idU = userRow.getInt("idU");
            
//            nom = userRow.getString("nomU");
//            prenom = userRow.getString("prenomU");
            //IdAdresseU=userRow.getInt("idAdresse");
//            telephoneU=userRow.getInt("telephoneU");
//            email = userRow.getString("emailU");
            role = userRow.getString("RoleU");
//            createdAtU = userRow.getString("createdAtU");
//            cinU=userRow.getInt("cinU");
//            profilepicture = userRow.getString("imagePU");
//            permis= userRow.getString("permisU");
//            abonnéU =userRow.getBoolean("abonnéU");
//            password = userRow.getString("mdpU");
//           
            

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            
        }

    }
  @Override
    
    public String toString() {
        return "User{" + "idU=" + idU + ", nomU=" + nom + ", prenomU=" + prenom + ", imagePU=" + profilepicture + ", mdpU=" + password + ", emailU=" + email + ", roleU=" + role + ", permisU=" + permis + ", createdAtU=" + createdAtU  + ", telephoneU=" + telephoneU + ", cinU=" + cinU + ", abonnéU=" + abonnéU + '}';
    }
}
