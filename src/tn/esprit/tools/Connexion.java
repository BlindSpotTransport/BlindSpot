/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sbs
 */
public class Connexion {
    
    private static Connexion instance;
    private Connection cnx;
        
    private final String URL = "jdbc:mysql://localhost:3306/taktak-4";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Connexion() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Conncting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}

