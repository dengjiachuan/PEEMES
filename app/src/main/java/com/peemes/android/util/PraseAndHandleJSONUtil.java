package com.peemes.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.peemes.android.db.IndexStandardVal;
import com.peemes.android.db.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cshao on 2018/10/14.
 */
/*
* 解析和处理服务器返回的数据
* 先使用JSONArray和JSONObject将数据解析出来，然后组装成实体类对象
* 在调用save()方法将数据存储到数据库中*/
public class PraseAndHandleJSONUtil {
    //解析和处理服务器返回的用户相关的信息
    public static boolean handleUserResponse(String response){
        Log.d("handleUserResponse","0000000000000");
        if (!TextUtils.isEmpty(response)) {
            try{
                Log.d("handleUserResponse","00000111111");
                JSONArray allUsers = new JSONArray(response);
                for(int i = 0;i < allUsers.length(); i++){
                    JSONObject userObject = allUsers.getJSONObject(i);
                    User user = new User();
                    Log.d("handleUserResponse","111111111");
                    user.setUserID(userObject.getString("USERID"));
                    user.setUserName(userObject.getString("USERNAME"));
                    user.setPassWord(userObject.getString("PASSWORD"));
                    user.setPartmentID(userObject.getString("PARTMENTID"));
                    user.setPrivGrade(userObject.getString("PRIV_GRADE"));
                    user.setLastLogin(userObject.getString("LAST_LOGIN"));
                    user.save();
                    Log.d("PraseAndHandleJSONO","account"+userObject.getString("USERNAME"));
                    Log.d("PraseAndHandleJSONO","password"+userObject.getString("PASSWORD"));
                }
                return true;
            }catch (JSONException je){
                je.printStackTrace();
                Log.d("PraseAndHandleJSONO","没有进入JSON数据解析");
            }
        }
        return false;
    }
    //解析和处理服务器返回的折能系数
    /*public static boolean handleZheNengXiShuResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray allZheNengXiShu = new JSONArray(response);
                for(int i = 0; i < allZheNengXiShu.length(); i++){
                    JSONObject xiShuObject = allZheNengXiShu.getJSONObject(i);
                    ZheNengXiShu zheNengXiShu = new ZheNengXiShu();
                    zheNengXiShu.setId(xiShuObject.getString("ID"));
                    zheNengXiShu.setName(xiShuObject.getString("NAME"));
                    zheNengXiShu.setVal(xiShuObject.getString("VAL"));
                    zheNengXiShu.setMeaning(xiShuObject.getString("MEANING"));
                    zheNengXiShu.setUom(xiShuObject.getString("UOM"));
                    zheNengXiShu.save();

                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }*/
    //解析和处理从服务器返回的基准值指标
    public static boolean handleIndexStandardValResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try{
                JSONArray valArray = new JSONArray(response);
                for(int i = 0 ; i<valArray.length(); i++){
                    JSONObject val = valArray.getJSONObject(i);
                    IndexStandardVal indexStandardVal = new IndexStandardVal();
                    indexStandardVal.setID(val.getString("ID"));
                    indexStandardVal.setVal(val.getString("VAL"));
                    indexStandardVal.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
