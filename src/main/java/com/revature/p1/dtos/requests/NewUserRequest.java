package com.revature.p1.dtos.requests;

public class NewUserRequest {
    private String userName;
    private String email;
    private String password1;
    private String password2;
    private String givenName;
    private String surName;
    private String isActive;
    private String roleId;

    public NewUserRequest(){
        super();
    }

    public NewUserRequest(String userName, String email, String password1, String password2, String givenName, String surName, String isActive, String roleId) {
        this.userName = userName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.givenName = givenName;
        this.surName = surName;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurName() {
        return surName;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surName='" + surName + '\'' +
                ", isActive='" + isActive + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
