package com.example.videomeeting.models;

public class User {
    public String firstName;
    public String lastName;
    public String ID;
    public String email;

    public User() {
    }

    public User(String firstName,String lastName,String ID,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID=ID;
        this.email=email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
