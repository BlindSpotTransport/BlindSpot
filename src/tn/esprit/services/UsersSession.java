/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tn.esprit.entity.User;
import tn.esprit.entity.Adresse;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author user
 */
public class UsersSession {
     public static UsersSession getInstance() {
        return instance;
    }

    private static int idU,tel,cin;
    public static UsersSession instance;
    private static String email;
    private static String role;
    private static String name;
    private static String lastname;
    private static String password;
    private static String profilepicture;
    private static int abonneUser;
    private static User user ;
    public static int getAbonneUser() {
        return abonneUser;
    }

    //private static  Adresse idA;

    public static int getIdU() {
        return idU;
    }

    public static void setIdU(int idU) {
        UsersSession.idU = idU;
    }
     public static int getTelU() {
        return tel;
    }

    public static void setTelU(int tel) {
        UsersSession.tel = tel;
    }

    public static int getTel() {
        return tel;
    }

    public static void setTel(int tel) {
        UsersSession.tel = tel;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UsersSession.user = user;
    }

    public static void setAbonneUser(int abonneUser) {
        UsersSession.abonneUser = abonneUser;
    }
    public static int getCin() {
        return cin;
    }

    public static void set(int cin) {
        UsersSession.cin = cin;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UsersSession.email = email;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UsersSession.role = role;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UsersSession.name = name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        UsersSession.lastname = lastname;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UsersSession.password = password;
    }

    public static String getProfilepicture() {
        return profilepicture;
    }

    public static void setProfilepicture(String profilepicture) {
        UsersSession.profilepicture = profilepicture;
    }


   public UsersSession(int idU,int tel,int cin, String role, String email, String name, String lastname, String profilepicture, int abonneU) {
        this.idU = idU;
        this.cin = cin;
        this.tel=tel;
        this.email = email;
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.profilepicture = profilepicture;
        this.abonneUser=abonneU;
    }
 public static void setInstance(UsersSession instance) {
        UsersSession.instance = instance;
    }

    public static UsersSession getInstance(int id,int tel,int cin, String email, String role, String name, String lastname, String profilepicture,int abonneU) {
        if (instance == null) {
            instance = new UsersSession(id, tel,cin,email, role, name, lastname, profilepicture,abonneU);
        }


        return instance;

    }
      public static void cleanUserSession() {
        idU = 0;
        cin=0;
        tel=0;
        email = null;
        role = null;
        name = null;
        lastname = null;
        profilepicture = null;
        
        instance = null;
    }
    
       public static void  addUserLogin(ResultSet userRow) {
        try {
//          String requete = "INSERT into userlogins(id_user) values(?) ";
//            PreparedStatement pst = MaConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.setInt(1, userRow.getInt("id"));
//            pst.execute();
//            pst.close();
           
            cin=userRow.getInt("cinu");
            idU = userRow.getInt("id");
            tel=userRow.getInt("telephoneu");
            System.out.println("Added Id user session!");
            email = userRow.getString("email");
            role = userRow.getString("roleu");
            name = userRow.getString("nomu");
            lastname = userRow.getString("prenomu");
            password = userRow.getString("password");
            profilepicture = userRow.getString("imagepu");
            abonneUser = userRow.getInt("abonneu");
            

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            
        }

    }
  @Override
    public String toString() {
        return "Logged in as {" +
                "Email='" + email + '\'' +
                ", Role ='" + role + '\'' +
                ", Name ='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }  
}
