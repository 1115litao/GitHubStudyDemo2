package com.rxjavatest.utlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author 李涛
 * @description 判断网络链接状态
 * @Date 2018/1/11.
 */


public class NetUtils {

        public  static   NetUtils netUtils = new NetUtils();
        public  static synchronized   NetUtils getInstance(){
            return netUtils;
        }


    /**
     * 判断是否有网络链接
     * @param context
     * @return
     */
    public  boolean isNetConnection(Context context){
            if(context != null){
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if(mNetworkInfo != null){
                    return mNetworkInfo.isAvailable();
                }
            }
            return false;
    }

    /**
     * 判断是否Wifi链接
     * @param context
     * @return
     */
    public  boolean isWifiConnection(Context context){
    if(context != null){
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifiNetInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(mWifiNetInfo != null){
            return mWifiNetInfo.isAvailable();
        }
    }

        return false;
    }


}
