package com.yunusseker.heroin.base;

import android.arch.lifecycle.ViewModel;

import com.yunusseker.heroin.data.DataRepository;
import com.yunusseker.heroin.data.DataSource;
import com.yunusseker.heroin.data.NetworkModule;
import com.yunusseker.heroin.data.local.LocalDataSource;
import com.yunusseker.heroin.data.remote.RemoteDataSource;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable ;
    private DataSource dataRepository;

    public BaseViewModel(DataSource dataSource) {
        this.dataRepository=dataSource;
        compositeDisposable=new CompositeDisposable();
    }

    protected DataSource getDataRepository() {
        return dataRepository;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
        super.onCleared();
    }
}
