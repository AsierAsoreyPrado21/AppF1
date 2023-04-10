package com.appf1.client;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RestClient {

    private String BASE_REAL_URL="https://ergast.com/api/f1";
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
    // Posteriores peticiones
}
