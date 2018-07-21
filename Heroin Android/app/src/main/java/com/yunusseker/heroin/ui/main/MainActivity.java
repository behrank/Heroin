package com.yunusseker.heroin.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yunusseker.heroin.R;
import com.yunusseker.heroin.base.BaseActivity;
import com.yunusseker.heroin.data.DataRepository;
import com.yunusseker.heroin.data.NetworkModule;
import com.yunusseker.heroin.data.local.LocalDataSource;
import com.yunusseker.heroin.data.remote.RemoteDataSource;
import com.yunusseker.heroin.databinding.ActivityMainBinding;
import com.yunusseker.heroin.ui.main.adapter.BannerPagerAdapter;
import com.yunusseker.heroin.ui.maps.MapsActivity;

import static com.google.gson.reflect.TypeToken.get;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private VMMainActivity vmMainActivity;
    BannerPagerAdapter bannerPagerAdapter;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vmMainActivity = ViewModelProviders.of(this, new VMMainActivity.Factory(getDataSource())).get(VMMainActivity.class);

        dataBinding.mainBtnHelpMe.setOnClickListener(v -> startActivityWithBackstack(MapsActivity.class));

    }
}
