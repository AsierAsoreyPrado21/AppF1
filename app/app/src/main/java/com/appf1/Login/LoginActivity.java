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
    private TextView restorePassword;
    private RequestQueue requestQueue;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Elementos de nuestro Xml
        user=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        restorePassword=findViewById(R.id.restorePassword);
        //Proceso al clicar en el textView de restaurar contraseña
        restorePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "¿Olvidaste contraseña?", LENGTH_SHORT).show();
                //nos mandara a una actividad nueva para restaurar la contraseña
                Intent intent = new Intent(context,Restaurar_contrasena.class);
                context.startActivity(intent);

            }
        });
        // Obtenemos las SharedPreferences
        sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
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
    //Registro hecho con las sharepPreferences
        String contraseña = password.getText().toString();
        String name = user.getText().toString();

        // Verificamos que se hayan ingresado todos los campos
        if (TextUtils.isEmpty(contraseña) || TextUtils.isEmpty(name)) {
            Toast.makeText(LoginActivity.this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtenemos los valores guardados en las SharedPreferences
        String savedPassword = sharedPreferences.getString("password", "");
        String savedName = sharedPreferences.getString("nombre", "");

        // Verificamos si los datos ingresados por el usuario son correctos
        if (contraseña.equals(savedPassword) && name.equals(savedName)) {
            // Si los datos son correctos, iniciamos sesión y guardamos el estado en las SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("is_logged_in", true);
            editor.apply();

            Toast.makeText(LoginActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();

            // Aquí podrías lanzar la actividad principal de tu aplicación
        } else {
            // Si los datos son incorrectos, mostramos un mensaje de error
            Toast.makeText(LoginActivity.this, "nombre o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    /*    JSONObject requestBody = new JSONObject();
        try{
            //Validacion de atributos del usuario
            if(user.getText().toString().contains("@")){
                requestBody.put("nombre",null);
                requestBody.put("email",user.getText().toString());
            }else{
                requestBody.put("nombre",user.getText().toString());
                requestBody.put("email",null);
            }
            requestBody.put("password",user.getText().toString());
        }catch (JSONException e){
            throw new RuntimeException(e);
        }


        Response.Listener<JSONObject> listener =
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String receivedToken;
                        String nombre;
                        try {
                            nombre = response.getString("nombre");
                            receivedToken = response.getString("token");
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(context, "Token", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("VALID_TOKEN", receivedToken);
                        editor.putString("VALID_NOMBRE",nombre);
                        editor.commit();
                        //Intent intent = new Intent(context, NavDrawwerF1.class);
                        //startActivity(intent);

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


        JsonObjectRequest request = RestClient.getInstance(context).sendPostLogin("/v1/sessions", requestBody, listener, errorListener);

        this.requestQueue.add(request);*/
    }
}
