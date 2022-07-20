
package com.ehire.entities;

/**
 *
 * @author Dhiaeddsn
 */
public class Reservation {
   private int id;
    private String namepart;
    private String usernamepart;
    private String ville;
    private String numtelephonepart;
    private String email_participent;
   
     
    
    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamepart() {
        return namepart;
    }

    public void setNamepart(String namepart) {
        this.namepart = namepart;
    }

    public String getUsernamepart() {
        return usernamepart;
    }

    public void setUsernamepart(String usernamepart) {
        this.usernamepart = usernamepart;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumtelephonepart() {
        return numtelephonepart;
    }

    public void setNumtelephonepart(String numtelephonepart) {
        this.numtelephonepart = numtelephonepart;
    }

    public String getEmail_participent() {
        return email_participent;
    }

    public void setEmail_participent(String email_participent) {
        this.email_participent = email_participent;
    }

    
  }
