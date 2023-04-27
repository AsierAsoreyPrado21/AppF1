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

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private CalendarView calendarView;
    private ArrayList<Evento> eventos = new ArrayList<Evento>();

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
                evento1.set(2023, 2, 5);
                eventos.add(new Evento(evento1, "Gran Premio de Baréin"));

                Calendar evento2 = Calendar.getInstance();
                evento2.set(2023, 2, 19);
                eventos.add(new Evento(evento2, "Gran Premio de Arabia Saudita"));

                Calendar evento3 = Calendar.getInstance();
                evento3.set(2023, 3, 2);
                eventos.add(new Evento(evento3, "Gran Premio de Australia"));

                Calendar evento4 = Calendar.getInstance();
                evento4.set(2023, 3, 30);
                eventos.add(new Evento(evento4, "Gran Premio de Azerbaiyán"));

                Calendar evento5 = Calendar.getInstance();
                evento5.set(2023, 4, 7);
                eventos.add(new Evento(evento5, "Gran Premio de Miami"));

                Calendar evento6 = Calendar.getInstance();
                evento6.set(2023, 4, 21);
                eventos.add(new Evento(evento6, "Gran Premio de Emilia-Romaña"));

                Calendar evento7 = Calendar.getInstance();
                evento7.set(2023, 4, 28);
                eventos.add(new Evento(evento7, "Gran Premio de Mónaco"));

                Calendar evento8 = Calendar.getInstance();
                evento8.set(2023, 5, 4);
                eventos.add(new Evento(evento8, "Gran Premio de España"));

                Calendar evento9 = Calendar.getInstance();
                evento9.set(2023, 5, 18);
                eventos.add(new Evento(evento9, "Gran Premio de Canadá"));

                Calendar evento10 = Calendar.getInstance();
                evento10.set(2023, 6, 2);
                eventos.add(new Evento(evento10, "Gran Premio de Austria"));

                Calendar evento11 = Calendar.getInstance();
                evento11.set(2023, 6, 9);
                eventos.add(new Evento(evento11, "Gran Premio de Gran Bretaña"));

                Calendar evento12 = Calendar.getInstance();
                evento12.set(2023, 6, 23);
                eventos.add(new Evento(evento12, "Gran Premio de Hungría"));

                Calendar evento13 = Calendar.getInstance();
                evento13.set(2023, 6, 30);
                eventos.add(new Evento(evento13, "Gran Premio de Bélgica"));

                Calendar evento14 = Calendar.getInstance();
                evento14.set(2023, 7, 27);
                eventos.add(new Evento(evento14, "Gran Premio de Países Bajos"));

                Calendar evento15 = Calendar.getInstance();
                evento15.set(2023, 8, 3);
                eventos.add(new Evento(evento15, "Gran Premio de Italia"));

                Calendar evento16 = Calendar.getInstance();
                evento16.set(2023, 8, 17);
                eventos.add(new Evento(evento16, "Gran Premio de Singapur"));

                Calendar evento17 = Calendar.getInstance();
                evento17.set(2023, 8, 24);
                eventos.add(new Evento(evento17, "Gran Premio de Japón"));

                Calendar evento18 = Calendar.getInstance();
                evento18.set(2023, 9, 8); //Catar
                eventos.add(new Evento(evento18, "Gran Premio de Catar"));

                Calendar evento19 = Calendar.getInstance();
                evento19.set(2023, 9, 22);
                eventos.add(new Evento(evento19, "Gran Premio de EEUU"));

                Calendar evento20 = Calendar.getInstance();
                evento20.set(2023, 9, 29);
                eventos.add(new Evento(evento20, "Gran Premio de México"));

                Calendar evento21 = Calendar.getInstance();
                evento21.set(2023, 10, 5);
                eventos.add(new Evento(evento21, "Gran Premio de Brasil"));

                Calendar evento22 = Calendar.getInstance();
                evento22.set(2023, 10, 18);
                eventos.add(new Evento(evento22, "Gran Premio de Las Vegas"));

                Calendar evento23 = Calendar.getInstance();
                evento23.set(2023, 10, 26);
                eventos.add(new Evento(evento23, "Gran Premio deAbu Dabi"));
                // Verificar si la fecha seleccionada está en la lista de eventos
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Evento eventoSeleccionado = null;
                for (Evento evento : eventos) {
                    if (evento.getFecha().get(Calendar.YEAR) == year
                            && evento.getFecha().get(Calendar.MONTH) == month
                            && evento.getFecha().get(Calendar.DAY_OF_MONTH) == dayOfMonth) {
                        eventoSeleccionado = evento;
                        break;
                    }
                }
                if (eventoSeleccionado != null) {
                    // Mostrar nombre del evento correspondiente
                    Toast.makeText(getActivity(), "Hay una carrera programada para el " + selectedDate + ": " + eventoSeleccionado.getNombre(), Toast.LENGTH_LONG).show();
                } else {
                    // Mostrar mensaje de fecha seleccionada
                    Toast.makeText(getActivity(), "No hay ninguna carrera programada para el " + selectedDate, Toast.LENGTH_LONG).show();
                }
            }

        });

        return view;
    }
    //clase evento
    public class Evento {
        private Calendar fecha;
        private String nombre;

        public Evento(Calendar fecha, String nombre) {
            this.fecha = fecha;
            this.nombre = nombre;
        }

        public Calendar getFecha() {
            return fecha;
        }

        public void setFecha(Calendar fecha) {
            this.fecha = fecha;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }
}

