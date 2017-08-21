package com.mobile.waterapp.http;

/**
 * Created by hito on 11-02-2015.
 */

import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestService {


    public static String readJSONFeed(String URL) {

        AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
        HttpGet request = new HttpGet(URL);
        HttpResponse response;

        try {
            response = client.execute(request);
           // Log.d("IN REST SERVICE", EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());

        }catch (Exception e ){
            e.printStackTrace();
            return null;

        }finally{
            client.close();
        }

 }

    public static String callPOSTURL(String URL, String [] paramValues) throws UnsupportedEncodingException, JSONException {

       AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");

        HttpPost request = new HttpPost(URL);

        JSONObject jsonObj = new JSONObject();

         for (int i=0; i < paramValues.length ; i++){
            String values = "element" + Integer.toString(i);
            jsonObj.put(values, paramValues[i]);
        }

        StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
        entity.setContentType("application/json");
        request.setEntity(entity);


        HttpResponse response;

        try {
            response = client.execute(request);

            Log.d("POST_URL", "Request Executed");
            // Log.d("IN REST SERVICE", EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());

        }catch (Exception e ){
            e.printStackTrace();
            return null;

        }finally{
            client.close();

        }
    }



}
