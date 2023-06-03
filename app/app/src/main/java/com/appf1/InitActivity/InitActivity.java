package com.appf1.InitActivity;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.appf1.Login.LoginActivity;
import com.appf1.R;
import com.appf1.register.RegisterActivity;

public class InitActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button register;
    private VideoView videoInit;
    private MediaPlayer gmediaPlayer;
    int videoPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);//xml de nuestra actividad inicial
        login=findViewById(R.id.login);// boton login de nuestro xml
        register=findViewById(R.id.register); // botom  register de nuestro xml
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        videoInit=findViewById(R.id.videoInit);
        Uri uri=Uri.parse ("android.resource://"+getPackageName()+"/"+R.raw.intro); //lo vinculamos e la carpeta donde se encuentra el video
        videoInit.setVideoURI(uri);
        videoInit.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                gmediaPlayer=mediaPlayer;
                gmediaPlayer.setLooping(true);
                if(videoPosition!=0){
                    gmediaPlayer.seekTo(videoPosition);
                    gmediaPlayer.start();
                }
            }
        });

    }
    //Metodos para el funcionamiento del video de fondo
    @Override
    protected void onPause() {
        super.onPause();
        videoPosition=gmediaPlayer.getCurrentPosition();
        videoInit.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        videoInit.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gmediaPlayer.release();
        gmediaPlayer=null;

    }
    //Metodo que nos permite elegir que salto de actividad damos
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                // se viajara al login de la app
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register:
                // se viajara al registro de la app
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
