/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Circuit;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InterfaceCircuit <C>{
     public void ajouterCircuit(Circuit c);
    public List<C> getAll();
    public List<C> findById(int id);
    public void supprimerCircuit(Circuit s);
    public void modifierCircuit(String departC,String ariveeC, Circuit c);
}
