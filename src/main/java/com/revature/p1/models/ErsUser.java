package com.revature.p1.models;

public class ErsUser {
    private String user_id;
    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surName;
    private Role role;

    public ErsUser() {
        super();
    }

    public ErsUser(String user_id, String username, String email, String password, String givenName, String surName, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surName = surName;
        this.role = role;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ErsUser{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surName='" + surName + '\'' +
                ", role=" + role +
                '}';
    }
}
