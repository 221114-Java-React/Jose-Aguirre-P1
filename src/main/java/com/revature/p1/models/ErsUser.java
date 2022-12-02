package com.revature.p1.models;

public class ErsUser {
    private String user_id;
    private String userName;
    private String password;
    private String email;
    private String givenName;
    private String surName;
    private Role role;

    public ErsUser(String user_id, String userName, String password, String email, String givenName, String surName, Role role) {
        this.user_id = user_id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.givenName = givenName;
        this.surName = surName;
        this.role = role;
    }

    public ErsUser(String userId, String username, String email, String password, String givenName, String surName, String isActive, Role role) {
        super();
    }

    public ErsUser(String toString, String userName, String password1, Role aDefault) {
        super();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surName='" + surName + '\'' +
                ", role=" + role +
                '}';
    }
}
