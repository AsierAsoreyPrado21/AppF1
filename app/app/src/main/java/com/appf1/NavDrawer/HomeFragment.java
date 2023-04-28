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

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VideoView videoView;
    private TextView videoTitle;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
