package com.ehire.entities;

public class User {

    private int id;
    private String username;

    private String email;

    private String password;
    private boolean admin;

    public User(String username, String email, String password, boolean admin) {
        this.username = username;
      
        this.email = email;
      
        this.password = password;
        this.admin = admin;
    }
    
     public User(String username, String email, String password) {
        this.username = username;
      
        this.email = email;
      
        this.password = password;
       
    }

    public User(int id, String username, String email,  String password, boolean admin) {
        this.id = id;
        this.username = username;
        
        this.email = email;
      
     
        this.password = password;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    

    @Override
    public String toString() {
        return email;
    }
}