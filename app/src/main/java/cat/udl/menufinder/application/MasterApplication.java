package cat.udl.menufinder.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import cat.udl.menufinder.utils.Constants;

public class MasterApplication extends Application {
    private final static String TAG = MasterApplication.class.getSimpleName();
    private static MasterApplication context;

    public static MasterApplication getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MasterApplication.context = this;
    }

    public SharedPreferences getPestormixSharedPreferences() {
        return getSharedPreferences(Constants.PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return getPestormixSharedPreferences().getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        getPestormixSharedPreferences().edit().putBoolean(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return getPestormixSharedPreferences().getString(key, defValue);
    }

    public void putString(String key, String value) {
        getPestormixSharedPreferences().edit().putString(key, value).apply();
    }

}
