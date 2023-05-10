/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author hp
 */
public class User {
    
    private int idU;
    private String nomU,prenomU,imagePU,mdpU,emailU, roleU ,permisU,createdAtU;
    private Adresse AdresseU;
    private  int telephoneU,cinU;
    
    private boolean  abonnéU;

    public User() {
    }

    public User(int idU, String nomU, String prenomU, String emailU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.emailU = emailU;
    }

    public User(int idU, String nomU, String prenomU, String imagePU, String mdpU, String emailU, String roleU, String permisU, Adresse AdresseU, int telephoneU, int cinU, String createdAtU, boolean abonnéU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.imagePU = imagePU;
        this.mdpU = mdpU;
        this.emailU = emailU;
        this.roleU = roleU;
        this.permisU = permisU;
        this.AdresseU = AdresseU;
        this.telephoneU = telephoneU;
        this.cinU = cinU;
        this.createdAtU = createdAtU;
        this.abonnéU = abonnéU;
    }
     public User(int idU, String nomU, String prenomU, String imagePU, String emailU, int IdAdresseU, int telephoneU ,String roleU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.imagePU = imagePU;
        this.emailU = emailU;
        this.AdresseU = AdresseU;
        this.telephoneU = telephoneU;
        this.roleU=roleU;
    }
     

    public User(int idU, String nomU, String prenomU, String imagePU, String emailU, int IdAdresseU, int telephoneU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.imagePU = imagePU;
        this.emailU = emailU;
        this.AdresseU = AdresseU;
        this.telephoneU = telephoneU;
    }
    
        public User(int idU, String nomU, String prenomU,  String emailU, int telephoneU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.emailU = emailU;
        this.telephoneU = telephoneU;
    }


  public User(String nom,String prenom,String role,String Email) {
                this.nomU = nomU;
        this.prenomU = prenomU;
    
        this.emailU = emailU;
        this.roleU = roleU;
       
    }




    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNomU() {
        return nomU;
    }

    public void setNomU(String nomU) {
        this.nomU = nomU;
    }

    public String getPrenomU() {
        return prenomU;
    }

    public void setPrenomU(String prenomU) {
        this.prenomU = prenomU;
    }

    public String getImagePU() {
        return imagePU;
    }

    public void setImagePU(String imagePU) {
        this.imagePU = imagePU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public void setMdpU(String mdpU) {
        this.mdpU = mdpU;
    }

    public String getEmailU() {
        return emailU;
    }

    public void setEmailU(String emailU) {
        this.emailU = emailU;
    }

    public String getRoleU() {
        return roleU;
    }

    public void setRoleU(String roleU) {
        this.roleU = roleU;
    }

    public String getPermisU() {
        return permisU;
    }

    public void setPermisU(String permisU) {
        this.permisU = permisU;
    }

    public Adresse getAdresseU() {
        return AdresseU;
    }

    public void setAdresseU(Adresse AdresseU) {
        this.AdresseU = AdresseU;
    }

  public int getIdAdresseU(){
  return getAdresseU().getIdAdresse();}

    public int getTelephoneU() {
        return telephoneU;
    }

    public void setTelephoneU(int telephoneU) {
        this.telephoneU = telephoneU;
    }

    public int getCinU() {
        return cinU;
    }

    public void setCinU(int cinU) {
        this.cinU = cinU;
    }

    public String getCreatedAtU() {
        return createdAtU;
    }

    public void setCreatedAtU(String createdAtU) {
        this.createdAtU = createdAtU;
    }

    public boolean isAbonnéU() {
        return abonnéU;
    }

    public void setAbonnéU(boolean abonnéU) {
        this.abonnéU = abonnéU;
    }

    @Override
    public String toString() {
        return "User{" + "idU=" + idU + ", nomU=" + nomU + ", prenomU=" + prenomU + ", imagePU=" + imagePU + ", mdpU=" + mdpU + ", emailU=" + emailU + ", roleU=" + roleU + ", permisU=" + permisU + ", createdAtU=" + createdAtU + ", AdresseU=" + AdresseU + ", telephoneU=" + telephoneU + ", cinU=" + cinU + ", abonn\u00e9U=" + abonnéU + '}';
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
        final User other = (User) obj;
        if (this.idU != other.idU) {
            return false;
        }
        if (this.telephoneU != other.telephoneU) {
            return false;
        }
        if (this.cinU != other.cinU) {
            return false;
        }
        if (this.abonnéU != other.abonnéU) {
            return false;
        }
        if (!Objects.equals(this.nomU, other.nomU)) {
            return false;
        }
        if (!Objects.equals(this.prenomU, other.prenomU)) {
            return false;
        }
        if (!Objects.equals(this.imagePU, other.imagePU)) {
            return false;
        }
        if (!Objects.equals(this.mdpU, other.mdpU)) {
            return false;
        }
        if (!Objects.equals(this.emailU, other.emailU)) {
            return false;
        }
        if (!Objects.equals(this.roleU, other.roleU)) {
            return false;
        }
        if (!Objects.equals(this.permisU, other.permisU)) {
            return false;
        }
        if (!Objects.equals(this.createdAtU, other.createdAtU)) {
            return false;
        }
        if (!Objects.equals(this.AdresseU, other.AdresseU)) {
            return false;
        }
        return true;
    }

   

   
      
}