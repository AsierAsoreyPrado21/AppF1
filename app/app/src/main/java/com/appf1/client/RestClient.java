package com.appf1.client;

import android.content.Context;
import androidx.annotation.Nullable;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RestClient {
    private String Base="http://10.0.2.2:8000";
    private static Context context;
    private static RequestQueue queue;

    private RestClient(Context context){
        this.context=context;
    }
    private static RestClient singleton= null;

    // este código crea una instancia única de "RestClient" y una cola de solicitudes de Volley
    // que se utiliza para enviar solicitudes y recibir respuestas de una API de red
    public static RestClient getInstance(Context context){
        if (singleton==null){
            singleton=new RestClient(context);
            queue= Volley.newRequestQueue(context);
        }
        return singleton;
    }
    // Login
    public JsonObjectRequest sendPostLogin(String endpoint, JSONObject requestBody, Response.Listener<JSONObject> listener,
                                           @Nullable Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                this.Base + endpoint,
                requestBody,
                listener, errorListener);
        return request;
    }

    // Posteriores peticiones
    public JsonObjectRequest RequestRegister(String endpoint, JSONObject jsonObject, Response.Listener response,
                                             Response.ErrorListener errorListener){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                this.Base + endpoint,
                jsonObject,
                response,
                errorListener
        );
        return request;
    }
}
