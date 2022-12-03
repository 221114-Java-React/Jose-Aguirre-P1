package com.revature.p1.dtos.responses;

import com.revature.p1.models.Role;

public class Principal {
    private String id;
    private String userName;
    private Role role;

    public Principal() {
        super();
    }

    public Principal(String id, String userName, Role role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }
}