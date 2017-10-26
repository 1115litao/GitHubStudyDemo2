package com.rxjavatest.netutils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.rxjavatest.api.API;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author 李涛
 * @description
 * @Date 2017/10/25.
 */


public class NetUtils {

    //配置Okhttp
    public   static OkHttpClient.Builder getOkhttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10,TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.addInterceptor(new OKInterceptor())
        .build();
        return builder;
    }

    //针对于返回字符串类型
    public static Retrofit RetrofitBuilderString(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpClient().build())
                .build();
        return retrofit;
    }

    //针对于返回Gson解析
    public static Retrofit RetrofitBuilderGson(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpClient().build())
                .build();
        return retrofit;
    }


}
