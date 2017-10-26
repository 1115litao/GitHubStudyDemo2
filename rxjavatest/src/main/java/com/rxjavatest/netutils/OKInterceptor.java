package com.rxjavatest.netutils;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 李涛
 * @description 拦截器打印网络请求链接 带上必传的参数
 * @Date 2017/10/25.
 */


public class OKInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request  originRequest = chain.request();
        HttpUrl httpUrl = originRequest.url();
        HttpUrl url = httpUrl.newBuilder().addQueryParameter("a","fy").build();
        Request request = originRequest.newBuilder()
                .url(url)
                .method(originRequest.method(),originRequest.body())
                .build();
        Log.i("Http",""+url);
        return chain.proceed(request);
    }
}
