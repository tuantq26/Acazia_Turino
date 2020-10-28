package com.tuantq.turino.view.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tuantq.turino.App;
import com.tuantq.turino.presenter.base_presenter.BasePresenter;
import com.tuantq.turino.view.OnActionCallBack;

public abstract class BaseDialog<T extends BasePresenter> extends Dialog implements View.OnClickListener {
    protected Context mContext;
    protected T mPresenter;
    protected OnActionCallBack mCallBack;

    public void setOnActionCallBack(OnActionCallBack event) {
        this.mCallBack = event;
    }

    public BaseDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        setContentView(getLayoutId());
        initPresenter();
        initViews();
    }


    public void setCallBack(OnActionCallBack event) {
        this.mCallBack = event;
    }

    protected abstract void initPresenter();

    protected abstract void initViews();

    protected abstract int getLayoutId();

    public <T extends View> T findViewById(int id, View.OnClickListener event, Typeface font) {
        T v = super.findViewById(id);

        if (event != null) {
            v.setOnClickListener(event);
        }
        if (v instanceof TextView) {
            if (font == null) {
                font = App.getInstance().getFontRegular();
            }
            ((TextView) v).setTypeface(font);
        }
        return v;
    }

    public <T extends View> T findViewById(int id, View.OnClickListener event) {
        return findViewById(id, event, null);
    }

    public <T extends View> T findViewById(int id, Typeface font) {
        return findViewById(id, null, font);
    }

    @Override
    public void onClick(View v) {

    }
}
