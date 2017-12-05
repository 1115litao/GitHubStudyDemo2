package com.rxjavatest.rxjava;
import android.content.Context;
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

    }

    @Override
    public void onError( Throwable e) {
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
