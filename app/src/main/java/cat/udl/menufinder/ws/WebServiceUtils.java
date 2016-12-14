package cat.udl.menufinder.ws;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class WebServiceUtils {
    public static final String baseUrl = "http://172.16.112.124:8080/MenuFinderWeb/webservice/menufinderws";// /restaurantMenus/2

    public static String get(String acction) {
        String result = null;
        try {
            URL url = new URL(baseUrl + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code " + conn.getResponseCode());
            }

            InputStream inputStream = conn.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sBuilder = new StringBuilder();

            String line;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line).append("\n");
            }

            inputStream.close();
            result = sBuilder.toString();

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
}
