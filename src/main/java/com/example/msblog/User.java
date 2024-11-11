package com.example.msblog;

public class User {
    private Integer id;

    private String username;
    private String email;
    private String motDePasse;
    private String role;

    public User(String username, String email, String motDePasse, String role) {
        this.username = username;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
