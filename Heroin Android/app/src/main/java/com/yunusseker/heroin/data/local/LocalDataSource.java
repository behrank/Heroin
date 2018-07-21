package com.yunusseker.heroin.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yunusseker.heroin.data.DataSource;


import io.reactivex.Observable;
import io.reactivex.Single;

public class LocalDataSource implements DataSource {

    private static LocalDataSource instance=null;
    private SharedPreferences sharedPreferences;

    public static LocalDataSource getInstance(Context context) {
        if (instance==null){
            instance=new LocalDataSource(context);
        }
        return instance;
    }

    private LocalDataSource(Context context) {
        this.sharedPreferences=context.getSharedPreferences("heroin", Context.MODE_PRIVATE);
    }

    @Override
    public Observable<String> getString() {
        return Observable.just("yunus");
    }
}
