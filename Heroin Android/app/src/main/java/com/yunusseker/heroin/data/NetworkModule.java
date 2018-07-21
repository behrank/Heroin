package com.yunusseker.heroin.data;

import com.google.gson.Gson;
import com.yunusseker.heroin.data.remote.Api;
import com.yunusseker.heroin.util.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    private static NetworkModule instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    private Api service;

    public NetworkModule() {

        retrofit = new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Api.class);
    }

    public static NetworkModule getInstance() {
        if (instance == null) {
            instance = new NetworkModule();
        }

        return instance;
    }

    public Api getService() {
        return service;
    }
}
