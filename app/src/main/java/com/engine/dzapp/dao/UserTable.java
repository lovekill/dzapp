package com.engine.dzapp.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER_TABLE.
 */
public class UserTable {

    private String userName;
    private String password;
    private String gespassword;
    private java.util.Date loginTime;

    public UserTable() {
    }

    public UserTable(String userName) {
        this.userName = userName;
    }

    public UserTable(String userName, String password, String gespassword, java.util.Date loginTime) {
        this.userName = userName;
        this.password = password;
        this.gespassword = gespassword;
        this.loginTime = loginTime;
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

    public String getGespassword() {
        return gespassword;
    }

    public void setGespassword(String gespassword) {
        this.gespassword = gespassword;
    }

    public java.util.Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(java.util.Date loginTime) {
        this.loginTime = loginTime;
    }

}
