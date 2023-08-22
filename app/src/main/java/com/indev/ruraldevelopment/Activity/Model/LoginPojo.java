package com.indev.ruraldevelopment.Activity.Model;

public class LoginPojo {
    private String email;
    private String password;
    private String user_id;
    private String user_name;
    private String role_id;
    private String assigned_village;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAssigned_village() {
        return assigned_village;
    }

    public void setAssigned_village(String assigned_village) {
        this.assigned_village = assigned_village;
    }
}
