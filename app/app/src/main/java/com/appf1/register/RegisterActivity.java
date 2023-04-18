package com.appf1.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appf1.InitActivity.InitActivity;
import com.appf1.R;
import com.appf1.client.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText username;
    private Button register;
    private Context context=this;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        CheckBox checkBox= findViewById(R.id.checkbox);
        TextView checkBoxTv= findViewById(R.id.checkboxsTV);
        register=findViewById(R.id.register);

        requestQueue= Volley.newRequestQueue(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            //Al clicar en registro el metodo comprobara que todos los campos han sido cubiertos correctamente
            public void onClick(View v) {
                boolean emailGood = true, passwordGood = true, usernameGood = true, containsArroba = false;
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Username = username.getText().toString();

                if(Email.isEmpty()){
                    email.setError("Debes incluir un correo electrónico");
                    emailGood = false;
                }
                if(Password.isEmpty()){
                    password.setError("Debes incluir una contraseña");
                    passwordGood = false;
                }else{
                    for(int i=0;i<email.length();i++){
                        if(Character.toLowerCase(Email.charAt(i)) == '@')
                            containsArroba = true;
                    }
                }
                if(Username.isEmpty()){
                    username.setError("Debes incluir un nombre de usuario");
                    usernameGood = false;
                }
                if(!containsArroba && emailGood){
                    email.setError("Este email no existe");
                }
                if(!checkBox.isChecked()){
                    checkBox.setError("Debes aceptar la política de datos");
                }
                if(emailGood && passwordGood && usernameGood && containsArroba && checkBox.isChecked()){
                    registerUser();
                }
            }
        });

    }
    //metodo que creará el registro
    private void registerUser() {
        /*
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", String.valueOf(username));
        editor.putString("email", String.valueOf(email));
        editor.putString("password", String.valueOf(password));
        editor.apply();

        // Mostrar un mensaje de confirmación
        Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, InitActivity.class);
        startActivity(intent);
*/
        
        JSONObject requestBody = new JSONObject();
        try{
            requestBody.put("name", username.getText().toString());
            requestBody.put("email", email.getText().toString());
            requestBody.put("password", password.getText().toString());
        }catch (JSONException e){
            throw new RuntimeException(e);
        }

        Response.Listener listener = new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, InitActivity.class);
                startActivity(intent);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                if(error.networkResponse == null){
                    Toast.makeText(context, "Error al conectar con el servidor", Toast.LENGTH_LONG).show();
                }else{
                    int serverCode = error.networkResponse.statusCode;
                    Toast.makeText(context, "Estado de respuesta: "+ serverCode, Toast.LENGTH_LONG).show();
                }
            }
        };

        JsonObjectRequest request = RestClient.getInstance(context).RequestRegister("/users", requestBody, listener, errorListener);
        this.requestQueue.add(request);

    }
}
