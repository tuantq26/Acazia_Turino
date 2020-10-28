package com.tuantq.turino;

import android.app.Application;
import android.graphics.Typeface;

import com.tuantq.turino.storage.StorageSharePref;


public class App extends Application {
    private static App instance;
    private StorageSharePref storagePref;
    private Typeface fontBold, fontRegular, fontThin, fontLight;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        storagePref = new StorageSharePref();

        init();

    }


    public StorageSharePref getStoragePref() {
        return storagePref;
    }


    private void init() {
        fontBold = Typeface.createFromAsset(getAssets(), "font/Roboto-Bold.ttf");
        fontRegular = Typeface.createFromAsset(getAssets(), "font/Roboto-Regular.ttf");
        fontThin = Typeface.createFromAsset(getAssets(), "font/Roboto-Thin.ttf");
        fontLight = Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf");
    }

    public Typeface getFontBold() {
        return fontBold;
    }

    public Typeface getFontRegular() {
        return fontRegular;
    }

    public Typeface getFontThin() {
        return fontThin;
    }

    public Typeface getFontLight() {
        return fontLight;
    }

}
