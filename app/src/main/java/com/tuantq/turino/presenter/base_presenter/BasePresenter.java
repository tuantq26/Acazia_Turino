package com.tuantq.turino.presenter.base_presenter;

import com.tuantq.turino.event.OnCallBack;


public abstract class BasePresenter<T extends OnCallBack> {

    private static final String TAG = BasePresenter.class.getName();
    protected T mListener;


    public BasePresenter(T event) {
        this.mListener = event;
    }

}
