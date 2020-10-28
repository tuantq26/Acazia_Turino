package com.tuantq.turino.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.tuantq.turino.App;


public class StorageSharePref {
    private static final String SHARE_FILE = "shareFile.pref";
    SharedPreferences pref;

    public StorageSharePref() {
        pref = App.getInstance().getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);
    }


    public String getPrefData(String key) {
//        SharedPreferences pref = App.getInstance().getSharedPreferences(SHARE_FILE, Context.MODE_PRIVATE);
        String data = pref.getString(key, null);
        return data;
    }

    public SharedPreferences getPref() {
        return pref;
    }

    public void clearPref() {

    }
}
