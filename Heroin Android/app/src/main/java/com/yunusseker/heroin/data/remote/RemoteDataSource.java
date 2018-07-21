package com.yunusseker.heroin.data.remote;

import com.yunusseker.heroin.data.DataSource;
import com.yunusseker.heroin.data.NetworkModule;

import io.reactivex.Observable;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource instance = null;
    private Api api;


    public static DataSource getInstance(Api api) {
        if (instance == null) {
            instance = new RemoteDataSource(api);
        }
        return instance;
    }

    private RemoteDataSource(Api api) {
        this.api = api;
    }

    @Override
    public Observable<String> getString() {
        return Observable.just("yunus");
    }
}
