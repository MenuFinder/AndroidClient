package cat.udl.menufinder.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterApplication;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.models.Restaurant;

public class Utils {

    public static Intent getShareIntent(String shareText) {
        Intent shareIntent;
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/*");
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText);
        return shareIntent;
    }

    public static LatLng getLatLngOfRestaurant(Restaurant restaurant, Context activity) {
        Geocoder geocoder = new Geocoder(activity);
        try {
            List<Address> addresses = geocoder.getFromLocationName(restaurant.getAddressWithCity(), 1);
            if (addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                return new LatLng(latitude, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    public static boolean isNetworkAvailable(Context activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIfNotGuest() {
        if (MasterApplication.getContext().getUserType() == UserType.GUEST) {
            MasterApplication.getContext().showToast(R.string.invalid_guest_action);
            return false;
        }
        return true;
    }

    public static String md5(String input) {

        String md5 = null;

        if (input == null) return null;

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
}
