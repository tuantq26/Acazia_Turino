package com.tuantq.turino.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.tuantq.problem01.R;
import com.tuantq.turino.constant.Constant;
import com.tuantq.turino.event.A01HomeOnCallBack;
import com.tuantq.turino.presenter.A01HomePresenter;
import com.tuantq.turino.view.base.BaseActivity;
import com.tuantq.turino.view.fragment.F001SplashFrg;
import com.tuantq.turino.view.fragment.F002LoginFrg;
import com.tuantq.turino.view.fragment.F003HomeFrg;

public class A01MainAct extends BaseActivity<A01HomePresenter> implements A01HomeOnCallBack {
    private F001SplashFrg f001SplashFrg;
    private F002LoginFrg f002LoginFrg;

    @Override
    protected void initViews() {
        requestPermission();
    }

    private void requestPermission() {
        if (checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        } else {
            showFrg(R.id.lnl_main, F001SplashFrg.TAG);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (permissions[0]
                    .equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showFrg(R.id.lnl_main, F001SplashFrg.TAG);
            } else {
                Toast.makeText(this, "Please allow access call phone in setting!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void initPresenter() {
        mPresenter = new A01HomePresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_01_main;
    }

    @Override
    public void onActionCallBack(String key, Object... data) {
        switch (key) {
            case F003HomeFrg.KEY_SHOW_LOGIN_FRG:
                saveBooToSharePref(Constant.KEY_REMEMBER_ME, false);
            case F001SplashFrg.KEY_SHOW_FRG_LOGIN:
                showFrg(R.id.lnl_main, F002LoginFrg.TAG);
                break;
            case F001SplashFrg.KEY_SHOW_FRG_HOME_BYPASS:
            case F002LoginFrg.KEY_SHOW_HOME_FRG:
                showFrg(R.id.lnl_main, F003HomeFrg.TAG);
                break;
            default:
                break;
        }
    }

    @Override
    public void getResult(String key, Object... data) {

    }
}
