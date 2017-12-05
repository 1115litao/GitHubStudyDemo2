package com.rxjavatest.netutils;

/**
 * @author 李涛
 * @description
 * @Date 2017/10/25.
 */


public  class RetrofitUtils {

    //针对于返回字符串
    public  static NewInterface RequestBackString(){
        NewInterface  newInterface =   NetUtils.RetrofitBuilderString().create(NewInterface.class);
        return newInterface;
    }

    //针对于返回Gson解析
    public  static NewInterface  RequestBackGson(){
        NewInterface  newInterface =   NetUtils.RetrofitBuilderGson().create(NewInterface.class);
        return newInterface;
    }


}
