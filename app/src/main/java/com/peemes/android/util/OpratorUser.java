package com.peemes.android.util;

import android.util.Log;

import com.peemes.android.db.User;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by cshao on 2018/10/18.
 */

public class OpratorUser {
    //自己创建一个用户表格在安卓系统中，从服务器中读出的数据总是格式不对（JSON格式不对），解析不出来
    //升级和创建数据库
    public  void createAndUpdataDatabases(){

        LitePal.getDatabase();
    }
    //往SQLite中添加数据
    public  boolean addToTable(User user){
        User user1 = new User();
        user1.setUserID(user.getUserID());
        user1.setUserName(user.getUserName());
        user1.setPassWord(user.getPassWord());
        user1.setPartmentID(user.getPartmentID());
        user1.setPrivGrade(user.getPrivGrade());
        user1.setLastLogin(user.getLastLogin());
        user1.setLastLogin(getNewDate());
        boolean temp = user1.save();
        if (temp) {
            Log.d("OperatorUser","添加数据到数据库中成功");
            return true;
        }else
        {
            Log.d("OperatorUser","添加数据到数据库中失败");
            return false;
        }
    }
    //在数据中更新数据，主要是更改密码，优先等级和部门号；
    //传入三个参数，用户的ID是唯一的，一个传入需要更新的项目，一个传入需要更新的内容
    public  boolean updataData(String userID,String name,String context){
        if ("passWord".equals(name)) {
            User user = new User();
            user.setPassWord(context);
            user.updateAll("userID = ?",userID);
            return true;
        }
        if ("partmentID".equals(name)) {
            User user = new User();
            user.setPartmentID(context);
            user.updateAll("userID = ?",userID);
            return true;
        }
        if ("privGrade".equals(name)) {
            User user = new User();
            user.setPrivGrade(context);
            user.updateAll("userID = ?",userID);
            return true;
        }
        return false;
    }
    //往数据表中删除数据，主要用于用户注销
    public  boolean deleteDataFromDatabase(String name,String password){
        DataSupport.deleteAll(User.class,"userName = ? and passWord = ?",name,password);
        if (!queryData(name,password)) {
            return true;
        }
        return false;
    }
    //在数据库中查询数据，主要通过姓名和密码来查询
    public  boolean queryData(String name,String password){
        //Log.d("有没有进行数据库查询","000000000000000");
        List<User> userList = DataSupport.findAll(User.class);
        //Log.d("获得数据库中的表的对象有几个",String.valueOf(userList.size()));
        for(User user : userList){
           // Log.d("name",user.getUserName());
            //Log.d("password",user.getPassWord());
            if ((user.getUserName().equals(name)) &&(user.getPassWord().equals(password))) {
                Log.d("operatorUser","查询数据库中的成员");
                return true;
            }
        }
        return false;
    }
    public boolean queryUserIDAndUserName(String name,String userID){
        List<User> userList = DataSupport.findAll(User.class);
        for(User user : userList){
            if ((user.getUserName().equals(name)) && (user.getUserID().equals(userID))) {
                return true;
            }else{
                Log.d("OperatorUser","数据库中无该用户，或者用户名和用户号不匹配");
                return false;
            }
        }
        return false;
    }
    //获取当前系统的时间，并放回
    public String getNewDate(){
        String  newDate = null;
        Date date = new Date();
        String dateYear = String.format("%tF",date);
        String dateTime = String.format("%tr",date);
        newDate = dateYear+" "+dateTime;
        return newDate;
    }
    //更新每次登录的时间,根据登录的用户进行更新
    public void updateLastLogin(String name){
        List<User> userList = DataSupport.findAll(User.class);
        for(User users:userList){
            if (users.getUserName().equals(name)) {
                users.setLastLogin(getNewDate());
                users.updateAll("userName = ?",name );
            }
        }
    }
}
