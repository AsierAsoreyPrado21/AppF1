package com.appf1.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appf1.R;
import com.appf1.client.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

public class Restaurar_contrasena extends AppCompatActivity {
    private Context context=this;
    private Button buttonSend;
    private EditText editTextEmail;
    private RequestQueue requestQueue;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurar);
        //Elementos de nuestro xml
        editTextEmail=findViewById(R.id.email);
        buttonSend=findViewById(R.id.send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRestoreEmail();
            }
        });requestQueue= Volley.newRequestQueue(this);

    }

    private void sendRestoreEmail() {
        JSONObject requestBody=new JSONObject();
        try{
            requestBody.put("email",editTextEmail.getText().toString());
        }catch (JSONException e){
            throw new RuntimeException(e);
        }
        Response.Listener<JSONObject> listener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(context, "Revisa tu correo electrónico", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("VALID_EMAIL", editTextEmail.getText().toString());
                        editor.commit();
                        finish();
                    }
                };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    Toast.makeText(context, "Failed request",Toast.LENGTH_LONG).show();
                }
                else {
                    int serverCode = error.networkResponse.statusCode;
                    Toast.makeText(context, "Response status: "+serverCode,Toast.LENGTH_LONG).show();
                }
            }
        };

        JsonObjectRequest request = RestClient.getInstance(context).sendPostRestorePassword("/restore", requestBody, listener, errorListener);

        this.requestQueue.add(request);
    }
}
