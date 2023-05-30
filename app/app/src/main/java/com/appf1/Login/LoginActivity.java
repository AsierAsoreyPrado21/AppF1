package com.appf1.Login;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appf1.InitActivity.InitActivity;
import com.appf1.NavDrawer.NavDrawerF1;
import com.appf1.R;
import com.appf1.client.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    //Atributos
    private Context context=this;
    private EditText user;
    private EditText password;
    private Button login;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Elementos de nuestro Xml
        user=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        //Proceso al clicar en el botton de Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRequest();
            }
        });requestQueue= Volley.newRequestQueue(this);
    }
    //Peticion
    private void sendPostRequest(){
        JSONObject requestBody = new JSONObject();
        try{
            //Validacion de atributos del usuario
            requestBody.put("email",user.getText().toString());
            requestBody.put("password",password.getText().toString());
        }catch (JSONException e){
            throw new RuntimeException(e);
        }


        Response.Listener<JSONObject> listener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String receivedToken;
                        String email;
                        try {
                            email = response.getString("email");
                            receivedToken = response.getString("token");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("VALID_TOKEN", receivedToken);
                        editor.putString("VALID_EMAIL",email);
                        editor.commit();
                        Toast.makeText(context, "Bienvenido de nuevo a F1Channel",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, NavDrawerF1.class);
                        startActivity(intent);

                    }
                };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    Toast.makeText(context, "Error al conectar con el servidor",Toast.LENGTH_LONG).show();
                }
                else {
                    int serverCode = error.networkResponse.statusCode;
                    Toast.makeText(context, "Estado de respuesta: "+serverCode,Toast.LENGTH_LONG).show();
                }
            }
        };


        JsonObjectRequest request = RestClient.getInstance(context).sendPostLogin("/sessions", requestBody, listener, errorListener);

        this.requestQueue.add(request);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, InitActivity.class);
        startActivity(intent);
        finish();
    }
}
