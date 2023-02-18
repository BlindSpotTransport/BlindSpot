/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class adresse {
     private int idAdresseU,NumPoste;
    private String region,cité,rue;

    public adresse() {
    }

    public adresse(int idAdresseU, int NumPoste, String region, String cité, String rue) {
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
    
}
