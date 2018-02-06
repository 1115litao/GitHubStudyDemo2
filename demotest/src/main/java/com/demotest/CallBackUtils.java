package com.demotest;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:MYM
 * date:2017-1-6
 */
public class CallBackUtils {
    private static CallBackUtils callBackUtils;
    private static List<MessageCallBack> messageCallBacks = new ArrayList<>();
    private Map<Object,MessageCallBack> objects = new HashMap<>();
    private CallBackUtils() {
    }
    public static CallBackUtils getInstance(){
        if (null != callBackUtils){

        }else {//采用DCL方式实现线程安全
            synchronized (CallBackUtils.class){
                if (null == callBackUtils){
                    callBackUtils = new CallBackUtils();
                }
            }
        }
        return callBackUtils;
    }
    /** 移除监听 */
    public void remove(Object o){
        if (objects.containsKey(o)){
            MessageCallBack messageCallBack = objects.get(o);
            if (messageCallBacks.contains(messageCallBack))
                messageCallBacks.remove(messageCallBack);
        }
    }

    /**
     * 向所有回调发送消息
     * @param value
     */
    public void sendMessage(String value){
        for (MessageCallBack messageCallBack : messageCallBacks){
            messageCallBack.callBack(value);
        }
    }

    /**
     * 向指定类发送消息，假如这个类没有找到，或者没有绑定监听，则不发送消息
     * @param o 绑定监听的对象
     * @param value 发送内容
     */
    public void sendMessageSingle(Object o,String value){
        if (objects.containsKey(o)){
            MessageCallBack messageCallBack = objects.get(o);
            if (messageCallBacks.contains(messageCallBack))
                messageCallBack.callBack(value);
        }
    }

    /**
     * 设置监听
     * @param o 主要用于取消绑定用的
     * @param messageCallBack
     */
    public void setListener(Object o,MessageCallBack messageCallBack){
        if (!messageCallBacks.contains(messageCallBack))
            messageCallBacks.add(messageCallBack);
        if (!objects.containsKey(o))
            objects.put(o,messageCallBack);
    }
    interface MessageCallBack{
        void callBack(String value);
    }
}
