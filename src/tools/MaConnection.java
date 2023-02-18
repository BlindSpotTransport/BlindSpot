/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class MaConnection {
    
    
        private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/taktak";
        String user = "root";
        String pwd = "";
        public static MaConnection ct;

    private MaConnection() {
        
        try {
        // 1 etape conx 
        // 2 etape nemchi librairie w add  mysql jdbc driver
        //ki tech3el bel a7mar lazem try w catch  5eter 5arjatli exception
         cnx = DriverManager.getConnection(url, user, pwd);
          System.out.println("conx etablie !!");
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());   
        }
    }
    
    public static MaConnection getInstance(){
        if(ct ==null)
            ct= new MaConnection();
        return ct;
    }  
    
    public Connection getCnx() {
        return cnx;
    }
        
        
        
        
        
        
        
    
}
