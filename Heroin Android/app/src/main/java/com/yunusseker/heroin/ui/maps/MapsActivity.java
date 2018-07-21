package com.yunusseker.heroin.ui.maps;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.yunusseker.heroin.R;
import com.yunusseker.heroin.base.BaseActivity;
import com.yunusseker.heroin.databinding.ActMapsBinding;
import com.yunusseker.heroin.ui.help.HelpActivity;
import com.yunusseker.heroin.ui.main.VMMainActivity;

public class MapsActivity extends BaseActivity<ActMapsBinding> implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap map;
    private VMMapsActivity vmMapsActivity;

    @Override
    public int getLayoutRes() {
        return R.layout.act_maps;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding.maps.onCreate(savedInstanceState);
        dataBinding.maps.getMapAsync(this);
        vmMapsActivity = ViewModelProviders.of(this, new VMMapsActivity.Factory(getDataSource())).get(VMMapsActivity.class);
        dataBinding.setLifecycleOwner(this);
        dataBinding.button.setOnClickListener(v -> startActivityWithBackstack(HelpActivity.class));

        vmMapsActivity.getFinisTimer().observe(this, aBoolean -> {
            startActivityWithBackstack(HelpActivity.class);
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBinding.maps.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dataBinding.maps.onLowMemory();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataBinding.maps.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBinding.maps.onDestroy();
    }
}
