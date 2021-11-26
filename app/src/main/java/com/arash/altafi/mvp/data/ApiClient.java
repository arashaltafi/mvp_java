package com.arash.altafi.mvp.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl("https://novindevelopers.ir/mvp/panel/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        return retrofit;
    }
}
