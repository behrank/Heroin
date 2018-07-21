package com.yunusseker.heroin.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.yunusseker.heroin.base.BaseViewModel;
import com.yunusseker.heroin.data.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VMMainActivity extends BaseViewModel {

    private MutableLiveData<String> data = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public VMMainActivity(DataSource dataSource) {
        super(dataSource);
    }

    public LiveData<String> getLiveData() {

        getCompositeDisposable().add(getDataRepository().getString()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(s->{
                    data.setValue(s);
                },e->{})
        );

        return data;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        private DataSource dataSource;

        public Factory(DataSource dataSource) {
            this.dataSource = dataSource;
        }
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new  VMMainActivity(dataSource);
        }
    }

}
