package cat.udl.menufinder.ws;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cat.udl.menufinder.models.Account;
import cat.udl.menufinder.models.Item;

import static cat.udl.menufinder.ws.Path.POST_METHOD;
import static cat.udl.menufinder.ws.Path.baseUrlREST;

public abstract class WebServiceUtils {
    public static final String TAG = WebServiceUtils.class.getSimpleName();

    public static String get(String acction) {
        String result = null;
        try {
            URL url = new URL(baseUrlREST + acction);
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
        return new Gson().fromJson(json, type);
    }

    public static <T> List<T> getBeanList(String json, Class<T[]> type) {
        if (json == null) return new ArrayList<>();
        return Arrays.asList(new Gson().fromJson(json, type));
    }

    public static <T> boolean post(String acction, T object) {
        return sendBean(Path.POST_METHOD, acction, object);
    }

    public static boolean delete(String acction) {
        try {
            URL url = new URL(baseUrlREST + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Path.DELETE_METHOD);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.getResponseCode();
            String result = getResponse(conn);
            Log.d(TAG, result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> boolean delete(String action, T object) {
        return sendBean(Path.DELETE_METHOD, action, object);
    }

    public static <T> boolean put(String acction, T object) {
        return sendBean(Path.PUT_METHOD, acction, object);
    }

    private static <T> boolean sendBean(String method, String acction, T object) {
        Gson gson = new Gson();
        try {
            URL url = new URL(baseUrlREST + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
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

    public static Map<Long, List<Item>> getMenuItemsByCategory(String json) {
        return new Gson().fromJson(json, new TypeToken<HashMap<Long, List<Item>>>() {
        }.getType());
    }

    public static double getItemRatingOfItem(String number) {
        return Double.parseDouble(number);
    }

    public static String login(String acction, String username, String password) {
        Account account = new Account();
        account.setId(username);
        account.setPassword(md5(password));
        Gson gson = new Gson();
        String result = null;
        try {
            URL url = new URL(baseUrlREST + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(gson.toJson(account));
            wr.flush();
            wr.close();
            conn.getResponseCode();
            result = getResponse(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getFilteredRestaurants(String acction, String where) {
        String result = "[]";
        try {
            URL url = new URL(baseUrlREST + acction);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(POST_METHOD);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(where);
            wr.flush();
            wr.close();
            conn.getResponseCode();
            result = getResponse(conn);
            Log.d(TAG, result);
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    // Get 1
    public static String soap(String methodName, String property) {
        SoapObject request = new SoapObject(Path.SOAP_NAMESPACE, methodName);
        request.addProperty("restaurantId", property);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(Path.baseUrlSOAP);
        try {
            httpTransport.call(methodName, envelope);
            SoapObject so = (SoapObject) envelope.getResponse();
            return soapObjectToJsonObject(so).toString();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return "[]";
    }

    // Get *
    public static String soap(String methodName) {
        SoapObject request = new SoapObject(Path.SOAP_NAMESPACE, methodName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(Path.baseUrlSOAP);
        try {
            httpTransport.call(methodName, envelope);
            Vector vector = (Vector) envelope.getResponse();
            Iterator iterator = vector.iterator();
            JSONArray jsonArray = new JSONArray();
            while (iterator.hasNext()) {
                jsonArray.put(soapObjectToJsonObject((SoapObject) iterator.next()));
            }
            Log.d(TAG, jsonArray.toString());
            return jsonArray.toString();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update/Add 1
    public static boolean soap(String methodName, KvmSerializable object) {
        SoapObject request = new SoapObject(Path.SOAP_NAMESPACE, methodName);
        request.addProperty("restaurant", object);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(Path.baseUrlSOAP);
        try {
            httpTransport.call(methodName, envelope);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static JSONObject soapObjectToJsonObject(SoapObject soapObject) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < soapObject.getPropertyCount(); i++) {
            PropertyInfo info = soapObject.getPropertyInfo(i);
            try {
                jsonObject.put(info.name, info.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
