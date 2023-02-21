/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.tests;


import java.sql.Date;
import taktak.entity.Abonnement;
import taktak.entity.TypeAbn;
import taktak.services.AbnService;
import taktak.services.TypeService;
import taktak.tools.MyConnection;
/**
 *
 * @author 21626
 */
public class TAKTAK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AbnService as= new AbnService ();
        TypeService ts= new TypeService ();
        //Date d= new Date(2005-1900,05,05);
 
       // Date d = java.sql.Date.valueOf("2022-02-14");
       Date d = java.sql.Date.valueOf("2005-06-05");
       Abonnement a= new Abonnement(1,"Bus",true,20,0);
       TypeAbn t=new TypeAbn(a.getIdA(),a.getIdU(),"Mensuel",30,d);
       as.add(a);
       // as.updateMoy("Métro", a);
       //as.delete(a);
       //System.out.println(as.getAll());
       //as.updateEtud(true, a);
       //as.updateRedEv(50,a);
       //System.out.println(as.findById(1));
       // ts.add(t);
       //ts.updateDuree("Semestriel", t);
       //ts.updatePrixA(50, t);
       //ts.delete(t);
    }
   
    
}
