package com.peemes.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.peemes.android.MainActivity;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by cshao on 2018/11/7.
 */

public class GetIP {
    public static String getIPAddress(Context context){
        NetworkInfo info = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                //当前是2G/3G/4G网络
                try{
                    for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                            en.hasMoreElements();){
                        NetworkInterface intf = en.nextElement();
                        for(Enumeration<InetAddress> enumIPAdd = intf.getInetAddresses();
                            enumIPAdd.hasMoreElements();){
                            InetAddress inetAddress = enumIPAdd.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }

                    }
                }catch (SocketException se){
                    se.printStackTrace();
                }
            }
        }else if(info.getType() == ConnectivityManager.TYPE_WIFI){
            //当前使用的无线网络
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ipAddress = intIPToStringIP(wifiInfo.getIpAddress());
            return ipAddress;
        }else{
            //当前无网络连接，请在设置中打开网络，否则将无法使用该APP
            Log.d("GetIP", "当前无网络连接，请在设置中打开网络，否则将无法使用该APP");
            return  null;
        }
        return null;
    }
  public static String intIPToStringIP(int ip){
      return (ip & 0xFF) +"." +((ip>>8) & 0xFF)+ "." +((ip>>16) & 0xFF)+ "." +((ip>>24) & 0xFF);
  }
    public static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
