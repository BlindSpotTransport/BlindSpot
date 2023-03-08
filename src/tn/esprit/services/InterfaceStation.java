/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
import tn.esprit.entity.Station;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceStation <S>{
     public void ajouterStation(Station s);
    public List<S> getAll();
    public Station findByNom(String nomS);
     public List findByNomS(String nomS);
   // public Station findBy(String nomS);
    public void supprimerStation(Station s);
    public void modifierStation(Station s, int id);
    public int getID();
}
