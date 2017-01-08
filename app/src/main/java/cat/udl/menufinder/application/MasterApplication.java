package cat.udl.menufinder.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import cat.udl.menufinder.database.DBManager;
import cat.udl.menufinder.database.DBManagerIterm;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.utils.Constants;
import cat.udl.menufinder.utils.FakeData;

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
        firstTime();
    }

    public DBManager getDbManager() {
        return DBManagerIterm.getInstance();
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

    public void removePreference(String key) {
        getPestormixSharedPreferences().edit().remove(key).apply();
    }

    public void login(Account account) {
        putString(Constants.PREFERENCES_USER_TYPE, account.getType());
        putString(Constants.PREFERENCES_USERNAME, account.getId());
    }

    public void logout() {
        removePreference(Constants.PREFERENCES_USER_TYPE);
        removePreference(Constants.PREFERENCES_USERNAME);
    }

    public boolean isLogged() {
        return !getUsername().equals("");
    }

    public UserType getUserType() {
        return UserType.fromString(getString(Constants.PREFERENCES_USER_TYPE, ""));
    }

    public String getUsername() {
        return getString(Constants.PREFERENCES_USERNAME, "");
    }

    private void firstTime() {
        String key = "first_time";
        if (getBoolean(key, true) && false) {
            putBoolean(key, false);
            new FakeData(getDbManager());
        }
    }

    public String getToken() {
        return getString(Constants.PREFERENCES_TOKEN, "");
    }

    public void setToken(String token) {
        putString(Constants.PREFERENCES_TOKEN, token);
    }
}
