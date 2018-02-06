package com.rxjavatest.api;

import com.rxjavatest.bean.MessageDataBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author 李涛
 * @description
 *
 * https://api.douban.com/v2/book/search?q=小王子&start=0&count=3
 *
 * @Date 2017/10/18.
 */


public interface NewInterface {

    /**
     * 有参数
     * @param map
     * @return
     */
    @POST(API.URL_PARAMETER)
    Observable<String> getUrlDataParams(@QueryMap Map<String,String> map);

    /***
     * 无参数
     * @return
     */
    @GET (API.URL_PARAMETER)
   Observable<MessageDataBean> getUrlData();


    /**
     * 测试
     * @return
     */
    @POST(API.URL_PARAMETER)
    Call<String> getUrlData2();

}
