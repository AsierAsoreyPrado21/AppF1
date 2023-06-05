package com.appf1.NavDrawer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.fragment.app.Fragment;
import com.appf1.R;

public class ResumenFragment extends Fragment {



    private VideoView videoView;
    private TextView videoTitle;

    public ResumenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        // Obtener la referencia al botón de pausa/reproducción
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button pauseResumeButton = view.findViewById(R.id.pauseResumeButton);
        videoView = view.findViewById(R.id.video_view);
        videoTitle = view.findViewById(R.id.video_title);
        videoTitle.setText("Resumen Gran Premio de Australia");

        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.resumen;
        Uri uri = Uri.parse(path);
        videoView.setVideoURI(uri);
        // Obtener la referencia al botón de reproducción
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button startButton = view.findViewById(R.id.play_button);
        // Agregar un listener al botón de reproducción
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ocultar el botón de reproducción
                startButton.setVisibility(View.GONE);
                // Iniciar la reproducción del video
                videoView.start();

            }
        });

        // Configurar el comportamiento del botón de pausa/reproducción
        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    // Si el video está reproduciéndose, pausarlo
                    videoView.pause();
                    pauseResumeButton.setText("Reproducir");
                } else {
                    // Si el video está pausado, reanudarlo
                    videoView.start();
                    pauseResumeButton.setText("Pausar");
                }
            }
        });
        // Agregar un listener de finalización del video
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getActivity(), "El video ha terminado", Toast.LENGTH_SHORT).show();
            }
        });
        Button replayButton = view.findViewById(R.id.replayButton);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reiniciar la reproducción del video
                videoView.seekTo(0);
                videoView.start();
            }
        });
        return view;
    }

}
