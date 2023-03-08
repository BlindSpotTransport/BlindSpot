/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entity.Circuit;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceCircuit <C>{
     public void ajouterCircuit(Circuit c);
    public List<C> getAll();
    public List<C> findByNomC(String nomC);
    public void supprimerCircuit(Circuit s);
    public void modifierCircuit(String departC,String ariveeC,String nomC, Circuit c);
}
