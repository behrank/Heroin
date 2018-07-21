package com.yunusseker.heroin.ui.maps;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yunusseker.heroin.base.BaseViewModel;
import com.yunusseker.heroin.data.DataSource;
import com.yunusseker.heroin.ui.main.VMMainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VMMapsActivity extends BaseViewModel {

    private MutableLiveData<Boolean> finisTimer = new MutableLiveData<>();
    private MutableLiveData<Boolean> isCallLayoutInvisible = new MutableLiveData<>();

    public VMMapsActivity(DataSource dataSource) {
        super(dataSource);
    }

    public void startTimer() {
        isCallLayoutInvisible.postValue(true);
        getCompositeDisposable().add(Observable.interval(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(timer -> {
                    finisTimer.postValue(true);
                })
        );


    }

    public MutableLiveData<Boolean> getFinisTimer() {
        return finisTimer;
    }

    public MutableLiveData<Boolean> getIsCallLayoutInvisible() {
        return isCallLayoutInvisible;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private DataSource dataSource;

        public Factory(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new VMMapsActivity(dataSource);
        }
    }

}