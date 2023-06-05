package com.appf1.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.appf1.R;

public class PortadaFragment extends Fragment {

    public PortadaFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_portada, container, false);

        // Obtener la referencia al ImageView del fondo
        ImageView imagenFondo = view.findViewById(R.id.image_view);

        // Establecer la imagen de fondo
        imagenFondo.setImageResource(R.drawable.portada);

        return view;
    }
}

