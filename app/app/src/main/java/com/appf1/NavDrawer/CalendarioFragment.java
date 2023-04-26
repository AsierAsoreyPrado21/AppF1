package com.appf1.NavDrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appf1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class CalendarioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private CalendarView calendarView;
    private ArrayList<Long> eventos = new ArrayList<Long>();

    public CalendarioFragment() {
        // Required empty public constructor
    }

    public static CalendarioFragment newInstance(String param1, String param2) {
        CalendarioFragment fragment = new CalendarioFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        calendarView = view.findViewById(R.id.calendarView);

        // Establecer listener para cuando se selecciona una fecha
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                // Crear objeto Calendar con la fecha seleccionada
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                // Agregar fechas de eventos a la lista de eventos
                Calendar evento1 = Calendar.getInstance();
                evento1.set(2023, 2, 5); // Baréin
                eventos.add(evento1.getTimeInMillis());

                Calendar evento2 = Calendar.getInstance();
                evento2.set(2023, 2, 19); // Arabia Saudita
                eventos.add(evento2.getTimeInMillis());

                Calendar evento3 = Calendar.getInstance();
                evento3.set(2023, 3, 2); // Australia
                eventos.add(evento3.getTimeInMillis());

                Calendar evento4 = Calendar.getInstance();
                evento4.set(2023, 3, 30); //Azerbaiyán
                eventos.add(evento4.getTimeInMillis());

                Calendar evento5 = Calendar.getInstance();
                evento5.set(2023, 4, 7); //Miami
                eventos.add(evento5.getTimeInMillis());

                Calendar evento6 = Calendar.getInstance();
                evento6.set(2023, 4, 21); //Emilia-Romaña
                eventos.add(evento6.getTimeInMillis());

                Calendar evento7 = Calendar.getInstance();
                evento7.set(2023, 4, 28); //Monaco
                eventos.add(evento7.getTimeInMillis());

                Calendar evento8 = Calendar.getInstance();
                evento8.set(2023, 5, 4); //Spain
                eventos.add(evento8.getTimeInMillis());

                Calendar evento9 = Calendar.getInstance();
                evento9.set(2023, 5, 18); //Canada
                eventos.add(evento9.getTimeInMillis());

                Calendar evento10 = Calendar.getInstance();
                evento10.set(2023, 6, 2); //Austria
                eventos.add(evento10.getTimeInMillis());

                Calendar evento11 = Calendar.getInstance();
                evento11.set(2023, 6, 9); //Gran Bretaña
                eventos.add(evento11.getTimeInMillis());

                Calendar evento12 = Calendar.getInstance();
                evento12.set(2023, 6, 23); //Hungria
                eventos.add(evento12.getTimeInMillis());

                Calendar evento13 = Calendar.getInstance();
                evento13.set(2023, 6, 30); //Belgica
                eventos.add(evento13.getTimeInMillis());

                Calendar evento14 = Calendar.getInstance();
                evento14.set(2023, 7, 27); //Paises Bajos
                eventos.add(evento14.getTimeInMillis());

                Calendar evento15 = Calendar.getInstance();
                evento15.set(2023, 8, 3); //Italia
                eventos.add(evento15.getTimeInMillis());

                Calendar evento16 = Calendar.getInstance();
                evento16.set(2023, 8, 17); //Singapur
                eventos.add(evento16.getTimeInMillis());

                Calendar evento17 = Calendar.getInstance();
                evento17.set(2023, 8, 24); //Japon
                eventos.add(evento17.getTimeInMillis());

                Calendar evento18 = Calendar.getInstance();
                evento18.set(2023, 9, 8); //Catar
                eventos.add(evento18.getTimeInMillis());

                Calendar evento19 = Calendar.getInstance();
                evento19.set(2023, 9, 22); //EEUU
                eventos.add(evento19.getTimeInMillis());

                Calendar evento20 = Calendar.getInstance();
                evento20.set(2023, 9, 29); //Mexico
                eventos.add(evento20.getTimeInMillis());

                Calendar evento21 = Calendar.getInstance();
                evento21.set(2023, 10, 5); //Brasil
                eventos.add(evento21.getTimeInMillis());

                Calendar evento22 = Calendar.getInstance();
                evento22.set(2023, 10, 18); //Las vegas
                eventos.add(evento22.getTimeInMillis());

                Calendar evento23 = Calendar.getInstance();
                evento23.set(2023, 10, 26); //Abu Dabi
                eventos.add(evento23.getTimeInMillis());


                // Verificar si la fecha seleccionada está en la lista de eventos
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                if (eventos.contains(calendar.getTimeInMillis())) {
                    // Mostrar mensaje del evento correspondiente
                    Toast.makeText(getActivity(), "Hay una carrera programada para el:" +selectedDate, Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar mensaje de fecha seleccionada
                    Toast.makeText(getActivity(), "No hay ninguna carrera programada para el: " + selectedDate, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}

