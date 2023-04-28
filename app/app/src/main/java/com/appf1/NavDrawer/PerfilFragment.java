package com.appf1.NavDrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.appf1.R;

public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView favPilotoTextView;
    private TextView favEquipoTextView;
    private String pilotoSeleccionado;
    private String equipoSeleccionado;

    public PerfilFragment() {
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
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ImageView profileImage = view.findViewById(R.id.profile_image);
        TextView usernameTextView = view.findViewById(R.id.username_textview);
        TextView emailTextView = view.findViewById(R.id.email_edittext);
        favPilotoTextView = view.findViewById(R.id.fav_piloto_textview);
        favEquipoTextView = view.findViewById(R.id.fav_equipo_textview);

        // Obtener la información del perfil de usuario
        String username = "Nombre de usuario";
        String email = "usuario@ejemplo.com";

        // Establecer los valores de los componentes según la información del perfil de usuario
        usernameTextView.setText(username);
        emailTextView.setText(email);
        // establecer la imagen de perfil del usuario en el ImageView
        profileImage.setImageResource(R.drawable.logo);
        // Agregar OnClickListener para el botón de seleccionar piloto favorito
        Button selectFavoritePilotButton = view.findViewById(R.id.select_favorite_pilot_button);
        selectFavoritePilotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir una nueva actividad o fragmento para seleccionar el piloto favorito
                /*Intent intent = new Intent(getActivity(), SelectFavoritePilotActivity.class);
                startActivity(intent);*/
                Toast.makeText(getActivity(), "Añadir piloto", Toast.LENGTH_LONG).show();
            }
        });

        // Agregar OnClickListener para el botón de seleccionar equipo favorito
        Button selectFavoriteTeamButton = view.findViewById(R.id.select_favorite_team_button);
        selectFavoriteTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir una nueva actividad o fragmento para seleccionar el equipo favorito
                /*Intent intent = new Intent(getActivity(), SelectFavoriteTeamActivity.class);
                startActivity(intent);*/
                Toast.makeText(getActivity(), "Añadir equipo", Toast.LENGTH_LONG).show();
            }
        });
        // Función para guardar un valor en las SharedPreferences
        /*private void saveStringToSharedPreferences() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString();
            editor.apply();
        }*/

        // Función para obtener un valor de las SharedPreferences
        /*private String getStringFromSharedPreferences() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            return sharedPreferences.getString();
        }*/
        // Actualizar los TextView con los nombres de los equipos y pilotos seleccionados por el usuario
        actualizarPreferencias();


        return view;
    }
    // Método para actualizar los TextView con los nombres de los equipos y pilotos seleccionados por el usuario
    private void actualizarPreferencias() {
        if (pilotoSeleccionado != null) {
            favPilotoTextView.setText(pilotoSeleccionado);
        } else {
            favPilotoTextView.setText("Piloto favorito no seleccionado");
        }

        if (equipoSeleccionado != null) {
            favEquipoTextView.setText(equipoSeleccionado);
        } else {
            favEquipoTextView.setText("Equipo favorito no seleccionado");
        }
    }
}
