package com.rxjavatest.netutils;

import com.rxjavatest.api.API;

import java.util.Map;

import io.reactivex.Observable;
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

    @POST(API.URL_PARAMETER)
    Observable<String> getUrlData(@QueryMap Map<String,String> map);

}
