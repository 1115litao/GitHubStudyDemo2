package com.rxjavatest.rxjava;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 李涛
 * @description
 * @Date 2017/10/26.
 */


public abstract class RXObservere<T> implements Observer<T> {
    private  Context context;
    private  Boolean isShow;
    public   HttpDialog httpDialog;


    public  RXObservere(Context context,Boolean isShow) {
        this.context = context;
        this.isShow =  isShow;
    }

    public   HttpDialog    getShowDialog(){
        if(httpDialog==null){
            synchronized (HttpDialog.class){
                httpDialog = new HttpDialog(context);
            }
        }
        return httpDialog;
    }


    @Override
    public void onSubscribe( Disposable d) {
        if(!getShowDialog().isShowing()&&isShow){
            getShowDialog().show();
        }
    }

    @Override
    public void onNext( T o) {
        if(o!=null|| !TextUtils.isEmpty(o.toString())){
            Log.i("http",""+o.toString());
        }else{
            Log.i("http","返回数据Null");
        }
    }

    @Override
    public void onError( Throwable e) {
        Log.i("http","失败");
        if(getShowDialog().isShowing()){
            getShowDialog().dismiss();
        }
    }

    @Override
    public void onComplete() {
        if(getShowDialog().isShowing()){
            getShowDialog().dismiss();
        }
    }
}
