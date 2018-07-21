package com.yunusseker.heroin.data;

import android.databinding.ObservableField;

import io.reactivex.Observable;

public interface DataSource {

    Observable<String> getString();
}
