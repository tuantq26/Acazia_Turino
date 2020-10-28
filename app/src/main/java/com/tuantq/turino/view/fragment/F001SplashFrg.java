package com.tuantq.turino.view.fragment;

import android.os.Handler;

import com.tuantq.problem01.R;
import com.tuantq.turino.constant.Constant;
import com.tuantq.turino.view.base.BaseFragment;

public class F001SplashFrg extends BaseFragment {
    public static final String TAG = F001SplashFrg.class.getName();
    public static final String KEY_SHOW_FRG_HOME_BYPASS = "KEY_SHOW_FRG_HOME_FROM_SPLASH";
    public static final String KEY_SHOW_FRG_LOGIN = "KEY_SHOW_FRG_LOGIN";

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoginFrg();
            }
        }, 1500);
    }

    private void showLoginFrg() {
        boolean rememberMe = getBooFormSharePref(Constant.KEY_REMEMBER_ME);
        if (!rememberMe) {
            mCallBack.onActionCallBack(KEY_SHOW_FRG_LOGIN, (Object) null);
        } else {
            mCallBack.onActionCallBack(KEY_SHOW_FRG_HOME_BYPASS, (Object) null);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_001_splash;
    }
}
