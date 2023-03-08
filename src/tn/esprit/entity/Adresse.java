/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Adresse {
     private int idAdresseU,NumPoste;
    private String region,cité,rue;

    public Adresse() {
    }

    public Adresse(int idAdresseU, int NumPoste, String region, String cité, String rue) {
        this.idAdresseU = idAdresseU;
        this.NumPoste = NumPoste;
        this.region = region;
        this.cité = cité;
        this.rue = rue;
    }

    public int getIdAdresse() {
        return idAdresseU;
    }

    public void setIdAdresse(int idAdresseU) {
        this.idAdresseU = idAdresseU;
    }

    public int getNumPoste() {
        return NumPoste;
    }

    public void setNumPoste(int NumPoste) {
        this.NumPoste = NumPoste;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCité() {
        return cité;
    }

    public void setCité(String cité) {
        this.cité = cité;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    @Override
    public String toString() {
        return "adresse{" + "idAdresseU=" + idAdresseU + ", NumPoste=" + NumPoste + ", region=" + region + ", cit\u00e9=" + cité + ", rue=" + rue + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Adresse other = (Adresse) obj;
        if (this.idAdresseU != other.idAdresseU) {
            return false;
        }
        if (this.NumPoste != other.NumPoste) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.cité, other.cité)) {
            return false;
        }
        if (!Objects.equals(this.rue, other.rue)) {
            return false;
        }
        return true;
    }
    
}
