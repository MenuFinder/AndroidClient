package cat.udl.menufinder.ws;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class WebServiceUtils {
    public static final String TAG = WebServiceUtils.class.getSimpleName();
    public static final String baseUrl = "http://172.16.113.185:8080/MenuFinderWeb/webservice/menufinderws";// /restaurantMenus/2

    public static String get(String acction) {
        String result = null;
        try {
            URL url = new URL(baseUrl + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Path.GET_METHOD);
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code " + conn.getResponseCode());
            }
            result = getResponse(conn);
        } catch (IOException e) {
            Log.e("IOException", e.toString());
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T getBean(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static <T> List<T> getBeanList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }

    public static <T> boolean post(String acction, T object) {
        return sendBean(Path.POST_METHOD, acction, object);
    }

    public static boolean delete(String acction) {
        try {
            URL url = new URL(baseUrl + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Path.DELETE_METHOD);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.getResponseCode();
            String result = getResponse(conn);
            Log.d(TAG, result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> boolean put(String acction, T object) {
        return sendBean(Path.PUT_METHOD, acction, object);
    }

    private static <T> boolean sendBean(String method, String acction, T object) {
        Gson gson = new Gson();
        try {
            URL url = new URL(baseUrl + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(gson.toJson(object));
            wr.flush();
            wr.close();
            conn.getResponseCode();
            String result = getResponse(conn);
            Log.d(TAG, result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @NonNull
    private static String getResponse(HttpURLConnection conn) throws IOException {
        InputStream inputStream = conn.getInputStream();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sBuilder = new StringBuilder();

        String line;
        while ((line = bReader.readLine()) != null) {
            sBuilder.append(line).append("\n");
        }

        inputStream.close();
        return sBuilder.toString();
    }

}
