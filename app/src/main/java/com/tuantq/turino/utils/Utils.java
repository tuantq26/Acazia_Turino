package com.tuantq.turino.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tuantq.turino.model.Location;
import com.tuantq.turino.model.User;
import com.tuantq.turino.model.sample.Profile;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is = null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG, "path " + jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<User> loadUsers(Context context) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "users.json"));
            List<User> listUser = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                User user = gson.fromJson(array.getString(i), User.class);
                listUser.add(user);
            }
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
