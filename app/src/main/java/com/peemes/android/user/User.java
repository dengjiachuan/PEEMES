package com.peemes.android.user;

/**
 * Created by cshao on 2018/11/17.
 */

public class User {
    private String userID;
    private String userName;
    private String passWord;
    private String  partmentID;
    private String privGrade;
    private String lastLogin;

    public User() {
    }

    public User(String userID, String userName, String passWord, String partmentID, String privGrade, String lastLogin) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
        this.partmentID = partmentID;
        this.privGrade = privGrade;
        this.lastLogin = lastLogin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPartmentID() {
        return partmentID;
    }

    public void setPartmentID(String partmentID) {
        this.partmentID = partmentID;
    }

    public String getPrivGrade() {
        return privGrade;
    }

    public void setPrivGrade(String privGrade) {
        this.privGrade = privGrade;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
