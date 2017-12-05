package com.demotest.crash;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.demotest.Main3Activity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author 李涛
 * @description
 * @Date 2017/12/5.
 */


public class CrashHandler  implements Thread.UncaughtExceptionHandler{
    // 程序的 Context 对象
    private Context mContext;
    public  static Application application;
    public  static CrashHandler  CRASH = new CrashHandler();
    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler  getInstance(){
        return CRASH;
    }


    public    void init(Context context){
            if(context instanceof Application){
                application = (Application) context;
            }
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while(cause!=null){
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);

        Intent intent = new Intent(mContext, Main3Activity.class);
        intent.putExtra("errordata",sb.toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // 退出程序,注释下面的重启启动程序代码
        System.exit(1);
        android.os.Process.killProcess(android.os.Process.myPid());

    }




}
