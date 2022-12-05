package com.rajendra.vacationtourapp.model;

public class User {
    String Username, Email, PNum, Password, CardNum, Address;
    public User() {
    }

    public User(String username, String email, String PNum, String password, String cardNum, String address) {
        Username = username;
        Email = email;
        this.PNum = PNum;
        Password = password;
        CardNum = cardNum;
        Address = address;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPNum() {
        return PNum;
    }

    public void setPNum(String PNum) {
        this.PNum = PNum;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String cardNum) {
        CardNum = cardNum;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
