package com.yunusseker.heroin.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.yunusseker.heroin.data.DataRepository;
import com.yunusseker.heroin.data.DataSource;
import com.yunusseker.heroin.data.NetworkModule;
import com.yunusseker.heroin.data.local.LocalDataSource;
import com.yunusseker.heroin.data.remote.RemoteDataSource;


public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity  {

    public DB dataBinding;

    private DataSource dataSource;

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSource();
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    }

    private void initDataSource() {
        dataSource= DataRepository.getInstance(
                RemoteDataSource.getInstance(NetworkModule.getInstance().getService()),
                LocalDataSource.getInstance(this.getApplicationContext())
        );
    }

    public void startActivityWithoutBackstack(Class activityClass){
        Intent i = new Intent(this, activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void startActivityWithBackstack(Class activityClass){
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
