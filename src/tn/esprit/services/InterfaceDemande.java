/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
import tn.esprit.entity.Demande;
import tn.esprit.entity.Station;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceDemande <D>{
    
     public void ajouterDemande(Demande d);
     public List<D> getAll();
     public void supprimerDemande(Demande d);
}
