package com.ehire.entities;

public class candidat {

    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String cv_url;

    public candidat(int id, String fullName, String description, String email, String cv_url) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cv_url = cv_url;
    }
    public candidat( String fullName,  String email, String cv_url) {
       
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        
    }

     public candidat(String fullName, String description, String email, String cv_url) {
       
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cv_url = cv_url;
    }
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCv_url() {
        return cv_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCv_url(String cv_url) {
        this.cv_url = cv_url;
    }

    

   
}
