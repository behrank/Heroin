package com.yunusseker.heroin.data;


import com.yunusseker.heroin.data.remote.Api;

import io.reactivex.Observable;

public class DataRepository implements DataSource {
    private DataSource localDataSouce;
    private DataSource remoteDataSource;
    private static DataRepository instance=null;

    public static DataRepository getInstance(DataSource dataSource,DataSource localDataSouce) {
            if (instance == null) {
                instance = new DataRepository(dataSource,localDataSouce);
            }
            return instance;
    }

    private DataRepository(DataSource remoteDataSource,DataSource localDataSouce) {
        this.remoteDataSource=remoteDataSource;
        this.localDataSouce=localDataSouce;
    }

    @Override
    public Observable<String> getString() {
        return Observable.just("yunus");
    }
}
