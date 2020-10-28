package com.tuantq.turino.view.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tuantq.problem01.R;
import com.tuantq.turino.App;
import com.tuantq.turino.presenter.base_presenter.BasePresenter;
import com.tuantq.turino.view.OnActionCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements View.OnClickListener {

    protected Context mContext;
    protected View rootView;
    protected OnActionCallBack mCallBack;
    protected T mPresenter;


    public void setOnActionCallBack(OnActionCallBack event) {
        this.mCallBack = event;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initPresenter();
        initView();
        return rootView;
    }

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    public <T extends View> T findViewById(int id, View.OnClickListener event, Typeface font) {
        T v = rootView.findViewById(id);
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

    public <T extends View> T findViewById(int id) {
        return findViewById(id, null, null);
    }

    public <T extends View> T findViewById(int id, Typeface font) {
        return findViewById(id, null, font);
    }

    @Override
    public void onClick(View v) {
        //do nothing
    }

    protected final void showFrg(int layoutMain, Fragment frg) {
        showFrg(layoutMain, frg, false);
    }

    protected StorageRAMCommon getStorageRAM() {
        return App.getInstance().getStorageRAM();
    }

    protected StorageFileCommon getStorageFile() {
        return App.getInstance().getStorageFile();
    }

    protected void showFrg(int layoutFrg, Fragment frg, boolean isAddToBackStack) {
        try {
            FragmentTransaction trans = getChildFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.animator.alpha_in, R.animator.alpha_out)
                    .replace(layoutFrg, frg, frg.getClass().getName());

            if (isAddToBackStack) {
                trans.addToBackStack("NameOfBackStack");
            }

            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void saveStrToSharePref(String KEY, String str) {
        getStoragePref().edit().putString(KEY, str).apply();
    }

    protected void saveBooToSharePref(String KEY, boolean boo) {
        getStoragePref().edit().putBoolean(KEY, boo).apply();
    }

    protected void saveIntToSharePref(String KEY, int str) {
        getStoragePref().edit().putInt(KEY, str).apply();
    }

    protected SharedPreferences getStoragePref() {
        return App.getInstance().getStoragePref().getPref();
    }

    protected String convertDateTimeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    protected String getStrFormSharePref(String KEY) {
        return getStoragePref().getString(KEY, "");
    }

    protected int getIntFormSharePref(String KEY) {
        return getStoragePref().getInt(KEY, -1);
    }

    protected boolean getBooFormSharePref(String KEY) {
        return getStoragePref().getBoolean(KEY, false);
    }


    protected void showAlert(String text) {
        final AlertDialog alert = new AlertDialog.Builder(mContext).create();
        alert.setMessage(text);
        alert.setCancelable(true);
        alert.setCanceledOnTouchOutside(true);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Okay", (dialog, which) -> {
            alert.dismiss();
        });
        alert.show();
    }


}
