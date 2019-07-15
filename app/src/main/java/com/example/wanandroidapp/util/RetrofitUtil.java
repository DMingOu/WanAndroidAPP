package com.example.wanandroidapp.util;

import com.example.wanandroidapp.core.http.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: ODM
 * @date: 2019/7/14
 */
public class RetrofitUtil {

    public static String baseUrl = ApiService.BASE_URL;

    public static ApiService apiService;
    //单例
    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (RetrofitUtil.class) {
                if (apiService == null) {
                    new  RetrofitUtil();
                }
            }
        }
        return  apiService;
    }

    private RetrofitUtil() {
        Retrofit  retrofit = new  Retrofit.Builder()
                                        .baseUrl(baseUrl)
                                        .client( new OkHttpClient())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static class SingletonHolder {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();
    }

    public static RetrofitUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    }
