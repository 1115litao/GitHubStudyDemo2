package com.rxjavatest.home;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonParser;
import com.rxjavatest.MainActivity;
import com.rxjavatest.basaitf.BasePresenter;
import com.rxjavatest.basaitf.BaseView;
import com.rxjavatest.bean.MessageDataBean;
import com.rxjavatest.netutils.RetrofitUtils;
import com.rxjavatest.rxjava.RXObservere;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 李涛
 * @description Home页面的Control数据管理类
 * @Date 2018/1/11.
 */


public class HomeControl implements HomeItf.homePersenter {

    private MessageDataBean urlData = new MessageDataBean();
    private HomeItf.homeView baseView;
    private Context context;
    private HomeItf.homePersenter persenter;
    public HomeControl(Context context) {
        this.context = context;
        this.baseView = (HomeItf.homeView) context;
    }


    /***
     * 测试网络请求
     */
    @Override
    public void   getNetData() {
        RetrofitUtils.RequestBackGson().getUrlData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RXObservere<MessageDataBean>(context,true) {
                    @Override
                    public void onNext(MessageDataBean o) {
                        super.onNext(o);
//                        new JsonParser().parse("12");
                        urlData =  o;
                        baseView.hettpNetData(urlData);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }




}
