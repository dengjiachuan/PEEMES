package com.peemes.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by cshao on 2018/10/14.
 */

public class User extends DataSupport {
    private int userID;
    private String userName;
    private String passWord;
    private int partmentID;
    private String privGrade;
    private String lastLogin;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public int getPartmentID() {
        return partmentID;
    }

    public void setPartmentID(int partmentID) {
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
