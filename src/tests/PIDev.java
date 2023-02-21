/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Circuit;
import entities.Station;
import services.ServiceCircuit;
import services.ServiceStation;

/**
 *
 * @author ASUS
 */
public class PIDev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         ServiceStation SS = new ServiceStation();
        Station s1 = new Station(1,"Barcelone","Grand Tunis");
        Station s2 = new Station(2,"passage","Grand Tunis");
        Station s3 = new Station(3,"10 Decembre","Ariana");
        
      // SS.ajouterStation(s1);
      // SS.ajouterStation(s2);
     // SS.ajouterStation(s3);
     // SS.supprimerStation(s1);
      
     // SS.modifierStation("farhat hached", s3);
    
       // System.out.println(SS.getAll());
     //   System.out.println(SS.findById(3));
        
        ServiceCircuit SC = new ServiceCircuit();
        Circuit c1 = new Circuit(1,"Passage","Ariana");
        Circuit c2 = new Circuit(2,"Barcelone","Manouba");
        Circuit c3 = new Circuit(3,"Barcelone","Ben arous");
        Circuit c4 = new Circuit(12,"Barcelone","La goulette");
        
       // SC.ajouterCircuit(c4);
        //SC.ajouterCircuit(c2);
       // SC.ajouterCircuit(c3);
       // SC.ajouterCircuit(c4);
       //SC.supprimerCircuit(c4);
     // SC.modifierCiruit("Barcelone", "Manouba", c1);
        System.out.println(SC.getAll());
      // System.out.println(SC.findById(4));
       
       
  
       
        
        
       
        
    }
    
}
