package com.tuantq.turino.view.base;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tuantq.problem01.R;
import com.tuantq.turino.App;
import com.tuantq.turino.presenter.base_presenter.BasePresenter;
import com.tuantq.turino.view.OnActionCallBack;

import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements OnActionCallBack {
    protected T mPresenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initPresenter();
        initViews();
    }

    protected abstract void initViews();

    protected abstract void initPresenter();

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

    protected final void showFrg(int layoutMain, String tag, boolean isAddToBackStack) {
        try {
            //Tạo đối tượng bằng TAG name của nó
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment frg = (BaseFragment) constructor.newInstance();
            frg.setOnActionCallBack(this);

            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.animator.alpha_in, R.animator.alpha_out, R.animator.alpha_in, R.animator.alpha_out)
                    .replace(layoutMain, frg, tag);

            if (isAddToBackStack) {
                trans.addToBackStack("NameOfBackStack");
            }

            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final void showFrg(int layoutMain, String tag) {
        showFrg(layoutMain, tag, false);
    }

    protected final void showFrg(int layoutMain, Fragment frg) {
        showFrg(layoutMain, frg, false);
    }

    protected final void showFrg(int layoutMain, Fragment frg, boolean isAddToBackStack) {
        try {
            ((BaseFragment) frg).setOnActionCallBack(this);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.animator.alpha_in, R.animator.alpha_out, R.animator.alpha_in, R.animator.alpha_out)
                    .replace(layoutMain, frg, frg.getClass().getName());

            if (isAddToBackStack) {
                trans.addToBackStack("NameOfBackStack");
            }

            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActionCallBack(String key, Object... data) {
        //do nothing
    }

    //Bộ nhớ RAM, lưu trong đối tượng StorageCommon
    protected StorageRAMCommon getStorage() {
        return App.getInstance().getStorageRAM();
    }

    //Bộ nhớ của ứng dụng nhưng người dùng không thể nhìn thấy
    //Có thể lưu mà không cần xin phép người dùng
    //Chỉ có thể xóa bằng cách vào cài đặt -> Ứng dụng -> Xóa bộ nhớ
    protected StorageFileCommon getStorageFile() {
        return App.getInstance().getStorageFile();
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


    protected String convertDateTimeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    protected SharedPreferences getStoragePref() {
        return App.getInstance().getStoragePref().getPref();
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

}

