package com.yunusseker.heroin.data.model;

import com.yunusseker.heroin.data.model.BaseError;

public class DataHolder<T> {

    private BaseError error;
    private T data;

    public DataHolder(T data) {
        this.data = data;
    }

    public BaseError getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return error == null;
    }

}

