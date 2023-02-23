/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Station;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceStation <S>{
     public void ajouterStation(Station s);
    public List<S> getAll();
    public List<S> findById(int id);
    public void supprimerStation(Station s);
    public void modifierStation(String nomS, Station s);
}