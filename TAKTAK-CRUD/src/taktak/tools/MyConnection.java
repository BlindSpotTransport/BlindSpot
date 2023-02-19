/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21626
 */
public class MyConnection {
       private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/taktak";
        String user = "root";
        String pwd = "";
        public static MyConnection ct;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static MyConnection getInstance(){
        if(ct ==null)
            ct= new MyConnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
