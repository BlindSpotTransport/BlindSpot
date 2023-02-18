/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Utilisateur {
    
    private int idU;
    private String nomU,prenomU,imagePU,mdpU, emailU, roleU ,permisU;
    private int idAdresseU ;
    private  int telephoneU,cinU;
    private Date createdAtU;
    private int abonnéU;
   

    public Utilisateur(int idU, String nomU, String prenomU, int idAdresseU, String imagePU, String mdpU, String emailU, String roleU, String permisU, int telephoneU, int cinU, Date createdAtU, int abonnéU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.idAdresseU = idAdresseU ;
        this.imagePU = imagePU;
        this.mdpU = mdpU;
        this.emailU = emailU;
        this.roleU = roleU;
        this.permisU = permisU;
        this.telephoneU = telephoneU;
        this.cinU = cinU;
        this.createdAtU = createdAtU;
        this.abonnéU = abonnéU;
    }
    
      public Utilisateur( String nomU, String prenomU, int idAdresseU, String imagePU, String mdpU, String emailU, String roleU, String permisU, int telephoneU, int cinU, Date createdAtU, int abonnéU) {
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.idAdresseU = idAdresseU ;
        this.imagePU = imagePU;
        this.mdpU = mdpU;
        this.emailU = emailU;
        this.roleU = roleU;
        this.permisU = permisU;
        this.telephoneU = telephoneU;
        this.cinU = cinU;
        this.createdAtU = createdAtU;
        this.abonnéU = abonnéU;
    }

    public Utilisateur(int idU, String nomU, String prenomU, String emailU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.emailU = emailU;
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

    public int getIdAdresseU() {
        return idAdresseU;
    }

    public void setIdAdresseU(int idAdresseU) {
        this.idAdresseU = idAdresseU;
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

    public Date getCreatedAtU() {
        return createdAtU;
    }

    public void setCreatedAtU(Date createdAtU) {
        this.createdAtU = createdAtU;
    }

    public int getAbonnéU() {
        return abonnéU;
    }

    public void setAbonnéU(int abonnéU) {
        this.abonnéU = abonnéU;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idU=" + idU + ", nomU=" + nomU + ", prenomU=" + prenomU + ", adresseU=" + idAdresseU + ", imagePU=" + imagePU + ", mdpU=" + mdpU + ", emailU=" + emailU + ", roleU=" + roleU + ", permisU=" + permisU + ", telephoneU=" + telephoneU + ", cinU=" + cinU + ", createdAtU=" + createdAtU + ", abonn\u00e9U=" + abonnéU + '}';
    }
      
    
     
      
}
