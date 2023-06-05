package com.appf1.NavDrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appf1.IComunicarFragment;
import com.appf1.R;
import com.appf1.adaptadores.AdapterEquipo;
import com.appf1.entidades.Equipo;

import java.util.ArrayList;

public class EquiposFragment extends Fragment {

    public EquiposFragment() {
        // Required empty public constructor
    }

    //variables
    AdapterEquipo adapterEquipo;
    RecyclerView recyclerViewEquipo;
    ArrayList<Equipo>listaEquipos;
    //comunicar fragments
    Activity activity;
    IComunicarFragment interfaceComunicarFragment;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equipos,container,false);

        recyclerViewEquipo= view.findViewById(R.id.recycler_viewEquipo);
        listaEquipos=new ArrayList<>();
        //cargar lista
        cargarLista();
        //mostrar datos
        mostrarDatos();
       return view;
    }

    private void mostrarDatos() {
        recyclerViewEquipo.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterEquipo= new AdapterEquipo(getContext(),listaEquipos);
        recyclerViewEquipo.setAdapter(adapterEquipo);

        adapterEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= listaEquipos.get(recyclerViewEquipo.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(),"Selecciono: "+nombre,Toast.LENGTH_LONG).show();
                interfaceComunicarFragment.enviarEquipo(listaEquipos.get(recyclerViewEquipo.getChildAdapterPosition(view)));
            }
        });
    }

    private void cargarLista() {
        listaEquipos.add(new Equipo("Red Bull",R.drawable.redbull,"Austríaca",5));
        listaEquipos.add(new Equipo("Aston Martin",R.drawable.aston_martin,"Británica",0));
        listaEquipos.add(new Equipo("Mercedes",R.drawable.mercedes,"Alemana",8));
        listaEquipos.add(new Equipo("Ferrari",R.drawable.ferrari,"Italiana",16));
        listaEquipos.add(new Equipo("Mclaren",R.drawable.mclaren,"Británica",8));
        listaEquipos.add(new Equipo("Alpine",R.drawable.alpine,"Francesa",2));
        listaEquipos.add(new Equipo("Hass",R.drawable.hass,"Estadounidense",0));
        listaEquipos.add(new Equipo("Alfa Romeo",R.drawable.alfaromeo," Suiza",0));
        listaEquipos.add(new Equipo("Alphatauri",R.drawable.alphatauri,"Italiana",0));
        listaEquipos.add(new Equipo("Williams",R.drawable.williams,"Británica",9));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity=(Activity) context;
            interfaceComunicarFragment=(IComunicarFragment) this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
