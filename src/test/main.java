/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import com.sun.xml.internal.bind.v2.TODO;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entities.User;
import entities.Adresse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import static java.util.Calendar.AM;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.service_user;
import services.service_adresse;
import tools.MaConnection;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          service_user u = new service_user() {
             
          };
          service_adresse A= new service_adresse(){
          };
        Date thisDate = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss a");
        String myString = sdf.format(thisDate);
        //System.out.println(); 
   Adresse a2 = new Adresse(2,5,"manouba","manouba","rue Amal");
   Adresse a3 = new Adresse(3,10,"Tunis","marsa","rue Amal");
   A.ajouter(a2);
        //User u1 = new User(12, "chabchoub", "karim","url","3gg3","c.g@gmail.com","admin","urlpermi",a2,25847123,1503258, myString,false);
     
        //User u2 = new User(11, "hattab", "halim","url","3gg3","r.g@gmail.com","chauffeur","urlpermi",a2,25841123,150222, myString,false);
         //User u4 = new User(15, "ghribi", "chaima","url","123aaaaa777","chaima.ghribi@gmail.com","client","urlpermi",a2,25847123,1522500, myString,false);
         //User u6 = new User(45, "rihab", "test","url","123aa","chaima.chaima@gmail.com","chauffeur","urlpermi",a3,25841123,111111, myString,true);
         User u6 = new User(55, "rachida", "ghribi","url","*5@5j","chacha.chacha@gmail.com","admin","None",a2,25841123,88775, myString,false);
         User u4 = new User(15, "gh", "ima","url","123aaaaa777","chaima.ghribi@gmail.caaa","client","urlpermi",a2,25847123,666666666, myString,false);
          User u8 = new User(20, "gh", "ima","url","123aaaaa777","aaaa.ghribi@gppp.caaa","client","urlpermi",a2,25847123,66666886, myString,false);
          User u9 = new User(12, "gh", "ima","url","123aaaaa777","aaaa.aaaa@gppp.caaa","client","urlpermi",a2,25847123,6644886, myString,false);
         User us = new User(10, "mama", "ghribi","url","chaima123","chacha.chacha@gmail.com","admin","None",a2,25841123,88775, myString,false);

         //u.ajouter(u9);
         String role =service_user.encryptMdp("aya123");
         System.out.println(role);
         //u.ajouter(u8);
        //u.modifier("benouirane","montassar","url","3333","monta.monta@gmail.com",a3,2145562,u6);
        //u.supprimer(u1);
        //System.out.println(u.getAll());
        //System.out.println(u.findById(15));
                

          
       
      //A.modifierA(20,"ariana","ariana_soghra" ,"hedi thameur", a1);
     //A.supprimer(a2);
     //System.out.println(A.findById(1));
     //System.out.println(u.findById(15));
    }
}
